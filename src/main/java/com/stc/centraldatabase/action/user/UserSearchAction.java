package com.stc.centraldatabase.action.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.datatable.DataTable;

import com.stc.centraldatabase.model.user.LazyUserDataModel;
import com.stc.centraldatabase.model.user.UserSearchData;
import com.stc.centraldatatbase.util.CommonEnum;
import com.stc.centraldatatbase.util.SetupData;




@ManagedBean(name = "userSearchAction")
@ViewScoped
public class UserSearchAction implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6474306882073540913L;
	
	UserSearchData userSearchData;
	List<SetupData>roleList;
	LazyUserDataModel userdataModel;

	public LazyUserDataModel getUserdataModel() {
		return userdataModel;
	}

	public void setUserdataModel(LazyUserDataModel userdataModel) {
		this.userdataModel = userdataModel;
	}

	public List<SetupData> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<SetupData> roleList) {
		this.roleList = roleList;
	}

	public UserSearchData getUserSearchData() {
		return userSearchData;
	}

	public void setUserSearchData(UserSearchData userSearchData) {
		this.userSearchData = userSearchData;
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
	
	public void reset(String targetDataTable) {
		resetPagination(targetDataTable);
		LoadData();
	}

	
	private void resetPagination(String targetDataTable) {
		DataTable d = (DataTable) FacesContext.getCurrentInstance().getViewRoot()
				.findComponent("userSearchForm:userSearchTable");
		d.setFirst(0);
	}
	
	public void search(String targetDataTable) {

		// Check Joined Date From should be earlier than Joined Date To.
			// Reset pagination and do the lazy data model process.
			resetPagination(targetDataTable);
			userdataModel = new LazyUserDataModel(userSearchData);
		
	}
	
	@PostConstruct
	public void LoadData()
	{

			roleList = getRoleData();
			userSearchData = new UserSearchData();
			userdataModel = new LazyUserDataModel(userSearchData);
		
	}
	
	public String edit(String id) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("userId", id);
		return "userRegistrationUpdate";
	}

	/**
	 * Put the userId as parameter. Go to user delete screen.
	 * 
	 * @param userId
	 * @return action outcome
	 */
	public String delete(String id) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("userId", id);
		return "userDelete";
	}
	
	public String detail(String id) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("userId", id);
		return "userDetail";
	}
	
	public String changePwd(String id) {
		FacesContext.getCurrentInstance().getExternalContext().getFlash().put("userId", id);
		return "passwordChange";
	}

}
