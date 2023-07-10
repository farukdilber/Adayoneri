package com.avivasa.rpa.model;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeUtility;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;

import com.avivasa.rpa.utiliy.EmailSend;
import com.avivasa.rpa.utiliy.UtilityMethods;
import com.avivasa.rpa.utiliy.log;
import com.avivasa.rpa.model.MailModel;

import com.avivasa.rpa.data.ExcelOperations;

import com.avivasa.rpa.data.GetData;
import com.avivasa.rpa.data.KPIAjanlariHesaplamalariData;


public class MailImp {
	
	private Store store;
	private Folder initialFolder;
	private boolean isMailOpened;

	private String host;
	private int port;
	private String userName;
	private String password;
	private String tempFolder;
	
	List<AdayBilgileri> adayBilgileriList1 = new ArrayList<AdayBilgileri>();
	UtilityMethods um = new UtilityMethods();
	ExcelOperations eo = new ExcelOperations();	
	
	public MailImp(String host, int port, String userName, String password, String tempFolder) {
		this.host = host;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.tempFolder = tempFolder;
	}

	public boolean connect() {

		try {
			Properties properties = System.getProperties();
			properties.setProperty("mail.imaps.auth.plain.disable", "true");
			properties.setProperty("mail.imaps.auth.ntlm.disable", "true");
			properties.put("mail.imap.partialfetch", "false");
			properties.put("mail.imap.fetchsize", "1048576");
			properties.put("mail.imaps.partialfetch", "false");
			properties.put("mail.imaps.fetchsize", "1048576");
			properties.put("mail.imaps.ssl.trust", "*");

			System.setProperty("mail.mime.decodetext.strict", "false");
			Session session = Session.getInstance(properties, null);
			store = session.getStore("imaps");
			store.connect(host, port, userName, password);

			initialFolder = store.getFolder(KPIAjanlariHesaplamalariData.mailImapInitialFolder);
			initialFolder.open(Folder.READ_WRITE);
			isMailOpened = true;
		} catch (MessagingException e) {
			isMailOpened = false;
			e.printStackTrace();
		}
		return isMailOpened;
	}

