package com.avivasa.rpa.model;

public class DLPAgentModel {

	private String status;
	private String machineName;
	private String userName;
	private String oS;
	private String platform;
	private String recentErrorMessages;
	private String endpointServer;
	private String adress_IP;
	private String version;
	private String connecttion;
	private String lastUpdateReceived;
	
	public DLPAgentModel() {}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMachineName() {
		return machineName;
	}

	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getoS() {
		return oS;
	}

	public void setoS(String oS) {
		this.oS = oS;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getRecentErrorMessages() {
		return recentErrorMessages;
	}

	public void setRecentErrorMessages(String recentErrorMessages) {
		this.recentErrorMessages = recentErrorMessages;
	}

	public String getEndpointServer() {
		return endpointServer;
	}

	public void setEndpointServer(String endpointServer) {
		this.endpointServer = endpointServer;
	}

	public String getAdress_IP() {
		return adress_IP;
	}

	public void setAdress_IP(String adress_IP) {
		this.adress_IP = adress_IP;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getConnecttion() {
		return connecttion;
	}

	public void setConnecttion(String connecttion) {
		this.connecttion = connecttion;
	}

	public String getLastUpdateReceived() {
		return lastUpdateReceived;
	}

	public void setLastUpdateReceived(String lastUpdateReceived) {
		this.lastUpdateReceived = lastUpdateReceived;
	}
	
}
