package com.frame.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.api.IUserRoleRelate;
import com.frame.model.bean.UserRoleRelate;
import com.frame.model.bean.UserRoleRelateCriteria;
import com.frame.model.dao.UserRoleRelateMapper;

@Service
public class UserRoleRelateImpl implements IUserRoleRelate {

	@Autowired
	UserRoleRelateMapper dao;

	@Override
	public List<UserRoleRelate> findUserRoleRelates(UserRoleRelateCriteria example) {
		// TODO Auto-generated method stub
		return this.dao.selectByExample(example);
	}

	@Override
	public void syncUserRoleRelate(int userId, List<Integer> roleIds) {

		UserRoleRelateCriteria example = new UserRoleRelateCriteria();
		example.or().andUserIdEqualTo(userId);
		List<UserRoleRelate> roles = this.dao.selectByExample(example);

		List<Integer> deleteIds = new ArrayList<Integer>();
		for (UserRoleRelate temp : roles) {
			if (null != roleIds && roleIds.contains(temp.getRoleId())) {
				roleIds.remove(temp.getRoleId());
			} else {
				deleteIds.add(temp.getRoleId());
			}
		}
		if (deleteIds.size() > 0) {
			UserRoleRelateCriteria example1 = new UserRoleRelateCriteria();
			example1.or().andUserIdEqualTo(userId).andRoleIdIn(deleteIds);
			this.dao.deleteByExample(example1);
		}
		UserRoleRelate temp;
		for (Integer id : roleIds) {
			temp = new UserRoleRelate();
			temp.setUserId(userId);
			temp.setRoleId(id);
			this.dao.insertSelective(temp);
		}
	}
}
