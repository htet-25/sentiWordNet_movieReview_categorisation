package com.thesis.util;


	import java.io.FileInputStream;
	import java.io.InputStream;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.util.Properties;
	import javax.faces.context.FacesContext;
	public class DataBaseConnection {
		
		public static Connection getConnection() {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String roothpath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/")+"DataBaseProperties/dbconfig.properties";
				Properties prop = new Properties();
				InputStream input = new FileInputStream(roothpath);
				prop.load(input);
				Connection con = null;
				if(prop != null)
				{
					 con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/"+prop.getProperty("dbname"), prop.getProperty("username"),  prop.getProperty("password"));
				}
				
				
				return con;
			} catch (Exception ex) {
				System.out.println("Database.getConnection() Error -->"
						+ ex.getMessage());
				return null;
			}
		}
		public static void close(Connection con) {
			try {
				con.close();
			
			} catch (Exception ex) {
			}
		}
		
		
	

}
