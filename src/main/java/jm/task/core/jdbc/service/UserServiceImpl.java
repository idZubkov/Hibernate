package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() throws HibernateException {
        userDaoHibernate.createUsersTable();
    }

    public void dropUsersTable() throws HibernateException {
        userDaoHibernate.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws HibernateException {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    public void removeUserById(long id) throws HibernateException {
        userDaoHibernate.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() throws HibernateException {
        userDaoHibernate.cleanUsersTable();
    }
}