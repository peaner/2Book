package cn.book.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.util.JSONPObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class Tills {
	
	/**
	 * �õ�request
	 * @return
	 */
	public HttpServletRequest getRequest(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * �õ�CustomData
	 * @return
	 */
	public CustomData getCustomData(){
		return new CustomData(this.getRequest());
	}
	
	
	public static Object returnObject(CustomData cd ,Map map){
		if(cd.containsKey("���ض���")){
			String string = cd.get("���ض���").toString();
			return new JSONPObject(string, map);
		}else {
			return map;
		}
	}

}
