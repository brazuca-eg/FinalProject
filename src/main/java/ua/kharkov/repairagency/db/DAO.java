package ua.kharkov.repairagency.db;

import ua.kharkov.repairagency.db.entity.Balance;
import ua.kharkov.repairagency.db.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    private static DAO dao;
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SQL_LOGIN_USER =
            "SELECT * FROM user WHERE login=? AND password=?";
    private static final String SQL_REGISTER_USER  =
            "INSERT INTO user (login, password, surname, role_id)"
                    + " VALUES (?, ?, ?, ?)";

    private static final String SQL_FIND_USER_BY_ID  =
            "SELECT * FROM user WHERE user_id=?";

    private static final String SQL_FIND_ALL_USERS_BY_ID  =
            "SELECT * FROM user WHERE role_id=?";

    private static final String SQL_CUSTOMER_BALANCE  =
            "SELECT balance FROM details WHERE repair.details.user_id=?";

    private static final String SQL_PAY_BALANCE  =
    "UPDATE  details SET (user_id, balance) VALUES (?, ?);";



    private DAO(){

    }
    public static DAO getInstance(){
        if(dao == null){
            dao = new DAO();
        }
        return dao;
    }

    public User login(String login, String password) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            pstmt = con.prepareStatement(SQL_LOGIN_USER);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return user;
    }

    public void register(String login, String password, String surname, int role_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            preparedStatement = con.prepareStatement(SQL_REGISTER_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, surname);
            preparedStatement.setInt(4, role_id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
    }

    public void pay(User user, double sum) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            preparedStatement = con.prepareStatement(SQL_PAY_BALANCE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setDouble(2, sum);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }

    }


    public User findUserId(int id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setInt(1, id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next())
                user = mapper.mapRow(resultSet);
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            //pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
           // pool.getInstance().commitAndClose(con);
        }
        return user;
    }

    public List<User> findClients(int role_id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        ArrayList<User> users = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_ALL_USERS_BY_ID );
            pstmt.setInt(1, role_id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                user = mapper.mapRow(resultSet);
                users.add(user);
            }
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
           //pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
           // pool.getInstance().commitAndClose(con);
        }
        return users;
    }

    public Balance checkCustomerBalance(User user) {
        int u_id = user.getId();
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        Balance balance = new Balance();
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(SQL_CUSTOMER_BALANCE);
            pstmt.setInt(1,  u_id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next())
                balance.setBalance(resultSet.getDouble("balance"));
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            //pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            // pool.getInstance().commitAndClose(con);
        }
        return balance;
    }



    private static class UserMapper implements EntityMapper<User> {
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt(Fields.ENTITY_ID));
                user.setLogin(rs.getString(Fields.USER_LOGIN));
                user.setPassword(rs.getString(Fields.USER_PASSWORD));
                user.setSurname(rs.getString(Fields.USER_SURNAME));
                user.setRole_id(rs.getInt(Fields.USER_ROLE_ID));
                return user;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }


    public static Connection getConnectionWithDriverManager() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost/repair?serverTimezone=Europe/Moscow&useSSL=false";
        Connection connection = DriverManager.getConnection(url, "root", "1234");
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        return connection;
    }

//    public static void main(String[] args) {
//        User user = new User();
//        user.setId(1);
//        double sum = 120;
//        PreparedStatement preparedStatement = null;
//        Connection con = null;
//        try {
//            con = getConnectionWithDriverManager();
//            DAO.UserMapper mapper = new DAO.UserMapper();
//            preparedStatement = con.prepareStatement(SQL_PAY_BALANCE);
//            preparedStatement.setInt(1, user.getId());
//            preparedStatement.setDouble(2, sum);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException ex) {
//            //pool.getInstance().rollbackAndClose(con);
//            ex.printStackTrace();
//        } finally {
//           // pool.getInstance().commitAndClose(con);
//        }
//
//    }






}
