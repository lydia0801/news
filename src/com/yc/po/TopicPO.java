package com.yc.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TopicPO implements Serializable{

	private int tid; //主题编号
	private String tname; //主题名称
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	
	public String toString(){
		return  "{\"tid\":\""+this.tid+"\",\"tname\":\""+this.tname+"\"}";  
	 }
	
	public static void main(String[] args) {
		TopicPO po =new TopicPO();
		po.setTid(1);
		po.setTname("aaaa");
		System.out.println(po.toString());
	}
}
