package com.avivasa.rpa.model;

public class CiscoModel {
	
	private String connector_Guid;
	private String hostName;
	private String operating_System;
	private String connector_Version;
	private String group;
	private String installDate;
	private String lastSeen;
	private String internal_IP;
	private String external_IP;
	private String adress_MAC;
	private String ios_Serial_Number;
	private String connector_Antivirus;
	private String last_Definiton_Update;
	private String last_Definiton_Update_Failure;
	private String process_Hardware_Identifier;
	
	public CiscoModel() {}

	public CiscoModel(String connector_Guid, String hostName, String operating_System, String connector_Version,
			String group, String installDate, String lastSeen, String internal_IP, String external_IP,
			String adress_MAC, String ios_Serial_Number, String connector_Antivirus, String last_Definiton_Update,
			String last_Definiton_Update_Failure, String process_Hardware_Identifier) {
		
		super();
		this.connector_Guid = connector_Guid;
		this.hostName = hostName;
		this.operating_System = operating_System;
		this.connector_Version = connector_Version;
		this.group = group;
		this.installDate = installDate;
		this.lastSeen = lastSeen;
		this.internal_IP = internal_IP;
		this.external_IP = external_IP;
		this.adress_MAC = adress_MAC;
		this.ios_Serial_Number = ios_Serial_Number;
		this.connector_Antivirus = connector_Antivirus;
		this.last_Definiton_Update = last_Definiton_Update;
		this.last_Definiton_Update_Failure = last_Definiton_Update_Failure;
		this.process_Hardware_Identifier = process_Hardware_Identifier;
	}

	public String getConnector_Guid() {
		return connector_Guid;
	}

	public void setConnector_Guid(String connector_Guid) {
		this.connector_Guid = connector_Guid;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getOperating_System() {
		return operating_System;
	}

	public void setOperating_System(String operating_System) {
		this.operating_System = operating_System;
	}

	public String getConnector_Version() {
		return connector_Version;
	}

	public void setConnector_Version(String connector_Version) {
		this.connector_Version = connector_Version;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getInstallDate() {
		return installDate;
	}

	public void setInstallDate(String installDate) {
		this.installDate = installDate;
	}

	public String getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}

	public String getInternal_IP() {
		return internal_IP;
	}

	public void setInternal_IP(String internal_IP) {
		this.internal_IP = internal_IP;
	}

	public String getExternal_IP() {
		return external_IP;
	}

	public void setExternal_IP(String external_IP) {
		this.external_IP = external_IP;
	}

	public String getAdress_MAC() {
		return adress_MAC;
	}

	public void setAdress_MAC(String adress_MAC) {
		this.adress_MAC = adress_MAC;
	}

	public String getIos_Serial_Number() {
		return ios_Serial_Number;
	}

	public void setIos_Serial_Number(String ios_Serial_Number) {
		this.ios_Serial_Number = ios_Serial_Number;
	}

	public String getConnector_Antivirus() {
		return connector_Antivirus;
	}

	public void setConnector_Antivirus(String connector_Antivirus) {
		this.connector_Antivirus = connector_Antivirus;
	}

	public String getLast_Definiton_Update() {
		return last_Definiton_Update;
	}

	public void setLast_Definiton_Update(String last_Definiton_Update) {
		this.last_Definiton_Update = last_Definiton_Update;
	}

	public String getLast_Definiton_Update_Failure() {
		return last_Definiton_Update_Failure;
	}

	public void setLast_Definiton_Update_Failure(String last_Definiton_Update_Failure) {
		this.last_Definiton_Update_Failure = last_Definiton_Update_Failure;
	}

	public String getProcess_Hardware_Identifier() {
		return process_Hardware_Identifier;
	}

	public void setProcess_Hardware_Identifier(String process_Hardware_Identifier) {
		this.process_Hardware_Identifier = process_Hardware_Identifier;
	}

	
	

}
