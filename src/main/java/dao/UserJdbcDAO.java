package dao;

import model.User;
import service.UserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void addUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("insert into user_tab (name, age, email) values (?, ?, ?)")) {
            stmt.setString(1, user.getName());
            stmt.setLong(2, user.getAge());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
        } catch (SQLException t) {
        }
    }

    @Override
    public void updateUser(User user) {
        try (PreparedStatement stmt = connection.prepareStatement("update user_tab set name=? , age=? , email=? where id=?")) {
            stmt.setString(1, user.getName());
            stmt.setLong(2,user.getAge());
            stmt.setString(3,user.getEmail());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException t) {
        }
    }

    @Override
    public void deleteUser(Long id) {
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM user_tab WHERE id = " + id);
        } catch (SQLException t) {
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
                Long age = result.getLong(3);
                String email = result.getString(4);
                list.add(new User(id, name, age, email));
            }
        } catch (SQLException t) {

        }
        return list;
    }

    @Override
    public boolean isUserExist(String name, Long age, String email) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT ROW_COUNT () FROM user_tab WHERE name='" + name + "'and age='" + age + "' and email='" + email + "'");
            ResultSet result = stmt.getResultSet();
            result.next();
            return result.first();
        } catch (SQLException d) {
            return false;
        }
    }

    @Override
    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("create table if not exists user_tab (id bigint auto_increment, name varchar(256), age bigint, email varchar(256), primary key (id))");
        } catch (SQLException t) {
        }
    }

    @Override
    public void dropTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate("DROP TABLE IF EXISTS user_tab");
        } catch (SQLException t) {

        }
    }
}
