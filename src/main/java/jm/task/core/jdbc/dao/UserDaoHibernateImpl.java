package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;
    private List<User> userList;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS my_db_jm.users (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (40)," +
                    "lastName VARCHAR (40)," +
                    "age INT," +
                    "PRIMARY KEY (id))");
            query.executeUpdate();
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS my_db_jm.users");
            query.executeUpdate();
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        userList = new ArrayList<>();
        try {
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            userList.add(user);
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DELETE FROM my_db_jm.users WHERE id = " + id);
            query.executeUpdate();
            transaction.commit();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        try {
            Query query = session.createSQLQuery("DELETE FROM my_db_jm.users");
            query.executeUpdate();
            userList.clear();
            transaction.commit();
        } finally {
            session.close();
        }
    }
}