package com.avivasa.rpa.data;

import java.io.File;

import com.avivasa.rpa.util.DataFinder;

@com.mongodb.annotations.ThreadSafe
@javax.annotation.concurrent.ThreadSafe
@net.jcip.annotations.ThreadSafe
public class GetData
{
	public static String FILESYSDATE;
	public static final int IS_ELEM_EXIST_TIME = 60; // as seconds
	public static final int IS_ELEM_NOT_EXIST_TIME = 2; // as seconds
	public static final int DEFAULT_WAIT = 60;
	public static final int DEFAULT_WAIT_LOADERBOX = 90;
	public static final String TEMP_FILE_PATH = File.separator + File.separator + "10.10.15.240" + File.separator
			+ "Bilgi_Teknolojileri" + File.separator + "BT Ortak" + File.separator + "TestOtomasyonRaporlari"
			+ File.separator;
	public static final String RESULT_FILE_PATH = "C:" + File.separator + "TestOtomasyonRaporlari" + File.separator;

	// URL("http://192.168.149.121:4444/wd/hub")//192.168.238.61 10.2.17.113
	public static final String REMOTE_MACHINE_IP = "http://192.168.238.61:4444/wd/hub";

	public enum Url
	{
		KIS_OPERASYON_URL, KIS_KURUMIK_URL, MINIBIS_URL, AS400_URL, ROP_URL, N11_URL;
	}

	public enum Data
	{
		GRUP_NO, GRUP_ADI_NO, SOZLEME_GRUP_NO, GRUP_ADI_NO2,

		UNVAN, VERGI_DAIRESI, SATAN_AD_SOYAD, SATAN_ACENTE, MBB_NO, GRUP_FON_TERCIHI_MBB_NO, GRUP_ODEME_GUNU,
		GIRIS_TARIHI, YETKILI_KISI_AD, YETKILI_KISI_SOYAD, YETKILI_KISI_TEL,

		YETKILI_KISI_TEL_GUNCELLEME, YETKILIKISIEMAIL, YETKILIKISIEMAILDOMAIN, ADRES, ADRES2, ADRES3, ILCE, POSTA_KODU,
		IL, TEL_NO, FAX, EMAIL_ADRESS, EMAIL_DOMAIN, CALISAN_SAYISI, KATKI_TUTARI;

		public String getValue( )
		{
			return DataFinder.getData( this );
		}
	}

	public enum LoginInfo
	{
		USERNAME, USERNAME_ONAY, PASSWORD,

		KURUMIK_USERNAME, KURUMIK_PASSWORD, KURUMIK_VKN,

		AS400_USERID, AS400_SIFRE, AS400_USERID_EKRAN, AS400_SIFRE_EKRAN;

		public String getValue( )
		{
			return DataFinder.getLoginInfo( this );
		}
	}

	public enum ParolamıUnuttum
	{
		PU_EMAIL, PU_VKN, PU_CEPTEL;

		public String getValue( )
		{
			return DataFinder.getValue( this.name( ) );
		}
	}

	public enum RopOrganizasyonelBilgileri
	{
		ROP_USERNAME1, ROP_USERNAME2, ROP_USERNAME3;

		public String getValue( )
		{
			return DataFinder.getValue( this.name( ) );
		}
	}

	// Yüklenen dosyaların yolu
	public static final String DOWNLOAD_FILE_PATH = TEMP_FILE_PATH + "Test Data" + File.separator + "Download";
	public static final String TAHSILAT_FILE_PATH = TEMP_FILE_PATH + "Test Data" + File.separator
			+ "TESTOTOMASYON_tahsilat.xls";
	public static final String TAHSILAT_TEMP_FILE_PATH = TEMP_FILE_PATH + "Test Data" + File.separator
			+ "TESTOTOMASYON_tahsilatTemp.xls";
	public static final String MUSTERI_BILGILERI_FILE_PATH = TEMP_FILE_PATH + "Test Data" + File.separator
			+ "Musteri_Bilgileri_Guncelleme.xls";
	public static final String BASVURU_TOPLU_AKTARIM_PATH = TEMP_FILE_PATH + "Test Data" + File.separator
			+ "TESTOTOMASYON_Basvuru_Toplu_Aktarim.xls";
	public static final String SCREENSHOTS_PATH = RESULT_FILE_PATH + "TestOtomasyonRaporlari" + File.separator
			+ "Test Error ScreenShots" + File.separator;

}
