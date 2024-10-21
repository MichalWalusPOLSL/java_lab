
package model;

import java.util.ArrayList;

/**
 * This class represents a collection of notices stored in an ArrayList. 
 * It provides methods to add, retrieve, and delete notices from the list.
 * 
 * @author Michal Walus
 * @version 1.1
 * @see Notice
 */
public class NoticeList {
    
    /** The list that stores all notice objects. */
    private ArrayList<Notice> allNotice = new ArrayList<Notice>();
    
    /**
     * Adds a new notice to the list of notices.
     * 
     * @param title The title of the notice.
     * @param author The username of the person who created the notice.
     * @param text The content or details of the notice.
     */
    public void addNotice(String title, String author, String text) {
        this.allNotice.add(new Notice(title, author, text));
    }
    
    /**
     * Returns all the notices currently stored in the list.
     * 
     * @return A list of all notices.
     */
    public ArrayList<Notice> getAll(){
        return allNotice;
    }
    
    /**
     * Returns a specific notice based on its position in the list.
     * 
     * @param i The index of the notice in the list.
     * @return The notice at the specified index.
     */
    public Notice getOne(int i){
        return this.allNotice.get(i);
    }
    
    /**
     * Deletes a specific notice based on its position in the list.
     * 
     * @param i The index of the notice to be deleted.
     */
    public void deleteOne(int i){
        this.allNotice.remove(i);
    }
    
    /**
     * Returns the number of notices currently stored in the list.
     * 
     * @return The number of notices.
     */
    public int getSize(){
        return this.allNotice.size();
    }
    
    
    
}
