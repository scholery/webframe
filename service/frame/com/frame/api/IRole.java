package com.frame.api;

import java.util.List;

import com.frame.bean.GridBean;
import com.frame.model.bean.Role;
import com.frame.model.bean.RoleCriteria;


public interface IRole {
	
	void addRole(Role obj);

	void updateRole(Role obj);

	void deleteRole(List<Integer> ids);
	
	Role getRoleById(int id);
	
	List<Role> findRoles(RoleCriteria obj);

	GridBean findRolesForGrid(RoleCriteria role, int page, int count);

	void setDefault(int id);
}
