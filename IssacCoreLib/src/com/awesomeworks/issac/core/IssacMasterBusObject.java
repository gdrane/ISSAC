package com.awesomeworks.issac.core;

import org.alljoyn.bus.BusAttachment;
import org.alljoyn.bus.BusObject;
import org.alljoyn.bus.Mutable;
import org.alljoyn.bus.SessionOpts;
import org.alljoyn.bus.SessionPortListener;
import org.alljoyn.bus.Status;

import com.awesomeworks.issac.core.audio.IssacSpeech;

public class IssacMasterBusObject implements BusObject {

	public IssacMasterBusObject() {
	}

	public boolean InitializeObject() {
		Status status = mBus.registerBusObject((BusObject) this,
				"/com/awesomeworks/issac/master");
		if (Status.OK != status) {
			System.out.println("BusAttachment.registerBusObject() failed: "
					+ status);
			// System.exit(0);
			return false;
		}
		status = mBus.connect();
		if (Status.OK != status) {
			System.out.println("BusAttachment.connect() failed: " + status);
			// System.exit(0);
			return false;
		}
		Mutable.ShortValue contactPort = new Mutable.ShortValue(CONTACT_PORT);

		SessionOpts sessionOpts = new SessionOpts();
		sessionOpts.traffic = SessionOpts.TRAFFIC_MESSAGES;
		sessionOpts.isMultipoint = false;
		sessionOpts.proximity = SessionOpts.PROXIMITY_ANY;
		sessionOpts.transports = SessionOpts.TRANSPORT_ANY;

		status = mBus.bindSessionPort(contactPort, sessionOpts,
				new SessionPortListener() {
					@Override
					public boolean acceptSessionJoiner(short sessionPort,
							String joiner, SessionOpts sessionOpts) {
						if (sessionPort == CONTACT_PORT) {
							System.out.println("Someone came to the bus");
							IssacSpeech spchText = new IssacSpeech();
							spchText.textToSpeech("Hello Jaineel");
							return true;
						} else {
							return false;
						}
					}
				});

		int flags = 0; // no request name flags
		status = mBus.requestName("com.awesomeworks.issac.master", flags);
		if (status != Status.OK) {
			System.out.println("BusAttachment.requestName failed: " + status);
			// System.exit(0);
			return false;
		}

		/*
		 * Important: the well-known name advertised should be identical to the
		 * well-known name requested from the bus. Using a different name is a
		 * logic error.
		 */
		status = mBus.advertiseName("com.awesomeworks.issac.master",
				SessionOpts.TRANSPORT_ANY);
		if (status != Status.OK) {
			/*
			 * If we are unable to advertise the name, release the well-known
			 * name from the local bus.
			 */
			mBus.releaseName("com.awesomeworks.issac.master");
			// System.exit(0);
			return false;
		}

		if (status != Status.OK) {
			// System.exit(0);
			return false;
		}

		return true;
	}

	private BusAttachment mBus = new BusAttachment("ISSAC");
	private static final short CONTACT_PORT = 42;

}
