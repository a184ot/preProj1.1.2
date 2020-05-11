package service;

import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import util.DBHelper;
import util.PropertyReader;

import java.util.List;

public class UserDaoFactory implements UserDAO {

    private final String DAOTYPE = PropertyReader.getProperty("daotype");

    UserHibernateDAO userHibernateDAO = new UserHibernateDAO();


    public static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(DBHelper.getInstance().getConnection());
    }


    @Override
    public void addUser(User user) {
        if (!isUserExist(user.getName(), user.getAge(), user.getEmail())) {
            switch (DAOTYPE) {
                case "hibernate":
                    userHibernateDAO.addUser(user);
                    break;
                case "jdbc":
                    getUserJdbcDAO().addUser(user);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
            }
        }
    }

    @Override
    public void updateUser(User user) {
        if (!isUserExist(user.getName(),user.getAge(),user.getEmail())) {
            switch (DAOTYPE) {
                case "hibernate":
                    userHibernateDAO.updateUser(user);
                    break;
                case "jdbc":
                    getUserJdbcDAO().updateUser(user);
                    break;
                default:
                    throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        switch (DAOTYPE) {
            case "hibernate":
                userHibernateDAO.deleteUser(id);
                break;
            case "jdbc":
                getUserJdbcDAO().deleteUser(id);
                break;
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }

    @Override
    public List<User> getAllUsers() {
        switch (DAOTYPE) {
            case "hibernate":
                return userHibernateDAO.getAllUsers();
            case "jdbc":
                return getUserJdbcDAO().getAllUsers();
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }

    @Override
    public User getUserById(Long id) {
        switch (DAOTYPE) {
            case "hibernate":
                return userHibernateDAO.getUserById(id);
            case "jdbc":
                return getUserJdbcDAO().getUserById(id);
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }

    @Override
    public boolean isUserExist(String name, Long age, String email) {
        switch (DAOTYPE) {
            case "hibernate":
                return userHibernateDAO.isUserExist(name, age, email);
            case "jdbc":
                return getUserJdbcDAO().isUserExist(name, age, email);
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }

    public void createTable () {
        switch (DAOTYPE) {
            case "hibernate":
                break;
            case "jdbc":
                getUserJdbcDAO().createTable();
                break;
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }

    public void dropTable() {
        switch (DAOTYPE) {
            case "hibernate":
                userHibernateDAO.dropTable();
                break;
            case "jdbc":
                getUserJdbcDAO().dropTable();
                break;
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }
}
