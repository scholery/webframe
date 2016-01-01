package com.frame.api.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frame.api.IModule;
import com.frame.model.bean.Module;
import com.frame.model.bean.ModuleCriteria;
import com.frame.model.bean.RoleModuleRelateCriteria;
import com.frame.model.dao.ModuleMapper;
import com.frame.model.dao.RoleModuleRelateMapper;

@Service
public class ModuleImpl implements IModule {

	@Autowired
	ModuleMapper dao;

	@Autowired
	RoleModuleRelateMapper roleModuleDao;
	
	@Override
	public void addModule(Module obj) {
		this.dao.insertSelective(obj);
	}

	@Override
	public void updateModule(Module obj) {
		this.dao.updateByPrimaryKeySelective(obj);
	}

	@Override
	public void deleteModule(List<Integer> ids) {
		if (null == ids || ids.size() == 0) {
			return;
		}
		for(int id : ids){
			deleteSubModules(id);
			RoleModuleRelateCriteria example = new RoleModuleRelateCriteria();
			RoleModuleRelateCriteria.Criteria cc = example.or();
			cc.andModuleIdEqualTo(id);
			this.roleModuleDao.deleteByExample(example);
			this.dao.deleteByPrimaryKey(id);
		}
	}

	private void deleteSubModules(int id) {
		ModuleCriteria example = new ModuleCriteria();
		example.or().andParentModuleIdEqualTo(id);
		List<Module> subs = this.dao.selectByExample(example);
		if (subs.size() > 0) {
			RoleModuleRelateCriteria ee = new RoleModuleRelateCriteria();
			for (Module temp : subs) {
				deleteSubModules(temp.getId());
				ee.clear();
				ee.or().andModuleIdEqualTo(temp.getId());
				this.roleModuleDao.deleteByExample(ee);
				this.dao.deleteByPrimaryKey(temp.getId());
			}
		}

	}

	@Override
	public Module getModuleById(int id) {
		return this.dao.selectByPrimaryKey(id);
	}
	
	@Override
	public List<Module> findModules(ModuleCriteria example) {
		// TODO Auto-generated method stub
		return this.dao.selectByExample(example);
	}

	@Override
	public int getSubModuleNum(int moduleId) {
		ModuleCriteria example = new ModuleCriteria();
		example.or().andParentModuleIdEqualTo(moduleId);
		return this.dao.countByExample(example);
	}
}
