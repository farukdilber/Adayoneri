package com.avivasa.rpa.KPIAjanlariHesaplamalari.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import com.avivasa.rpa.base.AbstractTest;
import com.avivasa.rpa.data.ExcelToModelListImp;
import com.avivasa.rpa.data.KPIAjanlariHesaplamalariData;
import com.avivasa.rpa.exceptions.KPIAjanlariHesaplamalariExceptions;
import com.avivasa.rpa.reports.CiscoReportsDTO;
import com.avivasa.rpa.reports.ComputersReportsDTO;
import com.avivasa.rpa.reports.DLPAgentReportsDTO;
import com.avivasa.rpa.reports.KeepnetReportsDTO;
import com.avivasa.rpa.reports.MCafeeReportsDTO;
import com.avivasa.rpa.reports.NessusReportsDTO;
import com.avivasa.rpa.reports.SCCMClientReportsDTO;
import com.avivasa.rpa.reports.TitusReportsDTO;
import com.avivasa.rpa.utiliy.EmailSend;
import com.avivasa.rpa.utiliy.UtilityMethods;
import com.avivasa.rpa.utiliy.log;
import com.avivasa.rpa.data.ExcelOperations;
import com.avivasa.rpa.KPIAjanlariHesaplamalari.actions.BiDunyaActions;
import com.avivasa.rpa.KPIAjanlariHesaplamalari.actions.KPIAjanlariHesaplamalariActions;
import com.avivasa.rpa.model.AdayBilgileri;
import com.avivasa.rpa.model.AvivasaComputersModel;
import com.avivasa.rpa.model.CiscoModel;
import com.avivasa.rpa.model.ComputersModel;
import com.avivasa.rpa.model.DLPAgentModel;
import com.avivasa.rpa.model.ExcelModel;
import com.avivasa.rpa.model.KeepnetModel;
import com.avivasa.rpa.model.MCafeeModel;
import com.avivasa.rpa.model.MailImp;
import com.avivasa.rpa.model.MailModel;
import com.avivasa.rpa.model.MailTableModel;
import com.avivasa.rpa.model.NessusModel;
import com.avivasa.rpa.model.SCCMClientModel;
import com.avivasa.rpa.model.TitusModel;

public class KPIAjanlariHesaplamalari extends AbstractTest {
	
	public KPIAjanlariHesaplamalari() throws MalformedURLException {
		super();
		// TODO Auto-generated constructor stub
	}

	static FileInputStream file;
	 
	AvivasaComputersModel avivasaComputersModel = null;
	ComputersModel computersModel = null;
	CiscoModel ciscoModel = null;
	DLPAgentModel dlpAgentModel = null;
	KeepnetModel keepnetModel = null;
	NessusModel nessusModel = null;
	SCCMClientModel sccmClientModel = null;
	TitusModel titusModel = null;
	MCafeeModel mCafeeModel = null;
	
	List<String[]> adayExcel = new ArrayList<String[]>();
	public List<AvivasaComputersModel> avivasaComputersList;
	public List<ComputersModel> computersModelsList;
	public List<CiscoModel> ciscoModelsList;
	public List<DLPAgentModel> dLPAgentModelsList;
	public List<KeepnetModel> keepnetModelsList;
	public List<NessusModel> nessusModelsList;
	public List<SCCMClientModel> sccmClientModelsList;
	public List<TitusModel> titusModelsList;
	public List<MCafeeModel> mCafeeModelsList; 
	List<AdayBilgileri> adayBilgileriList = new ArrayList<AdayBilgileri>();
	List<File> allPDFFiles;
	
	private static MailImp mailImp;
	
	MailTableModel mailTableModel = new MailTableModel();
	ExcelToModelListImp excelToModelListImp = new ExcelToModelListImp();
	BiDunyaActions biDunyaActions;	
	
	ExcelOperations eo = new ExcelOperations();	
	UtilityMethods um = new UtilityMethods();
	KPIAjanlariHesaplamalariActions actions = new KPIAjanlariHesaplamalariActions();
	
