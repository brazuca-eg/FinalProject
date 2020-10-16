package ua.kharkov.repairagency.db.interfaces;

import ua.kharkov.repairagency.db.entity.Feedback;
import ua.kharkov.repairagency.db.entity.RequestMaster;
import ua.kharkov.repairagency.db.entity.RequestUser;
import ua.kharkov.repairagency.db.entity.User;
import ua.kharkov.repairagency.exception.DataBaseException;

import java.util.List;
import java.util.Map;

public interface UserDao {
    void userDefaultFeedback(int reqId) throws DataBaseException;
    void userArchiveFeedback(Feedback feedback, int reqId) throws DataBaseException;
    void userPayRequest1(int requestId, double price) throws DataBaseException;
    void userPayRequest2(int reqId,  int statusId) throws DataBaseException;
    List<RequestUser> getUserAllRequests(User user)throws DataBaseException;
    List<RequestUser> getUserRequests(int masterId, int statusId)throws DataBaseException;
    Map<RequestMaster, Feedback> getUserRequestsArchiveFeedback(int userId)throws DataBaseException;
    void makeRequest(int user_id, String name, String description,String date)throws DataBaseException;
}
