
package model;

/**
 * Class that characterises the user. It stores his username.
 * 
 * @author Michal Walus
 * @version 1.1
 */
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
        
        if(name.isEmpty()) {
            throw new MyThrownException("You have to pass your username");
        }
        
        else if (Character.isLetter(name.charAt(0))){
            this.name = name;
        }
        else {
            throw new MyThrownException("Given username doesn't start with a letter");
        }
    }

    /**
     * Returns the username of the user.
     * 
     * @return The username of the user.
     */
    public String getName() {
        return name;
    }
    
}
