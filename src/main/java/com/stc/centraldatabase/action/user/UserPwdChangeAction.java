package com.stc.centraldatabase.action.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stc.centraldatabase.dao.user.UserDao;
import com.stc.centraldatabase.model.user.PasswordData;



@ManagedBean(name = "userPwdChangeAction")
@ViewScoped
public class UserPwdChangeAction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3917655302105565608L;
	String userId;
	PasswordData pwdData;
	
	
	
	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public PasswordData getPwdData() {
		return pwdData;
	}



	public void setPwdData(PasswordData pwdData) {
		this.pwdData = pwdData;
	}
	
	public String updatePassword()
	{
		if(!pwdData.getConfirmpwd().equals(pwdData.getNewpwd()))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "New Password and Confirm Password are not valid!"));
			return null;
		}else if(pwdData.getNewpwd().equals(pwdData.getCurrentpwd()))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "Current Password and New Password are not valid!"));
			return null;
		}
		
		if(UserDao.updatePassword(pwdData))
			return "passwordChangeComplete";
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "Current Password is invalid!"));
			return null;
		}
		
	}

@PostConstruct
	public void init()
	{

			pwdData = new PasswordData();
			if(userId == null)
			{
				userId = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("userId");
				pwdData.setUserid(userId);
			}
		
	}

}
