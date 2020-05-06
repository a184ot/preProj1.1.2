package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserDAO;
import service.UserService;


import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public void addUser(User user) {
        Session session = UserService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        Session session = UserService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Long id = user.getId();
        String name = user.getName();
        Long age = user.getAge();
        String email = user.getEmail();
        try {
            Query query = session.createQuery("update User set name = :name , age = :age , email = :email where id = :id")
                    .setParameter("id", id)
                    .setParameter("name", name)
                    .setParameter("age", age)
                    .setParameter("email", email);
                    query.executeUpdate();
        }catch (Exception e){
            transaction.rollback();
        }finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(Long id) {
        Session session = UserService.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("delete from User where id = :id").setParameter("id", id);
            query.executeUpdate();
        }catch (Exception e){

        }finally {
            session.close();
        }
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Session session = UserService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<User> userList = session.createQuery("FROM User").list();
            transaction.commit();
            return userList;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean isUserExist(String name, Long age, String email) {
        Session session = UserService.getSessionFactory().openSession();
        try {
            Query query = session.createQuery("FROM User WHERE name = :name  AND age = :age AND email = :email");
            query.setParameter("name", name);
            query.setParameter("age", age);
            query.setParameter("email", email);
            return query.uniqueResult() != null;
        }catch (Exception r){
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public void createTable() {
    }

    @Override
    public void dropTable() {
        Session session = UserService.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.createSQLQuery("TRUNCATE TABLE User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}
