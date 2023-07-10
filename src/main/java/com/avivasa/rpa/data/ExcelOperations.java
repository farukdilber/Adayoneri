package com.avivasa.rpa.data;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.Charsets;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;

import com.avivasa.rpa.model.AdayBilgileri;
import com.avivasa.rpa.model.ComputersModel;
import com.avivasa.rpa.model.ExcelModel;
import com.avivasa.rpa.utiliy.EmailSend;
import com.avivasa.rpa.utiliy.UtilityMethods;
import com.avivasa.rpa.utiliy.log;

public class ExcelOperations 
{
	WebDriver driver;
	
	UtilityMethods utilityMethods = new UtilityMethods();
	static FileInputStream file;

	List<String[]> EkTeminatList = new ArrayList<String[]>();
	List<String[]> EkTeminatGelenDataList = new ArrayList<String[]>();
	
	List<String[]> VefatList = new ArrayList<String[]>();
	List<String[]> VefatGelenDataList = new ArrayList<String[]>();



	public boolean generateAdayOnerıyorumReportExcel(String reportFileName)
			throws InterruptedException, IOException {

		try {
			File file = new File(KPIAjanlariHesaplamalariData.filePath + reportFileName);
			if (!file.exists())
				file.createNewFile();

			Workbook workbook = new XSSFWorkbook();
			Sheet sheetContentReport = workbook.createSheet("AdayÖneriyorum");

			Row rowContentReportData = sheetContentReport.createRow(0);

			Cell cell0 = rowContentReportData.createCell(0);//basvuru ılan
			Cell cell1 = rowContentReportData.createCell(1);//adı soyadı
			Cell cell2 = rowContentReportData.createCell(2);// emaıl
			Cell cell3 = rowContentReportData.createCell(3);//sonuc
			Cell cell4 = rowContentReportData.createCell(4);//hatasebebi
			
			cell0.setCellValue("Basvuru İlan");
			cell1.setCellValue("Adi Soyadi");
			cell2.setCellValue("E-Mail");
			cell3.setCellValue("Sonuc");
			cell4.setCellValue("Hata Sebebi");

			for (int cell = 0; cell < 5; cell++) {
				sheetContentReport.autoSizeColumn(cell);
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(new File(KPIAjanlariHesaplamalariData.filePath + reportFileName));

			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			log.error("Hata!! Rapor excel dosyasi olusturulamadi: " + e.toString());
			//EmailSend.excelDosyasiOlusturmaHatasi();
			throw new SkipException("HATA: Rapor excel dosyasi olusturulamadi.");
		}
		return true;
	}

	public boolean deleteRow(String sheetName, String excelPath) throws IOException {

		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;

		try {

			FileInputStream file = new FileInputStream(new File(excelPath));
			workbook = new XSSFWorkbook(file);
			sheet = workbook.getSheet(sheetName);

			if (sheet == null) {
				return false;
			}
			int lastRowNum = sheet.getLastRowNum();

			for (int i = lastRowNum; i > 0; i--) {

				XSSFRow removingRow = sheet.getRow(i);
				if (removingRow != null) {
					sheet.removeRow(removingRow);
				}
			}
			
			file.close();
			FileOutputStream outFile = new FileOutputStream(new File(excelPath));
			workbook.write(outFile);
			outFile.close();

		}

		catch (Exception e) {
			throw e;
		} finally {
			if (workbook != null)
				workbook.close();
		}
		return false;
	}

	public Boolean controlFileAndSendMail(String filePath, String dataFileName) throws InterruptedException {

		boolean statusBrowser = false;

		if (!isFileExists(filePath, dataFileName)) {
			EmailSend.dosyaBulunamadi(filePath, dataFileName);
			log.info(filePath + dataFileName + " isimli dosya bulunamamistir.");
			statusBrowser = true;
		}

		return statusBrowser;
	}
	
	public boolean isColumnNameValid(Cell cell, String columnName) {
		DataFormatter fmt = new DataFormatter();
		String cellName = UtilityMethods.turkishCharConvert(fmt.formatCellValue(cell)).trim().toLowerCase().replace(" ", "");
		columnName = UtilityMethods.turkishCharConvert(columnName).trim().toLowerCase().replace(" ", "");
		if(columnName.contains(cellName))
			return true;
		else
			return false;
	}
	
	private static boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();
		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}
	
