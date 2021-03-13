package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alexander", "Pushkin", (byte) 37);
        userService.saveUser("Mikhail", "Lermontov", (byte) 26);
        userService.saveUser("Leo", "Tolstoy", (byte) 82);
        userService.saveUser("Fyodor", "Dostoevsky", (byte) 59);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}