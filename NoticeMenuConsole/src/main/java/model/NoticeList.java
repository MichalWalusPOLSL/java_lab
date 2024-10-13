
package model;

import java.util.ArrayList;

/**
 * Objects of NoticeList class are used for containing Notices.
 * <p>
 * allNotice is an ArrayList of Notice class objects. It stores data 
 * about all Notices.
 * </p>
 * @author Michal Walus
 * @version 1.0
 * @see Notice
 */
public class NoticeList {
    ArrayList<Notice> allNotice = new ArrayList<Notice>();
    
    /**
     * Method used for adding notices to allNotice field.
     * @param title title of notice
     * @param author username
     * @param text details of notice
     */
    public void addNotice(String title, String author, String text) {
        this.allNotice.add(new Notice(title, author, text));
    }
    
    /**
     * Returns all notices currently stored.
     * @return all notices
     */
    public ArrayList<Notice> getAll(){
        return allNotice;
    }
    
    /**
     * Returns one notice chosen by user. 
     * @param i position of notice in allNotice field
     * @return chosen notice
     */
    public Notice getOne(int i){
        return this.allNotice.get(i);
    }
    
    /**
     * Deletes one notice chosen by user.
     * @param i position of notice in allNotice field
     */
    public void deleteOne(int i){
        this.allNotice.remove(i);
    }
    
    /**
     * Returns number of notices currently stored.
     * @return number of currently stored notices
     */
    public int getSize(){
        return this.allNotice.size();
    }
    
    
}
