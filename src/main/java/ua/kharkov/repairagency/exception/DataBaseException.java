package ua.kharkov.repairagency.exception;

public class DataBaseException extends RuntimeException {

    public DataBaseException(){

    }

    public DataBaseException(String message){
        super(message);
    }
}