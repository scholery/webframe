package com.frame.api.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.api.IRole;
import com.frame.api.ObjectConstants;
import com.frame.bean.GridBean;
import com.frame.model.bean.Role;
import com.frame.model.bean.RoleCriteria;
import com.frame.model.bean.RoleModuleRelateCriteria;
import com.frame.model.bean.User;
import com.frame.model.bean.UserRoleRelateCriteria;
import com.frame.model.dao.RoleMapper;
import com.frame.model.dao.RoleModuleRelateMapper;
import com.frame.model.dao.UserRoleRelateMapper;

@Service
public class RoleImpl implements IRole {

	@Autowired
	RoleMapper dao;

	@Autowired
	RoleModuleRelateMapper roleModuleDao;

	@Autowired
	UserRoleRelateMapper userRoleDao;

	@Override
	public void addRole(Role obj) {
		this.dao.insertSelective(obj);
	}

	@Override
	public void updateRole(Role obj) {
		this.dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void deleteRole(List<Integer> ids) {
		if (null == ids || ids.size() == 0) {
			return;
		}
		for(int id : ids){
			RoleModuleRelateCriteria example = new RoleModuleRelateCriteria();
			example.or().andRoleIdEqualTo(id);
			this.roleModuleDao.deleteByExample(example);

			UserRoleRelateCriteria exap = new UserRoleRelateCriteria();
			exap.or().andRoleIdEqualTo(id);
			this.userRoleDao.deleteByExample(exap);

			this.dao.deleteByPrimaryKey(id);
		}
	}

	@Override
	public Role getRoleById(int id) {
		// TODO Auto-generated method stub
		return this.dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Role> findRoles(RoleCriteria example) {
		// TODO Auto-generated method stub
		return this.dao.selectByExample(example);
	}

	@Override
	public void setDefault(int id) {
		// TODO Auto-generated method stub
		Role role = this.dao.selectByPrimaryKey(id);
		if (null == role) {
			return;
		}
		Role dd = new Role();
		dd.setDefaultFlag("");
		RoleCriteria example = new RoleCriteria();
		example.or().andDomainIdEqualTo(role.getDomainId()).andDefaultFlagEqualTo(ObjectConstants.DEFAULT);
		this.dao.updateByExampleSelective(dd, example);

		role.setDefaultFlag(ObjectConstants.DEFAULT);
		this.updateRole(role);
	}

	@Override
	public GridBean findRolesForGrid(RoleCriteria role, int page, int count) {
		GridBean grid = new GridBean();
		grid.setPage(page);
		grid.setPageCount(count);

		RowBounds row = new RowBounds(grid.getStart(), grid.getPageCount());
		grid.setRecords(this.dao.countByExample(role));
		grid.setRows(this.dao.selectByExampleWithRowbounds(role, row).toArray());
		return grid;
	}
}
