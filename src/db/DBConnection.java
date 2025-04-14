package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    private static String user = "root";
    private static String password = "java"; //Remember to change back to java when pushing
    private static String url = "jdbc:mysql://localhost:3306/coursescheduling_db"; //This will be the name of our app's db, only change for testing purposes

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("Could not connect to database.");
                System.exit(1);

            } catch (ClassNotFoundException e) {
                throw new RuntimeException("MySQL driver could not be found", e);
            }
        }
        return connection;
    }}
