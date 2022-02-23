package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users " +
                                            "(id INTEGER not NULL AUTO_INCREMENT, " +
                                            "name VARCHAR(50) not NULL, " +
                                            "lastName VARCHAR (50), " +
                                            "age INTEGER not NULL, " +
                                            "PRIMARY KEY (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = Util.getConnect().prepareStatement("INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?);")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate("DELETE FROM Users WHERE id = " + id);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (PreparedStatement statement = Util.getConnect().prepareStatement("SELECT * FROM Users")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                userList.add(new User(rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getByte("age")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE Users");
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
