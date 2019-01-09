package com.thesis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.thesis.model.AspectWord;

public class AspectWordDao {
	
	public String getWordsByCategory(int type, Connection con) throws SQLException
	{
		String res = "";
	
		Statement st;
	
			st = con.createStatement();
			ResultSet rs = st.executeQuery("Select aspect_word from aspect_word where category ="+type);

			while (rs.next()) {
				res = rs.getString("aspect_word");
			}
		
		
		return res;
	}
	
	public ArrayList<AspectWord> getAll(Connection con) throws SQLException
	{
		 ArrayList<AspectWord> res = new ArrayList<>();
	
		Statement st;
	
			st = con.createStatement();
			ResultSet rs = st.executeQuery("Select * from aspect_word");

			while (rs.next()) 
			{
				AspectWord aspectword = new AspectWord();
				aspectword.setId(rs.getInt("id"));
				aspectword.setAspectWord(rs.getString("aspect_word"));
				aspectword.setType(rs.getInt("category"));
				res.add(aspectword);
			}
				
		return res;
	}
	
	public  boolean insert(AspectWord aspectWord,Connection con) throws SQLException {
		PreparedStatement ps = null;
		String query = "";
		
     
			query = " insert into aspect_word (category, aspect_word) values (?,?)";
			    	        
			    int i = 1;
				ps = con.prepareStatement(query);
				ps.setInt(i++, aspectWord.getType());
				ps.setString(i++, aspectWord.getAspectWord());
				ps.execute();				      
			    con.close();
			    return true;		
		
	}

}
