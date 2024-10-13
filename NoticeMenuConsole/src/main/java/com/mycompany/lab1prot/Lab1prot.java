
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
     * @version 1.0
     */
public class Lab1prot {
    /**
     * Allows user to choose action, that he wants to do.
     * @param args[] username given by customer
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
