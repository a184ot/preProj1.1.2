package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public void addUser(User user) {
        Session session = DBHelper.getSessionFactory().openSession();
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
        Session session = DBHelper.getSessionFactory().openSession();
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
        Session session = DBHelper.getSessionFactory().openSession();
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
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
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
        Session session = DBHelper.getSessionFactory().openSession();
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
    public boolean isUserExist(String name, Long age, String email) {
        Session session = DBHelper.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("select count (*) from User WHERE name = :name and age = :age and email = :email");
            query.setParameter("name", name);
            query.setParameter("age", age);
            query.setParameter("email", email);
            return (Long)query.uniqueResult() != 0L;
        } catch (Exception r) {
            return false;
        } finally {
            session.close();
        }
    }

    public void createTable() {
    }

    public void dropTable() {
        Session session = DBHelper.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createQuery("delete User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}

