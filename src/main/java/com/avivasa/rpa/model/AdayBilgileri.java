package com.avivasa.rpa.model;

import javax.mail.Message;

public class AdayBilgileri {
private String basvuruIlan;
private String adiSoyadi;
private String email;
private String dogumTarihi;
private String egitimBilgileri;
private String yabancidil;
private String medeniDurum;
private String askerlikDurum;
private String ikemetgahAdresi;
private String onyazıGenelBilgiler;
private Message message;
public String getBasvuruIlan() {
	return basvuruIlan;
}
public void setBasvuruIlan(String basvuruIlan) {
	this.basvuruIlan = basvuruIlan;
}
public String getAdiSoyadi() {
	return adiSoyadi;
}
public void setAdiSoyadi(String adiSoyadi) {
	this.adiSoyadi = adiSoyadi;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
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
public String getYabancidil() {
	return yabancidil;
}
public void setYabancidil(String yabancidil) {
	this.yabancidil = yabancidil;
}
public String getMedeniDurum() {
	return medeniDurum;
}
public void setMedeniDurum(String medeniDurum) {
	this.medeniDurum = medeniDurum;
}
public String getAskerlikDurum() {
	return askerlikDurum;
}
public void setAskerlikDurum(String askerlikDurum) {
	this.askerlikDurum = askerlikDurum;
}
public String getIkemetgahAdresi() {
	return ikemetgahAdresi;
}
public void setIkemetgahAdresi(String ikemetgahAdresi) {
	this.ikemetgahAdresi = ikemetgahAdresi;
}
public String getOnyazıGenelBilgiler() {
	return onyazıGenelBilgiler;
}
public void setOnyazıGenelBilgiler(String onyazıGenelBilgiler) {
	this.onyazıGenelBilgiler = onyazıGenelBilgiler;
}
@Override
public String toString() {
	return "AdayBilgileri [basvuruIlan=" + basvuruIlan + ", adiSoyadi=" + adiSoyadi + ", email=" + email
			+ ", dogumTarihi=" + dogumTarihi + ", egitimBilgileri=" + egitimBilgileri + ", yabancidil=" + yabancidil
			+ ", medeniDurum=" + medeniDurum + ", askerlikDurum=" + askerlikDurum + ", ikemetgahAdresi="
			+ ikemetgahAdresi + ", onyazıGenelBilgiler=" + onyazıGenelBilgiler + "]";
}
}
