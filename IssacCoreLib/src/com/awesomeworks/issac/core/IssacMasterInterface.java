package com.awesomeworks.issac.core;

import org.alljoyn.bus.BusException;
import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusMethod;

@BusInterface(name = "com.awesomeworks.issac.master")
public interface IssacMasterInterface {
	@BusMethod
	public String Search(String inStr) throws BusException;
}
