package service;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    boolean isUserExist(String name, Long age, String email);

    void createTable();

    void dropTable();


}
