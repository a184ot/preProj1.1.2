package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

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
    private UserDAO userDAO;



    @Override
    public void updateUser(User user) {
        if (!isUserExist(user.getName(),user.getPassword())) {
            userDAO.updateUser(user);
        }else {
            if (!user.getRole().equals(getUserById(user.getId()).getRole())){
                userDAO.updateUser(user);
            }
        }

    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Override
    public boolean isUserExist(String name, String password) {
        return userDAO.isUserExist(name, password);
    }

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
        userDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {
        if (!isUserExist(user.getName(), user.getPassword())) {
            userDAO.addUser(user);
        }
    }

}

