package com.awesomeworks.issac.core.audio;

import javax.speech.*;
import java.util.*;
import javax.speech.synthesis.*;

public class IssacSpeech {

	public void textToSpeech(String speechText) {
		//String voiceName = voicename;
		try {
			SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general",
					Locale.US, null, null);
			Synthesizer synthesizer = Central.createSynthesizer(desc);
			synthesizer.allocate();
			synthesizer.resume();
			desc = (SynthesizerModeDesc) synthesizer.getEngineModeDesc();
			Voice[] voices = desc.getVoices();
			Voice voice = voices[0];
			for (int i = 0; i < voices.length; i++) {
				System.out.println(voices[i].getName());
			}
			synthesizer.getSynthesizerProperties().setVoice(voice);
			synthesizer.speakPlainText(speechText, null);
			synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
			synthesizer.deallocate();
		} catch (Exception e) {
			String message = " missing speech.properties in "
					+ System.getProperty("user.home") + "\n";
			System.out.println("" + e);
			System.out.println(message);
		}
	}
}
