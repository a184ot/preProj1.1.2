package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import dao.UserJdbcDAO;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserService implements UserDAO {

    private static UserService userService;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    private UserDaoFactory userDaoFactory = UserDaoFactory.getInstance();



    @Override
    public void updateUser(User user) {
        if (!isUserExist(user.getName(),user.getAge(),user.getEmail())) {
            userDaoFactory.updateUser(user);
        }else {
            if (user.getRole() != getInstance().getUserById(user.getId()).getRole()){
                userDaoFactory.updateUser(user);
            }
        }

    }

    @Override
    public User getUserById(Long id) {
        return userDaoFactory.getUserById(id);
    }

    @Override
    public boolean isUserExist(String name, Long age, String email) {
        return userDaoFactory.isUserExist(name, age, email);
    }

    @Override
    public List<User> getAllUsers() {
        return userDaoFactory.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        userDaoFactory.deleteUser(id);
    }

    @Override
    public void addUser(User user) {

        if (isUserExist(user.getName(), user.getAge(), user.getEmail())==false) {
            userDaoFactory.addUser(user);
        }
    }

    public void dropTable() {
        userDaoFactory.dropTable();
    }

    public void createTable()  {
        userDaoFactory.createTable();
    }

    public static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(DBHelper.getInstance().getConnection());
    }
}

