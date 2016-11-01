package cn.book.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
*<p>Title CustomExceptionResolver.java</p>
*<p>Description</p>
*@author GD
*@date 2016年8月20日 上午10:02:07
*@version 1.0
*/
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		CustomException customException = null;
		if (ex instanceof CustomException){
			customException = (CustomException) ex;
		}else{
			customException = new CustomException("未知错误");
		}
		
		String message = customException.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("message", message);
		
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

	

}
