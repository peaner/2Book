package cn.book.mapper;

import cn.book.po.User;
import cn.book.po.UserCustom;

/**
 * 包装类
 * @author M-PEANER
 *
 */
public class UserQueryVo {
	
	private User user;
	
    private UserCustom userCustom;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserCustom getUserCustom() {
		return userCustom;
	}

	public void setUserCustom(UserCustom userCustom) {
		this.userCustom = userCustom;
	}
    
   
}
