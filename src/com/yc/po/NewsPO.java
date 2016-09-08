package com.yc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class NewsPO implements Serializable{
	private int nid; //新闻编号
	private int ntid; //新闻类型
	private String tname; //新闻类型名
	private String ntitle; //新闻标题
	private String nauthor; //新闻作者
	private String ncreatedate; //发布日期
	private String npicpath; //新闻图片地址
	private String ncontent; //新闻内容
	private String nmodifydate; //修改日期
	private String nsummary; //概要
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public int getNtid() {
		return ntid;
	}
	public void setNtid(int ntid) {
		this.ntid = ntid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public String getNtitle() {
		return ntitle;
	}
	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}
	public String getNauthor() {
		return nauthor;
	}
	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}
	public String getNcreatedate() {
		return ncreatedate;
	}
	public void setNcreatedate(String ncreatedate) {
		this.ncreatedate = ncreatedate;
	}
	public String getNpicpath() {
		return npicpath;
	}
	public void setNpicpath(String npicpath) {
		this.npicpath = npicpath;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public String getNmodifydate() {
		return nmodifydate;
	}
	public void setNmodifydate(String nmodifydate) {
		this.nmodifydate = nmodifydate;
	}
	public String getNsummary() {
		return nsummary;
	}
	public void setNsummary(String nsummary) {
		this.nsummary = nsummary;
	}
	
}
