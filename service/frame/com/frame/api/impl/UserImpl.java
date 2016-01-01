package com.frame.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.api.IUser;
import com.frame.bean.GridBean;
import com.frame.model.bean.RoleModuleRelate;
import com.frame.model.bean.RoleModuleRelateCriteria;
import com.frame.model.bean.User;
import com.frame.model.bean.UserCriteria;
import com.frame.model.bean.UserRoleRelate;
import com.frame.model.bean.UserRoleRelateCriteria;
import com.frame.model.dao.ModuleMapper;
import com.frame.model.dao.RoleModuleRelateMapper;
import com.frame.model.dao.UserMapper;
import com.frame.model.dao.UserRoleRelateMapper;

@Service
public class UserImpl implements IUser {

	@Autowired
	private UserMapper dao;

	@Autowired
	UserRoleRelateMapper userRoleDao;

	@Autowired
	ModuleMapper moduleDao;

	@Autowired
	RoleModuleRelateMapper roleModuleDao;

	@Override
	public void addUser(User user) {
		dao.insertSelective(user);
	}

	@Override
	public User getUserById(int id) {
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public void updateUser(User user) {
		dao.updateByPrimaryKeySelective(user);
	}

	@Override
	public void deleteUser(List<Integer> ids) {
		if (null == ids || ids.size() == 0) {
			return;
		}
		for (int id : ids) {
			UserRoleRelateCriteria example = new UserRoleRelateCriteria();
			example.or().andUserIdEqualTo(id);
			this.userRoleDao.deleteByExample(example);
			this.dao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public List<User> findUsers(UserCriteria user) {
		return dao.selectByExample(user);
	}

	@Override
	public User getUserWithPass(String account) {
		UserCriteria user = new UserCriteria();
		user.or().andUserAccountEqualTo(account);
		List<User> users = this.dao.selectByExampleWithBLOBs(user);
		if (null != users && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	@Override
	public List<Integer> getUserModuleIds(int id) {

		List<Integer> moduleIds = new ArrayList<Integer>();

		UserRoleRelateCriteria userRole = new UserRoleRelateCriteria();
		userRole.or().andUserIdEqualTo(id);
		List<UserRoleRelate> userRoles = userRoleDao.selectByExample(userRole);
		if (null == userRoles || userRoles.size() == 0) {
			return moduleIds;
		}

		List<Integer> roleIds = new ArrayList<Integer>();
		for (UserRoleRelate temp : userRoles) {
			roleIds.add(temp.getRoleId());
		}
		RoleModuleRelateCriteria roleModule = new RoleModuleRelateCriteria();
		roleModule.or().andRoleIdIn(roleIds);
		List<RoleModuleRelate> roleModules = this.roleModuleDao.selectByExample(roleModule);
		if (null == roleModules || roleModules.size() == 0) {
			return moduleIds;
		}
		for (RoleModuleRelate temp : roleModules) {
			moduleIds.add(temp.getModuleId());
		}

		return moduleIds;
	}

	@Override
	public GridBean findUsersForGrid(UserCriteria user, int page, int count) {
		GridBean grid = new GridBean();
		grid.setPage(page);
		grid.setPageCount(count);

		RowBounds row = new RowBounds(grid.getStart(), grid.getPageCount());
		grid.setRecords(this.dao.countByExample(user));
		grid.setRows(this.dao.selectByExampleWithRowbounds(user, row).toArray());
		return grid;
	}

}
