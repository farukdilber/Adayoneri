package com.avivasa.rpa.data;

import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
	private List<List<List<Object>>> allSheetData;

	private String excelFile;

	private List<String> sheetNames;

	public ExcelReader() {
		this.excelFile = "";
		this.sheetNames = new ArrayList<>();
		this.allSheetData = new ArrayList<>();
	}

	public ExcelReader(String excelFile) {
		this.excelFile = excelFile;
		this.sheetNames = new ArrayList<>();
		this.allSheetData = new ArrayList<>();
	}

	public List<String> getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(List<String> sheetNames) {
		this.sheetNames = sheetNames;
	}

	public List<List<List<Object>>> getAllSheetData() {
		return allSheetData;
	}

	public void setAllSheetData(List<List<List<Object>>> allSheetData) {
		this.allSheetData = allSheetData;
	}

	public String getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(String excelFile) {
		this.excelFile = excelFile;
	}

}