	public List<String[]> populateRaporList(String filePath, String filename)
			throws InterruptedException, IOException {
              
		try {
			file = new FileInputStream(new File(filePath + filename));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(0);
			DataFormatter fmt = new DataFormatter();

			Iterator<Row> rowIterator = sheet.iterator();
			
			for (int i = 0; i < 1; i++) {
				if (rowIterator.hasNext()) {
					Row rows = rowIterator.next();
					Iterator<Cell> cellIterator = rows.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 12) {
							break;
						}

						String checkColumnName = null;
						switch(cell.getColumnIndex()) {
							case 0:
								checkColumnName = "Basvuru İlan";
								break;
							case 1:
								checkColumnName = "Adi Soyadi";
								break;
							case 2:
								checkColumnName = "E-Mail";
								break;
							case 3:
								checkColumnName = "Dogum Tarihi";
								break;
							case 4:
								checkColumnName = "Eğitim Bilgileri";
								break;
							case 5:
								checkColumnName = "Yabanci Dil";
								break;
							case 6:
								checkColumnName = "Medeni Durum";
								break;
							case 7:
								checkColumnName = "Askerlik Durum";
								break;
							case 8:
								checkColumnName = "İkemetgah Adresi";
								break;
							case 9:
								checkColumnName = "Önyazı/Genel Bilgiler";
								break;
							case 10:
								checkColumnName = "Sonuc";
								break;
							case 11:
								checkColumnName = "Hata Sebebi";
								break;
						}
						
						if(!isColumnNameValid(cell, checkColumnName)) {
							log.error(cell.getColumnIndex() + " sutun basligi eslesmedi: " + fmt.formatCellValue(cell));
							throw new SkipException(cell.getColumnIndex() + " sutun basligi eslesmedi: " + fmt.formatCellValue(cell));
						}
					}
				}
					
			}
			
