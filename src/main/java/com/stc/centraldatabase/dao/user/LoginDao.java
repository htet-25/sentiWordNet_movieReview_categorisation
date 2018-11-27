package com.stc.centraldatabase.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.stc.centraldatabase.model.user.UserInfo;
import com.stc.centraldatatbase.util.CommonEnum;
import com.stc.centraldatatbase.util.DataBaseConnection;


public class LoginDao {
	
	public static UserInfo validate(String user, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		UserInfo userdto = null;
		try {
			con = DataBaseConnection.getConnection();
			ps = con.prepareStatement("Select * from USERS_INFO where user_id = ? and password = AES_ENCRYPT(?, '01234567890') and status = "+CommonEnum.UserStatus.active.value());
			ps.setString(1, user);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userdto = new UserInfo();
				userdto.setId(rs.getInt("id"));
				userdto.setUserId(rs.getString("user_id"));
				userdto.setUserName(rs.getString("user_name"));
				userdto.setRole(rs.getInt("role"));
				return userdto;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return userdto;
		} finally {
			DataBaseConnection.close(con);
		}
		return userdto;
	}
	
	public static UserInfo validateUser(String user,Connection con) {
		PreparedStatement ps = null;
		UserInfo userdto = null;
		try {
			con = DataBaseConnection.getConnection();
			ps = con.prepareStatement("Select * from USERS_INFO where user_id = ? and status="+CommonEnum.UserStatus.active.value());
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userdto = new UserInfo();
				userdto.setId(rs.getInt("id"));
				userdto.setUserId(rs.getString("user_id"));
				userdto.setUserName(rs.getString("user_name"));
				return userdto;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return userdto;
		} 
		return userdto;
	}
	
	public static boolean validateUserUpdate(String user,long id) {
		PreparedStatement ps = null;
		UserInfo userdto = null;
		try {
			Connection con = DataBaseConnection.getConnection();
			ps = con.prepareStatement("Select * from USERS_INFO where user_id = ? and id <> ? and status = "+CommonEnum.UserStatus.active.value());
			ps.setString(1, user);
			ps.setLong(2, id);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) 
			{
				userdto = new UserInfo();
				userdto.setId(rs.getInt("id"));
				userdto.setUserId(rs.getString("user_id"));
				userdto.setUserName(rs.getString("user_name"));
				return false;
			}
			else return true;
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} 
		
	}

}