	public void disConnect() {
		try {
			initialFolder.close(true);
			store.close();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		isMailOpened = false;
	}

	public boolean isMailOpened() {
		return isMailOpened;
	}

	public ArrayList<MailModel> getInitialFolderMessages( ) 
	{
		ArrayList<MailModel> mailModels = new ArrayList<>();
		AdayBilgileri aday = new AdayBilgileri();
		Message[] messages = null;
		if (!isMailOpened())
			return mailModels;
		try 
		{
			messages = initialFolder.getMessages();
			String subject = "";
			for (Message message : messages) 
			{
				
				
				MailModel mailModel = new MailModel();
				try {
										  
					mailModel.setProcessResult("");
					mailModel.setMessage(message);
					log.info(mailModel.getMessage().toString());
					mailModel.setSendTime(message.getSentDate());
					mailModel.setSender(((InternetAddress) message.getFrom()[0]).getAddress());
					log.info(mailModel.getSender().toString()); 
					mailModel.setProcessTime(new Date());
					subject = mailModel.getMessage().getSubject().toString().toLowerCase();
                    if (mailModel.getSender().contains("@agesa1")) { 
						
					downloadFile(message, mailModel, KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH);
					eo.guncelLıste(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH,KPIAjanlariHesaplamalariData.CISCO_INPUT_RENAME,0);
						//if(mailModel.getProcessResult().equals("Mail içerisinde attachment bulunamadı."));
						//EmailSend.pdfyadaexceleksik(mailModel.getSender());

					} else if(mailModel.getSender().contains("@agesa1")) {	
						EmailSend.agesaCalisaniHatasi((mailModel.getSender()));
					
					} else if (mailModel.getSender().contains("@agesa")) {
						downloadFile(message, mailModel, KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH);
						 
						adayBilgileriList1 = eo.guncelLıste(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH,KPIAjanlariHesaplamalariData.CISCO_INPUT_RENAME,0);
						

						for (AdayBilgileri value : adayBilgileriList1) {
						    if (value.getBasvuruIlan().contains("Operasyon")) {
						        EmailSend.agesaVeAksigortaCalisaniOlmayanmailGonder(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH, new String[]{EmailSend.io, EmailSend.io_1, EmailSend.io_2});
						        break;
						    } else if (value.getBasvuruIlan().contains("Kaynakları")) {
						        EmailSend.agesaVeAksigortaCalisaniOlmayanmailGonder(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH, new String[]{EmailSend.io, EmailSend.io_1, EmailSend.io_2});
						        break;
						    } else if (value.getBasvuruIlan().contains("Finans")) {
						        EmailSend.agesaVeAksigortaCalisaniOlmayanmailGonder(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH, new String[]{EmailSend.io, EmailSend.io_1, EmailSend.io_2});
						        break;
						    } else if (value.getBasvuruIlan().contains("Genel")) {
						        EmailSend.agesaVeAksigortaCalisaniOlmayanmailGonder(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH, new String[]{EmailSend.io, EmailSend.io_1, EmailSend.io_2});
						        break;
						    } else if (value.getBasvuruIlan().contains("Satış")) {
						        EmailSend.agesaVeAksigortaCalisaniOlmayanmailGonder(KPIAjanlariHesaplamalariData.CISCO_REPORT_PATH, new String[]{EmailSend.io, EmailSend.io_1, EmailSend.io_2});
						        break;
						    }
						}

						log.info(mailModel.getSender() + "  Okunan Mail Aksigorta veya Agesa calisanindan iletilmedi! ");
					}
					
					mailModels.add(mailModel);
					
				} catch (Exception e) 
				{
					mailModel.setProcessResult(KPIAjanlariHesaplamalariData.messageMailReadError);
					EmailSend.dokumanEksikHatası(mailModel.getSender());
					EmailSend.mailIslemleriHatasi(new String[]{KPIAjanlariHesaplamalariData.to});
					e.printStackTrace( );
				}
			}
		} catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		return mailModels;
	}
	
	public List<File> getAttachments(Message message, String filePath, MailModel mailModel) throws Exception 
	{
		ArrayList<File> attachments = new ArrayList<>();
		
	    Object content = message.getContent();
	    if (content instanceof String)
	        return null;        

	    if (content instanceof Multipart) 
	    {
	        Multipart multipart = (Multipart) content;
	        List<InputStream> result = new ArrayList<InputStream>();

	        for (int i = 0; i < multipart.getCount(); i++) 
	        {
	            result.addAll(getAttachments(multipart.getBodyPart(i), filePath, mailModel));
	        }
	        
	        return attachments;

	    }
	    return null;
	}
	

	private List<InputStream> getAttachments( BodyPart part, String filePath, MailModel mailModel) throws Exception 
	{
	    List<InputStream> result = new ArrayList<InputStream>();
	    Object content = part.getContent();
	    InputStream inputStream = null;
	    
	    if(content instanceof InputStream || content instanceof String ) 
	    {
	        if(Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition()) || StringUtils.isNotBlank(part.getFileName())) 
	        {
	            result.add(part.getInputStream());
	            
	            String fileName = MimeUtility.decodeText(part.getFileName());
	            log.info("Attachment file name: " + fileName );
	         
	            if(fileName.toLowerCase().contains("csv") || fileName.toLowerCase().contains("xls") || fileName.toLowerCase().contains("xlsx") || fileName.toLowerCase().contains("pdf"))
	            {	
	            	mailModel.setAttachmentName(fileName);
	            	inputStream = part.getInputStream( );
					File f = new File(filePath  + fileName );
					FileOutputStream fos = new FileOutputStream(f);
					byte[] buf = new byte[4096];
					int bytesRead;
					while ((bytesRead = inputStream.read(buf)) != -1) 
					{
						fos.write(buf, 0, bytesRead);
					}
					fos.close();
	            }
	            
	            return result;
	        } else 
	        {
	            return new ArrayList<InputStream>();
	        }
	    }

	    if(content instanceof Multipart) 
	    {
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) 
            {
                BodyPart bodyPart = multipart.getBodyPart(i);
                result.addAll(getAttachments(bodyPart, filePath, mailModel));
                
            }
	    }
	    return result;
	}
	
	private List<File> saveAttachments(Message message) {
		ArrayList<File> attachments = null;
		Multipart multipart;
		MimeBodyPart  bodyPart;
		try {
			if (message.getContent() instanceof Multipart) {
				multipart = (Multipart) message.getContent();
				attachments = new ArrayList<>();
				for (int i = 0; i < multipart.getCount(); i++) {
					try {
						bodyPart = (MimeBodyPart) multipart.getBodyPart(i);
						
						System.out.println("Content type:" + bodyPart.getContentType() );
					
						String disposition = bodyPart.getDisposition();
						System.out.println( "Des: " +  bodyPart.getDisposition() );
						if (!Part.ATTACHMENT.equalsIgnoreCase(bodyPart.getDisposition())
								&& StringUtils.isBlank(bodyPart.getFileName())) {
							continue; // dealing with attachments only
						}
						
						if (disposition == null) {
							  // Check if plain
							  MimeBodyPart mbp = (MimeBodyPart)bodyPart;
							  if (mbp.isMimeType("text/plain")) {
							    // Handle plain
							  } else {
							    
							  }
							
							}
						
						if(bodyPart.getFileName() == null || bodyPart.getFileName().equals("")) {
							continue;
						}
						InputStream is = bodyPart.getInputStream();
						// -- EDIT -- SECURITY ISSUE --
						// do not do this in production code -- a malicious email can easily contain
						// this filename: "../etc/passwd", or any other path: They can overwrite _ANY_
						// file on the system that this code has write access to!
						String fileName = MimeUtility.decodeText(bodyPart.getFileName());
						File f = new File(tempFolder + (new Date()).getTime() + fileName);
						FileOutputStream fos = new FileOutputStream(f);
						byte[] buf = new byte[4096];
						int bytesRead;
						while ((bytesRead = is.read(buf)) != -1) {
							fos.write(buf, 0, bytesRead);
						}
						fos.close();
						attachments.add(f);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
		}
		return attachments;
	}
	
	
	public void desposition( Message message ) throws MessagingException
	{
		String disposition = message.getDisposition();
	      if (disposition == null){
	        ; // do nothing
	      }else if (disposition.equals(Part.INLINE)) {
	        System.out.println("This part should be displayed inline");
	      } else if (disposition.equals(Part.ATTACHMENT)) {
	        System.out.println("This part is an attachment");
	        String fileName = message.getFileName();
	        System.out.println("The file name of this attachment is " + fileName);
	      }
	      String description = message.getDescription( );
	      if (description != null) {
	        System.out.println("The description of this message is " + description);
	      }
	}
	
	public boolean moveMailListToFolder(ArrayList<MailModel> mailList ) 
	{
		if (!isMailOpened())
			return false;
		if (mailList == null || mailList.size() < 1) 
		{
			return false;
		}
		boolean result = false;
		Folder archiveFolder = null;
		
		try 
		{
			archiveFolder = store.getFolder(KPIAjanlariHesaplamalariData.mailImapArchiveFolder);
			
			archiveFolder.open(Folder.READ_WRITE);
			
			mailList.forEach(item -> {
				try 
				{
					item.getMessage().setFlag(Flags.Flag.SEEN, false);
				} catch (MessagingException e1) {
					
					e1.printStackTrace();
				}
			});
		
			
			Message[] archiveMails = mailList.stream()
					.map(x -> x.getMessage())  // folder maybe will be archive hear ?
					.toArray(Message[]::new);

			initialFolder.copyMessages(archiveMails, archiveFolder);
			try 
			{
				Thread.sleep(5000);
			} catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			
			mailList.forEach(item -> {
				try 
				{
					item.getMessage().setFlag(Flags.Flag.DELETED, true);
				} catch (MessagingException e) 
				{
					item.setProcessResult(KPIAjanlariHesaplamalariData.messageDeleteMovedMailError);
					e.printStackTrace();
				}
			});
			
			initialFolder.expunge();
			result = true;
			
			log.info( mailList.size() + " mails was moved to archive mail box folder ");
			
		} catch (MessagingException e) 
		{
			mailList.forEach(item -> item.setProcessResult(KPIAjanlariHesaplamalariData.messageMoveMailGeneralError));
			EmailSend.mailTasimaHatasi(new String[]{KPIAjanlariHesaplamalariData.to});
			e.printStackTrace();

		} finally 
		{

			try {
				if (archiveFolder != null && archiveFolder.isOpen( ) ) 
				{
					archiveFolder.close( true );
				}
				
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	public void downloadFile(Message message, MailModel mailModel, String filePath)	{
		
		List<File> attachments;
		
		try {
			attachments = getAttachments( message, filePath, mailModel);
			if (attachments != null && attachments.size() > 1) 
			{
				mailModel.setFileList(attachments);
			} else 
			{
				mailModel.setProcessResult(KPIAjanlariHesaplamalariData.messageNoAttachment);
			}	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}

