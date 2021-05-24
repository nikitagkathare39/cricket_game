package com.cricketgame.code.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*public class JDBC_Connector {
    private final String URL = "jdbc:mysql://127.0.0.1:3306/CricketGameIntern?useSSL=false";
    private final String DB_USERNAME = "newuser";
    private final String DB_PASSWORD = "password";
    Connection conn;

    public Connection jdbc_connection() throws SQLException {

        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println("JAVA: Class.forName() error");
                e.printStackTrace();
            }
            try {

                conn = DriverManager.getConnection(URL, DB_USERNAME, DB_PASSWORD);
            } catch (SQLException e) {
                System.out.println("Error in initializing a connection to MYSQL DB");
                e.printStackTrace();

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }
}
*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connector {

    private static Connection conn = null;

    static
    {
        String url = "jdbc:mysql://127.0.0.1:3306/cricketintern?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "----";
        String pass = "----";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
}
