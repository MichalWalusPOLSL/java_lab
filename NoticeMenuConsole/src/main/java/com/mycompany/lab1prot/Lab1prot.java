
package com.mycompany.lab1prot;
import controller.ControllProgramFlow;
import model.NoticeList;
import model.User;

/**
     * Program is designed for notice management. User can choose one of 5 options
     * (check all notices, add notice, delete notice, check notice details, leave). 
     * Customer can run program from command line providing his username, if not 
     * application will ask him to enter it manually.
     * @author Michal Walus group 13, Monday 16:30-19:30
     * @version 1.3
     */
public class Lab1prot {
   /**
    * The main method that starts the notice management program. 
    * It checks if the username is provided via command-line arguments. 
    * If not, the program will prompt the user to enter one manually. 
    * The user can then interact with the program by selecting options 
    * from the main menu.
    * 
    * @param args Command-line arguments; the first argument should be username
    */
    public static void main(String[] args) {
        
        ControllProgramFlow controller = new ControllProgramFlow();
        User someUser = new User();
        controller.checkIfUsernameGood(args, someUser);
        
        boolean run = true;
        NoticeList notices = new NoticeList();
        
        
        while(run) {
            controller.printMainMenu();
            int selectedOption = controller.getIntFromUser();
            
            switch (selectedOption) {
                case 1:
                    controller.printAllNotices(notices);
                    break;
                case 2:
                    controller.addNewNotice(notices, someUser.getName());
                    break;
                case 3:
                    controller.deleteNotice(notices);
                    break;
                case 4:
                    controller.checkSpecificNotice(notices);
                    break;
                default:
                    run = false;
                    break;
            }
            
        }
        
    }
}
