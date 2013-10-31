package com.awesomeworks.issac.desktop;

import com.awesomeworks.issac.core.IssacClientBusObject;
import com.awesomeworks.issac.core.audio.IssacSpeech;

public class IssacClientMain {
	/* Load the native alljoyn_java library. */
	static {
		System.loadLibrary("alljoyn_java");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IssacClientBusObject busObject = new IssacClientBusObject();
		System.out.println(busObject.InitializeObject());
		/*
		IssacSpeech spchText = new IssacSpeech();
		while(true){
			spchText.textToSpeech("Hello Jaineel");
			//time.sleep(1);
		}
		*/
		while(true);
	}

}
