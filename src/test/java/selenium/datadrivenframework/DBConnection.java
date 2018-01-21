package selenium.datadrivenframework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SeleniumGuru.com on 1/18/18.
 */
public class DBConnection {
    private final static String DB_URL = "jdbc:postgresql://seleniumgurudb.c5flgwp4x0yv.us-east-1.rds.amazonaws.com/newtours_customers";
    private final static String USER = "udemy_selenium_guru";
    private final static String PASS = "seleniumguru#";

    public static Connection connectToDatabase() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                System.out.println("You made it, take control your database now!");
                return conn;
            } else {
                System.out.println("Failed to make connection!");
            }
        }
        return conn;
    }
}
