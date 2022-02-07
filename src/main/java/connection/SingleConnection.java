package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleConnection {

    private static String url = "jdbc:postgresql://ec2-18-215-8-186.compute-1.amazonaws.com:5432/d9pr46isppuk1v?sslmode=require&autoReconnect=true";
    private static String password = "2362d90545ae46ed6263a66fd1a86c03737fb26f5729e501f17932ffd583d9ea";
    private static String user = "qqrwshmjwxzjgo";

    private static Connection conn = null;

    static {
        conectar();
    }

    public SingleConnection() {

        conectar();

    }

    private static void conectar() {

        try {

            if (conn == null) {

                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection(url, user, password);
                conn.setAutoCommit(false);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Connection getConnection() {

        return conn;
    }

    public static void closeConnection() {

        if (conn != null) {

            try {
                conn.close();
            } catch (SQLException e) {

                e.printStackTrace();
            }

        }

    }

}
