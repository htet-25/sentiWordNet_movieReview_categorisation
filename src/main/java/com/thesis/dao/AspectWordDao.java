package com.thesis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.thesis.model.AspectWord;
import com.thesis.util.DataBaseConnection;

public class AspectWordDao {
	
	public String getWordsByCategory(int type)
	{
		String res = "";
		Connection con = null;
		con = DataBaseConnection.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery("Select aspect_word from aspect_word where category ="+type);

			while (rs.next()) {
				res = rs.getString("aspect_word");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public  boolean insert(AspectWord aspectWord) {
		Connection con = null;
		PreparedStatement ps = null;
		String query;
		
		try {
			con = DataBaseConnection.getConnection();
			     
			query = " insert into aspect_word (category, aspect_word) values (?,?)";
			    	        
			    int i = 1;
				ps = con.prepareStatement(query);
				ps.setInt(i++, aspectWord.getType());
				ps.setString(i++, aspectWord.getAspectWord());
				ps.execute();				      
			    con.close();
			    return true;		
		} catch (SQLException ex) {
			System.out.println("doc Registration error -->" + ex.getMessage());
			return false;
		} finally {
			DataBaseConnection.close(con);
		}
	}

}
