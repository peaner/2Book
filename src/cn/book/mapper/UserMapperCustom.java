package cn.book.mapper;

import cn.book.po.UserCustom;
import cn.book.util.CustomData;

public interface UserMapperCustom {
	public UserCustom findUserList(UserQueryVo userQueryVo) throws Exception;

	public void addUser(CustomData cd) throws Exception;
}
