package ua.kharkov.repairagency.exception;

public class DataBaseException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBaseException(){

    }

    public DataBaseException(String message){
        super(message);
    }
}