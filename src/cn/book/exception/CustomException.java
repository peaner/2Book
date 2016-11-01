package cn.book.exception;

/**
*<p>Title CustomException</p>
*<p>Description 系统自定义异常类，针对预期的异常，需要在程序中抛出此类的异常 </p>
*@author GD
*@date 2016年8月20日 上午9:53:30
*@version 1.0
*/
public class CustomException extends Exception{
	
	private static final long serialVersionUID = 5588851272466312002L;
	private String message;
	
	public CustomException() {
		super();
	}
	
	public String getMessage() {
		return message;
	}

	
	public void setMessage(String message) {
		this.message = message;
	}

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

}
