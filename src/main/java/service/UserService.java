package service;

import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService;
    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(DBHelper.getSessionFactory());
        }
        return userService;
    }

    public UserService() {
    }

    public void updateUser(User user) throws SQLException {
//        getUserJdbcDAO().updateUser(user);
        new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).updateUser(user);
    }


    public User getUserById(Long id) {
//        return getUserJdbcDAO().getUserById(id);
        return new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).getUserById(id);
    }


    public boolean isUserExist(String name, Long age, String email) {
//        return getUserJdbcDAO().isUserExist(name, age, email);//jhv
        return new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).isUserExist(name, age, email);
    }

    public List<User> getAllUsers() {
//        return getUserJdbcDAO().getAllUsers();
        return new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).getAllUsers();
    }

    public void deleteUser(Long id) throws SQLException {
//        getUserJdbcDAO().deleteUser(id);
        new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).deleteUser(id);
    }

    public void addUser(User user) throws SQLException {

//        if (isUserExist(user.getName(), user.getAge(), user.getEmail())  == false) {
//            getUserJdbcDAO().addUser(user);
        new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).addUser(user);
//        } else {
//            return false;
//        }
    }


    public void dropTable() {
//        getUserJdbcDAO().dropTable();
        new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).dropTable();
    }

    public void createTable()  {
//        getUserJdbcDAO().createTable();
        new UserHibernateDAO(DBHelper.getSessionFactory().openSession()).createTable();
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

