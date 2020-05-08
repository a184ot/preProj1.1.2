package service;

import model.User;

import java.util.List;

public interface Service {

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    boolean isUserExist(String name, Long age, String email);



}
