package com.yc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AdminPO implements Serializable{

	private int aid;
	private String aname;
	private String apwd;
	private String message;  //消息信息
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
