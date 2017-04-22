package main.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Kuznetsov on 20/04/2017.
 */

public class MyConnection {
    //private static final Logger LOGGER = Logger.getLogger(MyConnection.class);

    private static final String DBNAME = "lsp-1";
    private static final String DBLOGIN = "postgres";
    private static final String DBPASSWORD = "656450";

    public static Connection connect() {

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Проблема с драйвером");
        }

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost/" + DBNAME, DBLOGIN, DBPASSWORD);
        } catch (SQLException e) {
            System.out.println("Проблема с соединением");
        }

        return connection;
    }
}
