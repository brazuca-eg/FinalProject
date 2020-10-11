package ua.kharkov.repairagency.db;

import ua.kharkov.repairagency.db.entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private static final String SQL_PAY_BALANCE_FOR_USER  =
    "INSERT INTO details  (user_id, balance) VALUES (?, ?);";



    private static final String SQL_SET_REQUEST_PARAMETERS  =
            "update request set status_id = 1, master_id = ?, price = ? where request_id = ?";


    private static final String SQL_UPDATE_BALANCE  =
            "UPDATE details SET balance=balance+? WHERE repair.details.user_id=?";

    private static final String SQL_CREATE_USER_REQUEST  =
            "INSERT INTO request (user_id, master_id, name, description, date, status_id) values (?, 4, ?, ?, ?, 6)";

    private static final String SQL_MANAGER_REQUEST_LIST  =
            "SELECT * FROM request";

private static final String SQL_MANAGER_REQUEST_LIST_SORTED  =
        "SELECT * FROM request ORDER BY ";

    private static final String SQL_USER_REQUEST_LIST2  =
    "SELECT request_id, date, status.name, user_id, master_id, request.name, description FROM repair.request INNER JOIN repair.status ON  request.status_id = status.status_id  WHERE user_id=? AND status_id=?";

    private static final String SQL_STATUS_REQUEST  =
    "SELECT status_id FROM  request WHERE request_id = ?";


    private static final String SQL_MANAGER_REQUESTS  =
            "SELECT request.request_id, t2.login, date,  request.name, request.description , request.price, status.name, t1.name, t1.surname FROM request \n" +
            "INNER JOIN status ON   request.status_id = status.status_id  \n" +
            "INNER JOIN user t1 ON  request.master_id = t1.user_id\n" +
            "INNER JOIN user t2 ON  request.user_id = t2.user_id ";

    private static final String SQL_MASTER_REQUESTS_PAID  =
            "SELECT request_id, t1.login, t1.name, t1.surname, date,  request.name, description , price, status.name FROM request \n" +
                    "INNER JOIN status ON request.status_id = status.status_id  \n" +
                    "INNER JOIN user t1 ON request.user_id = t1.user_id\n" +
                    "INNER JOIN user t2 ON request.master_id = t2.user_id\n" +
                    "WHERE t2.user_id=? AND status.status_id = 2 OR status.status_id = 4";

    private static final String SQL_MASTER_REQUESTS_ARCHIVE =
            "SELECT request_id, t1.login, t1.name, t1.surname, date,  request.name, description , price, status.name FROM request \n" +
                    "INNER JOIN status ON request.status_id = status.status_id  \n" +
                    "INNER JOIN user t1 ON request.user_id = t1.user_id\n" +
                    "INNER JOIN user t2 ON request.master_id = t2.user_id\n" +
                    "WHERE t2.user_id=? AND status.status_id = 5";

    private static final String SQL_STATUS_ALL  =
            "SELECT * FROM STATUS";

    private static final String SQL_UPDATE_UNPAID_REQUEST  =
            "UPDATE repair.request SET request.price = ?, master_id = ? WHERE request_id=?";

    private static final String SQL_UPDATE_BY_MASTER  =
            "UPDATE request SET status_id = ? WHERE request_id = ?";

    private static final String SQL_SELECT_STATUS_BY_REQUEST_ID =
            "select status_id from request where request_id = ?";

    private DAO(){

    }
    public static DAO getInstance(){
        if(dao == null){
            dao = new DAO();
        }
        return dao;
    }


    public List<RequestMaster> getMasterRequestsArchive(int masterId) {
        PreparedStatement statement = null;
        Connection con = null;
        ResultSet rs  = null;
        List<RequestMaster> requests = new ArrayList<>();
        try {
            con = pool.getConnection();
            statement = con.prepareStatement(SQL_MASTER_REQUESTS_ARCHIVE);
            statement.setInt(1, masterId);
            rs = statement.executeQuery();
            while (rs.next()){
                RequestMaster request = new RequestMaster();
                request.setId(rs.getInt("request_id"));
                request.setClient_login(rs.getString("t1.login"));
                request.setClient_name(rs.getString("t1.name"));
                request.setClient_surname(rs.getString("t1.surname"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                requests.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requests;
    }

    public void updatePrice(double price, int masterId, int requestId) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_UNPAID_REQUEST );
            preparedStatement.setDouble(1, price);
            preparedStatement.setInt(2, masterId);
            preparedStatement.setInt(3, requestId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
    }

    public int getStatusByRequestId(int requestId) {
        int statusId = 0;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(SQL_SELECT_STATUS_BY_REQUEST_ID);
            pstmt.setInt(1, requestId);
            resultSet = pstmt.executeQuery();
            if (resultSet.next())
                statusId = resultSet.getInt("status_id");
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return statusId;
    }

    public void updateStatusByMaster(int statusId, int requestId) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            preparedStatement = con.prepareStatement(SQL_UPDATE_BY_MASTER);
            preparedStatement.setDouble(1, statusId);
            preparedStatement.setDouble(2, requestId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
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

    public void makeRequest(int user_id, String name, String description,String date) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
       // Request request = null;
        try {
            con = pool.getConnection();
            DAO.UserMapper mapper = new DAO.UserMapper();
            preparedStatement = con.prepareStatement(SQL_CREATE_USER_REQUEST);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, date);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
    }

    public List<StatusEntity> getStatuses() {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection con = null;
        List<StatusEntity> statuses = new ArrayList<>();
        try {
            con = pool.getConnection();
            DAO.RequestMapper mapper = new DAO.RequestMapper();
            statement = con.createStatement();
            resultSet = statement.executeQuery(SQL_STATUS_ALL);
            while (resultSet.next()){
                StatusEntity status = new StatusEntity();
                status.setId(resultSet.getInt("status_id"));
                status.setName(resultSet.getString("name"));
                statuses.add(status);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return statuses ;
    }

    public List<RequestMaster> getMasterRequests(int masterId) {
        PreparedStatement statement = null;
        Connection con = null;
        ResultSet rs  = null;
        List<RequestMaster> requests = new ArrayList<>();
        try {
            con = pool.getConnection();
            statement = con.prepareStatement(SQL_MASTER_REQUESTS_PAID);
            statement.setInt(1, masterId);
            rs = statement.executeQuery();
            while (rs.next()){
                RequestMaster request = new RequestMaster();
                request.setId(rs.getInt("request_id"));
                request.setClient_login(rs.getString("t1.login"));
                request.setClient_name(rs.getString("t1.name"));
                request.setClient_surname(rs.getString("t1.surname"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                requests.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requests;
    }




    public List<Request> getUserRequests() {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection con = null;
        Request request = null;
        List<Request> requests = new ArrayList<>();
        try {
            con = pool.getConnection();
            DAO.RequestMapper mapper = new DAO.RequestMapper();
            statement = con.createStatement();
            resultSet = statement.executeQuery(SQL_MANAGER_REQUEST_LIST);
            while (resultSet.next()){
                request = mapper.mapRow(resultSet);
                requests.add(request);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requests;
    }

    public List<RequestSQL> getManagerRequests() {
        Statement statement = null;
        ResultSet rs = null;
        Connection con = null;
        List<RequestSQL> requestsSQL = new ArrayList<>();
        try {
            con = DAO.getConnectionWithDriverManager();
            statement = con.createStatement();
            rs = statement.executeQuery(SQL_MANAGER_REQUESTS);
            while (rs.next()){
                RequestSQL request = new RequestSQL();
                request.setId(rs.getInt("request_id"));
                request.setUserlogin(rs.getString("login"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                request.setMaster_name(rs.getString("t1.name"));
                request.setMaster_surname(rs.getString("t1.surname"));
                requestsSQL.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requestsSQL;
    }

    public List<RequestSQL> getManagerRequests(String param) {
        Statement statement = null;
        ResultSet rs = null;
        Connection con = null;
        List<RequestSQL> requestsSQL = new ArrayList<>();
        try {
            con = DAO.getConnectionWithDriverManager();
            statement = con.createStatement();
            rs = statement.executeQuery(SQL_MANAGER_REQUESTS + " ORDER BY " + param);
            while (rs.next()){
                RequestSQL request = new RequestSQL();
                request.setId(rs.getInt("request_id"));
                request.setUserlogin(rs.getString("login"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                request.setMaster_name(rs.getString("t1.name"));
                request.setMaster_surname(rs.getString("t1.surname"));
                requestsSQL.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requestsSQL;
    }

    public List<RequestSQL> getManagerRequestsFilterStatus(int id) {
        Statement statement = null;
        ResultSet rs = null;
        Connection con = null;
        List<RequestSQL> requestsSQL = new ArrayList<>();
        try {
            con = DAO.getConnectionWithDriverManager();
            statement = con.createStatement();
            rs = statement.executeQuery(SQL_MANAGER_REQUESTS +  "WHERE status.status_id=" + id);
            while (rs.next()){
                RequestSQL request = new RequestSQL();
                request.setId(rs.getInt("request_id"));
                request.setUserlogin(rs.getString("login"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                request.setMaster_name(rs.getString("t1.name"));
                request.setMaster_surname(rs.getString("t1.surname"));
                requestsSQL.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requestsSQL;
    }

    public List<RequestSQL> getManagerRequestsFilterMaster(int id) {
        Statement statement = null;
        ResultSet rs = null;
        Connection con = null;
        List<RequestSQL> requestsSQL = new ArrayList<>();
        try {
            con = DAO.getConnectionWithDriverManager();
            statement = con.createStatement();
            rs = statement.executeQuery(SQL_MANAGER_REQUESTS +  "WHERE t1.user_id=" + id);
            while (rs.next()){
                RequestSQL request = new RequestSQL();
                request.setId(rs.getInt("request_id"));
                request.setUserlogin(rs.getString("login"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                request.setMaster_name(rs.getString("t1.name"));
                request.setMaster_surname(rs.getString("t1.surname"));
                requestsSQL.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requestsSQL;
    }

    public List<Request> getUserRequestsSorted(String sortedValue) {
        Statement statement = null;
        ResultSet resultSet = null;
        Connection con = null;
        Request request = null;
        List<Request> requests = new ArrayList<>();
        try {
            con = pool.getConnection();
            DAO.RequestMapper mapper = new DAO.RequestMapper();
            statement = con.createStatement();
            resultSet = statement.executeQuery( SQL_MANAGER_REQUEST_LIST_SORTED + sortedValue );
            while (resultSet.next()){
                request = mapper.mapRow(resultSet);
                requests.add(request);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return requests;
    }




    public Map<List<Request>,String> getSpecialisedUserRequests(User user, int status_id) {
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        Request request = null;
        List<Request> requests = new ArrayList<>();
        Map<List<Request>,String> map = new HashMap<>();
        String res = null;
        try {
            con = pool.getConnection();
            DAO.RequestMapper mapper = new DAO.RequestMapper();
            pstmt = con.prepareStatement( SQL_USER_REQUEST_LIST2);
            pstmt.setInt(1, user.getId());
            pstmt.setInt(2, status_id);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Request request1 =new Request();
                request.setId(resultSet.getInt(Fields.REQUEST_USER_ID));
                request.setDate(resultSet.getDate(Fields.REQUEST_DATE));
                res = resultSet.getString("status.name");
                request.setUser_id(resultSet.getInt(Fields.REQUEST_USER_ID));
                request.setMaster_id(resultSet.getInt(Fields.REQUEST_MASTER_ID));
                request.setName(resultSet.getString(Fields.REQUEST_NAME));
                request.setDescription(resultSet.getString(Fields.REQUEST_DESCRIPTION));
                requests.add(request1);
            }
            map.put(requests, res);
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return map;
    }


    public void pay(User user, double sum) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            preparedStatement = con.prepareStatement(SQL_PAY_BALANCE_FOR_USER);
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

//    public void updateUnpaidRequest(int request_id, double price, int master_id) {
//        PreparedStatement preparedStatement = null;
//        Connection con = null;
//        try {
//            con = pool.getConnection();
//            preparedStatement = con.prepareStatement(SQL_PAY_BALANCE_FOR_USER);
//            preparedStatement.setDouble(1, price);
//            preparedStatement.setInt(2, master_id);
//            //preparedStatement.setInt(3,request_id);
//            preparedStatement.executeUpdate();
//            preparedStatement.close();
//        } catch (SQLException ex) {
//            pool.getInstance().rollbackAndClose(con);
//            ex.printStackTrace();
//        } finally {
//            pool.getInstance().commitAndClose(con);
//        }
//    }




    public void updateUnpaidRequest(int master_id, double price, int request_id) {
        PreparedStatement preparedStatement = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            preparedStatement = con.prepareStatement(SQL_SET_REQUEST_PARAMETERS);
            preparedStatement.setInt(1, master_id);
            preparedStatement.setDouble(2, price);
            preparedStatement.setInt(3,request_id);
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
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return user;
    }

    public int statusOfRequest(int request_id){
        int res = 0;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        Connection con = null;
        try {
            con = pool.getConnection();
            pstmt = con.prepareStatement(SQL_STATUS_REQUEST);
            pstmt.setInt(1, request_id);
            resultSet = pstmt.executeQuery();
            if (resultSet.next())
               res = resultSet.getInt("status_id");;
            resultSet.close();
            pstmt.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        return res;
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

    private static class RequestMapper implements EntityMapper<Request> {
        public Request mapRow(ResultSet rs) {
            try {
                Request request = new Request();
                request.setId(rs.getInt(Fields.REQUEST_ID ));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setStatus_id(rs.getInt(Fields.REQUEST_STATUS_ID));
                request.setUser_id(rs.getInt(Fields.REQUEST_USER_ID));
                request.setMaster_id(rs.getInt(Fields.REQUEST_MASTER_ID));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                return request;
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
        Statement statement = null;
        ResultSet rs = null;
        Connection con = null;
        List<RequestSQL> requestsSQL = new ArrayList<>();
        try {
            con = DAO.getConnectionWithDriverManager();
            statement = con.createStatement();
            rs = statement.executeQuery(SQL_MANAGER_REQUESTS);
            while (rs.next()){
                RequestSQL request = new RequestSQL();
                request.setId(rs.getInt("request_id"));
                request.setUserlogin(rs.getString("login"));
                request.setDate(rs.getDate(Fields.REQUEST_DATE));
                request.setName(rs.getString(Fields.REQUEST_NAME));
                request.setDescription(rs.getString(Fields.REQUEST_DESCRIPTION));
                request.setPrice(rs.getDouble(Fields.REQUEST_PRICE));
                request.setStatus_name(rs.getString("status.name"));
                request.setMaster_name(rs.getString("t1.name"));
                request.setMaster_surname(rs.getString("t1.surname"));
                requestsSQL.add(request);
            }
            rs.close();
            statement.close();
        } catch (SQLException ex) {
            pool.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            pool.getInstance().commitAndClose(con);
        }
        System.out.println(requestsSQL);
    }






}
