package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersUpdate {

	public static void main(String[] args) {
		//String sql = "update user set name = 'gildong', gender='여',city='부산' where userid='gildong';"
		String sql = "update users set name = ?, gender=? ,city=? where userid=?";
		
		//class loading
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver laoding");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
	    PreparedStatement stmt =null;
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "scott";
		String pass = "tiger";
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "mybatis");
			stmt.setString(2, "man");
			stmt.setString(3, "인천");
			stmt.setString(4, "gildong");
			
			int cnt = stmt.executeUpdate();
			System.out.println("갱신된 건수 " + cnt);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null ) stmt.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		

	}

}
