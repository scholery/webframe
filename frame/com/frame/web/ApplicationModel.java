package com.frame.web;

import java.io.Serializable;
import java.util.Date;

import com.frame.bean.MenuItem;


public class ApplicationModel implements Serializable {

	private String appName;

	private String userName;
	
	private long domainId;
	
	private long userId;
	
	private String userAccount;

	private long selectedModuleId;
	
	private MenuItem[] menuItems;
	
	private String userLocaltion;
	
	private String serverLocaltion;
	
	private Date loginTime;

	public long getSelectedModuleId() {
		return selectedModuleId;
	}

	public void setSelectedModuleId(long selectedModuleId) {
		this.selectedModuleId = selectedModuleId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public MenuItem[] getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(MenuItem[] menuItems) {
		this.menuItems = menuItems;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	

	public String getUserLocaltion() {
		return userLocaltion;
	}

	public void setUserLocaltion(String userLocaltion) {
		this.userLocaltion = userLocaltion;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getServerLocaltion() {
		return serverLocaltion;
	}

	public void setServerLocaltion(String serverLocaltion) {
		this.serverLocaltion = serverLocaltion;
	}

	public long getDomainId() {
		return domainId;
	}

	public void setDomainId(long domainId) {
		this.domainId = domainId;
	}

}
