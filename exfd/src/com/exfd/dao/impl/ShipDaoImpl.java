package com.exfd.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.exfd.dao.ShipDao;
import com.exfd.domain.Ship;
import com.exfd.util.MysqlUtils;

public class ShipDaoImpl implements ShipDao{

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public void add(Ship ship) {

		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = createInsertStatement(ship);
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}
	

	private String createInsertStatement(Ship ship) {
		StringBuilder sb = new StringBuilder(3000);
		sb.append("INSERT INTO `SHIPINFO` VALUES ('");

		sb.append(ship.getShipid()).append("','");
		sb.append(ship.getShipname()).append("','");
		sb.append(ship.getShipnamecn()).append("','");
		sb.append(ship.getMmsi()).append("','");
		sb.append(ship.getImo()).append("','");
		
		sb.append(ship.getCallsign()).append("','");
		sb.append(ship.getShipflag()).append("','");
		sb.append(ship.getShiptype()).append("','");
		sb.append(ship.getShiplength()).append("','");
		sb.append(ship.getShipwidth()).append("','");
		
		sb.append(ship.getDraft()).append("','");
		sb.append(ship.getGpstime()).append("','");
		sb.append(ship.getLatitude().replace("'", "''")).append("','");
		sb.append(ship.getLongitude().replace("'", "''")).append("','");
		sb.append(ship.getLat()).append("','");
		sb.append(ship.getLon()).append("','");
		
		sb.append(ship.getSpeed()).append("','");
		sb.append(ship.getDirection()).append("','");
		sb.append(ship.getTruehending()).append("','");
		sb.append(ship.getReporttype()).append("','");
		sb.append(ship.getState()).append("','");
		sb.append(ship.getUpdatetime()).append("','");
		
		sb.append(ship.getGpstimepre()).append("','");
		sb.append(ship.getLatpre()).append("','");
		sb.append(ship.getLonpre()).append("','");
		sb.append(ship.getAveragespeed()).append("','");
		sb.append(ship.getDistanceMoved()).append("');");
	
		return sb.toString();
	}
	

