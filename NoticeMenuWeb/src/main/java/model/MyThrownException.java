
package model;

/**
 * Custom exception created by the developer for specific use cases in the program.
 * This exception is thrown when certain user-related validation fails (e.g., 
 * incorrect username input).
 * 
 * @author Michal Walus
 * @version 1.0
 */
public class MyThrownException extends Exception{
    
    /**
     * Constructs a new exception with a specified custom message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public MyThrownException (String message){
        super(message);
    }
    
}
