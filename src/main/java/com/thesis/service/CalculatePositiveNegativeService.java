package com.thesis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thesis.dao.SentiWordNetDao;
import com.thesis.model.SentiWordNet;
import com.thesis.model.Word;
import com.thesis.util.DataBaseConnection;

public class CalculatePositiveNegativeService {
	
	public int getReviewWordCount(ArrayList<Word> worddatalist)
	{
		int tncount = 0;
		for (Word word : worddatalist) 
		{
			SentiWordNetDao wordnetDao = new SentiWordNetDao();
			Connection con = DataBaseConnection.getConnection();
			ArrayList<SentiWordNet> wordnetList;
			try {
				
				wordnetList = wordnetDao.getWordList(word.getWord(),con);
				for (SentiWordNet sentiWordNet : wordnetList) {
					String[] originalwords = sentiWordNet.getTerms().split(" ");
				
						for(int j=0; j<originalwords.length; j++)
						{
							String[] originalword = originalwords[j].split("#");
							if(word.getWord().equals(originalword[0]))
							{
								if(!sentiWordNet.getNescore().equals("0") || !sentiWordNet.getPoscore().equals("0"))
								{
									if(Double.parseDouble(sentiWordNet.getNescore()) > Double.parseDouble(sentiWordNet.getPoscore()))
									{
										tncount += 1;	
									}
								}
								break;
							}
						}				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally
			{
				DataBaseConnection.close(con);
			}
		
		}
		return tncount;
	}
	

}