	@BeforeSuite
	public void beforeSuit() throws InterruptedException, IOException, EncryptedDocumentException, InvalidFormatException, KPIAjanlariHesaplamalariExceptions {
		
		log.startTestCase("Aday Öneriyorum Süreci ");

/*		
		if( um.controlFileAndSendMail( KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME ) ) 
		{
			log.error(  KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME + " dosyası bulunamamistir.");
			EmailSend.dosyaBulunamadi(KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME, 
					new String[]{KPIAjanlariHesaplamalariData.to});
			throw new SkipException( KPIAjanlariHesaplamalariData.BUSSINESS_PATH  + KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME + " dosyası bulunamamistir.");
		}
		if( um.fileOpenInFolder( KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME ) ) 
		{
			log.error(   KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME + " dosyasi acik.");
			EmailSend.dosyaAcik(   KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME,
					new String[]{KPIAjanlariHesaplamalariData.to});
			throw new SkipException("Hata!! " +  KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME + " dosyasi acik.");
		}
		
		if( um.controlFileAndSendMail(  KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME ) ) 
		{
			log.error(  KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME + " dosyası bulunamamistir.");
			EmailSend.dosyaBulunamadi(KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME,
					new String[]{KPIAjanlariHesaplamalariData.to_8, KPIAjanlariHesaplamalariData.to});
			throw new SkipException( KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME + " dosyası bulunamamistir.");
		}
		if( um.fileOpenInFolder( KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME ) ) 
		{
			log.error(  KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME + " dosyasi acik.");
			EmailSend.dosyaAcik(   KPIAjanlariHesaplamalariData.BUSSINESS_PATH, KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME,
					new String[]{KPIAjanlariHesaplamalariData.to_8, KPIAjanlariHesaplamalariData.to});
			throw new SkipException("Hata!! " + KPIAjanlariHesaplamalariData.BUSSINESS_PATH + KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME + " dosyasi acik.");
		}
		
		um.copyFilesCommomToRPA( KPIAjanlariHesaplamalariData.COMMON_PATH, KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME );
		um.copyFilesCommomToRPA( KPIAjanlariHesaplamalariData.COMPUTERS_REPORT_PATH,KPIAjanlariHesaplamalariData.COMPUTERS_INPUT_FILE_NAME );

		
		avivasaComputersList = new ArrayList<>();
		avivasaComputersList = excelToModelListImp.getAvivasaComputersModelsList(
				eo.readCSVToList(KPIAjanlariHesaplamalariData.COMMON_PATH, 
						KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME), avivasaComputersModel);
		
		mailTableModel.setAgesaComputers_Size(new DecimalFormat("#0").format(avivasaComputersList.size()));
		log.info("AvivasaComputersList Size: " + mailTableModel.getAgesaComputers_Size());
		
*/
		mailImp = new MailImp(KPIAjanlariHesaplamalariData.mailImapHost, KPIAjanlariHesaplamalariData.mailImapPort, 
				KPIAjanlariHesaplamalariData.mailImapUserName, KPIAjanlariHesaplamalariData.mailImapPassword,
				KPIAjanlariHesaplamalariData.COMMON_PATH);

		if (!mailImp.connect()) {
			log.info("Mail baglantisi sirasinda hata!");
			EmailSend.IMAPBaglantiHatasi(new String[]{KPIAjanlariHesaplamalariData.to});
			onFinish();
			return;
		}
		
		ArrayList<MailModel> mails = mailImp.getInitialFolderMessages();

		
		

		try {
			eo.generateAdayOnerıyorumReportExcel(KPIAjanlariHesaplamalariData.haftalıkrapor);
			log.info("Haftalık Rapor Olusturuldu");
		} catch (Exception e) {
			log.info("Haftalık Rapor Olusturulamadı.");
			throw new SkipException("Haftalık Rapor Olusturulamadı.");
		}

        adayBilgileriList = eo.guncelLıste(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH,KPIAjanlariHesaplamalariData.CISCO_INPUT_RENAME,0);
        adayExcel = eo.populateRaporList(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH,KPIAjanlariHesaplamalariData.CISCO_INPUT_RENAME);
        
		biDunyaActions = new BiDunyaActions();
        biDunyaActions.openUrl(KPIAjanlariHesaplamalariData.urlBıDunya)
        .searchBasvuruIlan(adayBilgileriList)
        .uploadFile(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH);
       
        eo.insertListToDısKaynakExcel(adayBilgileriList, KPIAjanlariHesaplamalariData.haftalıkrapor1,"AdayÖneriyorum");
        
        
        
		mailImp.moveMailListToFolder(mails);
		onFinish();
	}
	
