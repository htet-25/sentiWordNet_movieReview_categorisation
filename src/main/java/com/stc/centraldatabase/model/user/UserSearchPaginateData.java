package com.stc.centraldatabase.model.user;

import java.io.Serializable;
import java.util.List;

public class UserSearchPaginateData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8793922769046576099L;
	
	int count;
	List<UserInfo> userInfoList;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}
	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

}
