
package model;

/**
 * Exception thrown in the program (created by developer for developer).
 * @author Michal Walus
 * @version 1.0
 */
public class MyThrownException extends Exception{
    
    /**
     * Constructs exception with custom message
     * @param message text of message
     */
    public MyThrownException (String message){
        super(message);
    }
    
}
