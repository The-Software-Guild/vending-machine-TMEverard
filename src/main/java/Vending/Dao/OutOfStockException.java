package Vending.Dao;

public class OutOfStockException extends Exception{

    public OutOfStockException(String message) {
        super(message);
    }
    public  OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }

}
