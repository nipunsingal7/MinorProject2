package pkg;
import java.sql.*;

public class Database {
	
	protected static Connection databaseconnection() throws SQLException, ClassNotFoundException {
		
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/bus";
		String name="root";
		String key="root";
		
		Class.forName(driver);
		Connection con= DriverManager.getConnection(url,name,key);
		
		return con;
		
	}

}
