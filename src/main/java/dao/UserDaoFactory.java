package dao;

import util.PropertyReader;

public class UserDaoFactory {

    private static UserDaoFactory userDaoFactory;

    public static UserDaoFactory getInstance() {
        if (userDaoFactory == null) {
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    private static final String DAOTYPE = PropertyReader.getProperty("daotype");


    public static UserDAO getUserDao() {
        switch (DAOTYPE) {
            case "hibernate":
                return UserHibernateDAO.getHibernateDAO;
            case "jdbc":
                return UserJdbcDAO.getUserJdbcDAO();
            default:
                throw new IllegalArgumentException("Wrong DAO type:" + DAOTYPE);
        }
    }




}
