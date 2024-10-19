
package view;

/**
 * This class is responsible for printing menu options and messages to the console.
 * It controls the interaction with the user by displaying prompts and feedback.
 * 
 * This class handles all the console output needed for the program's user interface.
 * 
 * @author Michal Walus
 * @version 1.1
 */
public class PrintMenu {
    
    /**
     * Prints the main menu options for the user to choose from.
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
     * Prompts the user to enter the title of their notice.
     */
    public void askTitle(){
        System.out.print("Title of your notice: ");
    }
    
     /**
     * Prompts the user to enter their name.
     */
    public void askAuthor(){
        System.out.print("Your name: ");
    }
    
    /**
     * Prompts the user to enter the details of their notice.
     */
    public void askText(){
        System.out.print("Text of your notice: ");
    }
    
    /**
     * Prints the specified string to the console.
     * 
     * @param s The string to be printed.
     */
    public void printString(String s){
        System.out.println(s);
    }
    
    /**
     * Prompts the user to specify the number (ID) of the notice they want to delete.
     */
    public void askNotice(){
        System.out.print("Which notice do you want to delete? ");
    }
    
    /**
     * Prints an error message when the user selects a number outside the allowed range.
     */
    public void printBoundException(){
        System.out.println("The number you picked is outside of boundry!");
    }
    
    /**
     * Prompts the user to specify the number (ID) of the notice they want to check.
     */
    public void askNoticeToCheck(){
        System.out.print("Which notice do you want to check? ");
    }
    
    /**
     * Prints the details of a specific notice, including its title, author, and text.
     * 
     * @param title The title of the notice.
     * @param author The username of the person who created the notice.
     * @param text The details or content of the notice.
     */
    public void printSpecificNotice(String title, String author, String text){
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Details: " + text);
    }
    
    /**
     * Prints an error message when the user does not enter a valid number.
     */
    public void printWrongNumberException(){
        System.out.print("You have to pass integer number. New number: ");
    }
    
    
}
