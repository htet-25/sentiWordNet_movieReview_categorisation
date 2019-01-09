package com.thesis.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.thesis.model.SentiWordNet;


public class SentiWordNetDao {
	
	public ArrayList<SentiWordNet> getWordList(String word , Connection con) throws SQLException {
		ArrayList<SentiWordNet>res = new ArrayList<>();

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
		
	}
	
}
