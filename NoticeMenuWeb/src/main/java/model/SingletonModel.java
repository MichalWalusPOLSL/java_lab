package model;

/**
 * SingletonModel is a utility class that provides shared instances of
 * NoticeList and User to ensure consistent and shared access to these objects
 * across the application.
 * 
 * @author Michal Walus
 * @version 1.0
 */
public class SingletonModel {
   
    /**
     * The single shared instance of the NoticeList class.
     */
    private static NoticeList instanceNotice;

    /**
     * The single shared instance of the User class.
     */
    private static User instanceUser;
    
    /**
     * Private constructor to prevent instantiation of this class.
     * SingletonModel is designed to follow the Singleton design pattern.
     */
    private SingletonModel() {        
    }
    
    /**
     * Returns the shared instance of NoticeList. If the instance does not
     * exist, it will be created lazily.
     *
     * @return the single instance of NoticeList.
     */
    public static NoticeList getInstanceNotice() {

        if(instanceNotice == null) {
            instanceNotice = new NoticeList();
        }
        return instanceNotice;
    }   
    
    /**
     * Returns the shared instance of User. If the instance does not exist, it
     * will be created lazily.
     *
     * @return the single instance of User.
     */
    public static User getInstanceUser(){
        if(instanceUser == null) {
            instanceUser = new User();
            
        }
        return instanceUser;
    }
}
