package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import unilt.DBUtil;

public class subscribeDao {
	public static int SubscribeDao(String name) {
		PreparedStatement pstmt = null;
		int rs = 0;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			String sql = "insert into  wx (name,integral) values(?,0)";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeUpdate();
			

		}catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;
	}
	public static int DelSubscribeDao(String name) {
		PreparedStatement pstmt = null;
		int rs = 0;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			String sql = "delete from  wx where name=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeUpdate();
			

		}catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return rs;
	}
}
