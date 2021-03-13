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
    private Connection connection = Util.getConnection();
    private List<User> userList;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS my_db_jm.users (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (40)," +
                    "lastName VARCHAR (40)," +
                    "age INT," +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "DROP TABLE IF EXISTS my_db_jm.users";
            statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        PreparedStatement preparedStatement = null;
        userList = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO my_db_jm.users (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
            userList.add(new User(name, lastName, age));
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "DELETE FROM my_db_jm.users WHERE id = " + id;
            statement.executeUpdate(query);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        return userList;
    }

    public void cleanUsersTable() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String query = "DELETE FROM my_db_jm.users";
            statement.execute(query);
            userList.clear();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}