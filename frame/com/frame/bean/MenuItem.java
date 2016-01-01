package com.frame.bean;

import java.io.Serializable;

import com.frame.util.StringUtil;

public class MenuItem implements Serializable {
	
	private long menuId;
	
	private String domainCode;
	
	private String domainName;

	private String label;
	
	private String path;
	
	private String icon;
	
	private MenuItem[] subItems = new MenuItem[0];

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public MenuItem[] getSubItems() {
		return subItems;
	}

	public void setSubItems(MenuItem[] subItems) {
		this.subItems = subItems;
	}
	
	
	public boolean hasSubItems(){
		return subItems.length > 0;
	}

	public String getDomainCode() {
		return domainCode;
	}

	public void setDomainCode(String domainCode) {
		this.domainCode = domainCode;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getEntireUrl(){
		String url = this.getDomainCode()+"/"+this.getPath()+"?selectedModuleId="+this.getMenuId();
		return url;
		
	}
	
	public boolean isSelected(String url){
		if(StringUtil.isEmpty(url)){
			return false;
		}
		return url.indexOf(this.getDomainCode()+"/"+this.getPath()) >= 0?true:false;
	}
	
}
