
package model;

/**
 * Class that characterises the user. It stores his username.
 * <p>
 * name is username (nickname of person currently runing the program)
 * </p>
 * @author Michal Walus
 * @version 1.0
 */
public class User {
    String name;

    /**
     * Sets name to username provided by user in the command line or with the keyboard.
     * @param name provided username
     * @throws MyThrownException throws exception when username is empty or doesn't start with a letter.
     * It can also appear when user doesn't pass anything.
     */
    public void setName(String name) throws MyThrownException{
        
        if(name.isEmpty()) {
            throw new MyThrownException("You have to pass only one argument (your username)");
        }
        
        else if (Character.isLetter(name.charAt(0))){
            this.name = name;
        }
        else {
            throw new MyThrownException("Given username doesn't start with a letter");
        }
    }

    /**
     * Used to return username.
     * @return username
     */
    public String getName() {
        return name;
    }
    
    
    
    
}
