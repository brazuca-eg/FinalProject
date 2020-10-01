package ua.kharkov.repairagency.db.entity;

public class Feedback {
    private String text;
    private int stars;

    public Feedback() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "text='" + text + '\'' +
                ", stars=" + stars +
                '}';
    }
}
