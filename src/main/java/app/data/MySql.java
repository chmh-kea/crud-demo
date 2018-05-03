package app.data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("mySql")
public class MySql
{
    private final String CONN_STRING = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";

//    @Value("${spring.datasource.host}")
//    private String host;
//    @Value("${spring.datasource.port}")
//    private String port;
//    @Value("${spring.datasource.username}")
//    private String username;
//    @Value("${spring.datasource.password}")
//    private String password;
//    @Value("${spring.datasource.url}")
//    private String url;
//    @Value("${spring.datasource.db}")
    private String db;

    //private String dbname;
    private String connString;

//    private static MySql instance;
//    public static MySql getInstance()
//    {
//        if (instance == null)
//            instance = new MySql();
//        return instance;
//    }


    private MySql(@Value("${spring.datasource.host}") String host,
                  @Value("${spring.datasource.port}") String port,
                  @Value("${spring.datasource.username}") String username,
                  @Value("${spring.datasource.password}") String password,
                  @Value("${spring.datasource.db}") String db)
    {
        // Hardcoded local db credentials
        // CHANGE
        connString = String.format(CONN_STRING, host, port, db, username, password);
        this.db = db;
    }

    public Connection getConn() throws SQLException
    {
        return DriverManager.getConnection(connString);
    }

}
