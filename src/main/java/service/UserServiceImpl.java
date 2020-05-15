package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl userServiceImpl;

    public static UserServiceImpl getInstance() {
        if (userServiceImpl == null) {
            userServiceImpl = new UserServiceImpl();
        }
        return userServiceImpl;
    }
    UserDAO userDAO = UserDaoFactory.getUserDao();

    @Override
    public void updateUser(User user) {
            userDAO.updateUser(user);
    }
/*    @Override
    public void updateUser(User user) {
        if (!isUserExist(user.getName(),user.getPassword())) {
            UserDaoFactory.getUserDao().updateUser(user);
        }else {
            if (!user.getRole().equals(getUserById(user.getId()).getRole())){
                UserDaoFactory.getUserDao().updateUser(user);
            }
        }

    }*/

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

