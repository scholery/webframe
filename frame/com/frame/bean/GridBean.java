package com.frame.bean;

import java.io.Serializable;

/**
 * 分页
 */
public class GridBean implements Serializable {

	private int page;
	private int records;
	private int pageCount;
	private Object[] rows = new Object[] {};
	private int total;

	/**
	 * Get records 
	 * 记录总数
	 * @return records
	 */
	public int getRecords() {
		return this.records;
	}

	/**
	 * Set records 
	 * 记录总数
	 * @parameter records
	 */
	public void setRecords(int records) {
		this.records = records;
	}

	/**
	 * Get page 当前页码，从1开始
	 * @return page
	 */
	public int getPage() {
		if(this.page <= 0){
			return 1;
		}
		return this.page;
	}

	/**
	 * Set page 当前页码，从1开始
	 * @parameter page
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * Get pageCount 每页显示多少条
	 * @return pageCount
	 */
	public int getPageCount() {
		if(this.pageCount <= 0){
			return 10;
		}
		return this.pageCount;
	}

	/**
	 * Set pageCount 每页显示多少条
	 * @parameter pageCount
	 */
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * Get data 当前页数据
	 * 
	 * @return data
	 */
	public Object[] getRows() {
		return this.rows;
	}

	/**
	 * 当前页数据
	 * @parameter rows
	 */
	public void setRows(Object[] rows) {
		this.rows = rows;
	}

	/**
	 * 总页数
	 * @return
	 */
	public int getTotal() {
		setTotal();
		return this.total;
	}

	/**
	 * 总页数
	 */
	public void setTotal() {
		if (this.records % this.getPageCount() == 0) {
			this.total = (this.records / this.getPageCount());
		} else {
			this.total = (this.records / this.getPageCount()) + 1;
		}
	}
	
	 public int getStart(){
		 int start = 0;
		 if(this.getPage() >= 1){
				start = (this.getPage() - 1) * this.getPageCount() ;
			}
		 return start;
	 }

}