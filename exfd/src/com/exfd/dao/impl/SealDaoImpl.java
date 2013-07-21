package com.exfd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.exfd.dao.SealDao;
import com.exfd.domain.Seal;
import com.exfd.util.mysqlUtils;

public class SealDaoImpl implements SealDao {

	// 1980-09-09
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// 添加一个seal记录.
	/* (non-Javadoc)
	 * @see com.exfd.dao.impl.SealDao#add(com.exfd.domain.Seal)
	 */
	@Override
	public void add(Seal seal) {

		Connection con = null;
		Statement stmt = null;
		try {
			con = mysqlUtils.getConnection();
			StringBuilder sb = new StringBuilder(1000);
			sb.append("INSERT INTO `SEALINFO` VALUES ('");
			sb.append(seal.getCode()).append("','");
			sb.append(seal.getStatus()).append("','");
			sb.append(seal.getLongitude()).append("','");
			sb.append(seal.getLatitude()).append("','");
			sb.append(df.format(seal.getCtime())).append("','");
			sb.append(df.format(seal.getMtime())).append("','");
			sb.append(seal.isMarkdel()?"1":"0").append("','");
			sb.append(seal.getRemark()).append("');");

			stmt = con.createStatement();
			System.out.println(sb.toString());
			int ret = stmt.executeUpdate(sb.toString());

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			mysqlUtils.closeStmt(stmt);
			mysqlUtils.closeConnection(con);
		}
	}
	
	// 更新一条seal的记录.
	@Override
	public void update(Seal seal) {

		Connection con = null;
		Statement stmt = null;
		try {
			con = mysqlUtils.getConnection();
			StringBuilder sb = new StringBuilder(1000);
			sb.append("UPDATE `SEALINFO` SET ");
			sb.append("status = '").append(seal.getStatus()).append("', ");
			sb.append("longitude = '").append(seal.getLongitude()).append("', ");
			sb.append("latitude = '").append(seal.getLatitude()).append("', ");
			sb.append("ctime = '").append(df.format(seal.getCtime())).append("', ");
			sb.append("mtime = '").append(df.format(seal.getMtime())).append("', ");
			sb.append("markdel = '").append(seal.isMarkdel()?"1":"0").append("', ");
			sb.append("remark = '").append(seal.getRemark()).append("' ");
			sb.append("WHERE code = '").append(seal.getCode()).append("';");

			stmt = con.createStatement();
			int ret = stmt.executeUpdate(sb.toString());

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			mysqlUtils.closeStmt(stmt);
			mysqlUtils.closeConnection(con);
		}
	}
	

	// 查找一个seal记录.
	/* (non-Javadoc)
	 * @see com.exfd.dao.impl.SealDao#find(java.lang.String)
	 */
	@Override
	public Seal find(String code) {

		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = mysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALINFO where code = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, code);
			rs = prepStmt.executeQuery();

			if (rs.next()) {
				Seal seal = new Seal();
				seal.setCode(rs.getString("code"));
				seal.setStatus(rs.getInt("status"));
				seal.setLongitude(rs.getDouble("longitude"));
				seal.setLatitude(rs.getDouble("latitude"));
				seal.setCtime(rs.getTimestamp("ctime"));
				seal.setMtime(rs.getTimestamp("mtime"));
				seal.setMarkdel(rs.getBoolean("markdel"));
				seal.setRemark(rs.getString("remark"));
				return seal;
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			mysqlUtils.closeResultSet(rs);
			mysqlUtils.closePrepStmt(prepStmt);
			mysqlUtils.closeConnection(con);
		}
	}
	
	public ArrayList<Seal> list() {

		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		
		try {
			con = mysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALINFO";
			prepStmt = con.prepareStatement(selectStatement);
			rs = prepStmt.executeQuery();

			ArrayList<Seal> array = new ArrayList<Seal>();
			while (rs.next()) {
				Seal seal = new Seal();
				seal.setCode(rs.getString("code"));
				seal.setStatus(rs.getInt("status"));
				seal.setLongitude(rs.getDouble("longitude"));
				seal.setLatitude(rs.getDouble("latitude"));
				seal.setCtime(rs.getTimestamp("ctime"));
				seal.setMtime(rs.getTimestamp("mtime"));
				seal.setMarkdel(rs.getBoolean("markdel"));
				seal.setRemark(rs.getString("remark"));
				array.add(seal);
			} 
			return array;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			mysqlUtils.closeResultSet(rs);
			mysqlUtils.closePrepStmt(prepStmt);
			mysqlUtils.closeConnection(con);
		}
	}



}
