package com.thesis.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import com.thesis.dao.AspectWordDao;
import com.thesis.model.AspectWord;
import com.thesis.model.Word;
import com.thesis.util.DataBaseConnection;

public class AspectWordService {
	
	public String getWordsByCategory(int type)
	{
		String res = "";
		
		Connection con = DataBaseConnection.getConnection();
		
		AspectWordDao aspectwordDao = new AspectWordDao();
		
		try {
			res = aspectwordDao.getWordsByCategory(type, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseConnection.close(con);
		}
		
		return res;
	}
	
	public int getCategorytypeByMaxWord(ArrayList<Word> wordlist) 
	{
		int res = 0;
		Connection con = DataBaseConnection.getConnection();
		AspectWordDao aspectWordDao = new AspectWordDao();
		int type = 0;
		ArrayList<AspectWord> aspectwordList;
		try {
			aspectwordList = aspectWordDao.getAll(con);
			for (AspectWord aspectWord : aspectwordList) 
			{
				int finalcount = 0 ;
				String [] aspectwordlist = aspectWord.getAspectWord().split(",");
				for (Word word : wordlist) 
				{
					for (String aspect : aspectwordlist) 
					{
						if(word.getWord().equals(aspect.toLowerCase()))
						{
							finalcount += 1;
							break;
						}
					}
					
				}
				if(finalcount > res)
				{
					res = finalcount;
					type = aspectWord.getType();
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseConnection.close(con);
		}
	
		return type;
	}
	
	public boolean insertAspectWord(AspectWord aspectword)
	{
		boolean flag = false;
		Connection con = DataBaseConnection.getConnection();
		AspectWordDao aspectworddao = new AspectWordDao();
		
		try {
			flag = aspectworddao.insert(aspectword, con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DataBaseConnection.close(con);
		}
		
		return flag;
	}

}
