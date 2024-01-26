package com.sist.di;
import java.util.*;
import java.sql.*;
public class CommensDAO {
	private Connection conn;
	private PreparedStatement ps;
	private String url,username,password;
	
	//���1 : ������ DI
	public CommensDAO(String driver)
	{
		try {
			Class.forName(driver);
		}catch(Exception ex) {}
	}
	
	//���2 : set DI
	public void setUrl(String url) {
		this.url = url;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//����
	public Connection getConnection()
	{
		try {
			conn=DriverManager.getConnection(url,username,password);
		}catch(Exception ex) {}
		
		return conn;
	}
	//����
	public void disConnection(Connection conn, PreparedStatement ps)
	{
		try {
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
}


