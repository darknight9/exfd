package com.exfd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.apple.jobjc.SEL;
import com.exfd.dao.SealDao;
import com.exfd.domain.Seal;
import com.exfd.util.MysqlUtils;

// 铅封Dao实现.
public class SealDaoImpl implements SealDao {

	// 1980-09-09 12:03:04
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
			con = MysqlUtils.getConnection();
			String str = createInsertStatement(seal);
			
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}

	/**
	 * @param seal
	 * @return
	 */
	private String createInsertStatement(Seal seal) {
		StringBuilder sb = new StringBuilder(1000);
		sb.append("INSERT INTO `SEALINFO` VALUES ('");
		sb.append(seal.getCode()).append("','");
		sb.append(seal.getStatus()).append("','");
		sb.append(seal.getLongitude()).append("','");
		sb.append(seal.getLatitude()).append("','");
		sb.append(df.format(seal.getCtime())).append("','");
		sb.append(df.format(seal.getMtime())).append("','");
		sb.append(seal.isMarkdel()?"1":"0").append("','");
		sb.append(seal.getRemark()).append("','");

		sb.append(seal.getPlate()).append("','");
		sb.append(df.format(seal.getGpstime())).append("','");
		sb.append(seal.getSpeed()).append("','");
		sb.append(seal.getDirection()).append("','");
		sb.append(seal.getDaymiles()).append("','");
		sb.append(seal.getMonthmiles()).append("','");
		sb.append(seal.getOil()).append("','");
		sb.append(seal.getEngst()).append("','");
		sb.append(seal.getPoi()).append("');");
		return sb.toString();
	}
	
	@Override
	public void add(ArrayList<Seal> seals) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			stmt = con.createStatement();
			for (Seal seal : seals) {
				String str = createInsertStatement(seal);
				System.out.println(str);
				stmt.executeUpdate(str);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}
	
	@Override
	public void delete(Seal seal) {
		delete(seal.getCode());
	}
	
	@Override
	public void delete(String code) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = "DELETE FROM `SEALINFO` WHERE code = '"+code+"'";
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
	public void delete(ArrayList<String> codes) {
		Connection con = null;
		PreparedStatement prepStmt = null;
		try {
			con = MysqlUtils.getConnection();
			String deleteStatement = "DELETE FROM `SEALINFO` WHERE code = ? ";
			prepStmt = con.prepareStatement(deleteStatement);
			for (String code : codes) {
				prepStmt.setString(1, code);
				prepStmt.executeUpdate();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
	}
	
	
	// 更新一条seal的记录.
	@Override
	public void update(Seal seal) {

		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = createUpdateStatment(seal);
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}

	/**
	 * @param seal
	 * @return
	 */
	private String createUpdateStatment(Seal seal) {
		StringBuilder sb = new StringBuilder(2000);
		sb.append("UPDATE `SEALINFO` SET ");
		sb.append("status = '").append(seal.getStatus()).append("', ");
		sb.append("longitude = '").append(seal.getLongitude()).append("', ");
		sb.append("latitude = '").append(seal.getLatitude()).append("', ");
		sb.append("ctime = '").append(df.format(seal.getCtime())).append("', ");
		sb.append("mtime = '").append(df.format(seal.getMtime())).append("', ");
		sb.append("markdel = '").append(seal.isMarkdel()?"1":"0").append("', ");
		sb.append("remark = '").append(seal.getRemark()).append("', ");
		
		sb.append("plate = '").append(seal.getPlate()).append("', ");
		sb.append("gpstime = '").append(df.format(seal.getGpstime())).append("', ");
		sb.append("speed = '").append(seal.getSpeed()).append("', ");
		sb.append("direction = '").append(seal.getDirection()).append("', ");
		sb.append("daymiles = '").append(seal.getDaymiles()).append("', ");
		sb.append("monthmiles = '").append(seal.getMonthmiles()).append("', ");
		sb.append("oil = '").append(seal.getOil()).append("', ");
		sb.append("engst = '").append(seal.getEngst()).append("', ");
		sb.append("poi = '").append(seal.getPoi()).append("' ");

		sb.append("WHERE code = '").append(seal.getCode()).append("';");
		return sb.toString();
	}
	
	@Override
	public void update(ArrayList<Seal> seals) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			stmt = con.createStatement();
			for (Seal seal : seals) {
				String str = createUpdateStatment(seal);
				System.out.println(str);
				stmt.executeUpdate(str);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
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
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALINFO where code = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, code);
			rs = prepStmt.executeQuery();

			if (rs.next()) {
				Seal seal = new Seal();
				resultSet2Seal(rs, seal);				
				return seal;
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

	/**
	 * @param rs
	 * @param seal
	 * @throws SQLException
	 */
	private void resultSet2Seal(ResultSet rs, Seal seal) throws SQLException {
		seal.setCode(rs.getString("code"));
		seal.setStatus(rs.getInt("status"));
		seal.setLongitude(rs.getDouble("longitude"));
		seal.setLatitude(rs.getDouble("latitude"));
		seal.setCtime(rs.getTimestamp("ctime"));
		seal.setMtime(rs.getTimestamp("mtime"));
		seal.setMarkdel(rs.getBoolean("markdel"));
		seal.setRemark(rs.getString("remark"));
		
		seal.setPlate(rs.getString("plate"));
		seal.setGpstime(rs.getTimestamp("gpstime"));
		seal.setSpeed(rs.getInt("speed"));
		seal.setDirection(rs.getDouble("direction"));
		seal.setDaymiles(rs.getDouble("daymiles"));
		seal.setMonthmiles(rs.getDouble("monthmiles"));
		seal.setOil(rs.getInt("oil"));
		seal.setEngst(rs.getString("engst"));
		seal.setPoi(rs.getString("poi"));
	}
	
	@Override
	public Map<String, Seal> find(ArrayList<String> codes) {
		Map<String, Seal> sealMap = new HashMap<String, Seal>();
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALINFO where code = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			
			for (String code : codes) {
				prepStmt.setString(1, code);
				rs = prepStmt.executeQuery();

				if (rs.next()) {
					Seal seal = new Seal();
					resultSet2Seal(rs, seal);
					sealMap.put(code, seal);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
		return sealMap;
	}

	
	public ArrayList<Seal> list() {

		ArrayList<Seal> arrayList = new ArrayList<Seal>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALINFO";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectStatement);
			while (rs.next()) {
				Seal seal = new Seal();
				resultSet2Seal(rs, seal);				
				arrayList.add(seal);
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
