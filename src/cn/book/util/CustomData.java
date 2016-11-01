package cn.book.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于专门接收页面信息的类
*<p>Title CustomData.java</p>
*<p>Description</p>
*@author GD
*@date 2016年9月26日 下午9:44:32
*@version 1.0
*/
public class CustomData extends HashMap implements Map{

	private static final long serialVersionUID = 7464846704588566919L;

	Map map = null;
	HttpServletRequest request;
	
	public CustomData(HttpServletRequest request) {
		this.request = request;
		//接收前端的数据（包括表单等，表单中的name为key，value为值）
		Map properties = request.getParameterMap();
		Map returnMap = new HashMap();
		
		Iterator iterator = properties.entrySet().iterator();
		Map.Entry entry;
		
		//用于接收前端表单的key和value
		String name = "";
		String value = "";
		
		while (iterator.hasNext()) {
			entry = (Map.Entry) iterator.next();
			name = (String) entry.getKey();
			Object valueObject = entry.getValue();
			if(valueObject == null){
				value = "";
			}else if (valueObject instanceof String[]) {
				String[] values = (String[]) valueObject;
				for (int i = 0; i < values.length; i++) {
					value = values[i]+",";
				}
				value = value.substring(0,value.length()-1);
			}else {
				value = valueObject.toString();
			}
			//返回的key和value
			returnMap.put(name, value);
		}
		map = returnMap;
	}
	
	public CustomData(){
		map = new HashMap();
	}
	
	//得到前端传输过来的数据
	@Override
	public Object get(Object key){
		Object object = null;
		if(map.get(key) instanceof Object[]){
			Object[] objects = (Object[]) map.get(key);
			object = request ==null ? objects:(request.getParameter((String) key)==null? objects:objects[0]);
		}else {
			object = map.get(key);
		}
		return object;
	}
	
	
	public String getString(Object key){
		return (String) get(key);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object put(Object key , Object value){
		return map.put(key, value);
	}
	
	
	public Object remove(Object key){
		return map.remove(key);
	}
	
	
	public void clear(){
		map.clear();
	}
	
	public boolean containsKey(Object key){
		return map.containsKey(key);
	}
	
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}
	
	public Set entrySet() {
		return map.entrySet();
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public Set keySet() {
		return map.keySet();
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map t) {
		map.putAll(t);
	}

	public int size() {
		return map.size();
	}

	public Collection values() {
		return map.values();
	}
	

}
