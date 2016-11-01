package cn.book.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *<p>Title GetRandomFileName</p>
 *<p>Description 得到上传文件的名字以及后缀名</p>
 *@author GD
 *@date 2016年8月9日 下午2:27:56
 *@version 1.0
 */
public class RandomFileName {
	public static void main(String[] args) {
		System.out.println(getRandomFileName("e:\\a.txt"));
	}
	
	public static String getRandomFileName(String fileAbsolutePath){
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String newFileName = UUID.randomUUID().toString().replace("-","" ).toUpperCase()+format.format(new Date())+Suffix.getSuffix(fileAbsolutePath);
		return newFileName;
	}

}