			while (rowIterator.hasNext()) {
				String row[] = new String[11];
				Row rows = rowIterator.next();
				if(isRowEmpty(rows))
					continue;
				Iterator<Cell> cellIterator = rows.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 11) {
						break;
					}
					switch (cell.getCellTypeEnum()) {
					case NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							try {
								DateFormat df = new SimpleDateFormat("M/d/yyyy");
						        Date date = cell.getDateCellValue();
						        row[cell.getColumnIndex()] = df.format(date);
							} catch(Exception exp) {
								log.error("Yanlis tarih formati girildi: " + fmt.formatCellValue(cell));
							}
					    }else {
					    	row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
					    }
						break;
					case STRING:
						row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
						break;	
					case BLANK:
						row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
						break;
					default:
						row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
						break;
					}
				}
				EkTeminatList.add(row);
			}
			
			workbook.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			
		}
		return EkTeminatList;
	}
	//Model sınıfı olusturulacak excel alınan bılgıler ıcın
	public List<AdayBilgileri> guncelLıste(String filePath, String fileName, int sheetIndex)
			throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
		List<AdayBilgileri> adayList = new ArrayList<AdayBilgileri>();
		try {
		file = new FileInputStream(new File(filePath + fileName));
		//ZipSecureFile.setMinInflateRatio(0);
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		DataFormatter fmt = new DataFormatter();
		Iterator<Row> rowIterator = sheet.iterator();
		AdayBilgileri aday = new AdayBilgileri();
		
	
		
		

		
 	
		for (int i = 0; i < 1; i++) {
			if (rowIterator.hasNext());
				rowIterator.next();
		}

		while (rowIterator.hasNext()) {
			rowIterator.hasNext();
			
			Row rows = rowIterator.next();
	        Iterator<Cell> cellIterator = rows.cellIterator();
			
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
			
				if (cell.getRowIndex() == 13) {
					break;
				}
					
					switch(cell.getRowIndex()) {

						case 3:
							aday.setBasvuruIlan(fmt.formatCellValue(cell));
							break;
						case 4:
							aday.setAdiSoyadi(fmt.formatCellValue(cell));
							break;
						case 5:
							aday.setEmail(fmt.formatCellValue(cell));
							break;
						case 6:
							aday.setDogumTarihi(fmt.formatCellValue(cell));
							break;
						case 7:
							aday.setEgitimBilgileri(fmt.formatCellValue(cell));
							break;				
						case 8:
							aday.setYabancidil(fmt.formatCellValue(cell));
							break;
						case 9:
							aday.setMedeniDurum(fmt.formatCellValue(cell));
							break;
						case 10:
							aday.setAskerlikDurum(fmt.formatCellValue(cell));
							break;
						case 11:
							aday.setIkemetgahAdresi(fmt.formatCellValue(cell));
							break;
						case 12:
							aday.setOnyazıGenelBilgiler(fmt.formatCellValue(cell));
							break;
												
						default:
							break;
					}
			}

			
			
		}
		adayList.add(aday);
		workbook.close();
		file.close();
		}
	 catch (FileNotFoundException e) {

	}
	return adayList;
	}
	//yaptıgı ıslemler ıcın rapora yazma

	public void insertListToDısKaynakExcel(List<AdayBilgileri> list, String filePath, String sheetName) throws InterruptedException, IOException {

		try {
			file = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowNumber = sheet.getLastRowNum()+1;
			for(AdayBilgileri aday : list) {
				XSSFRow row = sheet.createRow(rowNumber);
				for (int i = 0; i < 10; i++) {
					
					XSSFCell cell = row.createCell(i);
					
					switch(cell.getColumnIndex()) {
						case 0:
							cell.setCellValue(aday.getBasvuruIlan());
						break;
						case 1:
							cell.setCellValue(aday.getAdiSoyadi());
							break;
						case 2:
							cell.setCellValue(aday.getEmail());
							break;
						case 3:
							cell.setCellValue(aday.getDogumTarihi());
							break;
						case 4:
							cell.setCellValue(aday.getEgitimBilgileri());
							break;
						case 5:
							cell.setCellValue(aday.getYabancidil());
							break;
						case 6:
							cell.setCellValue(aday.getMedeniDurum());
							break;
						case 7:
							cell.setCellValue(aday.getAskerlikDurum());
							break;
						case 8:
							cell.setCellValue(aday.getIkemetgahAdresi());
							break;
						case 9:
							cell.setCellValue(aday.getOnyazıGenelBilgiler());
							break;
						
						default:
							break;
					}

				}
				rowNumber++;
			}
			for(int columnIndex = 0; columnIndex < 7; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
			}
			FileOutputStream fileOut = new FileOutputStream(new File(filePath));
			 
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			log.error("Hata!! Rapor excel dosyasi doldurulamadi: " + e.toString());
		//	EmailSend.excelDosyasiDoldurmaHatasi();
			throw new SkipException("HATA: Rapor excel dosyasi doldurulamadi.");
		}
	}
	
	
	
	
	
	public List<String[]> populateVefatList(String filePath, String filename)
			throws InterruptedException, IOException {

		try {
			file = new FileInputStream(new File(filePath + filename));

			XSSFWorkbook workbook = new XSSFWorkbook(file);

			XSSFSheet sheet = workbook.getSheetAt(1);
			DataFormatter fmt = new DataFormatter();

			Iterator<Row> rowIterator = sheet.iterator();
			
			for (int i = 0; i < 1; i++) {
				if (rowIterator.hasNext()) {
					Row rows = rowIterator.next();
					Iterator<Cell> cellIterator = rows.cellIterator();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (cell.getColumnIndex() == 5) {
							break;
						}
						String checkColumnName = null;
						switch(cell.getColumnIndex()) {
							case 0:
								checkColumnName = "TC Kimlik No";
								break;
							case 1:
								checkColumnName = "Teminat Türü";
								break;
							case 2:
								checkColumnName = "İhbar Tarihi";
								break;
							case 3:
								checkColumnName = "Tazminat Nedeni";
								break;
							case 4:
								checkColumnName = "ICD Kodu";
								break;
						}
						
						if(!isColumnNameValid(cell, checkColumnName)) {
							log.error(cell.getColumnIndex() + " sutun basligi eslesmedi: " + fmt.formatCellValue(cell));
							throw new SkipException(cell.getColumnIndex() + " sutun basligi eslesmedi: " + fmt.formatCellValue(cell));
						}
					}
				}
					
			}
			
			while (rowIterator.hasNext()) {
				String row[] = new String[5];
				Row rows = rowIterator.next();
				if(isRowEmpty(rows))
					continue;
				Iterator<Cell> cellIterator = rows.cellIterator();

				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getColumnIndex() == 5) {
						break;
					}
					switch (cell.getCellTypeEnum()) {
					case NUMERIC:
						if (HSSFDateUtil.isCellDateFormatted(cell)) {
							try {
								DateFormat df = new SimpleDateFormat("M/d/yyyy");
						        Date date = cell.getDateCellValue();
						        row[cell.getColumnIndex()] = df.format(date);
							} catch(Exception exp) {
								log.error("Yanlis tarih formati girildi: " + fmt.formatCellValue(cell));
							}
					    }else {
					    	row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
					    }
						break;
					case STRING:
						row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
						break;	
					case BLANK:
						row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
						break;
					default:
						row[cell.getColumnIndex()] = fmt.formatCellValue(cell);
						break;
					}
				}
				VefatList.add(row);
			}
			
			workbook.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			
		}
		return VefatList;
	}


	public void insertListToExcel(List<String[]> list, String fileName, String sheetName, int columnCount)
			throws InterruptedException, IOException {

		try {

			int listSize = list.size();
			if(listSize == 0)
				listSize--;
			file = new FileInputStream(new File(KPIAjanlariHesaplamalariData.filePath + fileName));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheet(sheetName);
			int rowNumber = sheet.getLastRowNum() + 1;

			
			Row row = sheet.createRow(rowNumber);
			for (int i = 0; i < columnCount; i++) {
				Cell cell = row.createCell(i);
				cell.setCellValue(list.get(listSize)[i]);
				sheet.autoSizeColumn(i);
			}
//
			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(new File(KPIAjanlariHesaplamalariData.filePath + fileName));
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			log.error("Hata!! Rapor excel dosyasi doldurulamadi: " + e.toString());
			//EmailSend.excelDosyasiDoldurmaHatasi();
			throw new SkipException("HATA: Rapor excel dosyasi doldurulamadi.");
		}
	}

	
	public void insertListGelenData(List<String[]> list, String fileName, String sheetName, int columnCount)
			throws InterruptedException, IOException {
		try {

			file = new FileInputStream(new File(KPIAjanlariHesaplamalariData.filePath + fileName));
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheet(sheetName);

			int rowNumber = sheet.getLastRowNum() + 1;

			for (int j = 0; j < list.size(); j++) {

				Row row = sheet.createRow(j + rowNumber);

				for (int i = 0; i < columnCount; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(list.get(j)[i]);
					sheet.autoSizeColumn(i);
				}
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(new File(KPIAjanlariHesaplamalariData.filePath + fileName));
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			log.error("Hata!! Rapor excel dosyasi doldurulamadi: " + e.toString());
		//	EmailSend.excelDosyasiDoldurmaHatasi();
			throw new SkipException("HATA: Rapor excel dosyasi doldurulamadi.");
		}
	}

	public void insertListToExcelDifferent(List<String[]> list, String fileName, String sheetName, int columnCount,
			int rowCount) throws InterruptedException, IOException {

		try {
			file = new FileInputStream(new File(KPIAjanlariHesaplamalariData.filePath + fileName));

			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheet(sheetName);

			for (int j = 0; j < list.size(); j++) {
				Row row = sheet.createRow(j + 1 + rowCount);

				for (int i = 0; i < columnCount; i++) {
					Cell cell = row.createCell(i);
					cell.setCellValue(list.get(j)[i]);
					sheet.autoSizeColumn(i);
				}
			}

			// Write the output to a file
			FileOutputStream fileOut = new FileOutputStream(new File(KPIAjanlariHesaplamalariData.filePath + fileName));
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();

		} catch (FileNotFoundException e) {
			log.error("Hata!! Rapor excel dosyasi doldurulamadi: " + e.toString());
		//	EmailSend.excelDosyasiKumulDoldurmaHatasi();
			throw new SkipException("HATA: Rapor excel dosyasi doldurulamadi.");
		}
	}

	public boolean isFileExists1(String filePath, String fileName) throws InterruptedException {
		boolean flag = false;
		File dir = new File(filePath);
		for (int tryCount = 1; !flag && tryCount > 0; tryCount--) {
			Thread.sleep(1000);
			log.info(fileName + " isimli dosya kontrol ediliyor. Kontrol : " + tryCount);

			File[] dir_contents = dir.listFiles();
			for (int i = 0; i < dir_contents.length; i++) {
				if (dir_contents[i].getName().equals(fileName)) {
					log.info(fileName + " isimli dosya var.");
					return flag = true;
				}
			}
		}
		return flag;
	}


	public boolean deleteFile1(String pathToFile) throws InterruptedException {
		Thread.sleep(2000);
		boolean flag = false;
		try {
			File file = new File(pathToFile);

			if (!file.exists())
				return false;

			flag = file.delete();

			if (flag) {
				Thread.sleep(1500);
				log.info(file.getName() + " dosyası silindi.");

			} else {
				log.error("Dosya silme islemi basarisiz.");
			}
		} catch (Exception e) {
			log.error("Error : Dosya silme islemi basarisiz.");
		}
		return flag;
	}
	
	
	
	
	
	public boolean isFileExists( String filePath, String fileName ) throws InterruptedException
	{
		boolean flag = false;
		File dir = new File( filePath );
		for( int tryCount = 5; !flag && tryCount > 0; tryCount-- )
		{
			Thread.sleep( 1000 );
			log.info( fileName + " isimli dosya kontrol ediliyor. Kontrol : " + tryCount );

			File[] dir_contents = dir.listFiles( );
			for( int i = 0; i < dir_contents.length; i++ )
			{
				if( dir_contents[i].getName( ).equals( fileName ) )
				{
					log.info( fileName + " isimli dosya var." );
					return flag = true;
				}
			}
		}
		return flag;
	}

	// İndirilen dosyalar aday ismine göre değiştirilecek ve kaydedilecek
	public List<String[]> populateAyrilanV2(String filePath, String fileName)
            throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException {
        List<String[]> ResponseListV2 = new ArrayList<String[]>();
         
        try {
        	  file = new FileInputStream(new File(filePath + fileName));
              XSSFWorkbook workbook = new XSSFWorkbook(file);
              XSSFSheet sheet = workbook.getSheet("Aday Öneriyorum Başvuru Formu");
             
              //başvuru ilan departmanı
                XSSFRow row1 = sheet.getRow(3);
                XSSFCell cell1 = row1.getCell(4);  
                System.out.println(cell1);
                //adı soyadı
                XSSFRow row2 = sheet.getRow(4);
                XSSFCell cell2 = row2.getCell(4);   
                System.out.println(cell2);
                //e-maıl adresı
                XSSFRow row3 = sheet.getRow(5);
                XSSFCell cell3 = row3.getCell(4);   
                System.out.println(cell3);
                //doğum tarihi 01-apr-1993 formatında geliyor
                XSSFRow row4 = sheet.getRow(6);
                XSSFCell cell4 = row4.getCell(4);   
                System.out.println(cell4);
                //eğitim bilgiler
                XSSFRow row5 = sheet.getRow(7);
                XSSFCell cell5 = row5.getCell(4);   
                System.out.println(cell5);
                //yabanci dil
                XSSFRow row6 = sheet.getRow(8);
                XSSFCell cell6 = row6.getCell(4);   
                System.out.println(cell6);
                //Medeni durum
                XSSFRow row7 = sheet.getRow(9);
                XSSFCell cell7 = row7.getCell(4);   
                System.out.println(cell7);
                //Askerlik durumu
                XSSFRow row8 = sheet.getRow(10);
                XSSFCell cell8 = row8.getCell(4);   
                System.out.println(cell8);
                //ikemetgah adresi
                XSSFRow row9 = sheet.getRow(11);
                XSSFCell cell9 = row9.getCell(4);   
                System.out.println(cell9);
                //önyazı genel bilgiler robotun yaptıgına dair bilgilerde eklenicek
                XSSFRow row10 = sheet.getRow(12);
                XSSFCell cell10 = row10.getCell(4);   
                System.out.println(cell10);
              
                workbook.write(new FileOutputStream(filePath + fileName)); 
                file.close();
                workbook.close();
          } catch (IOException e) {
        	  e.printStackTrace();
          }
          return ResponseListV2;
        }
// raporun içine yazdırma kısmı	
/*	public void insertListToDısKaynakExcel(List<Guncel> list, String filePath, String sheetName) throws InterruptedException, IOException {

		try {
			file = new FileInputStream(new File(filePath));
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			int rowNumber = sheet.getLastRowNum()+1;
			for(Guncel personal : list) {
				XSSFRow row = sheet.createRow(rowNumber);
				for (int i = 0; i < 6; i++) {
					
					XSSFCell cell = row.createCell(i);
					
					switch(cell.getColumnIndex()) {
						case 0:
							cell.setCellValue(personal.getSicil());
						break;
						case 1:
							cell.setCellValue(personal.getAd());
							break;
						case 2:
							cell.setCellValue(personal.getSoyad());
							break;
						case 3:
							cell.setCellValue(personal.getGenelMudurYardımcılıgı());
							break;
						case 4:
							cell.setCellValue(personal.getGrupMudurlugu());
							break;
						case 5:
							cell.setCellValue(personal.getMudurluk());
							break;
						default:
							break;
					}

				}
				rowNumber++;
			}
			for(int columnIndex = 0; columnIndex < 7; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
			}
			FileOutputStream fileOut = new FileOutputStream(new File(filePath));
			 
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
		} catch (FileNotFoundException e) {
			log.error("Hata!! Rapor excel dosyasi doldurulamadi: " + e.toString());
			//EmailSend.excelDosyasiDoldurmaHatasi();
			throw new SkipException("HATA: Rapor excel dosyasi doldurulamadi.");
		}
	}
	
	
	
	
	
	*/

	
	public boolean isFileExistsWithTime( String filePath, String fileName, int count ) throws InterruptedException
	{
		boolean flag = false;
		File dir = new File( filePath );
		for( int tryCount = count; !flag && tryCount > 0; tryCount-- )
		{
			Thread.sleep( 1000 );
			log.info( fileName + " isimli dosya kontrol ediliyor. Kontrol : " + tryCount );

			File[] dir_contents = dir.listFiles( );
			for( int i = 0; i < dir_contents.length; i++ )
			{
				if( dir_contents[i].getName( ).equals( fileName ) )
				{
					log.info( fileName + " isimli dosya var." );
					return flag = true;
				}
			}
		}
		return flag;
	}

	public boolean deleteFile( String pathToFile ) throws InterruptedException
	{
		Thread.sleep( 2000 );
		boolean flag = false;
		try
		{
			File file = new File( pathToFile );

			if( !file.exists( ) )
				return false;

			flag = file.delete( );

			if( flag )
			{
				Thread.sleep( 1500 );
				log.info( file.getName( ) + " dosyası silindi." );

			}else
			{
				log.error( "Dosya silme islemi basarisiz." );
			}
		}catch( Exception e )
		{
			log.error( "Error : Dosya silme islemi basarisiz." );
		}
		return flag;
	}

	public boolean deleteRow( String sheetName, String excelPath, int rowNo ) throws IOException

	{

		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;

		try
		{
			FileInputStream file = new FileInputStream( new File( excelPath ) );
			workbook = new XSSFWorkbook( file );
			sheet = workbook.getSheet( sheetName );
			if( sheet == null )
			{
				return false;
			}
			int lastRowNum = sheet.getLastRowNum( );
			if( rowNo >= 0 && rowNo < lastRowNum )
			{
				sheet.shiftRows( rowNo + 1, lastRowNum, -1 );
			}
			if( rowNo == lastRowNum )
			{
				XSSFRow removingRow = sheet.getRow( rowNo );
				if( removingRow != null )
				{
					sheet.removeRow( removingRow );
				}
			}
			file.close( );
			FileOutputStream outFile = new FileOutputStream( new File( excelPath ) );
			workbook.write( outFile );
			outFile.close( );

		}catch( Exception e )
		{
			throw e;
		}finally
		{
			if( workbook != null )
				workbook.close( );
		}
		return false;
	}
	
	public List<List<Object>> readToList( String filePath, String fileName) throws IOException
	{
		
		List<List<Object>> list = new ArrayList<>();
		
		FileInputStream file = null;

		try
		{
			file = new FileInputStream( new File( filePath + fileName ) );
			
			Workbook wb = null;
			DataFormatter fmt = new DataFormatter();
			
			wb = WorkbookFactory.create( file );
			int sheetNumber = wb.getNumberOfSheets();
			
			for( int i = 0; i < sheetNumber; i++ )
			{
				Sheet currSheet = wb.getSheetAt( i );
				
				Iterator<Row> rowIterator = currSheet.iterator();
				
				while( rowIterator.hasNext( ) )
				{
					Row row = rowIterator.next( ) ;
				
					List<Object> listCells = new ArrayList<>( );
					Iterator<Cell> cellIterator = row.cellIterator( );
					while( cellIterator.hasNext( ) )
					{
						Cell cell = cellIterator.next( );

						switch( cell.getCellType( ) ) 
						{
							case Cell.CELL_TYPE_NUMERIC:
	
								listCells.add( fmt.formatCellValue( cell ).trim( ) ); 
								break;
	
							case Cell.CELL_TYPE_STRING:
								listCells.add( cell.getStringCellValue( ) );
								break;
							default:
								listCells.add(fmt.formatCellValue( cell ).trim( ) );
								break;
						}
						
						
					}
					
					list.add( listCells );
				}
				
			}
			
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncryptedDocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		list.remove(0);	
		file.close();
		return list;
	}
	
	public String convertCSVtoXlSX(String fPath, String fName)
	{
		ArrayList arList=null;
        ArrayList al=null;
        String thisLine;
        int count=0;
        try {
        	FileInputStream fis = new FileInputStream(fPath + fName);
            DataInputStream myInput = new DataInputStream(fis);
            int i = 0;
            arList = new ArrayList();
            while ((thisLine = myInput.readLine()) != null)
            {
            	if(thisLine.contains(",")) {
            		al = new ArrayList();
            		String strar[] = thisLine.split(",");
            		for(int j=0;j<strar.length;j++)
            		{
            			al.add(strar[j]);
            		}
            		arList.add(al);
            		i++;
            	}
            	else if(thisLine.contains(";"))	{
            		al = new ArrayList();
            		String strar[] = thisLine.split(";");
            		for(int j=0;j<strar.length;j++)
            		{
            			al.add(strar[j]);
            		}
            		arList.add(al);
            		i++;
            	}
            }

            try
            {
                XSSFWorkbook hwb = new XSSFWorkbook();
                XSSFSheet sheet = hwb.createSheet("Agent");
                for(int k=0;k<arList.size();k++)
                {
                    ArrayList ardata = (ArrayList)arList.get(k);
                    XSSFRow row = sheet.createRow((short) 0+k);
                    for(int p=0;p<ardata.size();p++)
                    {
                        XSSFCell cell = row.createCell((short) p);
                        String data = ardata.get(p).toString();
                        if(data.startsWith("=")){
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            data=data.replaceAll("\"", "").replaceAll("=", "");
                            cell.setCellValue(data);
                        }else if(data.startsWith("\"")){
                            data=data.replaceAll("\"", "");
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            cell.setCellValue(data);
                        }else{
                            data=data.replaceAll("\"", "");
                            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(data);
                        }
                    }
                }
                fName = fName.replaceAll("csv", "xlsx");
                FileOutputStream fileOut = new FileOutputStream(fPath + fName);
                hwb.write(fileOut);
                fileOut.close();
                fis.close();
                System.out.println("Your excel file has been generated");
                
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
          
		} catch (Exception e) {
			// TODO: handle exception
		} 
        
        return fName;	
	}
	
	public void deleteFile(String filePath, String fileName)
	{
		try {
			File file = new File(filePath + fileName);
			file.delete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String renameFile(String filePath, String currentFileName, String renameFile) {

		File file = new File(filePath + currentFileName);
		String fileName = file.getName();
		String fileExtensionValue = currentFileName.substring(fileName.lastIndexOf("."));
		File rename = new File(filePath + (renameFile += fileExtensionValue));

		if (file.renameTo(rename)) {
			log.info("File renamed succsessfully : " + rename.getName());
			return rename.getName();
		} else {
			
			log.error("File not renamed!");
			return null;
		}
	}

	public void copyFile(String sourcePath, String targetPath, String fileName, boolean... delete)
			throws InterruptedException {

		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			File afile = new File(sourcePath + "//" + fileName);
			File bfile = new File(targetPath + "//" + fileName);

			inStream = new FileInputStream(afile);
			outStream = new FileOutputStream(bfile);

			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);
			}

			inStream.close();
			outStream.close();

			if (delete.length > 0 && delete[0] != true)
				// delete the original file
				afile.delete();

			if (isFileMoved(targetPath, fileName) != true)
				log.error("File is copied failed! " + fileName);

			log.info("File is copied successful! " + fileName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveToFile(String filePath, String targetFolder, String fileName) throws Exception {

		File file = new File(filePath + fileName);

		// renaming the file and moving it to a new location
		if (file.renameTo(new File(targetFolder + fileName))) {
			// if file copied successfully then delete the original file
			if (isFileMoved(targetFolder, fileName) == true) {
				file.delete();
				log.info("File moved successfully. " + filePath + fileName);
			} else
				log.error("File is copied failed!");
		} else {
			log.error("Failed to move the file. " + filePath + fileName);
			throw new Exception();
		}
	}

	public boolean isFileMoved(String movedPath, String fileName) throws InterruptedException {
		boolean flag = false;
		File dir = new File(movedPath);
		for (int tryCount = 30; !flag && tryCount > 0; tryCount--) {
			Thread.sleep(1000);
			log.info("Tasinan/Kopyalanan dosya kontrol ediliyor. Kontrol : " + tryCount);
			File[] dir_contents = dir.listFiles();
			for (int i = 0; i < dir_contents.length; i++) {
				if (dir_contents[i].getName().equals(fileName))
					return flag = true;
			}
		}
		return flag;
	}
	
	public void copyAndMoveToFile(String filePath, String copyTargetFolder, String moveTargetFolder, String fileName) throws Exception {
		
		copyFile(filePath, copyTargetFolder, fileName);
		moveToFile(filePath, moveTargetFolder, fileName);
		
	}
	
	public void createDirectory(String path)	{
		
		File theDir = new File(path);
		theDir.mkdirs();
		log.info("dirs. is created");
	
		
	}
	
	public  List<List<Object>> filterListByContains( List<List<Object>> readingList, int fileIndex, String value )
	{
		List<List<Object>> filterList = new ArrayList<>( );
		String date = "";
		if(readingList.get(readingList.size() - 1).isEmpty() || readingList.get(readingList.size() - 1).get(0).equals(""))
			readingList.remove(readingList.size() - 1);

		for( int i = 0; i < readingList.size(); i++ )
		{		
			date = readingList.get(i).get(fileIndex).toString().trim();
			if(!date.equals("N/A".trim()) && !date.equals("".trim()) && !date.equals(null))
				if(utilityMethods.is30days(date , value) <= 30)
					filterList.add( readingList.get(i));
		}
			
				
		return filterList;
	}
	
	public  List<List<Object>> filterListByContains( List<List<Object>> readingList, String fileIndex, String value )
	{
		List<List<Object>> filterList = new ArrayList<>( );
		
		if(readingList.get(readingList.size() - 1).isEmpty())	{
			readingList.remove(readingList.size() - 1);
			if(readingList.get(readingList.size() - 1).isEmpty())
				readingList.remove(readingList.size() - 1);
		}
		
		for( int i = 0; i < readingList.size(); i++ ) {	
			if(readingList.get(i).size() > 1) {
				if(readingList.get(i).get(1).toString().toLowerCase().equals(KPIAjanlariHesaplamalariData.SCCM_CLIENT_FILTER_VALUE.toLowerCase()) ||
						readingList.get(i).get(1).toString().toLowerCase().equals(KPIAjanlariHesaplamalariData.SCCM_CLIENT_FILTER_VALUE_EXTRA.toLowerCase()))
					filterList.add( readingList.get(i));
			}
		}
		
		return filterList;
	}
	

	public  List<ComputersModel> filterListForComputers( List<ComputersModel> readingList, String filterValue )
	{
		List<ComputersModel> filterList = new ArrayList<>( );
			
		for( int i = 0; i < readingList.size(); i++ )	
			if(!readingList.get(i).getName().startsWith(filterValue))
				filterList.add(readingList.get(i));
				
		return filterList;
	}
	
	
	
	public List<List<Object>> readCSVToList(String filePath, String fileName)
			throws InterruptedException, IOException {

		List<List<Object>> arList = new ArrayList<>();
		List<String[]> al = new ArrayList<>();
		
		try {

			URL url = new File(filePath + fileName).toURI().toURL();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charsets.UTF_8));
			CSVParser parser = new CSVParser(reader, CSVFormat.DEFAULT);
			Iterator<CSVRecord> recordIterator = parser.getRecords().iterator();
			for (int i = 0; i < 2; i++) {
				if (recordIterator.hasNext())
					recordIterator.next();
			}
			while (recordIterator.hasNext()) {
				String row[] = new String[1];
				CSVRecord record = recordIterator.next();
				int i = 0;
				for (String str : record) {
					row[i++] = str;
				}
				al.add(row);
			}
			parser.close();
			reader.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		for (int j = 0; j < al.size(); j++) {
			
			String name = "";
			List<Object> temp = new ArrayList<>();
			char arr[] = al.get(j)[0].toCharArray();
			for (int i = 0; i < arr.length; i++)
				if (arr[i] != 0 && arr[i] != 45)
					name = name.concat(String.valueOf(arr[i]));
			
			temp.add(name.trim());
			
			if(!(temp.get(0).equals("")) && !(temp.get(0).equals("name")))
				arList.add(temp);			
		}
		
		return arList;

	}
	public  List<List<Object>> readToListKeepnet( String filePath, String fileName) throws IOException
    {
          
          List<List<Object>> list = new ArrayList<>();
          
          FileInputStream file = null;
          try
          {
               file = new FileInputStream( new File( filePath + fileName ) );
               
               Workbook wb = null;
               DataFormatter fmt = new DataFormatter();
               
               wb = WorkbookFactory.create( file );
               int sheetNumber = wb.getNumberOfSheets();
               
               for( int i = 0; i < sheetNumber; i++ )
               {
                    Sheet currSheet = wb.getSheetAt( i );
                    Iterator<Row> rowIterator = currSheet.iterator();
                    while( rowIterator.hasNext( ) )
                    {
                          Row row = rowIterator.next( ) ;
                    
                          List<Object> listCells = new ArrayList<>( );
                          
                          for(int j = 0; j < row.getLastCellNum(); j++)    {
                               
                               Cell cell = row.getCell(j);
                               
                               if( cell == null)     
                                    listCells.add("");
                                    
                               else
                                    switch( cell.getCellType()) 
                                    {                               
                               
                                          case Cell.CELL_TYPE_NUMERIC:
                                               listCells.add( fmt.formatCellValue( cell ).trim( ) ); 
                                               break;
                                          case Cell.CELL_TYPE_BLANK:
                                               listCells.add("");
                                               break;
                                          
                                          case Cell.CELL_TYPE_STRING:
                                               listCells.add( cell.getStringCellValue( ) );
                                               break;
                                          
                                          default:
                                               listCells.add(fmt.formatCellValue( cell ).trim( ) );
                                               break;
                                    }
                          }
                          list.add( listCells );     
                    }
               }
          } catch (FileNotFoundException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          } catch (EncryptedDocumentException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          } catch (InvalidFormatException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          } catch (IOException e)
          {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
          list.remove(0); 
          file.close();
          return list;
    }	
}
