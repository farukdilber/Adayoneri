package com.avivasa.rpa.utiliy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.store.Path;

import com.avivasa.rpa.data.KPIAjanlariHesaplamalariData;
import com.avivasa.rpa.util.DateUtil;
	
public class UtilityMethods {
	int waitSFTP = 10000;
	int maxColumn = 22;
	public static final String ENCODER= "Cp1254";
	String policeNo = "";
//	public static final String ENCODER= "ISO 8859-9";
	DateTimeFormatter formatter;
	LocalDate newDate;
	
	 public static String getDate(){
		 Date date = new Date();
		 SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		 return ft.format(date);
	 }
		public static String turkishCharConvert(String str)
		{
		   char[] oldChar = new char[] { 'İ', 'ı','ü', 'Ü', 'ç', 'Ç','Ğ', 'ğ','Ş', 'ş','ö','Ö' };
		   char[] newChar = new char[] { 'I', 'i', 'u','U','c','C','G','g','S', 's','o','O', };
		   for (int i = 0; i < oldChar.length; i++)
		   {
			   str = str.replace(oldChar[i], newChar[i]);
		   }
		   return str;
		}

	 public static String getDateFormat(){
		 Date date = new Date();
		 SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
		 return ft.format(date);
	 }
	 
	 public String getTime() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("HH-mm-ss");
		return ft.format(date);
		}
	 
	 public String getTimeForAkbankFormat() {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		return ft.format(date);
		}
	 
	 public String getTimeDifference(String time1, String time2) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		Date date1 = ft.parse(time1);
		Date date2 = ft.parse(time2);
		long difference = Math.abs(date2.getTime() - date1.getTime());
		//System.out.println(difference/1000);
		
		String differenceAsString = Long.toString(difference/1000);
		
		return differenceAsString;
		} 
		
		public static String getLastDayOfTheMonth(String date) {
	        String lastDayOfTheMonth = "";

	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        try{
	        java.util.Date dt= formatter.parse(date);
	        Calendar calendar = Calendar.getInstance();  
	        calendar.setTime(dt);  

	        calendar.add(Calendar.MONTH, 1);  
	        calendar.set(Calendar.DAY_OF_MONTH, 1);  
	        calendar.add(Calendar.DATE, -1);  

	        java.util.Date lastDay = calendar.getTime();  

	        lastDayOfTheMonth = formatter.format(lastDay);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return lastDayOfTheMonth;
	    }
		
		public Boolean controlFileAndSendMail(String filePath, String fileName) throws InterruptedException {

			boolean statusBrowser = false;

			if (!isFileExists(filePath, fileName)) {
	     		log.error(filePath + fileName + " isimli dosya bulunamamistir.");
				statusBrowser = true;
			}
			return statusBrowser;
		}
		
		public boolean isFileExists(String filePath, String fileName) throws InterruptedException {
			boolean flag = false;
			File dir = new File(filePath);
			for (int tryCount = 3; !flag && tryCount > 0; tryCount--) {
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
		
		public long is30days(String date, String agent)	{
			
			SimpleDateFormat sdf = null;			
			if(agent.equals(KPIAjanlariHesaplamalariData.CISCO_KEYWORD) || agent.equals(KPIAjanlariHesaplamalariData.NESSUS_KEYWORD) )	{
				
				date = date.substring(0, 10);
			}
			else if(agent.equals(KPIAjanlariHesaplamalariData.DLP_AGENT_KEYWORD) 
					|| agent.equals(KPIAjanlariHesaplamalariData.TITUS_KEYWORD) 
					|| agent.equals(KPIAjanlariHesaplamalariData.MCAFEE_KEYWORD))	{
				
				if(date.substring(1, 2).equals("/"))	{
					if(date.substring(3, 4).equals("/"))	{
						formatter = DateTimeFormatter.ofPattern("M/d/yy");
						newDate = LocalDate.parse(date.substring(0, 6), formatter);
					}
					else	{
						formatter = DateTimeFormatter.ofPattern("M/dd/yy");
						newDate = LocalDate.parse(date.substring(0, 7), formatter);
					}		
				}
				else	{
					if(date.substring(4, 5).equals("/"))	{
						formatter = DateTimeFormatter.ofPattern("MM/d/yy");
						newDate = LocalDate.parse(date.substring(0, 7), formatter);
					}
					else	{
						formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
						newDate = LocalDate.parse(date.substring(0, 8), formatter);
					}		
				}
				date = newDate.toString();
			}else if(agent.equals(KPIAjanlariHesaplamalariData.KEEPNET_KEYWORD))     {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                newDate = LocalDate.parse(date.substring(0, 10).trim(), formatter);
			/*
				
				if(date.substring(4, 5).trim().isEmpty())	
					formatter = DateTimeFormatter.ofPattern("MMM  d yyyy", Locale.ENGLISH);	
				else 
					formatter = DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
				//formatter = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH);
				newDate = LocalDate.parse(date.substring(0, 11).trim(), formatter);
				date = newDate.toString();
			*/
			}
			
			sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			SimpleDateFormat systemFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date firstDate;			
		    Date secondDate;
		    long diff = 0;
			try {
				firstDate = sdf.parse(date);
				secondDate = systemFormat.parse(getDate());
				long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
			    diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			   
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return diff;
		}
		
		public Boolean fileOpenInFolder( String path, String filenameInFolder )
		{
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			String fileName = null;
			boolean status = false;

			for (int i = 0; i < listOfFiles.length; i++)
			{
				if (listOfFiles[i].isFile())
				{
					fileName = listOfFiles[i].getName();
					if (fileName.contains("~$") && fileName.contains(filenameInFolder))
					{
						status = true;
						break;
					}
					// log.info("File " + listOfFiles[i].getName());
				}
				// else if (listOfFiles[i].isDirectory()) {
				// log.info("Directory " + listOfFiles[i].getName());
				// }
			}

			return status;
		}
		
		public void copyFilesCommomToRPA(String filePath, String copyFileName) throws IOException
		{
			File sourceFile = new File(KPIAjanlariHesaplamalariData.BUSSINESS_PATH + copyFileName );
			File destFile = new File(filePath + copyFileName );

			copyFile( sourceFile, destFile );

			log.info(sourceFile.getAbsolutePath() + " file was succesfully copy to " + destFile.getAbsolutePath());
		}
		
		public void copyFile(File source, File dest) throws IOException
		{
			InputStream is = null;
			OutputStream os = null;

			try
			{
				is = new FileInputStream(source);
				os = new FileOutputStream(dest);
				byte[] buffer = new byte[1024];
				int length;

				while ((length = is.read(buffer)) > 0)
				{
					os.write(buffer, 0, length);
				}
			} finally
			{
				is.close();
				os.close();

			}	
		}
		
		public void renameFile(String filePath, String currentFileName, String renameFile) {

			File file = new File(filePath + currentFileName);
			String fileName = file.getName();
			String fileExtensionValue = currentFileName.substring(fileName.lastIndexOf("."));
			File rename = new File(filePath + (renameFile + fileExtensionValue));

			if (file.renameTo(rename)) {
				log.info("File renamed succsessfully : " + rename.getName());
				
			} else {
				log.error("File not renamed!");
				
			}
		}
		
		public void copyFile(String sourcePath, String targetPath, String fileName)
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
				
				for (int i = 0; i < 2; i++) {
					if (isFileExists(sourcePath + "\\", fileName)) {
						Files.deleteIfExists(Paths.get(sourcePath + "\\" + fileName));
					}
				}
				

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
		
		public String createDirectory(String path)	{
			String newPath = path + DateUtil.getReportDate();
			File folder = new File(newPath);

			if (folder.exists() && folder.isDirectory()) {
				log.info("Dosya yolu bulundu : " + newPath);
				return newPath;
			}
			else	{
				log.error("Dosya yolu bulunamadı : " + newPath);
				File theDir = new File(newPath);
				theDir.mkdirs();
				log.info("dirs. is created");
				return newPath;
			}
			
			
		}
		
}
