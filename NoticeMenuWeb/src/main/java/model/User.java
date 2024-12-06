
package model;
import lombok.Getter;

/**
 * Class that characterises the user. It stores his username.
 * 
 * @author Michal Walus
 * @version 1.2
 */

@Getter
public class User {
    
    /** The username (nickname) of the user. */
    private String name;
    

    /**
     * Sets the username provided by the user either via command line or keyboard input.
     * 
     * @param name The username provided by the user.
     * @throws MyThrownException if the username is empty or does not start with a letter.
     * It is also thrown when no argument is passed.
     */
    public void setName(String name) throws MyThrownException{
        
        if (name == null || name.isBlank()) {
        throw new MyThrownException("You have to pass your username");
        }
        if (!Character.isLetter(name.charAt(0))) {
            throw new MyThrownException("Given username doesn't start with a letter");
        }
        
        this.name = name;
    }

    
    
}
