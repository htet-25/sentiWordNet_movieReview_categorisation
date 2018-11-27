package com.stc.centraldatabase.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.stc.centraldatabase.model.user.UserInfo;
import com.stc.centraldatabase.model.user.UserSearchData;
import com.stc.centraldatabase.model.user.UserSearchPaginateData;
import com.stc.centraldatatbase.util.CommonEnum;
import com.stc.centraldatatbase.util.DataBaseConnection;




public class UserSearchDao {
	
	public static UserSearchPaginateData find(UserSearchData usearch)
	{
		UserSearchPaginateData res = new UserSearchPaginateData();
		String filter = getCriteria(usearch);
		List<UserInfo> resList = new ArrayList<UserInfo>();
		Connection con = null;
		UserInfo user;
		String query = "Select id,user_id,user_name,position,role,address from USERS_INFO"+filter;
		
		
		try {
			con = DataBaseConnection.getConnection();
			Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery(query);
			 
			 while(rs.next())
			 {
				 user = new UserInfo();
				 user.setUserId(rs.getString("user_id"));
				 user.setUserName(rs.getString("user_name"));
				 user.setId(rs.getInt("id"));
				 user.setRole(rs.getInt("role"));
				 user.setRolelbl(user.getRole()==2?CommonEnum.Role.adminRole.description():CommonEnum.Role.userRole.description());
				 user.setAddress(rs.getString("address"));
				 user.setPosition(rs.getString("position"));
				 resList.add(user);
			 }
			
			res.setCount(getTotalUserCount(usearch,con));
			res.setUserInfoList(resList);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseConnection.close(con);
		}
			
		return res;
	}
	
	public static int getTotalUserCount(UserSearchData search,Connection con)
	{
		int count = 0;
		String filter = getCountCriteria(search);
		String query = "Select count(*) as count from USERS_INFO "+ filter;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			if(rs.next())
				count = rs.getInt("count");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return count;
	}
	
	public static UserInfo getUserById(String id)
	{
		UserInfo user = new UserInfo();
		Connection con = null;
		String query = "Select *, CAST(AES_DECRYPT(PASSWORD, '01234567890') AS CHAR(50)) password_decrypt from USERS_INFO where user_id = '"+id+"' and status = "+CommonEnum.UserStatus.active.value();
		
		con = DataBaseConnection.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			 ResultSet rs = st.executeQuery(query);
			 if(rs.next())
			 {
				 user.setUserId(rs.getString("user_id"));
				 user.setUserName(rs.getString("user_name"));
				 user.setId(rs.getInt("id"));
				 user.setRole(rs.getInt("role"));
				 user.setGender(rs.getInt("gender"));
				 user.setRolelbl(user.getRole()==2?CommonEnum.Role.adminRole.description():CommonEnum.Role.userRole.description());
				 user.setAddress(rs.getString("address"));
				 user.setPosition(rs.getString("position"));
				 user.setGenderlbl(user.getGender() == 0 ? "":(user.getGender()==1?CommonEnum.Gender.Male.description():CommonEnum.Gender.Female.description()));			
				 user.setDescription(rs.getString("description"));
				 user.setPassword(rs.getString("password_decrypt"));
			 }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseConnection.close(con);
		}
		
		
		return user;
	}
	
	public static String getCriteria(UserSearchData criteria)
	{
		String filter = " where status="+CommonEnum.UserStatus.active.value();
		
		if(!criteria.getPosition().equals(""))
			
		{
			filter += " and position like %'"+criteria.getPosition()+"%'";
			
		}
		if(!criteria.getRole().equals(""))
		{
			filter += " and role="+criteria.getRole();
		}
		if(!criteria.getUserid().equals(""))
		{
			
			filter += " and user_id like '%"+criteria.getUserid()+"%'";
		}
		if(!criteria.getUserName().equals(""))
		{
			filter += " and user_name like '%"+criteria.getUserName()+"%'";
		}
		if(!criteria.getAddress().equals(""))
		{
			filter += " and address like %'"+criteria.getAddress()+"%'";
		}
		
		filter += " limit "+criteria.getOffset()+","+criteria.getLimit();
		
		return filter;
	}
	
	public static String getCountCriteria(UserSearchData criteria)
	{
		String filter = " where status="+CommonEnum.UserStatus.active.value();
		
		if(!criteria.getPosition().equals(""))
			
		{
			filter += " and position like %'"+criteria.getPosition()+"%'";
			
		}
		if(!criteria.getRole().equals(""))
		{
			filter += " and role="+criteria.getRole();
		}
		if(!criteria.getUserid().equals(""))
		{
			
			filter += " and user_id like '%"+criteria.getUserid()+"%'";
		}
		if(!criteria.getUserName().equals(""))
		{
			filter += " and user_name like '%"+criteria.getUserName()+"%'";
		}
		if(!criteria.getAddress().equals(""))
		{
			filter += " and address like %'"+criteria.getAddress()+"%'";
		}
		
		
		return filter;
	}
	
	

}
