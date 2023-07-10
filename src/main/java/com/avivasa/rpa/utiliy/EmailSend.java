package com.avivasa.rpa.utiliy;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.hibernate.id.TableHiLoGenerator;

import com.avivasa.rpa.base.AbstractTest.AutomationVariables;
import com.avivasa.rpa.util.DateUtil;

public class EmailSend
{
	// from Mail Address
	
	static String from = "ahmetfaruk.dilber@agesa.com.tr";
	static String cc = "ahmetfaruk.dilber@agesa.com.tr";
	static String cc1 = "ahmetfaruk.dilber@agesa.com.tr";
	static String cc2 = "ahmetfaruk.dilber@agesa.com.tr";
	static String to = "ahmetfaruk.dilber@agesa.com.tr";
	
	//İŞORTAKLARI MAİLLERİ
	public static final  String io = "ahmetfaruk.dilber@agesa.com.tr";
	public static final  String io_1 = "ahmetfaruk.dilber@agesa.com.tr";
//	public static final  String io_1 = "Aylin.Uzun@agesa.com.tr";
	public static final  String io_2 = "ahmetfaruk.dilber@agesa.com.tr";
//	public static final  String io_1 = "elcin.sahin@agesa.com.tr";
//	public static final  String io_2 = "edaaycan.polat@agesa.com.tr";
		
	private EmailSend( )
	{
		throw new IllegalStateException("Utility class");
	}
		
