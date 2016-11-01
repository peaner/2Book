package cn.book.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.book.exception.CustomException;
import cn.book.po.UserCustom;
import cn.book.service.UserService;
import cn.book.util.CustomData;
import cn.book.util.Tills;



/**
 * web注册登录入口
 * @author M-PEANER
 *
 */

@Controller
@RequestMapping(value="/login")
public class LoginController {
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 获取登录用户的IP=====????
	 * @param USERNAME
	 * @throws Exception
	 */
	public void getUserIP(String USERNAME) throws Exception{
		CustomData cd = new CustomData();
		Tills tills = new Tills();
		HttpServletRequest request = tills.getRequest();
		String ip = "";
		//获取请求的头部信息,也是为之添加了头部信息，从而获取客户端的真实ip地址
		if(request.getHeader("x-forwarded-for")==null){
			ip = request.getRemoteAddr();
		}else {
			ip = request.getHeader("x-forwarded-for");
		}
		cd.put("USERNAME", USERNAME);
		cd.put("IP", ip);
		
		//???????
		//userService.saveIP(cd);
	}
	
	/**
	 * 数据检验
	 * @param param  校验
	 * 1.校验用户名  2：校验电话号码 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkData")
	@ResponseBody
	public CustomData checkData() throws Exception{
		CustomData cd = new CustomData();
		Tills tills = new Tills();
		cd = tills.getCustomData();
		
		//System.out.println("前台接收的数据"+param);
		try {
			cd = userService.checkData(cd);
			System.out.println(cd.getString("MESSAGE"));
		} catch (Exception e) {
			e.printStackTrace();
			CustomException ce = new CustomException();
			ce.setMessage("填写数据有问题！");
			cd.put("MESSAGE", ce.getMessage());
		}
		System.out.println(cd.getString("MESSAGE"));
		return cd;
	}
	
	
	
	/**
	 * 进入注册页
	 */
	@RequestMapping(value="/toRegistView")
	public ModelAndView toRegistView() throws Exception{
		ModelAndView mv = new ModelAndView();
		CustomData cd = new CustomData();
		Tills tills = new Tills();
		cd = tills.getCustomData();
		//进入注册页的路径
		mv.setViewName("register");
		return mv;
		
	}
	
	/**
	 * 用户注册
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/regist")
	public ModelAndView Regist() throws Exception{
    	//先行对用户数据校验进行检测
    	ModelAndView mv = new ModelAndView();
    	CustomData cd = new CustomData(); 
    	Tills tills = new Tills();
    	cd = tills.getCustomData(); 
    	
    	try {
			cd = userService.addUser(cd);
			if(cd.get("MESSAGE")=="SUCCESS"){
				//注册成功后跳转的页面===可以是首页，最好是用户进入注册页前的浏览的页面
				mv.setViewName("");
			}else {
				//注册失败跳转页面
				mv.setViewName("");                                                                       
			}
			//return mv;
		} catch (Exception e) {
			e.printStackTrace();
			CustomException ce = new CustomException();
			ce.setMessage("注册失败. 请校验数据后请再提交数据");
			cd.put("MESSAGE", ce.getMessage());
			mv.addObject("cd", cd);
		} 
    	return mv;
	}
	
	
	/**
	 * 进入登录页
	 * @return mv 
	 * @throws Exception
	 */
	@RequestMapping(value="/toLoginView")
	public ModelAndView toLoginView() throws Exception{
		ModelAndView mv = new ModelAndView();
		CustomData cd = new CustomData();
		Tills tills = new Tills();
		cd = tills.getCustomData();
		//cd.put("SYSTEM", 12);
		//进入登录页的路径
		mv.setViewName("进入登录页的路径");
		//传递到前端的值
		//mv.addObject("cd",cd);  
		return mv;
	}
	
	/**
	 * 用户登录
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
    public Object login() throws Exception{
		
    	Map<String, String> map = new HashMap<String, String>();
    	
    	CustomData cd = new CustomData();
    	Tills tills = new Tills();
    	cd = tills.getCustomData();
    	
    	//获取用户名和密码
    	String USERNAME = cd.getString("username");
    	String PASSWORD = cd.getString("password");
    	
    	String MESSAGE = "";   //显示的信息
    	
    	//key/value值
    	cd.put("uname", USERNAME);
    	//对密码进行加密===????与注册的密码加密保持一致
    	String passwd = new SimpleHash("SHA-1",USERNAME,PASSWORD).toString();
    	cd.put("password", passwd);
    	
    	//验证用户名和密码
        cd = userService.getUserByNameAndPwd(USERNAME,passwd);
    	
    	//验证存在
    	if(cd!=null){
    		System.out.println("success!");
    		//此为在登录前对用户信息的获取
    		UserCustom userCustom = new  UserCustom();
    		userCustom.setId(Integer.parseInt(cd.getString("学号")));
    		userCustom.setName(cd.getString("USERNAME"));
    		userCustom.setPasswd(cd.getString("PASSWORD"));
    		//userCustom.setUsercode(cd.getString("USERCODE"));
    		//userCustom.setNickname(cd.getString("NICKNAME"));
    		userCustom.setSchool(cd.getString("school"));
    		userCustom.setPhonenum(cd.getString("PHONENUMBER"));
    		//userCustom.setSchoolnumber(cd.getString("学校编号"));
    		userCustom.setSex(cd.getString("SEX"));
    		//userCustom.setNeeds(cd.getString("NEEDS"));
    		
    		//shiro加入身份认证
    		Subject subject = SecurityUtils.getSubject();
    		UsernamePasswordToken token = new UsernamePasswordToken(USERNAME, PASSWORD);
    		try {
    			//在这里调用login会进入到shiro中进行CustomRealm
				subject.login(token);
			} catch (AuthenticationException e) {
				MESSAGE = "身份验证失败！";
			}
    		System.out.println("登录成功！");
    		
    	}else {
			MESSAGE = "用户名或密码有误！";
		}
    	
    	map.put("message", MESSAGE);
    	return tills.returnObject(new CustomData(), map);

    }
	
	
	
	/**
	 * 用户注销
	 * @return
	 */
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(){
		ModelAndView mv = new ModelAndView();
		CustomData cd = new CustomData();
		
		//shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		
		//session的一系列removeAttribute对象
		//.....
		
		//shiro销毁对象
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		
		Tills tills = new Tills();
		cd = tills.getCustomData();
		String MESSAGE = cd.getString("接收到信息的key值");
		cd.put("接收到信息的key值", MESSAGE);
		
		mv.setViewName("视图名称路径");
		mv.addObject("cd", cd);
		return mv;
		
	}
	
}
