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
    public static synchronized ConnectionPool getInstance(){
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
            DataSource dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool");
            connection = dataSource.getConnection();
        } catch (NamingException  e) {
            System.out.println("NamingException in pool ");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLException  in pool");
            e.printStackTrace();
        }
        return connection;
    }
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
