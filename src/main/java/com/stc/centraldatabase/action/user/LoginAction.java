package com.stc.centraldatabase.action.user;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.stc.centraldatabase.model.user.UserInfo;

@ManagedBean(name = "loginAction")
@SessionScoped
public class LoginAction implements Serializable{

	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -4930794008257080949L;
	
	String userid;
	String pwd;
	int role;
	
	@ManagedProperty(value = "#{userInfo}")
	UserInfo user;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPwd() { 
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	public String login()
	{
		return "home";
	}

}
