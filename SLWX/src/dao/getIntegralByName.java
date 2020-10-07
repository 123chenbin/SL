package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.login;
import unilt.DBUtil;

public class getIntegralByName {
	public static login GetIntegralByName(String name) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			String sql = "select integral from wx where name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new login(
						rs.getString("integral"));
			}

		}catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		DBUtil.free(rs, pstmt, connection);
		return null;
	}
	
}
