package com.exfd.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.ContainerDao;
import com.exfd.domain.Container;
import com.exfd.util.MysqlUtils;

public class ContainerDaoImpl implements ContainerDao {

	private static Logger logger = LogManager.getLogger();

	// 1980-09-09 12:03:04
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void add(Container container) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = createInsertStatement(container);
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}
	
	private String createInsertStatement(Container container) {
		StringBuilder sb = new StringBuilder(1000);
		sb.append("INSERT INTO `CONTAINERINFO` VALUES ('");
		sb.append(container.getCode()).append("','");
		sb.append(container.getCompany()).append("','");
		sb.append(df.format(new Date())).append("','");
		sb.append(df.format(new Date())).append("','");
		
		sb.append(container.getDownload()).append("','");
		sb.append(container.getNotfound()).append("','");
		sb.append(container.getParseerror()).append("','");
		
		sb.append(container.getTableString()).append("','");
		sb.append(container.getJsonString()).append("','");
		sb.append(container.getHttpresult()).append("');");
	
		return sb.toString();
	}

	@Override
	public void delete(String code) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = "DELETE FROM `CONTAINERINFO` WHERE code = '"+code+"'";
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}

	}

	@Override
	public void update(Container container) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = createUpdateStatment(container);
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}

	}

	private String createUpdateStatment(Container container) {
		StringBuilder sb = new StringBuilder(2000);
		sb.append("UPDATE `CONTAINERINFO` SET ");
		//sb.append("code = '").append(container.getCode()).append("', ");
		sb.append("company = '").append(container.getCompany()).append("', ");
		
		// ctime不会在更新记录时候更新.
		//sb.append("ctime = '").append(df.format(seal.getCtime())).append("', ");
		sb.append("mtime = '").append(df.format(new Date())).append("', ");
		
		sb.append("download = '").append(container.getDownload()).append("', ");
		sb.append("notfound = '").append(container.getNotfound()).append("', ");
		sb.append("parseerror = '").append(container.getParseerror()).append("', ");
		
		sb.append("tablestring = '").append(container.getTableString()).append("', ");
		sb.append("jsonstring = '").append(container.getJsonString()).append("', ");
		sb.append("httpresult = '").append(container.getHttpresult()).append("' ");

		sb.append("WHERE code = '").append(container.getCode()).append("';");
		return sb.toString();

	}

	@Override
	public Container find(String code) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from CONTAINERINFO where code = '"+code+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectStatement);
			
			if (rs.next()) {
				Container container = new Container();
				resultSet2Container(rs, container);				
				return container;
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
		return null;
	}

	private void resultSet2Container(ResultSet rs, Container container) throws SQLException {
		container.setCode(rs.getString("code"));
		container.setCompany(rs.getString("company"));
		container.setCtime(rs.getTimestamp("ctime"));
		container.setMtime(rs.getTimestamp("mtime"));
		
		container.setDownload(rs.getInt("download"));
		container.setNotfound(rs.getInt("notfound"));
		container.setParseerror(rs.getInt("parseerror"));

		container.setTableString(rs.getString("tablestring"));
		container.setJsonString(rs.getString("jsonstring"));
		container.setHttpresult(rs.getString("httpresult"));
		
	}

	@Override
	public ArrayList<Container> list() {

		ArrayList<Container> arrayList = new ArrayList<Container>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from CONTAINERINFO";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectStatement);
			while (rs.next()) {
				Container container = new Container();
				resultSet2Container(rs, container);			
				arrayList.add(container);
			}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
		return arrayList;
	}

	@Override
	public void updateOrAdd(Container container) {
		Connection con = null;
		Statement stmtQuery = null;
		Statement stmtUpdate = null;
		ResultSet rs = null;
		String code = container.getCode();
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from CONTAINERINFO where code = '"+code+"'";
			stmtQuery = con.createStatement();
			rs = stmtQuery.executeQuery(selectStatement);

			String str = null;
			if (rs.next()) {
				str = createUpdateStatment(container);				
			} else {
				str = createInsertStatement(container);
			}
			stmtUpdate = con.createStatement();
			stmtUpdate.executeUpdate(str);

		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closeStmt(stmtUpdate);
			MysqlUtils.closeStmt(stmtQuery);
			MysqlUtils.closeConnection(con);
		}
		
	}

}
