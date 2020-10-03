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
            "INSERT INTO user (email, login, password, name, surname, role_id)"
                    + " VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_FIND_USER_BY_ID  =
            "SELECT * FROM user WHERE user_id=?";

    private static final String SQL_FIND_ALL_USERS_BY_ID  =
            "SELECT * FROM user WHERE role_id=?";

    private static final String SQL_CUSTOMER_BALANCE  =
            "SELECT balance FROM details WHERE repair.details.user_id=?";

    private static final String SQL_PAY_BALANCE  =
    "INSERT  details  (user_id, balance) VALUES (?, ?);";

    private static final String SQL_UPDATE_BALANCE  =
            "UPDATE details SET balance=balance+? WHERE repair.details.user_id=?";



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

    public void register(String email, String login, String password, String name, String surname, int role_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            preparedStatement = con.prepareStatement(SQL_REGISTER_USER);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, surname);
            preparedStatement.setInt(6, role_id);
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

    public boolean updateBalance(User user, int sum) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        boolean b = false;
        try {
            con = pool.getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_BALANCE);
            preparedStatement.setInt(1,sum);
            preparedStatement.setInt(2,  user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            b = true;
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return b;
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
        List<User> users = new ArrayList<>();
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
           pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
           pool.getInstance().commitAndClose(con);
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
                user.setEmail(rs.getString(Fields.USER_EMAIL));
                user.setName(rs.getString(Fields.USER_NAME));
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

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        boolean b = false;
        try {
            con = DAO.getConnectionWithDriverManager();
            preparedStatement = con.prepareStatement(SQL_UPDATE_BALANCE);
            preparedStatement.setInt(1, 100);
            preparedStatement.setInt(2, 7);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            b = true;
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }

    }






}
