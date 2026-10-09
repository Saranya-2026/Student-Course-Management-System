package com.jsp.scms;
import java.sql.Connection;
import java.sql.SQLException;
public class Connections {
	public static void main(String[]args) {
		try {	
		 Connection connection = ConnectionPool.getDataSource().getConnection();
			System.out.println(connection);
			System.out.println("connected successfully");	
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
