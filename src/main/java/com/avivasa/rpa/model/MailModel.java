package com.avivasa.rpa.model;


import java.io.File;
import java.util.Date;
import java.util.List;

import javax.mail.Message;

public class MailModel {
	
	private List<File> fileList;
	private File mergeFile;
	private String sender;
	private Date sendTime, processTime;
	private String processResult;
	private String mailNewFolderName;
	private String uploadReferanceNo;
	private Message message;
	private String attachmentName;
	
	public MailModel() {
	}

	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getProcessTime() {
		return processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getProcessResult() {
		return processResult;
	}

	public void setProcessResult(String processResult) {
		this.processResult = processResult;
	}

	public File getMergeFile() {
		return mergeFile;
	}

	public void setMergeFile(File mergeFile) {
		this.mergeFile = mergeFile;
	}

	public String getMailNewFolderName() {
		return mailNewFolderName;
	}

	public void setMailNewFolderName(String mailNewFolderName) {
		this.mailNewFolderName = mailNewFolderName;
	}

	public String getUploadReferanceNo() {
		return uploadReferanceNo;
	}

	public void setUploadReferanceNo(String uploadReferanceNo) {
		this.uploadReferanceNo = uploadReferanceNo;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	
	

}

