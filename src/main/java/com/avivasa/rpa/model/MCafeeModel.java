package com.avivasa.rpa.model;

public class MCafeeModel {
	
	private String lastCommunication;
	private String systemName;
	
	public MCafeeModel() {}
	
	public MCafeeModel(String lastCommunication, String systemName) {
		super();
		this.lastCommunication = lastCommunication;
		this.systemName = systemName;
	}
	public String getLastCommunication() {
		return lastCommunication;
	}
	public void setLastCommunication(String lastCommunication) {
		this.lastCommunication = lastCommunication;
	}
	public String getSystemName() {
		return systemName;
	}
	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}
	
	
	
}
