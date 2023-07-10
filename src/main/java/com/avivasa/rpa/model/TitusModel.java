package com.avivasa.rpa.model;

public class TitusModel {
	
	private String computerName;
	private String date;
	private String configration;
	private String productVersion;
	
	public TitusModel() {}
	
	public TitusModel(String computerName, String date, String configration, String productVersion, String row_Num) {
		
		this.computerName = computerName;
		this.date = date;
		this.configration = configration;
		this.productVersion = productVersion;
	}

	public String getComputerName() {
		return computerName;
	}

	public void setComputerName(String computerName) {
		this.computerName = computerName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getConfigration() {
		return configration;
	}

	public void setConfigration(String configration) {
		this.configration = configration;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

}
