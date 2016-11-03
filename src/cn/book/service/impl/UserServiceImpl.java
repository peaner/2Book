package cn.book.service.impl;

import java.util.List;

import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.book.mapper.UserMapper;
import cn.book.mapper.UserMapperCustom;
import cn.book.mapper.UserQueryVo;
import cn.book.po.User;
import cn.book.po.UserCustom;
import cn.book.po.UserExample;
import cn.book.po.UserExample.Criteria;
import cn.book.service.UserService;
import cn.book.util.CustomData;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapperCustom userMapperCustom;
	@Autowired
	private UserMapper userMapper;
	
	
	//用户列表
	@Override
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception {
		userMapperCustom.findUserList(userQueryVo);
		return null;
	}

	//根据id查询用户
	@Override
	public UserCustom findUserById(Integer id) throws Exception {
		
		//中间对用户信息进行业务逻辑处理
		//...
		//返回UserCustom
		UserCustom userCustom = new UserCustom();
		//将user的属性值拷贝到UserCustom
		BeanUtils.copyProperties(null, userCustom);
		
		return userCustom;
	}

	//更新用户
	@Override
	public void updateUser(Integer id, UserCustom userCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//在这里进行校验id是否为空，如果为空就抛出异常
		
		//更新用户信息使用持久层的方法来根据id更新User表中的字段，包括大文本类型字段
		
	}

	//验证数据，校验
	@Override
	public CustomData checkData(CustomData cd) throws Exception {
		
		UserExample example = new UserExample();
		Criteria criteria =  example.createCriteria();
		CustomData  cData = new CustomData();
		String SUCCESS = "" ;
		String ERROR  = "";
		//cd.containsKey("");
		//根据不同的数据类型指定不同的查询条件
		if(cd.containsKey("usr")!=false){
			criteria.andNameEqualTo(cd.getString("usr"));
			 SUCCESS = "用户名可用！";
			 ERROR = "用户名不用！";
		}else if (cd.containsKey("tel")!=false) {
			criteria.andPhonenumEqualTo(cd.getString("tel"));
			SUCCESS = "手机号可用！验证码已发送";
			cData.put("mes", 123456);
			ERROR = "手机号已注册！";
		}
		
		//执行查询结果是否为空
		List<User> list = userMapper.selectByExample(example);
		if(list == null || list.size()==0){
			//未查到数据证明可用,返回可用的提示信息
			cData.put("MESSAGE", SUCCESS);
		}else {
			cData.put("MESSAGE", ERROR);
		}
		//查到数据证明不可用，返回不可用的提示信息
		return cData;
	}

	//增加操作
	@Override
	public CustomData addUser(CustomData cd) throws Exception {
		//如果添加成功就保存，添加不成功就可以回滚
		CustomData cData = new CustomData();
		
		//获取timestamp
		//UserCustom userCustom = new UserCustom();
		//添加一些用户的信息
		//cd.put("timestamp", userCustom.getTimestamp());
		
		
		//对密码进行md5加密=====与登录的要保持好
		String md5 = DigestUtils.md5DigestAsHex(cd.getString("password").getBytes());
		cd.put("password", md5);
		
		try {
			//中间加入一些验证信息？？？？？
			userMapperCustom.addUser(cd);
			cData.put("MESSAGE", "SUCCESS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cData;
	}


	//验证用户名和密码
	@Override
	public CustomData getUserByNameAndPwd(String uSERNAME, String pASSWORD) {
		//对用户名、密码进行校验
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(uSERNAME);
		List<User> list = userMapper.selectByExample(example);
		//判断用户是否存在
		if(list == null || list.size() == 0){
			//返回用户不存在的提示信息
			return null;
		}
		
		//密码校验
		User user = list.get(0);
		if(!DigestUtils.md5DigestAsHex(pASSWORD.getBytes()).equals(user.getPasswd())){
			//返回密码错误的提示信息
			return null;
		}
		
		
	    //返回CustomData的信息
		return null;
	}

	@Override
	public User findUserByName(User loginUser) {
		return null;
	}


   
}
