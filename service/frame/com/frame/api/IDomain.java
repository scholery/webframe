package com.frame.api;

import java.util.List;

import com.frame.model.bean.Domain;
import com.frame.model.bean.DomainCriteria;


public interface IDomain {
	
	void addDomain(Domain obj);

	void updateDomain(Domain obj);

	void deleteDomain(int id);
	
	Domain getDomainById(int id);
	
	List<Domain> getDomains(DomainCriteria obj);
}
