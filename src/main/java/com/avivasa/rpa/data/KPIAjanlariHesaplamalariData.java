package com.avivasa.rpa.data;

import org.openqa.selenium.By;

public class KPIAjanlariHesaplamalariData
{
	//PATH
	public static final String BUSSINESS_PATH = "\\\\10.10.15.240\\Bilgi_Teknolojileri\\TechbotBG\\AgentKPI\\";
	
	public static final String COMMON_PATH = "C:\\RPA\\DataFile\\KPIAjanlariHesaplamalari\\";
	public static final String filePath = "C:\\RPA\\DataFile\\KPIAjanlariHesaplamalari\\"; // excelin indiği dosya alanı değişirilicek
	//Agents Credentials
	public static final String AGESA_COMPUTERS_INPUT_FILE_NAME = "AgesaComputers.csv";
	public static final String AGESA_COMPUTERS_BACKUP_PATH = COMMON_PATH + "Backup\\Agesa Computers\\";
	
	public static final By txtInputFile = By.xpath( "//input[@name='File']" ) ;
	public static final By btnUploadTamam = By.xpath( "//button[@type='button']" ) ;
	
	public static final String COMPUTERS_BACKUP_PATH = COMMON_PATH + "Backup\\Computers\\";
	public static final String COMPUTERS_REPORT_PATH = COMMON_PATH + "Computers\\";
	public static final String COMPUTERS_INPUT_FILE_NAME = "Computers.csv";
	public static final String COMPUTERS_REPORT_FILE_NAME = "Computers_Agent_Report.xlsx";
	public static final String COMPUTERS_FILTER_VALUE = "TSL";
	public static final int COMPUTERS_FILTER_INDEX = 0;
	public static final String haftalıkrapor = "Aday Öneriyorum Haftalık Rapor.xlsx";
	public static final String haftalıkrapor1 = filePath + "Aday Öneriyorum Haftalık Rapor.xlsx";
	
	public static final String CISCO_BACKUP_PATH = COMMON_PATH + "Backup\\Cisco\\";
	public static final String CISCO_REPORT_PATH = COMMON_PATH + "Cisco\\";
	public static final String CISCO_INPUT_RENAME= "Aday Öneriyorum Başvuru Formu_v1.xlsx";
	public static final String ADAY_INPUT_FILE_NAME = "Av.Beyza KAMAŞIK CV.pdf";
	public static final String CISCO_INPUT_FILE_NAME = "Cisco_Agent_List.csv";
	public static final String CISCO_TEMP_INPUT_FILE_NAME = "Cisco_Agent_List.xlsx";
	public static final String CISCO_REPORT_FILE_NAME = "Cisco_Agent_Report.xlsx";
	public static final String CISCO_KEYWORD = "aday";
	public static final int CISCO_FILTER_INDEX = 6;
	
	public static final String DLP_AGENT_BACKUP_PATH =  COMMON_PATH + "Backup\\DLPAgent\\";
	public static final String DLP_AGENT_REPORT_PATH =  COMMON_PATH + "DLPAgent\\";
	public static final String DLP_AGENT_INPUT_RENAME = "DLP_Agent_List";
	public static final String DLP_AGENT_INPUT_FILE_NAME = "DLP_Agent_List.csv";
	public static final String DLP_AGENT_TEMP_INPUT_FILE_NAME = "DLP_Agent_List.xlsx";
	public static final String DLP_AGENT_REPORT_FILE_NAME = "DLP_Agent_Report.xlsx";
	public static final String DLP_AGENT_KEYWORD = "dlp";
	public static final int DLP_AGENT_FILTER_INDEX = 10;
	
	public static final String KEEPNET_BACKUP_PATH =  COMMON_PATH + "Backup\\KeepNet\\";
	public static final String KEEPNET_REPORT_PATH =  COMMON_PATH + "KeepNet\\";
	public static final String KEEPNET_INPUT_RENAME = "Keepnet_Agent_List";
	public static final String KEEPNET_INPUT_FILE_NAME = "Keepnet_Agent_List.xlsx";
	public static final String KEEPNET_REPORT_FILE_NAME = "Keepnet_Agent_Report.xlsx";
	public static final String KEEPNET_KEYWORD = "keepnet";
	public static final int KEEPNET_FILTER_INDEX = 4; //0
	
	public static final String NESSUS_BACKUP_PATH =  COMMON_PATH + "Backup\\Nessus\\";
	public static final String NESSUS_REPORT_PATH =  COMMON_PATH + "Nessus\\";
	public static final String NESSUS_INPUT_RENAME = "Nessus_Agent_List";
	public static final String NESSUS_TEMP_INPUT_FILE_NAME = "Nessus_Agent_List.xlsx";
	public static final String NESSUS_INPUT_FILE_NAME = "Nessus_Agent_List.csv";
	public static final String NESSUS_REPORT_FILE_NAME = "Nessus_Agent_Report.xlsx";
	public static final String NESSUS_KEYWORD = "nessus";
	public static final int NESSUS_FILTER_INDEX = 5;
	
