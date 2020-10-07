package dao;

import java.sql.Connection;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cos;
import unilt.DBUtil;


public class GetCosDao {
	//获取url?id查询数据库对应信息
	public static List<Cos> CosByUrlOn(String OrderNo) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Cos> coss = new ArrayList<>();
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			String sql = "select * from cos where OrderNo like "+"'%"+OrderNo+"%'"+" or FactoryPO like "+"'%"+OrderNo+"%'";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int int1 = rs.getInt("Slid");
				java.sql.Date date = rs.getDate("FtyOrderDD");
				String string = rs.getString("Business");
				String string2 = rs.getString("Fty");
				String string3 = rs.getString("Mailing_order");
				String string4 = rs.getString("OrderNo");
				String string5 = rs.getString("Item");
				String string6 = rs.getString("Product_name");
				String string7 = rs.getString("FILAPO");
				String string8 = rs.getString("StyleColor");
				String string9 = rs.getString("FactoryPO");
				int int10 = rs.getInt("Qty");
				String string11 = rs.getString("Remark");
				java.sql.Date  date1 = rs.getDate("Confirmation_date");				
				java.sql.Date  date2 = rs.getDate("DeliveryDD");				
				String string12 = rs.getString("State");
				Cos cos = new Cos(int1, date, string, string2, string3, string4, string5, string6, string7, string8,
						string9, int10, string11, date1,date2,string12);
				coss.add(cos);

			}

		}catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}
		DBUtil.free(rs, pstmt, connection);
		return coss;
	}
//根据查询ID获取订单状态，返回不同结果与Servlet
	public static int CosBySlidGetState(Cos cos) {
		PreparedStatement pstmt = null;
		int result = -1;
		ResultSet rs = null;
		Connection connection = null;
		try {
			connection = DBUtil.getConnection();

			String sql = "update cos set State= ? , SlDate=? where Slid=?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, cos.getState());
			pstmt.setString(2, cos.getSlDate());
			pstmt.setInt(3, cos.getSlid());
			result = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		} 
		DBUtil.free(rs, pstmt, connection);
		return result;
	}

}