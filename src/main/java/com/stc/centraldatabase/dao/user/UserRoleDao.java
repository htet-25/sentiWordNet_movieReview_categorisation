package com.stc.centraldatabase.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stc.centraldatatbase.util.DataBaseConnection;
import com.stc.centraldatatbase.util.SetupData;



public class UserRoleDao {
	
	public static ArrayList<SetupData> getRoleList()
	{
		Connection con = DataBaseConnection.getConnection();
		ArrayList<SetupData> res = new ArrayList<SetupData>();
		Statement st;
		try {
			st = con.createStatement();
			String query = "Select * from USER_ROLE ";
			ResultSet rs = st.executeQuery(query);
			while(rs.next())
			{
				SetupData setup = new  SetupData();
				setup.setLabel(rs.getString("ROLE_NAME"));
				setup.setValue(rs.getInt("ROLE_ID"));
				res.add(setup);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally{
			DataBaseConnection.close(con);
		}
		
		
		return res;
	}

}
