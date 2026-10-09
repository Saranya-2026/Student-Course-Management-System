package com.jsp.scms;
import java.sql.Connection;
import java.sql.SQLException;
import com.zaxxer.hikari.HikariDataSource;
public class ConnectionPool {
	private static HikariDataSource dataSource;
	static {
		dataSource=new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/jdbcdb");
		dataSource.setUsername("postgres");
		dataSource.setPassword("root");
		
		dataSource.setMaximumPoolSize(10);
		dataSource.setMinimumIdle(3);
		dataSource.setIdleTimeout(5000);
	}
	public static HikariDataSource getDataSource() {
		return dataSource;
	}
	public static Connection getConnection() throws SQLException {
	
		 return dataSource.getConnection();
	}

}
