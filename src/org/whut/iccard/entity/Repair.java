package org.whut.iccard.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.whut.iccard.adapter.DateAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement 
public class Repair {
	private long id;
	private String type;
	private String description;
	private String userName;
	private String oldBarCode;
	private int oldIndication;
	private String newBarCode;
	private int newIndication;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date postDate;
	@XmlJavaTypeAdapter(DateAdapter.class)
	private Date completeDate;
	private String address;
	private int isUpdate;
	private int uploadFlag;
	private int isComplete;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOldBarCode() {
		return oldBarCode;
	}
	public void setOldBarCode(String oldBarCode) {
		this.oldBarCode = oldBarCode;
	}
	public int getOldIndication() {
		return oldIndication;
	}
	public void setOldIndication(int oldIndication) {
		this.oldIndication = oldIndication;
	}
	public String getNewBarCode() {
		return newBarCode;
	}
	public void setNewBarCode(String newBarCode) {
		this.newBarCode = newBarCode;
	}
	public int getNewIndication() {
		return newIndication;
	}
	public void setNewIndication(int newIndication) {
		this.newIndication = newIndication;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(int isUpdate) {
		this.isUpdate = isUpdate;
	}
	public int getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(int uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public int getIsComplete() {
		return isComplete;
	}
	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}
}
