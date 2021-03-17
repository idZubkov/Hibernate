package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction;
    private List<User> userList = new ArrayList<>();
    Session session = null;
    private static SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() throws HibernateException {
        try {
            session = sessionFactory.openSession();
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("CREATE TABLE IF NOT EXISTS my_db_jm.users (" +
                    "id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR (40)," +
                    "lastName VARCHAR (40)," +
                    "age INT," +
                    "PRIMARY KEY (id))");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("HibernateException in 'createUsersTable' method");
        } finally {
            session.close();
            sessionFactory.getCurrentSession().close();
        }
    }

    @Override
    public void dropUsersTable() throws HibernateException {
        try {
            session = sessionFactory.openSession();
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("DROP TABLE IF EXISTS my_db_jm.users");
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("HibernateException in 'dropUsersTable' method");
        } finally {
            session.close();
            sessionFactory.getCurrentSession().close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws HibernateException {
        User user = new User();
        try {
            session = sessionFactory.openSession();
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            userList.add(user);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("HibernateException in 'saveUser' method");
        } finally {
            session.close();
            sessionFactory.getCurrentSession().close();
        }
    }

    @Override
    public void removeUserById(long id) throws HibernateException {
        try {
            session = sessionFactory.openSession();
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM my_db_jm.users WHERE id = " + id);
            query.executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("HibernateException in 'removeUserById' method");
        } finally {
            session.close();
            sessionFactory.getCurrentSession().close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public void cleanUsersTable() throws HibernateException {
        try {
            session = sessionFactory.openSession();
            transaction = sessionFactory.getCurrentSession().beginTransaction();
            Query query = sessionFactory.getCurrentSession().createSQLQuery("DELETE FROM my_db_jm.users");
            query.executeUpdate();
            userList.clear();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw new HibernateException("HibernateException in 'cleanUsersTable' method");
        } finally {
            session.close();
            sessionFactory.getCurrentSession().close();
        }
    }
}