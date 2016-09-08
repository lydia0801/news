package com.yc.commons;

import java.util.ArrayList;
import java.util.List;

import com.yc.po.TopicPO;
/**
 * json数据格式转化工具
 * @author Administrator
 *
 */
public class JsonUtil {

	public <T> String toArray(List<T> list){
		StringBuffer sb =new StringBuffer();
		sb.append("[");
		for(int i=0;i<list.size();i++){
			if(i==list.size()-1){//循环到最后一个对象时
				sb.append(list.get(i).toString()+"]");
			}else{
				sb.append(list.get(i).toString()+",");
			}
		}
		return sb.toString();
	}
	
	public Object  put(String key,Object value){
		StringBuffer sb = new StringBuffer();
		sb.append("{\""+key+"\":"+value+"}");
		return sb;
	}
	
	public static void main(String[] args) {
		List<TopicPO> list = new ArrayList<TopicPO>();
		TopicPO po =new TopicPO();
		po.setTid(1);
		po.setTname("aaaa");
		list.add(po);
		po =new TopicPO();
		po.setTid(2);
		po.setTname("sss");
		list.add(po);
		JsonUtil  util = new JsonUtil();
		System.out.println(util.toArray(list));
		System.out.println(util.put("topicList", list).toString());
		
	}
}
