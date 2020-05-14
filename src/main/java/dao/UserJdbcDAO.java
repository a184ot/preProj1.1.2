package dao;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
        }
    }



    @Override
    public void addUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("insert into user_tab (name, password, age, email, role) values (?, ?, ?, ?, ?)")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setLong(3, user.getAge());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getRole());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException t) {
        try {
            connection.rollback();
        } catch (SQLException e) {
        }
    }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("update user_tab set name=? , password=? , age=? , email=? , role=? where id=?")) {
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getPassword());
            stmt.setLong(3, user.getAge());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getRole());
            stmt.setLong(6, user.getId());
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException t) {
            try {
                connection.rollback();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("DELETE FROM user_tab WHERE id = ?");
            stmt.setLong(1, id);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException t) {
            try {
                connection.rollback();
            } catch (SQLException e) {
            }
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM user_tab");
            ResultSet result = stmt.getResultSet();
            while (result.next()) {
                long id = result.getLong(1);
                String name = result.getString(2);
                String password = result.getString(3);
                Long age = result.getLong(4);
                String email = result.getString(5);
                String role = result.getString(6);
                list.add(new User(id, name, password, age, email, role));
            }
        } catch (SQLException t) {
        }
        return list;
    }

    @Override
    public User getUserById(Long id) {
        try (PreparedStatement stmt = connection.prepareStatement("select * from user_tab where id= ?")) {
            stmt.setLong(1, id);
            ResultSet result = stmt.executeQuery();
            result.next();
            String name = result.getString(2);
            String password = result.getString(3);
            Long age = result.getLong(4);
            String email = result.getString(5);
            String role = result.getString(6);
            return new User(id, name, password,age, email, role);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public User getUserByName(String name) {
        try (PreparedStatement stmt = connection.prepareStatement("select * from user_tab where name= ?")) {
            stmt.setString(1, name);
            ResultSet result = stmt.executeQuery();
            result.next();
            if (result != null) {
                Long id = result.getLong(1);
                String password = result.getString(3);
                Long age = result.getLong(4);
                String email = result.getString(5);
                String role = result.getString(6);
                return new User(id, name, password, age, email, role);
            } else {
                return new User();
            }
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean isUserExist(String name, String password) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT ROW_COUNT () FROM user_tab WHERE name= ? and password = ?")) {
//            stmt.execute("SELECT ROW_COUNT () FROM user_tab WHERE name= :name and age = :age and email= :email");
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            result.next();
            return result.first();
        } catch (SQLException d) {
            return false;
        }
    }

/*    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists user_tab (id bigint auto_increment, name varchar(256), password varchar(256), age bigint, email varchar(256), role varchar(256), primary key (id))");
            connection.commit();
        } catch (SQLException t) {
            try {
                connection.rollback();
            } catch (SQLException e) {
            }
        }
    }

    public void dropTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS user_tab");
            connection.commit();
        } catch (SQLException t) {
            try {
                connection.rollback();
            } catch (SQLException e) {
            }
        }
    }*/
}

