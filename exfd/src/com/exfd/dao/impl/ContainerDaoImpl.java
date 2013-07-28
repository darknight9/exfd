package com.exfd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.exfd.dao.ContainerDao;
import com.exfd.domain.Container;
import com.exfd.domain.Seal;
import com.exfd.util.MysqlUtils;

public class ContainerDaoImpl implements ContainerDao {

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
			throw new RuntimeException(e);
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
			System.out.println(str);
			stmt.executeUpdate(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
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
			throw new RuntimeException(e);
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
		sb.append("httpresult = '").append(container.getHttpresult()).append("' ");

		sb.append("WHERE code = '").append(container.getCode()).append("';");
		return sb.toString();

	}

	@Override
	public Container find(String code) {
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from CONTAINERINFO where code = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, code);
			rs = prepStmt.executeQuery();

			if (rs.next()) {
				Container container = new Container();
				resultSet2Container(rs, container);				
				return container;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
	}

	private void resultSet2Container(ResultSet rs, Container container) throws SQLException {
		container.setCode(rs.getString("code"));
		container.setCompany(rs.getString("company"));
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
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
		return arrayList;
	}

}
