package cn.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.book.po.UserCustom;
import cn.book.service.UserService;


/**
 * 用户处理的controller
 * @author M-PEANER
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//用户信息的检查
	public UserCustom checkData(@PathVariable String param , @PathVariable Integer type){
		///UserCustom resUserCustom = userService.checkData(param,type);
		return null;
	}
	
	//用户的查询

	
	//用户的修改

}
