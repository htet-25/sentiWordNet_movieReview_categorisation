package com.stc.centraldatabase.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import com.stc.centraldatabase.model.user.PasswordData;
import com.stc.centraldatabase.model.user.UserInfo;
import com.stc.centraldatatbase.util.DataBaseConnection;



public class UserDao {
	
	public static boolean insert(UserInfo user) {
		Connection con = null;
		PreparedStatement ps = null;
		UserInfo userdto = null;
		String query;
		try {
			con = DataBaseConnection.getConnection();
			
			userdto = LoginDao.validateUser(user.getUserId(), con);
			
			if(userdto==null)
			{
				Calendar calendar = Calendar.getInstance();
			     java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			     
			     query = " insert into USERS_INFO (USER_ID, USER_NAME, CREATED_DATE , MODIFIED_DATE , FIRST_TIME_LOGIN ,"
			     		+ "CREATED_USER_NAME,MODIFIED_USER_NAME,DESCRIPTION,PASSWORD,GENDER,ROLE,ADDRESS,POSITION,STATUS) values (?, ?,?, ?, ?, ?,?,?,"
			     		+ "AES_ENCRYPT(?, '01234567890'),?,?,?,?,?)";
			    	        

			    int i = 1;
				ps = con.prepareStatement(query);
				ps.setString(i++, user.getUserId());
				ps.setString(i++, user.getUserName());
				ps.setDate(i++, startDate);
				ps.setDate(i++, startDate);
				ps.setInt(i++, 0);
				ps.setString(i++,user.getCreatedUserName());
				ps.setString(i++,user.getCreatedUserName());
				ps.setString(i++, user.getDescription());
				ps.setString(i++, "111111");
				ps.setInt(i++, user.getGender());
				ps.setInt(i++, user.getRole());
				ps.setString(i++, user.getAddress());
				ps.setString(i++, user.getPosition());
				ps.setInt(i++, 1);
				ps.execute();
			      
			    con.close();
			    return true;
			}else
			{
				return false;
			}
			
			
		} catch (SQLException ex) {
			System.out.println("User Registration error -->" + ex.getMessage());
			return false;
		} finally {
			DataBaseConnection.close(con);
		}
	}
	
	public static boolean update(UserInfo user) {
		Connection con = null;
		PreparedStatement ps = null;
		String query;
		try {
			con = DataBaseConnection.getConnection();
			
				Calendar calendar = Calendar.getInstance();
			     java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
			    	        
			     query = "Update USERS_INFO set user_id =?,user_name=?,modified_date=?,modified_user_name=?"
			     		+ ",description=?,gender=?,role=?,address=?,position=? where id=?";
			    int i = 1;
				ps = con.prepareStatement(query);
				ps.setString(i++, user.getUserId());
				ps.setString(i++, user.getUserName());
				ps.setDate(i++, startDate);
				ps.setString(i++,user.getModifiedUserName());
				ps.setString(i++, user.getDescription());
				ps.setInt(i++, user.getGender());
				ps.setInt(i++, user.getRole());
				ps.setString(i++, user.getAddress());
				ps.setString(i++, user.getPosition());
				ps.setLong(i++, user.getId());
				ps.execute();
			      
			    con.close();
			    return true;
			
			
			
		} catch (SQLException ex) {
			System.out.println("User Registration Update error -->" + ex.getMessage());
			return false;
		} finally {
			DataBaseConnection.close(con);
		}
	}
	
	public static boolean delete(String userId) {
		Connection con = null;
		PreparedStatement ps = null;
		String query;
		try {
			con = DataBaseConnection.getConnection();
			    	        
			     query = "DELETE FROM USERS_INFO where user_id=?";
			    int i = 1;
				ps = con.prepareStatement(query);
				ps.setString(i++, userId);
				ps.execute();
			      
			    con.close();
			    return true;
			
			
			
		} catch (SQLException ex) {
			System.out.println("User Delete error -->" + ex.getMessage());
			return false;
		} finally {
			DataBaseConnection.close(con);
		}
	}
	
	public static boolean validCurrentPwd(String pwd,String userid,Connection con)
	{
		boolean isValid = false;
		PreparedStatement ps = null;
		String query = "Select * from USERS_INFO where user_id=? and password = AES_ENCRYPT(?, '01234567890')";
		try {
			ps = con.prepareStatement(query);
			  int i = 1;
			ps.setString(i++, userid);
			ps.setString(i++, pwd);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
				isValid = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isValid;
	}
	
	public static boolean updatePassword(PasswordData pwd) {
		Connection con = null;
		PreparedStatement ps = null;
		String query;
		try {
			con = DataBaseConnection.getConnection();
			if(validCurrentPwd(pwd.getCurrentpwd(), pwd.getUserid(), con))
			{

			     query = "Update USERS_INFO set password=AES_ENCRYPT(?, '01234567890')  where user_id=?";
			    int i = 1;
				ps = con.prepareStatement(query);
				ps.setString(i++, pwd.getConfirmpwd());
				ps.setString(i++, pwd.getUserid());
				ps.execute();
			      
			    con.close();
			    return true;
			}else return false;
			
			
			
		} catch (SQLException ex) {
			System.out.println("User Delete error -->" + ex.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		} finally {
			DataBaseConnection.close(con);
		}
	}
	

}
