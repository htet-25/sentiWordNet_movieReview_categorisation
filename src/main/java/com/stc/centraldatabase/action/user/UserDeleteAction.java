package com.stc.centraldatabase.action.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stc.centraldatabase.dao.user.UserDao;
import com.stc.centraldatabase.dao.user.UserSearchDao;
import com.stc.centraldatabase.model.user.UserInfo;




@ManagedBean(name = "userDeletAction")
@ViewScoped
public class UserDeleteAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 767999823111101208L;
	
	String userID;
	
	UserInfo userData;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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
			if(userID == null)
			{
				this.userID = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("userId");
				this.userData = UserSearchDao.getUserById(userID);
			}
		
	}
	
	public String delete()
	{
		
		if(UserDao.delete(userID))
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO!", "Delete User Successfully!"));
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			return "userSearch";
		}
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "User cannot delete!"));
			return null;
		}
		
	}
	
}
