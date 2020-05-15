package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

public static UserHibernateDAO getHibernateDAO = new UserHibernateDAO();
private static SessionFactory sFactory = DBHelper.getSessionFactory();

    @Override
    public void addUser(User user) {
        Session session = sFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = sFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Session session = sFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(getUserById(id));
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList();
        Session session = sFactory.openSession();
        try {
            userList = session.createQuery("FROM User").list();
            return userList;
        } catch (Exception e) {

        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public User getUserById(Long id) {
        Session session = sFactory.openSession();
        try {
            Query query = session.createQuery("from User where id= :id");
            query.setParameter("id", id);
            User user = (User) query.uniqueResult();
            return user;
        } catch (Exception r) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public User getUserByName(String name) {
        Session session = sFactory.openSession();
        try {
            Query query = session.createQuery("from User where name= :name");
            query.setParameter("name", name);
            User user = (User) query.uniqueResult();
            return user;
        } catch (Exception r) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean isUserExist(String name, String password) {
        Session session = sFactory.openSession();
        try {
            Query query = session.createQuery("select count (*) from User WHERE name = :name and password = :password");
            query.setParameter("name", name);
            query.setParameter("password", password);
            return (Long)query.uniqueResult() != 0L;
        } catch (Exception r) {
            return false;
        } finally {
            session.close();
        }
    }


}