	@Override
	public void add(List<Ship> ships) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			stmt = con.createStatement();
			for (Ship ship : ships) {
				String str = createInsertStatement(ship);
				stmt.executeUpdate(str);
			}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}

	@Override
	public void delete(Ship ship) {
		delete(ship.getMmsi());
	}

	@Override
	public void delete(String code) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = "DELETE FROM `SHIPINFO` WHERE mmsi = '"+code+"'";
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
	public void delete(List<String> codes) {
		Connection con = null;
		PreparedStatement prepStmt = null;
		try {
			con = MysqlUtils.getConnection();
			String deleteStatement = "DELETE FROM `SHIPINFO` WHERE mmsi = ? ";
			prepStmt = con.prepareStatement(deleteStatement);
			for (String code : codes) {
				prepStmt.setString(1, code);
				prepStmt.executeUpdate();
			}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
		
	}

	@Override
	public void update(Ship ship) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			String str = createUpdateStatment(ship);
			stmt = con.createStatement();
			stmt.executeUpdate(str);
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
	}
	
	private String createUpdateStatment(Ship ship) {
		StringBuilder sb = new StringBuilder(3000);
		sb.append("UPDATE `SHIPINFO` SET ");
		sb.append("shipid = '").append(ship.getShipid()).append("', ");
		sb.append("shipname = '").append(ship.getShipname()).append("', ");
		sb.append("shipnamecn = '").append(ship.getShipnamecn()).append("', ");
		// mmsi作为key，不必更新.
		//sb.append("mmsi = '").append(ship.getMmsi()).append("', ");
		sb.append("imo = '").append(ship.getImo()).append("', ");
		
		sb.append("callsign = '").append(ship.getCallsign()).append("', ");
		sb.append("shipflag = '").append(ship.getShipflag()).append("', ");
		sb.append("shiptype = '").append(ship.getShiptype()).append("', ");
		sb.append("shiplength = '").append(ship.getShiplength()).append("', ");
		sb.append("shipwidth = '").append(ship.getShipwidth()).append("', ");
		
		sb.append("draft = '").append(ship.getDraft()).append("', ");
		sb.append("gpstime = '").append(ship.getGpstime()).append("', ");
		sb.append("latitude = '").append(ship.getLatitude().replace("'", "''")).append("', ");
		sb.append("longitude = '").append(ship.getLongitude().replace("'", "''")).append("', ");
		sb.append("lat = '").append(ship.getLat()).append("', ");
		sb.append("lon = '").append(ship.getLon()).append("', ");
	
		sb.append("speed = '").append(ship.getSpeed()).append("', ");
		sb.append("direction = '").append(ship.getDirection()).append("', ");
		sb.append("truehending = '").append(ship.getTruehending()).append("', ");
		sb.append("reporttype = '").append(ship.getReporttype()).append("', ");
		sb.append("state = '").append(ship.getState()).append("', ");
		sb.append("updatetime = '").append(ship.getUpdatetime()).append("', ");
	
		sb.append("gpstimepre = '").append(ship.getGpstimepre()).append("', ");
		sb.append("latpre = '").append(ship.getLatpre()).append("', ");
		sb.append("lonpre = '").append(ship.getLonpre()).append("', ");
		sb.append("averagespeed = '").append(ship.getAveragespeed()).append("', ");
		sb.append("distanceMoved = '").append(ship.getDistanceMoved()).append("' ");
		
		sb.append("WHERE mmsi = '").append(ship.getMmsi()).append("';");
		return sb.toString();
	}
	

	@Override
	public void update(List<Ship> ships) {
		Connection con = null;
		Statement stmt = null;
		try {
			con = MysqlUtils.getConnection();
			stmt = con.createStatement();
			for (Ship ship : ships) {
				String str = createUpdateStatment(ship);
				stmt.executeUpdate(str);
			}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closeConnection(con);
		}
		
	}
	
	

	@Override
	public Ship find(String code) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SHIPINFO where mmsi = '"+code+"'";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectStatement);

			if (rs.next()) {
				Ship ship = new Ship();
				resultSet2Ship(rs, ship);			
				return ship;
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

	private void resultSet2Ship(ResultSet rs, Ship ship) throws SQLException {
		ship.setShipid(rs.getString("shipid"));
		ship.setShipname(rs.getString("shipname"));
		ship.setShipnamecn(rs.getString("shipnamecn"));
		ship.setMmsi(rs.getString("mmsi"));
		ship.setImo(rs.getString("imo"));
		
		ship.setCallsign(rs.getString("callsign"));
		ship.setShipflag(rs.getString("shipflag"));
		ship.setShiptype(rs.getString("shiptype"));
		ship.setShiplength(rs.getDouble("shiplength"));
		ship.setShipwidth(rs.getDouble("shipwidth"));
		
		ship.setDraft(rs.getDouble("draft"));
		ship.setGpstime(rs.getString("gpstime"));
		ship.setLatitude(rs.getString("latitude"));
		ship.setLongitude(rs.getString("longitude"));
		ship.setLat(rs.getDouble("lat"));
		ship.setLon(rs.getDouble("lon"));

		ship.setSpeed(rs.getDouble("speed"));
		ship.setDirection(rs.getDouble("direction"));
		ship.setTruehending(rs.getDouble("truehending"));
		ship.setReporttype(rs.getString("reporttype"));
		ship.setState(rs.getString("state"));
		ship.setUpdatetime(rs.getLong("updatetime"));

		ship.setGpstimepre(rs.getString("gpstimepre"));
		ship.setLatpre(rs.getDouble("latpre"));
		ship.setLonpre(rs.getDouble("lonpre"));
		ship.setAveragespeed(rs.getDouble("averagespeed"));
		ship.setDistanceMoved(rs.getDouble("distanceMoved"));
	}
	
	
	@Override
	public Map<String, Ship> find(List<String> codes) {
		Map<String, Ship> shipMap = new HashMap<String, Ship>();
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SHIPINFO where mmsi = ? ";
			prepStmt = con.prepareStatement(selectStatement);
			
			for (String code : codes) {
				prepStmt.setString(1, code);
				rs = prepStmt.executeQuery();

				if (rs.next()) {
					Ship ship = new Ship();
					resultSet2Ship(rs, ship);
					shipMap.put(code, ship);
				} else {
					shipMap.put(code, null);
				}
			}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
		return shipMap;
	}

	@Override
	public ArrayList<Ship> list() {

		ArrayList<Ship> arrayList = new ArrayList<Ship>();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			con = MysqlUtils.getConnection();
			String selectStatement = "select * "
					+ "from SHIPINFO";
			stmt = con.createStatement();
			rs = stmt.executeQuery(selectStatement);
			while (rs.next()) {
				Ship ship = new Ship();
				resultSet2Ship(rs, ship);				
				arrayList.add(ship);
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
	public ArrayList<Ship> findDetail(String operid, String keystr, String type,
			int start_ship, int end_ship) {
		
		ArrayList<Ship> shipList = new ArrayList<Ship>();
		Connection con = null;
		PreparedStatement prepStmt = null;
		ResultSet rs = null;
		String selectStatement = null;
		try {
			con = MysqlUtils.getConnection();
			
			if (type.equals("name")) {
				selectStatement = "select * "
						+ "from SHIPINFO where shipnamecn = ? ";
				prepStmt = con.prepareStatement(selectStatement);
			} else if (type.equals("mmsi")) {
				selectStatement = "select * "
						+ "from SHIPINFO where mmsi = ? ";
				prepStmt = con.prepareStatement(selectStatement);
			} else if (type.equals("imo")) {
				selectStatement = "select * "
						+ "from SHIPINFO where imo = ? ";
				prepStmt = con.prepareStatement(selectStatement);
			} else {
				selectStatement = "select * "
						+ "from SHIPINFO where callsign = ? ";
				prepStmt = con.prepareStatement(selectStatement);
			}
			prepStmt.setString(1, keystr);
			rs = prepStmt.executeQuery();

			int current_no = 1;
			while (rs.next()) {
				if (start_ship <= current_no && current_no < end_ship){
					Ship ship = new Ship();
					resultSet2Ship(rs, ship);
					shipList.add(ship);
				} else if (current_no >= end_ship) {
					return shipList;
				}
				current_no++;
			}
			return shipList;
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
		return shipList;
	}
	
	@Override
	public void updateOrAdd(Ship ship) {
		Connection con = null;
		Statement stmtQuery = null;
		Statement stmtUpdate = null;
		String selectStatement = "select * "
				+ "from SHIPINFO where mmsi = '"+ship.getMmsi()+"'";
		ResultSet rs = null;
		String str = null;
		try {
			con = MysqlUtils.getConnection();
			stmtQuery = con.createStatement();
			stmtUpdate = con.createStatement();
			rs = stmtQuery.executeQuery(selectStatement);
			if (rs.next()) {
				// 船有记录，更新记录.
				str = createUpdateStatment(ship);
				stmtUpdate.executeUpdate(str);
			} else {
				// 没有记录，添加记录.
				str = createInsertStatement(ship);
				stmtUpdate.executeUpdate(str);				}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closeStmt(stmtUpdate);
			MysqlUtils.closeStmt(stmtQuery);
			MysqlUtils.closeConnection(con);
		}
		
	}
	
	@Override
	public void updateOrAdd(List<Ship> ships) {
		Connection con = null;
		Statement stmt = null;
		PreparedStatement prepStmt = null;
		String selectStatement = "select * "
				+ "from SHIPINFO where mmsi = ? ";
		ResultSet rs = null;
		String str = null;
		try {
			con = MysqlUtils.getConnection();
			prepStmt = con.prepareStatement(selectStatement);
			stmt = con.createStatement();
			for (Ship ship : ships) {
				prepStmt.setString(1, ship.getMmsi());
				rs = prepStmt.executeQuery();
				if (rs.next()) {
					// 船有记录，更新记录.
					str = createUpdateStatment(ship);
					stmt.executeUpdate(str);
				} else {
					// 没有记录，添加记录.
					str = createInsertStatement(ship);
					stmt.executeUpdate(str);
				}
			}
		} catch (Exception e) {
			logger.catching(e);
		} finally {
			MysqlUtils.closeResultSet(rs);
			MysqlUtils.closeStmt(stmt);
			MysqlUtils.closePrepStmt(prepStmt);
			MysqlUtils.closeConnection(con);
		}
		
	}

}
