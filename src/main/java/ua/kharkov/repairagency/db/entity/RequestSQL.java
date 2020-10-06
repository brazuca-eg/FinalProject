package ua.kharkov.repairagency.db.entity;

public class RequestSQL extends Request {
    private String userlogin;
    private String master_name;
    private String master_surname;
    String status_name;

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getUserlogin() {
        return userlogin;
    }

    public void setUserlogin(String userlogin) {
        this.userlogin = userlogin;
    }

    public String getMaster_name() {
        return master_name;
    }

    public void setMaster_name(String master_name) {
        this.master_name = master_name;
    }

    public String getMaster_surname() {
        return master_surname;
    }

    public void setMaster_surname(String master_surname) {
        this.master_surname = master_surname;
    }

    @Override
    public String toString() {
        return "RequestSQL{" +
                "userlogin='" + userlogin + '\'' +
                ", master_name='" + master_name + '\'' +
                ", master_surname='" + master_surname + '\'' +
                ", status_name='" + status_name + '\'' +
                '}';
    }
}
