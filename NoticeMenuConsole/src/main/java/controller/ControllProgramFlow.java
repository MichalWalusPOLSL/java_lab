
package controller;
import java.util.Scanner;
import model.*;
import view.PrintMenu;
import java.util.InputMismatchException;



/**
 * Controller handling exceptions and providing user with chosen options.
 * <p>
 * viewMenu object of PrintMenu, used to display text on screen
 * </p>
 * @author Michal Walus
 * @version 1.0
 * @see PrintMenu
 */
public class ControllProgramFlow {
    
    PrintMenu viewMenu;
    
    /**
     * Chcecks if username provided by user is correct (it has to be just one 
     * argument from command line and needs to start with a letter)
     * if the username is not valid, method will loop until all rules are followed.
     * @param args arguments from command line (optional) program also works
     * without them
     * @param someUser object identifying user who is currently running application
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
     * Method, which goal is to read Integer provided by user. 
     * @return returns Integer provided by user
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
     * Method, which goal is to read String provided by user.
     * @return returns String provided by user
     * @see User
     */
    public String getStringFromUser() {
        Scanner contact = new Scanner(System.in);
        String answer = contact.nextLine();
        return answer;
    }
    
    /**
     * Chcecks all notices that are currently in the system and transfers information
     * to view, which puts them on a screen.
     * @param notices object of NoticeList class with all notices in the system
     * @see NoticeList
     */
    public void printAllNotices(NoticeList notices){
        for(int i = 0; i < notices.getSize(); i++) {
                    String nb = Integer.toString(i + 1);
                    this.viewMenu.printString(nb + " " + notices.getOne(i).getTitle());
                    
                }
    }
    
    /**
     * Adds new notice to the system.
     * @param notices object of NoticeList class with all notices in the system
     * @param givenAuthor Nickname provided by user currently running the application
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
     * Deletes one notice chosen by user.
     * @param notices object of NoticeList class with all notices in the system
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
     * Checks details of notice chosen by user.
     * @param notices object of NoticeList class with all notices in the system
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
     * Creates new object with PrintMenu class field.
     */
    public ControllProgramFlow(){
        this.viewMenu = new PrintMenu();
    }
    
    /**
     * Allows to print all main menu options.
     */
    public void printMainMenu(){
        this.viewMenu.printOptions();
    }
}
