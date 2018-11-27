package com.stc.centraldatabase.model.user;

import java.io.Serializable;

public class UserSearchData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6926228751031854183L;
	
	String userid;
	String userName;
	String role;
	String address;
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

	String position;
	int offset;
	int limit;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public UserSearchData()
	{
		this.position = "";
		this.role = "";
		this.userid = "";
		this.offset = 0;
		this.limit = 0;
		this.userName = "";
		this.address = "";
	}
	
	

}
