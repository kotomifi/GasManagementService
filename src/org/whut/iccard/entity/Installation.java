package org.whut.iccard.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.whut.iccard.adapter.DateAdapter;

/**
 * @XmlJavaTypeAdapter(DateAdapter.class)
 * @author baisu
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement 
public class Installation {
	private long id;
	private String address;
	private String userName;
	private String postDate;
	
	private String completeDate;
	private int isComplete;
	private int uploadFlag;
	private String barCode;
	private int indication;
	
	public int getUploadFlag() {
		return uploadFlag;
	}
	public void setUploadFlag(int uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public int getIndication() {
		return indication;
	}
	public void setIndication(int indication) {
		this.indication = indication;
	}
	public void setIsComplete(int isComplete) {
		this.isComplete = isComplete;
	}
	public int getIsComplete() {
		return isComplete;
	}
	public void setComplete(int isComplete) {
		this.isComplete = isComplete;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostDate() {
		return postDate;
	}
	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	@Override
	public String toString() {
		return "Installation [id=" + id + ", address=" + address
				+ ", userName=" + userName + ", postDate=" + postDate
				+ ", completeDate=" + completeDate + ", isComplete="
				+ isComplete + ", uploadFlag=" + uploadFlag + ", barCode="
				+ barCode + ", indication=" + indication + "]";
	}
}
