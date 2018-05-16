package com.jinhui.model;

import java.io.Serializable;
import java.util.List;

public class OrganizationResponse implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2352915859146270151L;

	private List<Organization> list;
	
	private int totalCount;

	public List<Organization> getList() {
		return list;
	}

	public void setList(List<Organization> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
