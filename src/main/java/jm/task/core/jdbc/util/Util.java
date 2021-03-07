package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Util instance;

    private static final String HOST = "jdbc:mysql://localhost:3306/my_db_jm?serverTimezone=UTC&autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null)
                    instance = new Util();
            }
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Util() {

    }
}
