package app.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySql
{
    private final String CONN_STRING = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";

    //private String dbname;
    private String connString;

    private static MySql instance;
    public static MySql getInstance()
    {
        if (instance == null)
            instance = new MySql();
        return instance;
    }

    private MySql()
    {
        // Hardcoded local db credentials
        // CHANGE
        connString = String.format(CONN_STRING, "localhost", "4200", "tech", "root", "pass1234");
    }


    public Connection getConn() throws SQLException
    {
        return DriverManager.getConnection(connString);
    }

}