	public static final String SCCM_CLIENT_BACKUP_PATH =  COMMON_PATH + "Backup\\SCCMClient\\";
	public static final String SCCM_CLIENT_REPORT_PATH =  COMMON_PATH + "SCCMClient\\";
	public static final String SCCM_CLIENT_INPUT_RENAME = "SCCM_Client_Agent_List";
	public static final String SCCM_CLIENT_INPUT_FILE_NAME = "SCCM_Client_Agent_List.xlsx";
	public static final String SCCM_CLIENT_REPORT_FILE_NAME = "SCCM_Client_Agent_Report.xlsx";
	public static final String SCCMClient_KEYWORD = "sccm";
	public static final String SCCM_CLIENT_FILTER_VALUE = "YES";
	public static final String SCCM_CLIENT_FILTER_VALUE_EXTRA = "Active";
	
	public static final String TITUS_BACKUP_PATH =  COMMON_PATH + "Backup\\Titus\\";
	public static final String TITUS_REPORT_PATH =  COMMON_PATH + "Titus\\";
	public static final String TITUS_INPUT_FILE_NAME = "Titus_Agent_List.xlsx";
	public static final String TITUS_INPUT_RENAME = "Titus_Agent_List";
	public static final String TITUS_REPORT_FILE_NAME = "Titus_Agent_Report.xlsx";
	public static final String TITUS_KEYWORD = "titus";
	public static final int TITUS_FILTER_INDEX = 2;
	
	public static final String MCAFEE_BACKUP_PATH =  COMMON_PATH + "Backup\\MCafee\\";
	public static final String MCAFEE_REPORT_PATH =  COMMON_PATH + "MCafee\\";
	public static final String MCAFEE_INPUT_RENAME = "MCafee_Agent_List";
	public static final String MCAFEE_INPUT_FILE_NAME = "MCafee_Agent_List.csv";
	public static final String MCAFEE_TEMP_INPUT_FILE_NAME = "MCafee_Agent_List.xlsx";
	public static final String MCAFEE_REPORT_FILE_NAME = "MCafee_Agent_Report.xlsx";
	public static final String MCAFEE_KEYWORD = "mcafee";
	public static final int MCAFEE_FILTER_INDEX = 0;
	
	//Mail To
	public static final String to = "Techbot_Destek@agesa.com.tr";
	public static final String to_1 = "cem.bozkurt@sabancidx.com";
	public static final String to_2 = "irfan.ak@agesa.com.tr";
	public static final String to_3 = "Noyan.Altayli@sabancidx.com";
	public static final String to_4 = "b.cakmak@sabancidx.com";
	public static final String to_5 = "sahzen.alver@agesa.com.tr";
	public static final String to_7 = "Can.Turker@sabancidx.com";
	public static final String to_8 = "Zeki.Ulucan@agesa.com.tr";
	public static final String to_9 = "halil.cagdas.darakci@agesa.com.tr";
	public static final String to_10 = "mevlut.erdogan@sabancidx.com";
	public static final String to_11 = "Ayhan.Karaman@sabancidx.com";
	public static final String to_12 = "Ugur.Candir@agesa.com.tr";
	public static final String to_13 = "Halil.Topcu@agesa.com.tr";
	public static final String to_14 = "ali.kaplan@agesa.com.tr";
	public static final String to_15 = "Devrim.Zimba@agesa.com.tr";
	public static final String to_16 = "nihan.cengiz@agesa.com.tr";
	public static final String to_17 = "BilgiGuvenligi@agesa.com.tr";
	public static final String to_18 = "umut.arica@sabancidx.com";
	public static final String to_19 = "gercek.karaman@sabancidx.com";
	public static final String to_20 = "tayfun.kapucuoglu@sabancidx.com";
	public static final String to_21 = "levent.polat@sabancidx.com";
	public static final String to_22 = "SabanciDx_Netsec@sabancidx.com";
	public static final String to_23 = "Olkan.Dogan@sabancidx.com";
	public static final String to_24 = "Dincer.Yilmazel@agesa.com.tr";
	public static final String to_25 = "ebru.yıldız@sabancidx.com";
	public static final By hamburgerMenu = By.xpath("//span[@class=\"icon-policy-app-menu\" and @id=\"1\"]");
	public static final By menuKariyerFırsatları = By.xpath("menu-item-text");
	
	
	public static String mailImapHost = "10.110.24.27";
	public static int mailImapPort = 993;
	public static String mailImapUserPattern = "Avivasa\\%s\\";
	public static String mailImapUserName = "techbot.btguvenlik@agesa.com.tr";
	public static String mailImapPassword = "Tu1509Tu";


	public static String mailImapInitialFolder = "Inbox/farukdeneme";
	public static String mailImapArchiveFolder = "Inbox/farukdeneme/arşiv";
	public static String messageMailReadError = "Mail okuma sırasında genel bir hata oluştu.";
	public static String messageNoAttachment = "Mail içerisinde attachment bulunamadı.";
	public static String messageDeleteMovedMailError = "Mail arşiv folder'a taşınmasınmasına rağmen eski folder'da kopyası kaldı. Elle silinmesi gerekiyor.";
	public static String messageMoveMailGeneralError = "Mail işlenmesine rağmen arşiv klasörüne taşınması sırasında hata alındı.";	
	
	public static String urlBıDunya = "https://bidunya.agesa.com.tr/kariyer-firsatlari/Sayfalar/default.aspx"; // /kariyer-firsatlari/Sayfalar/default.aspx
	public static final int MONTHLY_REPORT_START_INDEX = 8;
	
	public static final int DAILY_REPORT_START_INDEX = 4;

	public static final int MONTHLY_REPORT_START_YEAR = 2019;
	
	public static final int MONTHLY_REPORT_START_MONTH = 4;

}


