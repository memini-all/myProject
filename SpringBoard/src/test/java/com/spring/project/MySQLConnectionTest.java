package com.spring.project;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest 
{
	public static final String DRIVE = "com.mysql.cj.jdbc.Driver";
	public static final String URL = 
			"jdbc:mysql://192.168.137.128:3306/test_db01?useSSL=false&serverTimezone=UTC";
	public static final String USER = "scott";
	public static final String PW = "tiger";
	
	@Test
	public void testConnection() throws Exception{
		
		Class.forName(DRIVE);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)) {
			
			System.out.println(con);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
