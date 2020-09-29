package ua.kharkov.repairagency.db;

import ua.kharkov.repairagency.db.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private static DAO dao;
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final String SQL_LOGIN_USER =
            "SELECT * FROM user WHERE login=? AND password=?";
    private static final String SQL_REGISTER_USER  =
            "INSERT INTO user (login, password, surname, role_id)"
                    + " VALUES (?, ?, ?, ?)";




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
            // DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            //DBManager.getInstance().commitAndClose(con);
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
            ex.printStackTrace();
        } finally {

        }

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
}
