package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    private Connection connection = util.getConnection();
    //Statement statement = null;
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age  INT)");
            //System.out.println("createUsersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
            //System.out.println("dropUsersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age){
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user (name, lastName, age) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
            //System.out.println("saveUser");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try ( PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM user WHERE ID = ?")) {
            preparedStatement.setLong(1, (int) id);
            preparedStatement.execute();
            //System.out.println("removeUserById");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> listOfUsers = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user")) {
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), (byte) resultSet.getInt(4));
                listOfUsers.add(user);
            }
            for (User user : listOfUsers) {
                System.out.println(user);
            }
            //System.out.println("getAllUsers");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement();) {
            statement.executeUpdate("DELETE FROM user");
            //System.out.println("cleanUsersTable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
