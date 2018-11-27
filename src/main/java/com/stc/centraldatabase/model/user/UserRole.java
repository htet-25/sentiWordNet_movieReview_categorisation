package com.stc.centraldatabase.model.user;

import java.io.Serializable;

public class UserRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4989273276929087700L;
	int roleId;
	String roleLable;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleLable() {
		return roleLable;
	}
	public void setRoleLable(String roleLable) {
		this.roleLable = roleLable;
	}

}
