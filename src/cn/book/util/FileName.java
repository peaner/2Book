package cn.book.util;

/**
 *<p>Title</p>
 *<p>Description</p>
 *@author GD
 *@date 2016年8月9日 下午3:09:55
 *@version 1.0
 */
public class FileName {
	
	public static String getFileName(String fileAbsolutePath){
		return fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\")+1, fileAbsolutePath.length());
	}

}
