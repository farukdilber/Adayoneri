package com.avivasa.rpa.model;

public class KeepnetModel {
	private String firstName;
    private String lastName;
    private String eMail;
    private String status;
    private String lastSeen;
    private String diagnosticTool;
    private String device;
    private String version;
    
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLastSeen() {
		return lastSeen;
	}
	public void setLastSeen(String lastSeen) {
		this.lastSeen = lastSeen;
	}
	public String getDiagnosticTool() {
		return diagnosticTool;
	}
	public void setDiagnosticTool(String diagnosticTool) {
		this.diagnosticTool = diagnosticTool;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
