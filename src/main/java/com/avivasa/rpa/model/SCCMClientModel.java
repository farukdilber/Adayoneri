package com.avivasa.rpa.model;

public class SCCMClientModel {
	
	private String device;
	private String obsoloteClient;
	private String activeClient;
	private String configMgrClient;
	
	public SCCMClientModel() {}

	public SCCMClientModel(String device, String obsoloteClient, String activeClient, String configMgrClient) {
		
		this.device = device;
		this.obsoloteClient = obsoloteClient;
		this.activeClient = activeClient;
		this.configMgrClient = configMgrClient;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getObsoloteClient() {
		return obsoloteClient;
	}

	public void setObsoloteClient(String obsoloteClient) {
		this.obsoloteClient = obsoloteClient;
	}

	public String getActiveClient() {
		return activeClient;
	}

	public void setActiveClient(String activeClient) {
		this.activeClient = activeClient;
	}

	public String getConfigMgrClient() {
		return configMgrClient;
	}

	public void setConfigMgrClient(String configMgrClient) {
		this.configMgrClient = configMgrClient;
	}
		

}