	public static void sendTablo(String agesaSize, String computersSize, String computersPercent, String ciscoSize,  String ciscoPercent, 
			String dlpSize, String dlpPercent, String keepnetSize, String keepnetPercent, String nessusSize, String nessusPercent, String sccmSize, 
			String sccmPercent, String titusSize, String titusPercent, String mcafeeSize, String mcafeePercent, String... mailTo)	{
		
		if(computersSize == null ) 
			computersSize = "Veri yok";
		
		if(computersPercent == null ) 
			computersPercent = "Veri yok";
		
		if(ciscoSize == null ) 
			ciscoSize = "Veri yok";
		
		if(ciscoPercent == null ) 
			ciscoPercent = "Veri yok";
		
		if(dlpSize == null ) 
			dlpSize = "Veri yok";
		
		if(dlpPercent == null ) 
			dlpPercent = "Veri yok";
		
		if(keepnetSize == null ) 
			keepnetSize = "Veri yok";
		
		if(keepnetPercent == null ) 
			keepnetPercent = "Veri yok";
		
		if(nessusSize == null ) 
			nessusSize = "Veri yok";
		
		if(nessusPercent == null ) 
			nessusPercent = "Veri yok";
		
		if(sccmSize == null ) 
			sccmSize = "Veri yok";
		
		if(sccmPercent == null ) 
			sccmPercent = "Veri yok";
		
		if(titusSize == null ) 
			titusSize = "Veri yok";
		
		if(titusPercent == null ) 
			titusPercent = "Veri yok";
		
		if(mcafeeSize == null ) 
			mcafeeSize = "Veri yok";
		
		if(mcafeePercent == null ) 
			mcafeePercent = "Veri yok";
		
		String systemDate = UtilityMethods.getDateFormat();
		
		String htmlTable = "<style type=\"text/css\">" + 
				".tg  {border-collapse:collapse;border-spacing:0;}" + 
				".tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;" + 
				"  overflow:hidden;padding:5px 5px;word-break:normal;}" + 
				".tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;" + 
				"  font-weight:normal;overflow:hidden;padding:5px 5px;word-break:normal;}" + 
				".tg .tg-i231{background-color:#D9D9D9;border-color:inherit;font-weight:bold;text-align:center;vertical-align:middle}" + 
				".tg .tg-z3i6{background-color:#A6A6A6;border-color:inherit;text-align:center;vertical-align:middle}" + 
				".tg .tg-i2m8{background-color:#BFBFBF;border-color:inherit;font-weight:bold;text-align:center;vertical-align:middle}" + 
				".tg .tg-o1kf{background-color:#BFBFBF;border-color:inherit;text-align:center;vertical-align:middle}" + 
				".tg .tg-yuab{background-color:#A6A6A6;border-color:inherit;font-weight:bold;text-align:center;vertical-align:middle}" + 
				"</style>" + 
				"<table class=\"tg\" style=\"undefined;table-layout: fixed;\">" + 
				"<colgroup>" + 
				"<col style=\"width: 100px\">" + 
				"<col style=\"width: 230px\">" + 
				"<col style=\"width: 150px\">" + 
				"<col style=\"width: 180px\">" + 
				"</colgroup>" + 
				"<thead>" + 
				"  <tr>" + 
				"    <th class=\"tg-i231\" rowspan=\"2\" style=\"width: 100px\">#</th>" + 
				"    <th class=\"tg-i231\" colspan=\"3\">"   + systemDate +   "</th>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <th class=\"tg-i231\" style=\"width: 230px\">Objeler </th>" + 
				"    <th class=\"tg-i231\" style=\"width: 150px\">Adet</th>" + 
				"    <th class=\"tg-i231\" style=\"width: 180px\">Oran</th>" + 
				"  </tr>" + 
				"</thead>" + 
				"<tbody>" + 
				"  <tr>" + 
				"    <td class=\"tg-i2m8\">1</td>" + 
				"    <td class=\"tg-o1kf\">Agesa Computers</td>" + 
				"    <td class=\"tg-o1kf\">" + agesaSize + "</td>" + 
				"    <td class=\"tg-o1kf\">N/A</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-yuab\">2</td>" + 
				"    <td class=\"tg-z3i6\">Computers</td>" + 
				"    <td class=\"tg-z3i6\">" + computersSize + "</td>" + 
				"    <td class=\"tg-z3i6\">" + ((!computersPercent.contains("Veri") && !computersPercent.contains("Döküman")) ? "%" : "") + computersPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-i2m8\">3</td>" + 
				"    <td class=\"tg-o1kf\">Cisco Agent</td>" + 
				"    <td class=\"tg-o1kf\">" + ciscoSize + "</td>" + 
				"    <td class=\"tg-o1kf\">" + ((!ciscoPercent.contains("Veri") && !ciscoPercent.contains("Döküman")) ? "%" : "") + ciscoPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-yuab\">4</td>" + 
				"    <td class=\"tg-z3i6\">DLP Agent</td>" + 
				"    <td class=\"tg-z3i6\">" + dlpSize + "</td>" + 
				"    <td class=\"tg-z3i6\">" + ((!dlpPercent.contains("Veri") && !dlpPercent.contains("Döküman")) ? "%" : "") + dlpPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-i2m8\">5</td>" + 
				"    <td class=\"tg-o1kf\">KeepNet Agent</td>" + 
				"    <td class=\"tg-o1kf\">" + keepnetSize + "</td>" + 
				"    <td class=\"tg-o1kf\">" + ((!keepnetPercent.contains("Veri") && !keepnetPercent.contains("Döküman")) ? "%" : "") + keepnetPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-yuab\">6</td>" + 
				"    <td class=\"tg-z3i6\">Nessus Agent</td>" + 
				"    <td class=\"tg-z3i6\">" + nessusSize + "</td>" + 
				"    <td class=\"tg-z3i6\">" + ((!nessusPercent.contains("Veri") && !nessusPercent.contains("Döküman")) ? "%" : "") + nessusPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-i2m8\">7</td>" + 
				"    <td class=\"tg-o1kf\">SCCM Agent</td>" + 
				"    <td class=\"tg-o1kf\">" + sccmSize + "</td>" + 
				"    <td class=\"tg-o1kf\">" + ((!sccmPercent.contains("Veri") && !sccmPercent.contains("Döküman")) ? "%" : "") + sccmPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-yuab\">8</td>" + 
				"    <td class=\"tg-z3i6\">Titus Agent</td>" + 
				"    <td class=\"tg-z3i6\">" + titusSize + "</td>" + 
				"    <td class=\"tg-z3i6\">" + ((!titusPercent.contains("Veri") && !titusPercent.contains("Döküman")) ? "%" : "") + titusPercent + "</td>" + 
				"  </tr>" + 
				"  <tr>" + 
				"    <td class=\"tg-i2m8\">9</td>" + 
				"    <td class=\"tg-o1kf\">Mcafee Agent</td>" + 
				"    <td class=\"tg-o1kf\">" + mcafeeSize + "</td>" + 
				"    <td class=\"tg-o1kf\">" + ((!mcafeePercent.contains("Veri") && !mcafeePercent.contains("Döküman")) ? "%" : "") + mcafeePercent + "</td>" + 
				"  </tr>" + 
				"</tbody>" + 
				"</table>";
		
		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci Haftalik Tablo", 
				"Sayin Yetkili," + "<br><br>" +
				"TechBot KPI Ajanları Hesaplamaları Sureci raporuna ait ozet tablo asagida bilgilerinize sunulmustur." + "<br><br>" +				  
				htmlTable +
				"<br><br>Bu e-posta TechBot tarafından otomatize edilip tarafınıza gönderilmiştir.", 
				"", "", "", mailTo);
	}
	public static void dosyaBulunamadi(String filePath, String fileName, String... mailTo) {	
		
		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
				"Sayın Yetkili," + "<br><br>" 
				+ filePath + fileName + " isimli dosya bulunamamıştır." + "<br><br><br>" 
				+ "Bu e-posta TechBot tarafından otomatize edilip tarafınıza gönderilmiştir.", "", fileName, "", mailTo);			
		
		log.info("############## dosyaBulunamadi hata bildirimi maili gönderildi. Ek : " + filePath + fileName + "  ##############");

	}
	
	public static void agesaVeAksigortaCalisaniOlmayanmailGonder(String folderPath, String... mailTo ) {
		agesaVeAksigortaCalisaniOlmayanmailGonder("TechBot - Aday Öneriyorum Süreci - Agesa ve Aksigorta  Calisani Olmayan Geri Bildirimi",
				"Ilgili mail dis kaynaktan gelmistir," + "<br><br>" +
				"Aday öneri icin isortaklarina iletildi ..." + "<br><br>" 
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.",mailTo, folderPath);
		
	}
	

	
	
	public static void agesaCalisaniHatasi(String mailTo) {

		mailGonderAday("TechBot - Aday Öneriyorum Süreci - Agesa Calisani Geri Bildirimi",
				"Merhaba," + "<br><br>" +
				"Bu mail adresini sadece Aksigorta'li arkadaslarimizin aday önerilerini almak için kullaniyoruz." + "<br><br>" +
				" Aday önerini iletmek istersen asagidaki linki kullanabilirsin." + "<br><br>" +
				"Sevgilerimizle," + "<br><br>" +
				"Insan Kaynaklari ve Sürdürülebilirlik" + "<br><br>" + "<b><b>" + 
				"https://bidunya.agesa.com.tr/kariyer-firsatlari/Sayfalar/default.aspx" + "<br><br>"
				+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.",mailTo);
	}
	public static void dosyaAcik( String path, String fileName, String... mailTo ) 
	{
		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
				"Sayın Yetkili," + "<br><br>" 
					+ fileName + " isimli dosyanın açık olmaması gerekiyor. Lütfen dosyayı kapatınız." + "<br><br>"
					+ "Dosya İsmi: " + fileName + "<br><br>" 
					+ "Dosya Yolu: " + path + fileName + "<br><br><br>"
				+ "Bu e-posta TechBot tarafından otomatize edilip tarafınıza gönderilmiştir.", "", "", "", mailTo);
		
		log.info("dosyaAcik hata bildirimi maili gönderildi. Ek : " + fileName);
	}
	
	public static void mailBulunamadı( String keyword, String... mailTo ) 
	{
		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
				"Sayın Yetkili," + "<br><br>" 
					+ keyword + "  anahtar kelimesine sahip mail bulunamadı." + "<br><br><br>"
				+ "Bu e-posta TechBot tarafından otomatize edilip tarafınıza gönderilmiştir.", "", "", "", mailTo);
		
		log.info("mailBulunamadı hata bildirimi maili gönderildi. Anahtar Kelime : " + keyword);
	}
	
	public static void IMAPBaglantiHatasi(String... mailTo) {

		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
				"Sayın Yetkili," + "<br><br>" +
				"Mail baglantisi sirasinda hata alindi.!" + "<br><br>" 
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.", "", "", "", mailTo);
	}
	public static void mailTasimaHatasi(String... mailTo) {

		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
				"Sayın Yetkili," + "<br><br>" +
				"Mail Taşima sirasinda hata alindi.!"
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.", "", "", "", mailTo);
	}
	public static void dokumanEksikHatası(String mailTo) {

		mailGonder("TechBot - Aday Öneriyorum Süreci - Dokuman Eksık Geri Bildirimi",
				"Merhaba," + "<br><br>" +
				"Aday öneri sistemimizin sorunsuz çalışabilmesi için ekteki 'aday öneri formunu' eksiksiz doldurup adayın özgeçmişini de bu maile eklemeni bekliyoruz." + "<br><br>" +
				"Sevgilerimizle," + "<br><br>" +
				"Insan Kaynaklari ve Sürdürülebilirlik" + "<br><br>" + "<b><b>"  
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.", "", "", "", mailTo);
	}
		
	public static void pdfyadaexceleksik(String mailTo) {

		mailGonderAday("TechBot - Aday Öneriyorum Süreci - Dokuman Eksik Geri Bildirimi",
				"Merhaba," + "<br><br>" +
				"Göndermis oldugunuz aday öneri mailinde CV yada Aday öneri formu eksik. Lütfen eksik belgeyi tamamlayip maili yeniden göndermelisiniz." + "<br><br>" +
				"Sevgilerimizle," + "<br><br>" +
				"Insan Kaynaklari ve Sürdürülebilirlik" + "<br><br>" + "<b><b>"  
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.", "", "", "", mailTo);
	}
	
	private static void mailGonderAday(String subject, String body, String string3, String string4, String string5,
			String mailTo) {

		// mail host bilgisi
		Properties props = new Properties();
		props.put("mail.smtp.host", "192.168.239.251");
		Session session = Session.getDefaultInstance(props, null);

		// mail içeriği
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from)); //from techbotdestek olacak
			msg.setSubject(subject);

			{
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

			}
		
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(multipart);
			Transport.send(msg);
			log.info("Mail gönderildi");
		} catch (Exception e) {
			log.error("Mail gönderilemedi. : " + e.toString());
		}
	}
		
	

	public static void mailIslemleriHatasi(String... mailTo) {

		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
				"Mail işlemleri sirasinda hata alindi.." 
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.", "", "", "", mailTo);
	}
	
	public static void dosyaIslemleriHatasi(String fileName, String... mailTo) {

		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Hata Bildirimi",
			"Dosya işlemleri sirasinda hata alindi.. : " + fileName 	
						+ "<br><br><br>" + "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.", "", "", "", mailTo);
	}
	
	public static void sonucRaporu(String filePath, String excelFileName, String... mailTo) {
		mailGonder("TechBot - KPI Ajanlari Hesaplamalari Süreci - Sonuc Raporu - " + excelFileName,
				"Sayin Yetkili," + "<br><br>"
						+ "TechBot - KPI Ajanlari Hesaplamalari Süreci sonuc raporu dosyasina ait sonuclar ekte bilgilerinize sunulmustur." + "<br><br>"
						+ excelFileName + " <br><br><br>"
						+ "Bu e-posta TechBot tarafindan otomatize edilip tarafiniza gönderilmistir.",
				"sonuc", filePath, excelFileName, mailTo);
		log.info("############## Onay maili gönderildi. Ek : " + excelFileName + "  ##############");
	}
	public static void agesaVeAksigortaCalisaniOlmayanmailGonder1(String subject, String body, String... mailTo) {

		// mail host bilgisi
		Properties props = new Properties();
		props.put("mail.smtp.host", "192.168.239.251");
		Session session = Session.getDefaultInstance(props, null);

		// mail içeriği
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);

			for (int i = 0; i < mailTo.length; i++)
			{
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress( mailTo[ i ] ) );
			}
		
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(multipart);
			Transport.send(msg);
			log.info("Mail gönderildi");
		} catch (Exception e) {
			log.error("Mail gönderilemedi. : " + e.toString());
		}
	}
	
	public static void agesaVeAksigortaCalisaniOlmayanmailGonder(String subject, String body, String[] mailTo, String folderPath) {

        // mail host bilgisi
        Properties props = new Properties();
        props.put("mail.smtp.host", "192.168.239.251");
        Session session = Session.getDefaultInstance(props, null);

        // mail içeriği
        Message msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(from));
            msg.setSubject(subject);

            for (String recipient : mailTo) {
                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            }

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(body, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Add attachments from the folder
            File folder = new File(folderPath);
            if (folder.exists() && folder.isDirectory()) {
                File[] files = folder.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
                            DataSource source = new FileDataSource(file.getAbsolutePath());
                            attachmentBodyPart.setDataHandler(new DataHandler(source));
                            attachmentBodyPart.setFileName(file.getName());
                            multipart.addBodyPart(attachmentBodyPart);
                        }
                    }
                }
            }

            msg.setContent(multipart);
            Transport.send(msg);
            log.info("Mail gönderildi");
        } catch (Exception e) {
            log.error("Mail gönderilemedi: " + e.toString());
        }
    }

	
	
	
	
	
	
	
	
	private static void mailGonderAday(String subject, String body, String mailTo) {

		// mail host bilgisi
		Properties props = new Properties();
		props.put("mail.smtp.host", "192.168.239.251");
		Session session = Session.getDefaultInstance(props, null);

		// mail içeriği
		Message msg = new MimeMessage(session);
		try {
			msg.setFrom(new InternetAddress(from)); //from techbotdestek olacak
			msg.setSubject(subject);

			{
				msg.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

			}
		
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			msg.setContent(multipart);
			Transport.send(msg);
			log.info("Mail gönderildi");
		} catch (Exception e) {
			log.error("Mail gönderilemedi. : " + e.toString());
		}
	}
	private static void mailGonder( String subject, String body, String flag, String filePath, String fname, String... mailTo )
	{
		if( !AutomationVariables.SEND_MAIL )
			return;
		
		// mail host bilgisi
		Properties props = new Properties();
		props.put("mail.smtp.host", "192.168.239.251");
		Session session = Session.getDefaultInstance(props, null);

		// mail içeriği
		Message msg = new MimeMessage(session);
		try
		{
			msg.setFrom(new InternetAddress(from));
			msg.setSubject(subject);
			
			for (int i = 0; i < mailTo.length; i++)
			{
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress( mailTo[ i ] ) );
			}

			msg.setRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc1));
			msg.addRecipient(Message.RecipientType.CC, new InternetAddress(cc2));
			if( AutomationVariables.EMAILS != null )
			{
				msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				for (String mails : AutomationVariables.EMAILS)
					msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mails));
			}
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body, "text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			if (flag.equals("sonuc")) {

				messageBodyPart = new MimeBodyPart();
				String fileName = fname;
				DataSource source = new FileDataSource(filePath + fileName);
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(fileName);
				multipart.addBodyPart(messageBodyPart);
			}

			msg.setContent( multipart );
			Transport.send( msg );
			
			log.info("Mail gönderildi");
			
		}catch (Exception e)
		{
			log.error("Mail gönderilemedi. : " + e.toString());
		}
	}
}