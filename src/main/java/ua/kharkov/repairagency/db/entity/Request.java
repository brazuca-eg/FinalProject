package ua.kharkov.repairagency.db.entity;

import java.time.LocalDate;

public class Request extends Entity {
    private double price;
    private LocalDate date;
    private int status_id;
    private int user_id;
    private int master_id;
    private String name;
    private String description;
    public Request() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getMaster_id() {
        return master_id;
    }

    public void setMaster_id(int master_id) {
        this.master_id = master_id;
    }

    @Override
    public String toString() {
        return "Request{" +
                "price=" + price +
                ", date=" + date +
                ", status_id=" + status_id +
                ", user_id=" + user_id +
                ", master_id=" + master_id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
