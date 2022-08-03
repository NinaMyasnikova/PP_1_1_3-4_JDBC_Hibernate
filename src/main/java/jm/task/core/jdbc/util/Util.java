package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String NAME = "Nina";
    private static final String PASSWORD = "root";
    private static Connection connection;
    public Util() {
    }

    public static Connection getConnection() {
        //Properties properties = new Properties();
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
            //properties.load(new FileInputStream(new File("src/resources/mySQL.properties")));
            //connection = DriverManager.getConnection(properties.getProperty("URL"), properties.getProperty("NAME"), properties.getProperty("PASSWORD"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
