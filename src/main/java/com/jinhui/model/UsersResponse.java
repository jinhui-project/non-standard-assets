package com.jinhui.model;

import java.io.Serializable;
import java.util.List;

public class UsersResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361381549068783723L;

	private List<Users> list;
	
	private int totalCount;

	public List<Users> getList() {
		return list;
	}

	public void setList(List<Users> list) {
		this.list = list;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
