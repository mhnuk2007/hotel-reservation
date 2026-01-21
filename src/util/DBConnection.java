package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    public static Properties properties = new Properties();

    static{
        try {
            InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                System.exit(1);
            }
            properties.load(input);
            Class.forName(properties.getProperty("db.driver"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load database configuration.");
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("db.url"),
                properties.getProperty("db.username"),
                properties.getProperty("db.password")
        );

    }
}
