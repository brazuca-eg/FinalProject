package ua.kharkov.repairagency.db.entity;

public class RequestMaster extends Request {
    private String client_login;
    private String client_name;
    private String client_surname;
    private String status_name;

    public String getClient_login() {
        return client_login;
    }

    public void setClient_login(String client_login) {
        this.client_login = client_login;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_surname() {
        return client_surname;
    }

    public void setClient_surname(String client_surname) {
        this.client_surname = client_surname;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    @Override
    public String toString() {
        return "RequestMaster{" +
                "client_login='" + client_login + '\'' +
                ", client_name='" + client_name + '\'' +
                ", client_surname='" + client_surname + '\'' +
                ", status_name='" + status_name + '\'' +
                '}';
    }
}