	@AfterSuite
	public void afterSuite() {
		
		try {
			
			um.copyFile(KPIAjanlariHesaplamalariData.COMMON_PATH, um.createDirectory(KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_BACKUP_PATH), KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME);

			EmailSend.sendTablo(mailTableModel.getAgesaComputers_Size(), mailTableModel.getComputers_Size(), mailTableModel.getComputers_Percentage(), 
					mailTableModel.getCisco_Size(), mailTableModel.getCisco_Percentage(), mailTableModel.getDlp_Size(), mailTableModel.getDlp_Percentage(), 
					mailTableModel.getKeepnet_Size(), mailTableModel.getKeepnet_Percentage(), mailTableModel.getNessus_Size(), mailTableModel.getNessus_Percentage(),
					mailTableModel.getSccm_Size(), mailTableModel.getSccm_Percentage(), mailTableModel.getTitus_Size(), mailTableModel.getTitus_Percentage(), 
					mailTableModel.getMcafee_Size(), mailTableModel.getMcafee_Percentage(),
					new String []{KPIAjanlariHesaplamalariData.to_15,KPIAjanlariHesaplamalariData.to_8, KPIAjanlariHesaplamalariData.to_9, KPIAjanlariHesaplamalariData.to_16, 
							KPIAjanlariHesaplamalariData.to_10, KPIAjanlariHesaplamalariData.to_17, KPIAjanlariHesaplamalariData.to_7,
							KPIAjanlariHesaplamalariData.to_11,KPIAjanlariHesaplamalariData.to_12,KPIAjanlariHesaplamalariData.to_13, KPIAjanlariHesaplamalariData.to_18,
							KPIAjanlariHesaplamalariData.to_19, KPIAjanlariHesaplamalariData.to_20, KPIAjanlariHesaplamalariData.to_21,
							KPIAjanlariHesaplamalariData.to_23,KPIAjanlariHesaplamalariData.to_25});
/*		
			EmailSend.sendTablo(mailTableModel.getAgesaComputers_Size(), mailTableModel.getComputers_Size(), mailTableModel.getComputers_Percentage(), 
					mailTableModel.getCisco_Size(), mailTableModel.getCisco_Percentage(), mailTableModel.getDlp_Size(), mailTableModel.getDlp_Percentage(), 
					mailTableModel.getKeepnet_Size(), mailTableModel.getKeepnet_Percentage(), mailTableModel.getNessus_Size(), mailTableModel.getNessus_Percentage(),
					mailTableModel.getSccm_Size(), mailTableModel.getSccm_Percentage(), mailTableModel.getTitus_Size(), mailTableModel.getTitus_Percentage(), 
					mailTableModel.getMcafee_Size(), mailTableModel.getMcafee_Percentage(),
					new String []{KPIAjanlariHesaplamalariData.to_2});
*/
		} catch (Exception e) {
			EmailSend.dosyaIslemleriHatasi(KPIAjanlariHesaplamalariData.AGESA_COMPUTERS_INPUT_FILE_NAME, new String []{KPIAjanlariHesaplamalariData.to});
		}
		
		log.endTestCase("KPI Ajanları Hesaplamaları Süreci - End");
	}
	
	private static void onFinish() {
		mailImp.disConnect();
		
	}
}
