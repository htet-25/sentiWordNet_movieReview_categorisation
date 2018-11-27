package com.stc.centraldatabase.action.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.stc.centraldatabase.dao.user.LoginDao;
import com.stc.centraldatabase.dao.user.UserDao;
import com.stc.centraldatabase.dao.user.UserSearchDao;
import com.stc.centraldatabase.model.user.UserInfo;
import com.stc.centraldatatbase.util.CommonEnum;
import com.stc.centraldatatbase.util.SessionUtil;
import com.stc.centraldatatbase.util.SetupData;



@ManagedBean(name = "userUpadatAction")
@ViewScoped
public class UserUpdateAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3356441403577889990L;
	
	String userID;
	UserInfo userData;
	List<SetupData>genderList;
	List<SetupData>roleList;
	
	
	public List<SetupData> getGenderList() {
		return genderList;
	}
	public void setGenderList(List<SetupData> genderList) {
		this.genderList = genderList;
	}
	public List<SetupData> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<SetupData> roleList) {
		this.roleList = roleList;
	}
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
		for(CommonEnum.Role r : CommonEnum.Role.values())
		{
			SetupData setup = new SetupData();
			setup.setLabel(r.description());
			setup.setValue(r.value());
			res.add(setup);
		}
		
		return res;
	}
	
	@PostConstruct
	public void init()
	{

			userData = new UserInfo();
			if(userID == null)
			{
				userID = (String) FacesContext.getCurrentInstance().getExternalContext().getFlash().get("userId");
				userData = UserSearchDao.getUserById(userID);
			}
			this.genderList = getGenderData();
			this.roleList = getRoleData();
		
	}
	
	public String update()
	{
		HttpSession session = SessionUtil.getSession();
		userData.setModifiedUserName((String) session.getAttribute("userid"));
		if(LoginDao.validateUserUpdate(userData.getUserId(),userData.getId()))
		{
			if(UserDao.update(userData)) 
			{
				userData.setGenderlbl((userData.getGender()==1)?CommonEnum.Gender.Male.description():CommonEnum.Gender.Female.description());
				userData.setRolelbl(userData.getRole()==2?CommonEnum.Role.adminRole.description():CommonEnum.Role.userRole.description());
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("userUpdateData", userData);
				return "userRegistrationUpdateSuccess";
			}
				
			else
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "Cannot Update!"));
		}else
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ERROR!", "User id must not duplicate!"));
		}
		
		return null;
	}
	

}
