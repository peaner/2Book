package cn.book.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
*<p>Title CustomDateConvert.java</p>
*<p>Description</p>
*@author GD
*@date 2016年8月20日 上午11:42:45
*@version 1.0
*/
public class CustomDateConvert implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	

}
