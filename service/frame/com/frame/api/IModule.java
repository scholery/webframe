package com.frame.api;

import java.util.List;

import com.frame.model.bean.Module;
import com.frame.model.bean.ModuleCriteria;


public interface IModule {
	
	void addModule(Module obj);

	void updateModule(Module obj);

	void deleteModule(List<Integer> ids);
	
	Module getModuleById(int id);
	
	List<Module> findModules(ModuleCriteria example);
	
	int getSubModuleNum(int moduleId);
}
