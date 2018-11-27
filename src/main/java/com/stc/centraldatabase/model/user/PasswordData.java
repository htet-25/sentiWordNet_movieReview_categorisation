package com.stc.centraldatabase.model.user;

import java.io.Serializable;

public class PasswordData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4087140420351847301L;
	
	String currentpwd;
	String newpwd;
	String confirmpwd;
	String userid;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCurrentpwd() {
		return currentpwd;
	}
	public void setCurrentpwd(String currentpwd) {
		this.currentpwd = currentpwd;
	}
	public String getNewpwd() {
		return newpwd;
	}
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	public String getConfirmpwd() {
		return confirmpwd;
	}
	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
	public PasswordData()
	{
		this.confirmpwd = "";
		this.currentpwd = "";
		this.newpwd = "";
		this.userid = "";
	}

}
