package com.avivasa.rpa.KPIAjanlariHesaplamalari.actions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;


import com.avivasa.rpa.base.AbstractPageHtmlUnit;
import com.avivasa.rpa.data.KPIAjanlariHesaplamalariData;
import com.avivasa.rpa.exceptions.KPIAjanlariHesaplamalariExceptions;
import com.avivasa.rpa.model.AdayBilgileri;
import com.avivasa.rpa.utiliy.EmailSend;
import com.avivasa.rpa.utiliy.UtilityMethods;
import com.avivasa.rpa.utiliy.log;

public class BiDunyaActions extends AbstractPageHtmlUnit {
	WebDriver driver;
	List<File> allPDFFiles;
	UtilityMethods utilityMethods = new UtilityMethods();
	
	public boolean biDünyaloginHatasi = false;
	
	public BiDunyaActions() throws MalformedURLException {
		
		super();
		driver = this.getDriver();
	}
	public void navigateTo(String url) {

		try {
			driver.get(url);
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
//			Wait(500);
			log.info("Web application launched");
			// LogPASS("Web application launched");
		} catch (Exception e) {
			log.error("Error while getting app url : " + e);
			// LogFAIL("Error while getting app url : " + e);

			throw new RuntimeException(e);
		}
	}
	
	public BiDunyaActions openUrl(String url) {

		try {

			navigateTo(url);
			
             log.info("openUrl transaction passed");

		} catch (Exception e) {
			log.error("Hata!! BiDünya url baslatilamadi: " + e);
			// EmailSend.BiKolayUrlHatasi();
			throw new SkipException("Hata!! BiDünya url baslatilamadi.");
		}

		return this;
	}
	public BiDunyaActions uploadFile(String pathToFile ) throws InterruptedException {
		try
		{		
		 String folderPath = pathToFile;
		    File folder = new File(folderPath);

		    if (folder.exists() && folder.isDirectory()) {
		        File[] files = folder.listFiles();

		        for (File file : files) {
		            if (file.isFile() && file.getName().endsWith(".pdf")) {
		                String filePath = file.getAbsolutePath();
		                
		                
		                driver.findElement(KPIAjanlariHesaplamalariData.txtInputFile ).sendKeys(filePath);
		                click( KPIAjanlariHesaplamalariData.btnUploadTamam );
		                log.info( filePath + " is succesfully upload to kariyer firsatlari. " );
		                Wait( 2000 );
		                driver.switchTo( ).defaultContent( );
		            }
		        }
		    }


							
			
		} catch (InterruptedException e) {
			log.info("Hata!! BiDünya ekranlarında Hamburger Menu Acılamadı : " + e.getMessage());
			log.error("Hata!! BiDünya ekranlarında Hamburger Menu Acılamadı : " + e.getMessage());
			
			

		}
			return this ;
		
		}
	
	
	public BiDunyaActions searchBasvuruIlan(List<AdayBilgileri> adayBilgileriList) throws KPIAjanlariHesaplamalariExceptions, InterruptedException, IOException {
		List<String> basvuruılanlıst = new ArrayList<>();
		String basvuruılanisim = "";
		try {
			
			if (isElementExist(By.xpath("/html/body/div[3]/div"))){
				click(By.xpath("/html/body/div[3]/div/button"));
			}
			else {
				log.info("Web sayfasında pop-up cıkmadı");
			}
			

		//	.replaceAll("[]", "")
			for (AdayBilgileri adayBilgileri : adayBilgileriList) {
				String basvuruılan = adayBilgileri.getBasvuruIlan();
				
				
				basvuruılanisim = basvuruılan.replaceAll("AgeSA ", "").trim();
				// basvuruılanlıst.add(basvuruılan);
				
				log.info(basvuruılanisim);
				
				break;
			}
			
			String sitedeAranacakBasvuruIlanAdi = "";
			int basvuruadıIndex = 1;
			do {
				
				if(basvuruadıIndex == 11 ) {
				//	basvuruadıIndex == 10 && isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div/div/div/a[" + basvuruadıIndex + "]/div[2]/div"))					
					click(By.xpath("//*[@id=\"CareerListWebPart\"]/div/div[3]/ul/li[3]/a"));
				basvuruadıIndex = 1;
				}
				
				
				
				if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div/div/div/a[" + basvuruadıIndex + "]/div[2]/div")))
					sitedeAranacakBasvuruIlanAdi = getTextOfElement(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div/div/div/a[" + basvuruadıIndex + "]/div[2]/div"));     
			 
				

					
				
				
				
				if(basvuruılanisim.equals(sitedeAranacakBasvuruIlanAdi)) {
									
					log.info("Basvuru ilan ismi ile site ilan adi denk geldi");
					click(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div[1]/div[1]/div/div[1]/div/div/div/div[2]/div/div/div/a[" + basvuruadıIndex + "]/div[2]/div"));
						click(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/button[2]"));	
						for (AdayBilgileri formBilgileri : adayBilgileriList) {
							String adıSoyadı = formBilgileri.getAdiSoyadi();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[1]/span[2]/input"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[1]/span[2]/input"),adıSoyadı,false);
							} 
							String eMail = formBilgileri.getEmail();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[2]/span[2]/input"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[2]/span[2]/input"),eMail,false);
							}
							String outputFormat = "MM/dd/yyyy";
							String dogumTarihi =  formBilgileri.getDogumTarihi();
							SimpleDateFormat inputDateFormat = new SimpleDateFormat("M/d/yy");
					        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
							
							try {
								Date date = inputDateFormat.parse(dogumTarihi);
								String outputDate = outputDateFormat.format(date);
								if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[3]/span[2]/input"))) {
									sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[3]/span[2]/input"),outputDate,false);
								}	
							} catch (ParseException e) {
								System.out.println("Invalid date format: " + dogumTarihi);
								e.printStackTrace();
							}
							 
							
							
							String egitimBilgileri = formBilgileri.getEgitimBilgileri();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[4]/span[2]/input"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[4]/span[2]/input"),egitimBilgileri,false);
							}
							String yabancıDil = formBilgileri.getYabancidil();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[5]/span[2]/input"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[5]/span[2]/input"),yabancıDil,false);
							}
							String medeniDurum = formBilgileri.getMedeniDurum();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[6]/span[2]/input"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[6]/span[2]/input"),medeniDurum,false);
							}
							String askerlikDurumu = formBilgileri.getAskerlikDurum();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[7]/span[2]/input"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[7]/span[2]/input"),askerlikDurumu,false);
							}
							String ikametgahAdresi = formBilgileri.getIkemetgahAdresi();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[8]/span[2]/textarea"))) {
								sendKeys(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[8]/span[2]/textarea"),ikametgahAdresi,false);
							}
							String onYazi = formBilgileri.getOnyazıGenelBilgiler();
							if(isElementExist(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[9]/span[2]/textarea"))) {
								sendKeys1(By.xpath("/html/body/form/div[3]/div/div[3]/div[2]/div[2]/div[3]/div[2]/div[3]/div/div/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div/div/div[5]/div[9]/span[2]/textarea"),onYazi,false);
								
							}
							
						}
					break;
						}
					
				 else {
					
				}
				basvuruadıIndex++;		
			}while(!basvuruılanlıst.equals(sitedeAranacakBasvuruIlanAdi));
			


		} catch (InterruptedException e) {
			log.info("Hata!! BiDünya ekranlarında Hamburger Menu Acılamadı : " + e.getMessage());
			log.error("Hata!! BiDünya ekranlarında Hamburger Menu Acılamadı : " + e.getMessage());
		//	throw new KPIAjanlariHesaplamalariExceptions(policePrefixNo, policeNo, "Başarısız", "BiKolay ekranında dökuman sorgulama başarısız", contentReportList);
		

	}
		return this;
	}
	public List<File> getAllExcelFilePath(String filepath, String extention) {

		List<File> excelFileName = new ArrayList<>();

		File file = new File(filepath);

		for (File filename : file.listFiles()) {
			if (filename.getAbsolutePath().contains(extention) && !filename.getAbsolutePath().contains("~"))
				excelFileName.add(new File(filename.getAbsolutePath()));
		}
		return excelFileName;
	}
}
