package nagarro.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

public class JDBCDemo {
	private static final Logger LOGGER = Logger.getLogger("JDBCDemo");

	static Connection con = null;
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "root@123");
		} catch (ClassNotFoundException e) {

			LOGGER.severe("Error Loading Class: " + e);

		} catch (SQLException e) {
			LOGGER.severe("Error getting connection: " + e);
		}

	}

	public static void main(String args[]) {

		JDBCDemo demo = new JDBCDemo();
		demo.updateTable(con, 1, new String[] { "43567" });

	}

	public String updateTable(Connection con, int id, String[] values) {
		int i = 0;
		try {
			PreparedStatement ps = con.prepareStatement("update nagarrotest set name=? where id=?");
			ps.setInt(1, id);
			ps.setString(2, values[0]);

			i = ps.executeUpdate();
			con.close();
			LOGGER.info("i + \" Records Updated successfully\"");
			return i + " Records Updated successfully";

		} catch (SQLException e) {
			LOGGER.severe("Unable to execute update: " + e);
			return "Unable to execute update: " + e.getMessage();
		}

	}
}
