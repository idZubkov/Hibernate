package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alexander", "Pushkin", (byte) 37);
        userService.saveUser("Mikhail", "Lermontov", (byte) 26);
        userService.saveUser("Leo", "Tolstoy", (byte) 82);
        userService.saveUser("Fyodor", "Dostoevsky", (byte) 59);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
        try {
            if (Util.getConnection() != null) {
                Util.getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}