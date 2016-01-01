package com.frame.api;

import java.util.List;

import com.frame.model.bean.UserRoleRelate;
import com.frame.model.bean.UserRoleRelateCriteria;


public interface IUserRoleRelate {
	
	List<UserRoleRelate> findUserRoleRelates(UserRoleRelateCriteria example);
	
	void syncUserRoleRelate(int userId,List<Integer> roleIds);
}
