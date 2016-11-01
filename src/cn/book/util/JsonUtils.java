package cn.book.util;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

public class JsonUtils {
	
	//定义一个jackson对象
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	/**
	 * 将对象转换成json对象
	 * @param data
	 * @return
	 */
	public static String objectToJson(Object data){
		try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将json结果转换成对象
	 * 
	 * @param jsonData  json数据
	 * @param beanType  对象类型
	 * @return
	 */
	public static <T> T jsonToPojo(String jsonData , Class<T> beanType){
		T t;
		try {
			t = MAPPER.readValue(jsonData, beanType);
			return t;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	/**
	 * 将json数据转换成pojo对象list
	 * peaner
	 * 2016.9.30
	 * @param jsonData
	 * @param beanType
	 * @return
	 */
	public static <T>List<T> JsonToList(String jsonData , Class<T> beanType){
		//JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
		JavaType javaType = null;
		try {
			List<T> list = MAPPER.readValue(jsonData, javaType);
			return list;
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
