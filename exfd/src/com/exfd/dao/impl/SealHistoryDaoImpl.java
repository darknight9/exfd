package com.exfd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.SealHistoryDao;
import com.exfd.domain.Seal;
import com.exfd.domain.SealHistoryRecord;
import com.exfd.util.MysqlUtils;

public class SealHistoryDaoImpl implements SealHistoryDao {

	static Logger logger = LogManager.getLogger();

	// 1980-09-09 12:03:04
	static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void add(SealHistoryRecord sRecord) {

		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = createInsertStatement(sRecord);
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}

	}

	private String createInsertStatement(SealHistoryRecord sRecord) {
		StringBuilder sb = new StringBuilder(2000);
		sb.append("INSERT INTO `SEALHISTORY` VALUES ('");
		
		sb.append(sRecord.getCode()).append("','");
		sb.append(sRecord.getLongitude()).append("','");
		sb.append(sRecord.getLatitude()).append("','");
		sb.append(sRecord.getSpeed()).append("','");
		sb.append(sRecord.getDirection()).append("','");
		
		sb.append(df.format(sRecord.getGpstime())).append("','");
		sb.append(sRecord.getPoi()).append("','");
		sb.append(sRecord.getEngst()).append("','");
		sb.append(sRecord.getOil()).append("','");
		sb.append(sRecord.getMonthmiles()).append("');");
		
		return sb.toString();
	}

	@Override
	public void add(ArrayList<SealHistoryRecord> sRecords) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			stmt = con.createStatement();
			for (SealHistoryRecord record : sRecords) {
				String str = createInsertStatement(record);
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
	public void delete(String code) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = "DELETE FROM `SEALHISTORY` WHERE code = '"+code+"'";
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}

	}

	@Override
	public ArrayList<SealHistoryRecord> find(String code) {
		
		ArrayList<SealHistoryRecord> records = new ArrayList<SealHistoryRecord>();
		
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALHISTORY where code = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, code);
			rs = prepStmt.executeQuery();

			while (rs.next()) {
				SealHistoryRecord record = new SealHistoryRecord();
				resultSet2SRecord(rs, record);				
				records.add(record);
			}
			
			// TODO. records需要根据gpstime进行排序.
			return records;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
	}

	private void resultSet2SRecord(ResultSet rs, SealHistoryRecord record) throws SQLException {
		record.setCode(rs.getString("code"));
		record.setLongitude(rs.getDouble("longitude"));
		record.setLatitude(rs.getDouble("latitude"));
		record.setSpeed(rs.getInt("speed"));
		record.setDirection(rs.getDouble("direction"));
		
		record.setGpstime(rs.getTimestamp("gpstime"));
		record.setPoi(rs.getString("poi"));
		record.setEngst(rs.getString("engst"));
		record.setOil(rs.getInt("oil"));
		record.setMonthmiles(rs.getDouble("monthmiles"));
	}

	@Override
	public ArrayList<SealHistoryRecord> find(String code, Date beginDate,
			Date endDate) {
		
		ArrayList<SealHistoryRecord> records = new ArrayList<SealHistoryRecord>();
		
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SEALHISTORY where code = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			prepStmt.setString(1, code);
			rs = prepStmt.executeQuery();

			while (rs.next()) {
				SealHistoryRecord record = new SealHistoryRecord();
				resultSet2SRecord(rs, record);				
				records.add(record);
			}
			
			// TODO. records 需要根据begin, end时间筛选。
			// TODO. records需要根据gpstime进行排序.
			return records;

		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
	}

	@Override
	public ArrayList<SealHistoryRecord> find(String code, String beginDate,
			String endDate) throws ParseException {
		return find(code, df.parse(beginDate), df.parse(endDate));
	}

}
