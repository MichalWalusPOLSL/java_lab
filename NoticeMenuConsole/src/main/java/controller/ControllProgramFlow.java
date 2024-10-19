
package controller;
import java.util.Scanner;
import model.*;
import view.PrintMenu;
import java.util.InputMismatchException;



/**
 * Controller handling the main program flow, managing user input and interaction.
 * This class communicates with the model to update or retrieve data and passes
 * information to the view for displaying text on the screen.
 *
 * @author Michal Walus
 * @version 1.2
 * @see PrintMenu
 */
public class ControllProgramFlow {
    
    /** Object used to display text on the screen */
    private PrintMenu viewMenu;
    
    /**
     * Validates the username provided by the user. The username must meet
     * the requirement of being a single argument from the command line and
     * starting with a letter. If the username is invalid, the method will 
     * repeatedly prompt the user until a valid name is provided.
     * 
     * @param args Arguments from the command line (optional); the program works without them.
     * @param someUser Object representing the user currently running the application.
     * @see User
     */
    public void checkIfUsernameGood(String[] args, User someUser){
        
        boolean goodUsername = false;
        String nick = new String();
        if(args.length == 0 || args.length > 1){
            nick = "";
        }
        else {
            nick = args[0];
        }
        
        while(!goodUsername){
            try{
                someUser.setName(nick);
                goodUsername = true;
            }
            catch(MyThrownException e) {
                this.viewMenu.printString(e.getMessage());
                this.viewMenu.askAuthor();
                nick = this.getStringFromUser();
            }
            
        }
    }
    
    /**
     * Reads an integer input from the user. Handles exceptions if the input 
     * is not a valid integer, and prompts the user to enter the value again.
     *
     * @return The valid integer provided by the user.
     * @see User
     */
    public int getIntFromUser() {
        Scanner contact = new Scanner(System.in);
        boolean goodNumber = false;
        int answer = 0;
        while(!goodNumber){
            try{
                answer = contact.nextInt();
                goodNumber = true;
            }
            catch (InputMismatchException e){
                this.viewMenu.printWrongNumberException();
                contact.nextLine();
            }
            
        }
        return answer;
    }
    
    /**
     * Reads a string input from the user.
     *
     * @return The string input provided by the user.
     * @see User
     */
    public String getStringFromUser() {
        Scanner contact = new Scanner(System.in);
        String answer = contact.nextLine();
        return answer;
    }
    
    /**
     * Displays all notices currently in the system. The notices are retrieved
     * from the model, and the details are passed to the view for printing.
     * 
     * @param notices The object containing all notices in the system.
     * @see NoticeList
     */
    public void printAllNotices(NoticeList notices){
        for(int i = 0; i < notices.getSize(); i++) {
                    String nb = Integer.toString(i + 1);
                    this.viewMenu.printString(nb + " " + notices.getOne(i).getTitle());
                    
                }
    }
    
    /**
     * Adds a new notice to the system. The user provides the title and text
     * for the notice, and the author's nickname is automatically assigned 
     * based on the username.
     * 
     * @param notices The object containing all notices in the system.
     * @param givenAuthor The nickname provided by the user currently running the application.
     * @see NoticeList
     * @see User
     */
    public void addNewNotice(NoticeList notices, String givenAuthor){
        this.viewMenu.askTitle();
        String title = this.getStringFromUser();
        String author = new String(givenAuthor);
        this.viewMenu.askText();
        String text = this.getStringFromUser();
        notices.addNotice(title, author, text);
    }
    
    /**
     * Deletes a specific notice chosen by the user. The user selects the 
     * notice from a list, and the notice is then removed from the system.
     * 
     * @param notices The object containing all notices in the system.
     * @see NoticeList
     */
    public void deleteNotice(NoticeList notices){
        printAllNotices(notices);
        this.viewMenu.askNotice();
        int numberNotice = this.getIntFromUser();
        try{
            notices.deleteOne(numberNotice - 1);
        }
        catch(IndexOutOfBoundsException e) {
            this.viewMenu.printBoundException();
        }
    }
    
    /**
     * Displays the details of a specific notice chosen by the user.
     * 
     * @param notices The object containing all notices in the system.
     * @see NoticeList
     */
    public void checkSpecificNotice(NoticeList notices){
        this.viewMenu.askNoticeToCheck();
        int numberNotice = this.getIntFromUser();
        try{
            Notice n = notices.getOne(numberNotice - 1);
            this.viewMenu.printSpecificNotice(n.getTitle(), n.getAuthor(), n.getText());
        }
        catch(IndexOutOfBoundsException e) {
            this.viewMenu.printBoundException();
        }
    }
    
    /**
     * Constructor that initializes the view menu object for interacting
     * with the user.
     */
    public ControllProgramFlow(){
        this.viewMenu = new PrintMenu();
    }
    
    /**
     * Displays all options from the main menu.
     */
    public void printMainMenu(){
        this.viewMenu.printOptions();
    }
}
