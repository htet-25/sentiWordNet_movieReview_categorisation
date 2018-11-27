package com.stc.centraldatabase.action.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stc.centraldatabase.model.user.UserInfo;




@ManagedBean(name = "userRegisterCompleteAction")
@ViewScoped
public class UserRegisterCompleteAction implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 3949168443374966040L;

	UserInfo user;
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	@PostConstruct
	public void init() 
	{
			this.user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("userRegisterData");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
	}
}
