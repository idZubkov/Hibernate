package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();
    List<User> userList;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS my_db_jm.users (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (40)," +
                    "lastName VARCHAR (40)," +
                    "age INT," +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(query);

        } catch (SQLException e) {
            throw new CatchException("Exception in 'createUsersTable' method");
        }
    }

    public void dropUsersTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS my_db_jm.users";
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new CatchException("Exception in 'dropUsersTable' method");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement;
        userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO my_db_jm.users (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            userList.add(new User(name, lastName, age));
        } catch (SQLException e) {
            throw new CatchException("Exception in 'saveUser' method");
        }
    }

    public void removeUserById(long id) {
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "DELETE FROM my_db_jm.users WHERE id = " + id;
            statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new CatchException("Exception in 'removeUserById' method");
        }
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void cleanUsersTable() {
        Statement statement;
        try {
            statement = connection.createStatement();
            String query = "DELETE FROM my_db_jm.users";
            statement.execute(query);
            userList.clear();
        } catch (SQLException e) {
            throw new CatchException("Exception in 'cleanUsersTable' method");
        }
    }
}

class CatchException extends RuntimeException {
    private String message;

    public CatchException(String message) {
        this.message = message;
    }
}