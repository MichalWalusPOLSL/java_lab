
package view;

/**
 * Controls prints in the console.
 * @author Michal Walus
 * @version 1.0
 */
public class PrintMenu {
    
    /**
     * Prints main options in the program.
     */
    public void printOptions(){
        System.out.println("Pick one of the options:");
        System.out.println("1. View all notices");
        System.out.println("2. Add your notice");
        System.out.println("3. Delete your notice");
        System.out.println("4. Chceck specific notice");
        System.out.println("5. Leave");
    }
    
    /**
     * Prints question regarding title of user notice.
     */
    public void askTitle(){
        System.out.print("Title of your notice: ");
    }
    
    /**
     * Prints question regarding name of user.
     */
    public void askAuthor(){
        System.out.print("Your name: ");
    }
    
    /**
     * Prints question regarding details of notice.
     */
    public void askText(){
        System.out.print("Text of your notice: ");
    }
    
    /**
     * Prints given argument (must be String format).
     * @param s parameter to print
     */
    public void printString(String s){
        System.out.println(s);
    }
    
    /**
     * Prints question regarding number (id) of notice to delete.
     */
    public void askNotice(){
        System.out.print("Which notice do you want to delete? ");
    }
    
    /**
     * Prints message connected to exception.
     * It appears when user chooses number out of boundry.
     */
    public void printBoundException(){
        System.out.println("The number you picked is outside of boundry!");
    }
    
    /**
     * Prints question regarding specifics of notice.
     */
    public void askNoticeToCheck(){
        System.out.print("Which notice do you want to check? ");
    }
    
    /**
     * Prints all details of chosen notice.
     * @param title title of notice
     * @param author username
     * @param text details of notice
     */
    public void printSpecificNotice(String title, String author, String text){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Details: " + text);
    }
    
    /**
     * Prints exception text when user doesn't pass correct number to program.
     */
    public void printWrongNumberException(){
        System.out.print("You have to pass integer number. New number: ");
    }
    
    
}
