package com.stc.centraldatabase.dao.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.stc.centraldatabase.model.user.SentiWordNet;
import com.stc.centraldatatbase.util.DataBaseConnection;


public class SentiWordNetDao {
	
	public static ArrayList<SentiWordNet> getWordList(String word) {
		Connection con = null;
		ArrayList<SentiWordNet>res = new ArrayList<>();
		
		try {
			con = DataBaseConnection.getConnection();
			Statement st = con.createStatement();
			 ResultSet rs = st.executeQuery("Select * from senti_wordnet where synset_terms like '%"+word+"%'");


			while (rs.next()) {
				SentiWordNet sentiwordnet = new SentiWordNet();
				sentiwordnet.setId(rs.getInt("id"));
				sentiwordnet.setPoscore(rs.getString("pos_score"));
				sentiwordnet.setNescore(rs.getString("net_score"));
				sentiwordnet.setTerms(rs.getString("synset_terms"));
				res.add(sentiwordnet);	
			}
			return res;
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return res;
		}
	}
	
}
