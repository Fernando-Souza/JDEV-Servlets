package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password ="admin";
	private static String user = "postgres";
	
	private static Connection conn = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		
		conectar();
		
	}
	
	private static  void conectar()  {
		
		try {
			
			if(conn==null) {
				
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(url, user, password);
			conn.setAutoCommit(false);
						
			}
			
					
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static Connection getConnection() {
		
		return conn;
	}
	
	public static void closeConnection() {
				
			if(conn!=null) {
				
				try {
					conn.close();
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				
			}
	
		
	}

}
