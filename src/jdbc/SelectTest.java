package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest {

	public static void main(String[] args) {
		String dbur1 = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbuser = "hr";
		String dbpass = "hr";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 1.드리이브 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.connection객체 얻기
			conn = DriverManager.getConnection(dbur1, dbuser, dbpass);
			// 3.statement객체 얻어오기
			stmt = conn.createStatement();
			// 4.statement 객체에 쿼리 담기
			String sql = "SELECT department_id, department_name " + "FROM departments";
			
			// 5.sql수행 (->resultset 받아오기)
			rs = stmt.executeQuery(sql);
			// 6. 데이터 처리
//			System.out.println(rs);
			// loop돌면서 출력
			while (rs.next()) {
				int deptId = rs.getInt(1);
				String deptName = rs.getString("department_name");
				System.out.printf("%s: %s%n", deptId, deptName);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("JDBC 드라이버에 로드실패!");
		} catch (SQLException e) {
			System.err.println("SQLEroor: " + e.getMessage());
		} finally {
			// 7.자원정리
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {

			}
		}

	}

}
