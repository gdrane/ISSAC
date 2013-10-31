package com.awesomeworks.issac.desktop;

import com.awesomeworks.issac.core.IssacMasterBusObject;

public class IssacMasterMain {
	/* Load the native alljoyn_java library. */
	static {
		System.loadLibrary("alljoyn_java");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello Alljoyn This is Master");
		IssacMasterBusObject busObject = new IssacMasterBusObject();
		System.out.println(busObject.InitializeObject());
		while(true);
	}
	
}
