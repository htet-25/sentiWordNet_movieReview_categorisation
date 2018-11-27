package com.stc.centraldatabase.model.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userInfo")
@SessionScoped
public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6891237132588129983L;
	

	String userName;
	long id;
	String createdDate;
	String modifiedDate;
	boolean firstTimeLogin;
	String createdUserName;
	String modifiedUserName;
	String password;
	String description;
	String userId;
	int gender;
	int role;
	String position;
	String genderlbl;
	String rolelbl;
	int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	String address;
	
	public String getPosition() {
		return position;
	}
	
	public String getDescription() {
		return description;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public boolean isFirstTimeLogin() {
		return firstTimeLogin;
	}
	public void setFirstTimeLogin(boolean firstTimeLogin) {
		this.firstTimeLogin = firstTimeLogin;
	}
	public String getCreatedUserName() {
		return createdUserName;
	}
	public void setCreatedUserName(String createdUserName) {
		this.createdUserName = createdUserName;
	}
	public String getModifiedUserName() {
		return modifiedUserName;
	}
	public void setModifiedUserName(String modifiedUserName) {
		this.modifiedUserName = modifiedUserName;
	}

	public UserInfo()
	{
		this.createdDate = "";
		this.createdUserName = "";
		this.id = 0;
		this.userId = "";
		this.firstTimeLogin = false;
		this.createdUserName = "";
		this.modifiedDate = "";
		this.modifiedUserName = "";
		this.userName = "";
		this.password = "";
		this.description = "";
		this.gender = 0;
		this.role = 1;
		this.position = "";
		this.address = "";
		this.genderlbl = "";
		this.rolelbl = "";
		this.status = 0;
	}
	public String getGenderlbl() {
		return genderlbl;
	}
	public void setGenderlbl(String genderlbl) {
		this.genderlbl = genderlbl;
	}
	public String getRolelbl() {
		return rolelbl;
	}
	public void setRolelbl(String rolelbl) {
		this.rolelbl = rolelbl;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	

}
