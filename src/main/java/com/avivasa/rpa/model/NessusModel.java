package com.avivasa.rpa.model;

public class NessusModel {
	
	private String agentName;
	private String status;
	private String adress_IP;
	private String platform;
	private String version;
	private String lastPluginUpdate;
	private String lastScanned;
	
	public NessusModel() {}

	public NessusModel(String agentName, String status, String adress_IP, String platform,
			String version, String lastPluginUpdate, String lastScanned) {
		
		this.agentName = agentName;
		this.status = status;
		this.adress_IP = adress_IP;
		this.platform = platform;
		this.version = version;
		this.lastPluginUpdate = lastPluginUpdate;
		this.lastScanned = lastScanned;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdress_IP() {
		return adress_IP;
	}

	public void setAdress_IP(String adress_IP) {
		this.adress_IP = adress_IP;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLastPluginUpdate() {
		return lastPluginUpdate;
	}

	public void setLastPluginUpdate(String lastPluginUpdate) {
		this.lastPluginUpdate = lastPluginUpdate;
	}

	public String getLastScanned() {
		return lastScanned;
	}

	public void setLastScanned(String lastScanned) {
		this.lastScanned = lastScanned;
	}
	
}
