package ua.kharkov.repairagency.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private ConnectionPool(){

    }
    public static ConnectionPool getInstance(){
        if(connectionPool == null){
            connectionPool = new ConnectionPool();
        }
        return connectionPool;
    }
    public Connection getConnection(){
        Context context;
        Connection connection = null;
        try{
            context = new InitialContext();
            System.out.println("before dataSource ");
            DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool");
            System.out.println("after dataSource ");
            System.out.println("before dataSource  connection = dataSource.getConnection()");
            connection = dataSource.getConnection();
            System.out.println("after dataSource  connection = dataSource.getConnection()");
        } catch (NamingException  e) {
            //e.printStackTrace();
            System.out.println("NamingException in pool ");
            e.printStackTrace();
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("SQLException  in pool");
            e.printStackTrace();
        }
        return connection;
    }

}
