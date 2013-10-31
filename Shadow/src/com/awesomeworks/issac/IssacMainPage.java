package com.awesomeworks.issac;

import org.alljoyn.bus.BusAttachment;
import org.alljoyn.bus.Status;

import com.awesomeworks.issac.core.IssacMasterBusObject;


import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class IssacMainPage extends Activity {
	/* Load the native alljoyn_java library. */
	static {
		System.loadLibrary("alljoyn_java");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shadow_main_page);
	    masterBus = new IssacMasterBusObject();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shadow_main_page, menu);
		return true;
	}
	IssacMasterBusObject masterBus;
}
