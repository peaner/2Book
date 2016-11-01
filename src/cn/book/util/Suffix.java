package cn.book.util;
/**
 *<p>Title Suffix</p>
 *<p>Description 得到文件后缀名</p>
 *@author GD
 *@date 2016年8月9日 下午2:19:19
 *@version 1.0
 */
public class Suffix {
	public static String getSuffix(String originalFilename){
		String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
		return fileSuffix;
	}
	

}
