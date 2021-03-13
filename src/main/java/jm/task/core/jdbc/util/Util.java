package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String HOST = "jdbc:mysql://localhost:3306/my_db_jm?serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;

    private Util() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                e.getMessage();
            }
        }
        return connection;
    }
}