package cn.book.service;

import java.util.List;

import cn.book.mapper.UserQueryVo;
import cn.book.po.User;
import cn.book.po.UserCustom;
import cn.book.util.CustomData;

public interface UserService {

	//业务层查询用户列表
	public List<UserCustom> findUserList(UserQueryVo userQueryVo) throws Exception;
	
	//业务层根据id查询用户信息
	public UserCustom findUserById(Integer id) throws Exception;
	
	//业务层修改用户信息
	/**
	 * 
	 * @param id  修改用户的id
	 * @param userCustom  要修改的用户信息
	 * @throws Exception
	 */
	public void updateUser(Integer id,UserCustom userCustom) throws Exception;

	//业务层检查数据
	public CustomData checkData(CustomData cd) throws Exception;
    //业务层增加
	public CustomData addUser(CustomData cd) throws Exception;
    //业务层验证用户名和密码
	public CustomData getUserByNameAndPwd(String uSERNAME, String pASSWORD);
    //业务层根据名称查询用户
	User findUserByName(User loginUser);

}
