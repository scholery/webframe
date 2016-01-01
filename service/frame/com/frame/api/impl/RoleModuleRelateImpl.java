package com.frame.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.api.IRoleModuleRelate;
import com.frame.model.bean.RoleModuleRelate;
import com.frame.model.bean.RoleModuleRelateCriteria;
import com.frame.model.dao.RoleModuleRelateMapper;

@Service
public class RoleModuleRelateImpl implements IRoleModuleRelate {

	@Autowired
	RoleModuleRelateMapper dao;

	@Override
	public List<RoleModuleRelate> findRoleModuleRelates(RoleModuleRelateCriteria example) {
		return this.dao.selectByExample(example);
	}

	@Override
	public void syncRoleModules(int roleId, List<Integer> moduleIds) {
		
		RoleModuleRelateCriteria example = new RoleModuleRelateCriteria();
		example.or().andRoleIdEqualTo(roleId);
		List<RoleModuleRelate> modules = this.dao.selectByExample(example);
		
		List<Integer> deleteIds = new ArrayList<Integer>();
		for (RoleModuleRelate temp : modules) {
			if (null != moduleIds && moduleIds.contains(temp.getModuleId())) {
				moduleIds.remove(temp.getModuleId());
			} else {
				deleteIds.add(temp.getModuleId());
			}
		}
		if (deleteIds.size() > 0) {
			RoleModuleRelateCriteria example1 = new RoleModuleRelateCriteria();
			example1.or().andRoleIdEqualTo(roleId).andModuleIdIn(deleteIds);
			this.dao.deleteByExample(example1);
		}
		RoleModuleRelate temp;
		for (Integer id : moduleIds) {
			temp = new RoleModuleRelate();
			temp.setModuleId(id);
			temp.setRoleId(roleId);
			this.dao.insertSelective(temp);
		}
	}
}
