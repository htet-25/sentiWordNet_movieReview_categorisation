package com.stc.centraldatabase.action.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.stc.centraldatabase.dao.user.UserDao;
import com.stc.centraldatabase.dao.user.UserRoleDao;
import com.stc.centraldatabase.model.user.UserInfo;
import com.stc.centraldatatbase.util.CommonEnum;
import com.stc.centraldatatbase.util.SessionUtil;
import com.stc.centraldatatbase.util.SetupData;



@ManagedBean(name = "userRegisterAction")
@ViewScoped
public class UserRegistration implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8960748410953236774L;
	List<SetupData>genderList;
	List<SetupData>roleList;
	
	@ManagedProperty(value = "#{userInfo}")
	UserInfo user;
	
	
	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<SetupData> getGenderData()
	{
		List<SetupData>res = new ArrayList<SetupData>() ;
		for(CommonEnum.Gender g : CommonEnum.Gender.values())
		{
			SetupData setup = new SetupData();
			setup.setLabel(g.description());
			setup.setValue(g.value());
			res.add(setup);
		}
		
		return res;
	}
	
	public List<SetupData> getRoleData()
	{
		List<SetupData>res = new ArrayList<SetupData>() ;
		res = UserRoleDao.getRoleList();
			
		return res;
	}

	public List<SetupData> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SetupData> roleList) {
		this.roleList = roleList;
	}

	public List<SetupData> getGenderList() {
		return genderList;
	}

	public void setGenderList(List<SetupData> genderList) {
		this.genderList = genderList;
	}

	@PostConstruct
	public void init()  
	{
	
			this.user = new UserInfo();
			

	}
	
	public String register()
	{
		HttpSession session = SessionUtil.getSession();
		user.setCreatedUserName((String) session.getAttribute("userid"));
		if(UserDao.insert(user))
		{	
			user.setGenderlbl((user.getGender()==1)?CommonEnum.Gender.Male.description():CommonEnum.Gender.Female.description());
			user.setRolelbl(user.getRole()==2?CommonEnum.Role.adminRole.description():CommonEnum.Role.userRole.description());
			FacesContext.getCurrentInstance().getExternalContext().getFlash().put("userRegisterData", user);
			return "UserRegistrationSuccess";
		}			
		else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "User's id are duplicate!"));
			return null;
		}
	}
	
}
