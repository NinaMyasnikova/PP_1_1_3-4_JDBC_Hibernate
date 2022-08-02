package jm.task.core.jdbc.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String NAME = "Nina";
    private static final String PASSWORD = "root";
    Connection connection = null;
    public Util() {
        //Properties properties = new Properties();
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);

            //properties.load(new FileInputStream(new File("src/resources/mySQL.properties")));
            //connection = DriverManager.getConnection(properties.getProperty("URL"), properties.getProperty("NAME"), properties.getProperty("PASSWORD"));

            /*if (!connection.isClosed()) {
                System.out.println("!connection.isClosed");
            }*/
            System.out.println("util");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
