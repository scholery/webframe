package com.frame.api;

import java.util.List;

import com.frame.model.bean.RoleModuleRelate;
import com.frame.model.bean.RoleModuleRelateCriteria;


public interface IRoleModuleRelate {
	
	List<RoleModuleRelate> findRoleModuleRelates(RoleModuleRelateCriteria example);
	
	void syncRoleModules(int roleId,List<Integer> moduleIds);
}
