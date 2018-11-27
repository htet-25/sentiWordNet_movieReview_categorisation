package com.stc.centraldatabase.action.user;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.stc.centraldatabase.dao.user.UserSearchDao;
import com.stc.centraldatabase.model.user.UserInfo;



@ManagedBean(name = "userDetailAction")
@ViewScoped
public class UserDetailAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4372970301156244048L;
	String userid;
	

	UserInfo userData;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public UserInfo getUserData() {
		return userData;
	}

	public void setUserData(UserInfo userData) {
		this.userData = userData;
	}
	
	
	@PostConstruct
	public void init()
	{

			userData = new UserInfo();
			if(userid == null)
			{
				userid = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("userId");
				userData = UserSearchDao.getUserById(userid);
			}
		
	}
	
}
