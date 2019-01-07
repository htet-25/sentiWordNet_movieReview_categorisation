package com.thesis.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.thesis.dao.AspectWordDao;
import com.thesis.model.AspectWord;
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
