package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.user.vo.UserVO;

public class UsersSelect {

	public static void main(String[] args) {
		//1.Driver class loading
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver laoding");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		Statement stmt =null;
		ResultSet rs = null;
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
		String user = "scott";
		String pass = "tiger";
		String sql = "select * from users";
		try {
			//2. connection 积己
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("Connection"+con);
			//3.Statment 按眉 积己
			stmt = con.createStatement();
			System.out.println("Statement" + stmt);
			//4.sql角青 : executeQuery(sql)
			rs = stmt.executeQuery(sql);
			System.out.println(rs);
			//5.query蔼 贸府
			UserVO userVO = null;
			List<UserVO> userList = new ArrayList<>();
			while(rs.next()) {
				String userid = rs.getString("userid");
				String name = rs.getString("name");
				char gender = rs.getString("gender").charAt(0);
				String city = rs.getString("city");
				//UserVO按眉 历厘
				userVO = new UserVO(userid,name,gender,city);
				//UserVO 按眉甫 ArrayList俊 历厘
				userList.add(userVO);
			}//while
			for (UserVO user2 : userList) {
				System.out.println(user2);				
			}
			
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
