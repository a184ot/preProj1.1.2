package service;

import dao.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class UserService {

    private static UserService userService;

//    private SessionFactory sessionFactory {
//        this.sessionFactory = sessionFactory;
//    }

    private static SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {

    }
//
//    private HibernateSessionFactoryUtil() { }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

    private static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(UserService.getSessionFactory());
        }
        return userService;
    }


    public UserService() {
    }

//    UserDAO userDAO = getUserDAO();
//    UserHibernateDAO userHibernateDAO =;
//    UserJdbcDAO userJdbcDAO = g


    public void updateUser(User user) {
        getUserJdbcDAO().updateUser(user);

    }


    public User getUserById(Long id) {
        return getUserJdbcDAO().getUserById(id);

    }


    public boolean isUserExist(String name, Long age, String email) {
        return getUserJdbcDAO().isUserExist(name, age, email);
    }


    public List<User> getAllUsers() {
        return getUserJdbcDAO().getAllUsers();
    }

    public void deleteUser(Long id) throws SQLException {
        getUserJdbcDAO().deleteUser(id);
    }

    public void addUser(User user) throws SQLException {

        if (isUserExist(user.getName(), user.getAge(), user.getEmail()) == false) {
            getUserJdbcDAO().addUser(user);
        } else {
        }
    }

    public void dropTable() {
        getUserJdbcDAO().dropTable();
    }

    public void createTable() {
        getUserJdbcDAO().createTable();
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

//    private static UserDAO getUserDAO() {
//        return new UserDAO(getMysqlConnection());
//    }

    private static UserJdbcDAO getUserJdbcDAO() {
        return new UserJdbcDAO(getMysqlConnection());
    }
}
//    public void updateUserName(Long id, String name) throws SQLException {
//        userDAO.updateUserName(id, name);
//    }
//
//    public void updateUserAge(Long id, Long age) throws SQLException {
//        userDAO.updateUserAge(id, age);
//    }
//
//    public void updateUserEmail(Long id, String email) throws SQLException {
//        userDAO.updateUserEmail(id, email);
//    }
//    public List<User> getAllUsersByName(String name) {
//        try {
//            return userDAO.getAllUsersByName(name);
//        } catch (SQLException e) {
//            return null;
//        }
//    }
//
//    public List<User> getAllUsersByAddress(String email) {
//        try {
//            return userDAO.getAllUsersByAddress(email);
//        } catch (SQLException e) {
//            return null;
//        }
//    }
//}
