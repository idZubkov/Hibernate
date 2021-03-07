package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();
    List<User> userList;

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        util.getInstance();
        Statement statement;
        try {
            statement = util.getConnection().createStatement();
            String query = "CREATE TABLE IF NOT EXISTS my_db_jm.users (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (40)," +
                    "lastName VARCHAR (40)," +
                    "age INT," +
                    "PRIMARY KEY (id))";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        util.getInstance();
        Statement statement;
        try {
            statement = util.getConnection().createStatement();
            String query = "DROP TABLE IF EXISTS my_db_jm.users";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        util.getInstance();
        PreparedStatement preparedStatement;
        userList = new ArrayList<>();
        try {
            preparedStatement = util.getConnection().prepareStatement("INSERT INTO my_db_jm.users (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            System.out.println("User с именем – " + name + " добавлен в базу данных");
            userList.add(new User(name, lastName, age));
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        util.getInstance();
        Statement statement;
        try {
            statement = util.getConnection().createStatement();
            String query = "DELETE FROM my_db_jm.users WHERE id = " + id;
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        util.getInstance();
        return userList;
    }

    public void cleanUsersTable() {
        util.getInstance();
        Statement statement;
        try {
            statement = util.getConnection().createStatement();
            String query = "DELETE FROM my_db_jm.users";
            statement.execute(query);
            userList.clear();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
