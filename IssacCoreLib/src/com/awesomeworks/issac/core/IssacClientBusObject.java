package com.awesomeworks.issac.core;

import org.alljoyn.bus.BusAttachment;
import org.alljoyn.bus.BusListener;
import org.alljoyn.bus.BusObject;
import org.alljoyn.bus.Mutable;
import org.alljoyn.bus.SessionListener;
import org.alljoyn.bus.SessionOpts;
import org.alljoyn.bus.Status;

public class IssacClientBusObject implements BusObject {

	public IssacClientBusObject() {
	}

	public boolean InitializeObject() {
		mBus = new BusAttachment("ISSAC", BusAttachment.RemoteMessage.Receive);

		mBus.registerBusListener(new BusListener() {
			@Override
			public void foundAdvertisedName(String name, short transport,
					String namePrefix) {
				short contactPort = CONTACT_PORT;
				SessionOpts sessionOpts = new SessionOpts();
				Mutable.IntegerValue sessionId = new Mutable.IntegerValue();
				System.out.println("Found Advertised Name " + name);
				Status status = mBus.joinSession(name, contactPort,
						sessionId, sessionOpts, new SessionListener());
				if (status != Status.OK) {
					System.out.println(status);
				}
			}
		});

		Status status = mBus.connect();
		if (status != Status.OK) {
			// System.exit(0);
			return false;
		}

		status = mBus.findAdvertisedName("com.awesomeworks.issac.master");
		if (status != Status.OK) {
			// System.exit(0);
			return false;
		}
		return true;
	}

	private static final short CONTACT_PORT = 42;
	private BusAttachment mBus;
}
