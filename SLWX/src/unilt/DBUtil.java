package unilt;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
public final class DBUtil {
	 
		/**
		 * @param args
		 */
		private static String url = "jdbc:mysql://localhost:3306/test"; // jdbc:mysql:///jdbc 本地默认端口可以省略
		private static String user = "root";
		private static String password = "123";
		
		private DBUtil() { }
		
		static { try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new ExceptionInInitializerError(e);
			}
}
	 
		public static Connection getConnection() throws SQLException{
			return DriverManager.getConnection(url, user, password);
		
		}
		static void close(Statement statement){
	        if(statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }


		public static void free(ResultSet rs, PreparedStatement st, Connection conn) {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (st != null) {
						st.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
