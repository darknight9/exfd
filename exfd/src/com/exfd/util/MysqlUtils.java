package com.exfd.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// 连接mysql数据库的辅助类.
public class MysqlUtils {

	// 第一种连接方式.
	private static String dbUrl = "jdbc:mysql://localhost:3306/SealDB";
	private static String dbUser = "root";
	private static String dbPwd = "kanekane.1";
	
	// 第二种连接方式.
	private static Context ctx;
	private static DataSource ds;

	static {
		try {
			// 第一种连接方式.
			Class.forName("com.mysql.jdbc.Driver");
			
			// 第二种连接方式.
			/*
			ctx = new InitialContext();
			if (ctx == null){
				throw new Exception("No Context");
			}
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/SealDB");
			*/
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public static Connection getConnection() throws Exception {
		
		// 第一种连接方式.
		return java.sql.DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		
		// 第二种连接方式.
		//return ds.getConnection();
	}

	public static void closeConnection(Connection con) {
		try {
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closePrepStmt(PreparedStatement prepStmt) {
		try {
			if (prepStmt != null)
				prepStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeStmt(Statement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeResultSet(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
