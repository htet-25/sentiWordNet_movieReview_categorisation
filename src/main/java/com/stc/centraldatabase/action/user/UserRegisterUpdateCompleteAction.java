package com.stc.centraldatabase.action.user;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.stc.centraldatabase.model.user.UserInfo;




/**
 * UserRegistrationCompleteActionBean.java
 *
 * @author Ei Ei Swe Minn
 */
@ManagedBean(name = "userRegisterUpdateCompleteAction")
@ViewScoped
public class UserRegisterUpdateCompleteAction implements Serializable {

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

			this.user = (UserInfo) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("userUpdateData");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		
	}
}
