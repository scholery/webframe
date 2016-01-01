package com.frame.api.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.api.IDomain;
import com.frame.api.IModule;
import com.frame.model.bean.Domain;
import com.frame.model.bean.DomainCriteria;
import com.frame.model.bean.Module;
import com.frame.model.bean.ModuleCriteria;
import com.frame.model.dao.DomainMapper;

@Service
public class DomainImpl implements IDomain {

	@Autowired
	DomainMapper dao;

	@Autowired
	IModule moduleService;

	@Override
	public void addDomain(Domain obj) {
		this.dao.insertSelective(obj);
	}

	@Override
	public void updateDomain(Domain obj) {
		this.dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void deleteDomain(int id) {
		// TODO Auto-generated method stub
		ModuleCriteria example = new ModuleCriteria();
		example.or().andDomainIdEqualTo(id);
		
		List<Module> modules = moduleService.findModules(example);
		List<Integer> ids = new ArrayList<Integer>();
		if (null != modules && modules.size() > 0) {
			for (Module temp : modules) {
				ids.add(temp.getId());
			}
			moduleService.deleteModule(ids);
		}
		this.dao.deleteByPrimaryKey(id);
	}

	@Override
	public Domain getDomainById(int id) {
		// TODO Auto-generated method stub
		return this.dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Domain> getDomains(DomainCriteria example) {
		// TODO Auto-generated method stub
		return this.dao.selectByExample(example);
	}
}
