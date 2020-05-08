package service;

import dao.UserHibernateDAO;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserService implements Service {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    UserHibernateDAO userHibernateDAO = new UserHibernateDAO(DBHelper.getSessionFactory());

    @Override
    public void updateUser(User user) {
//        DBHelper.getUserJdbcDAO().updateUser(user);
        if (!isUserExist(user.getName(),user.getAge(),user.getEmail())) {
            userHibernateDAO.updateUser(user);

        }

    }

    @Override
    public User getUserById(Long id) {
//        return DBHelper.getUserJdbcDAO().getUserById(id);
        return userHibernateDAO.getUserById(id);
    }

    @Override
    public boolean isUserExist(String name, Long age, String email) {
//        return DBHelper.getUserJdbcDAO().isUserExist(name, age, email);//jhv
        return userHibernateDAO.isUserExist(name, age, email);
    }

    @Override
    public List<User> getAllUsers() {
//        return DBHelper.getUserJdbcDAO().getAllUsers();
        return userHibernateDAO.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
//        DBHelper.getUserJdbcDAO().deleteUser(id);
        userHibernateDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {

        if (!isUserExist(user.getName(), user.getAge(), user.getEmail())) {
//            DBHelper.getUserJdbcDAO().addUser(user);
            userHibernateDAO.addUser(user);
//        } else {
//            return false;
        }
    }

    public void dropTable() {
//        DBHelper.getUserJdbcDAO().dropTable();
        userHibernateDAO.dropTable();
    }

    public void createTable()  {

//        DBHelper.getUserJdbcDAO().createTable();
        userHibernateDAO.createTable();
    }


}

