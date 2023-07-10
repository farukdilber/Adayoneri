package com.avivasa.rpa.model;

public class ExcelModel {
	private String başvurulanIlan;
	private String adSoyad;
	private String Email;
	private String dogumTarihi;
	private String egitimBilgileri;
	private String yabancıDil;
	private String medeniDurum;
	private String askerlikDurumu;
	private String ikametgahAdresi;
	private String onYazı;
	public String getBaşvurulanIlan() {
		return başvurulanIlan;
	}
	public void setBaşvurulanIlan(String başvurulanIlan) {
		this.başvurulanIlan = başvurulanIlan;
	}
	public String getAdSoyad() {
		return adSoyad;
	}
	public void setAdSoyad(String adSoyad) {
		this.adSoyad = adSoyad;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getDogumTarihi() {
		return dogumTarihi;
	}
	public void setDogumTarihi(String dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	public String getEgitimBilgileri() {
		return egitimBilgileri;
	}
	public void setEgitimBilgileri(String egitimBilgileri) {
		this.egitimBilgileri = egitimBilgileri;
	}
	public String getYabancıDil() {
		return yabancıDil;
	}
	public void setYabancıDil(String yabancıDil) {
		this.yabancıDil = yabancıDil;
	}
	public String getMedeniDurum() {
		return medeniDurum;
	}
	public void setMedeniDurum(String medeniDurum) {
		this.medeniDurum = medeniDurum;
	}
	public String getAskerlikDurumu() {
		return askerlikDurumu;
	}
	public void setAskerlikDurumu(String askerlikDurumu) {
		this.askerlikDurumu = askerlikDurumu;
	}
	public String getIkametgahAdresi() {
		return ikametgahAdresi;
	}
	public void setIkametgahAdresi(String ikametgahAdresi) {
		this.ikametgahAdresi = ikametgahAdresi;
	}
	public String getOnYazı() {
		return onYazı;
	}
	public void setOnYazı(String onYazı) {
		this.onYazı = onYazı;
	}
	@Override
	public String toString() {
		return "ExcelModel [başvurulanIlan=" + başvurulanIlan + ", adSoyad=" + adSoyad + ", Email=" + Email
				+ ", dogumTarihi=" + dogumTarihi + ", egitimBilgileri=" + egitimBilgileri + ", yabancıDil=" + yabancıDil
				+ ", medeniDurum=" + medeniDurum + ", askerlikDurumu=" + askerlikDurumu + ", ikametgahAdresi="
				+ ikametgahAdresi + ", onYazı=" + onYazı + "]";
	}

}
