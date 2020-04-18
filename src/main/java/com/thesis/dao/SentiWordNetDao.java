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
		ArrayList<SentiWordNet> negativewords = new ArrayList<>();
		ArrayList<SentiWordNet> positivewords = new ArrayList<>();
			Statement st = con.createStatement();
			Statement st1 = con.createStatement();
			double tp = 0;
			int count = 0;
			double tn = 0;
			String term = "";
			word = word.replaceAll("\\'", "");
			 ResultSet rs = st.executeQuery("Select * from senti_wordnet where synset_terms like '%"+word+"%' ORDER BY pos_score");
			 ResultSet rsnegative = st1.executeQuery("Select * from senti_wordnet where synset_terms like '%"+word+"%' ORDER BY net_score");

//			 ResultSet rs = st.executeQuery("Select * from senti_wordnet where synset_terms like '%"+word+"%' and id = (select max(id) from "
//			 		+ "senti_wordnet where synset_terms like '%"+word+"%')");

			while (rs.next()) {
				SentiWordNet sentiwordnet = new SentiWordNet();
				sentiwordnet.setId(rs.getInt("id"));
				sentiwordnet.setPoscore(rs.getString("pos_score"));
				sentiwordnet.setNescore(rs.getString("net_score"));
				sentiwordnet.setTerms(rs.getString("synset_terms"));
				positivewords.add(sentiwordnet);	
//				count += 1;
//				tp += Double.parseDouble(rs.getString("pos_score"));
//				tn += Double.parseDouble(rs.getString("net_score"));
//				term = rs.getString("synset_terms");
			}
			rs.close();
			
			while(rsnegative.next())
			{
				SentiWordNet sentiwordnet = new SentiWordNet();
				sentiwordnet.setId(rsnegative.getInt("id"));
				sentiwordnet.setPoscore(rsnegative.getString("pos_score"));
				sentiwordnet.setNescore(rsnegative.getString("net_score"));
				sentiwordnet.setTerms(rsnegative.getString("synset_terms"));
				negativewords.add(sentiwordnet);	

			}
			rsnegative.close();
			
			if(positivewords.size()>0 && negativewords.size()>0)
			{
				SentiWordNet positiveword = new SentiWordNet();
				SentiWordNet negativeword = new SentiWordNet();
				if(positivewords.size()%2 == 0)
				{
					positiveword = positivewords.get(positivewords.size()/2);
				}else
				{
					positiveword = positivewords.get(positivewords.size()/2);
				}
				
				if(negativewords.size()%2 == 0)
				{
					negativeword = negativewords.get((negativewords.size()/2));
				}else
				{
					negativeword = negativewords.get(negativewords.size()/2);
				}
				
				if(Double.parseDouble(positiveword.getPoscore()) > Double.parseDouble(negativeword.getNescore()))
				{
					res.add(positiveword);
				}else res.add(negativeword);
				
			}else if(positivewords.size() > 0 && negativewords.size() == 0)
			{
				if(positivewords.size()%2 == 0)
				{
					res.add(positivewords.get(positivewords.size()/2));
				}else
				{
					res.add(positivewords.get((positivewords.size()/2)-1));
				}			
			}else if(negativewords.size() > 0 && positivewords.size() == 0)
			{
				if(negativewords.size()%2 == 0)
				{
					res.add(negativewords.get((negativewords.size()/2)));
				}else
				{
					res.add(negativewords.get((negativewords.size()/2)-1));
				}
				
			}
			
//			if(!term.equals(""))
//			{
//				if(rs != null && count>0)
//				{
//					tp = tp/count;
//					tn = tn/count;
//				}
//				SentiWordNet sentiwordnet = new SentiWordNet();
//				sentiwordnet.setPoscore(String.valueOf(tp));
//				sentiwordnet.setNescore(String.valueOf(tn));
//				sentiwordnet.setTerms(term);
//				res.add(sentiwordnet);	
//			}
			con.close();
			
			return res;
		
	}
	
}
