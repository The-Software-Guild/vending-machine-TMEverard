package Vending.UI;

public class UserEntryException extends Exception{

    public UserEntryException(String message) {
            super(message);
        }
    public  UserEntryException(String message, Throwable cause) {
            super(message, cause);
        }

    }
