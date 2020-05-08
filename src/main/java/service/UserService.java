package service;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
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
//        getUserJdbcDAO().updateUser(user);
        if (!isUserExist(user.getName(),user.getAge(),user.getEmail())) {
            userHibernateDAO.updateUser(user);

        }

    }

    @Override
    public User getUserById(Long id) {
//        return getUserJdbcDAO().getUserById(id);
        return userHibernateDAO.getUserById(id);
    }

    @Override
    public boolean isUserExist(String name, Long age, String email) {
//        return getUserJdbcDAO().isUserExist(name, age, email);//jhv
        return userHibernateDAO.isUserExist(name, age, email);
    }

    @Override
    public List<User> getAllUsers() {
//        return getUserJdbcDAO().getAllUsers();
        return userHibernateDAO.getAllUsers();
    }

    @Override
    public void deleteUser(Long id) {
//        getUserJdbcDAO().deleteUser(id);
        userHibernateDAO.deleteUser(id);
    }

    @Override
    public void addUser(User user) {

        if (!isUserExist(user.getName(), user.getAge(), user.getEmail())) {
//            getUserJdbcDAO().addUser(user);
            userHibernateDAO.addUser(user);
//        } else {
//            return false;
        }
    }

    public void dropTable() {
//        getUserJdbcDAO().dropTable();
        userHibernateDAO.dropTable();
    }

    public void createTable()  {
//        getUserJdbcDAO().createTable();
        userHibernateDAO.createTable();
    }


    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=1100").
                    append("&serverTimezone=UTC");
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(getMysqlConnection());
    }

}
//
