
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class represents a collection of notices stored in an ArrayList. 
 * It provides methods to add, retrieve, and delete notices from the list.
 * 
 * @author Michal Walus
 * @version 1.3
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
     * Deletes a specific notice from the list.
     * 
     * @param notice The notice to be deleted.
     */
    public void deleteOne(Notice notice) {
        this.allNotice.remove(notice); 
    }
    
    /**
     * Returns the number of notices currently stored in the list.
     * 
     * @return The number of notices.
     */
    public int getSize(){
        return this.allNotice.size();
    }
    
    /**
     * Returns all the notices currently stored in the list as ObservableList.
     * 
     * @return A list of all notices as an ObservableList.
     */
    public ObservableList<Notice> getAllObservable() {
        return FXCollections.observableArrayList(allNotice);
    }
    
    /**
     * Returns all notices currently stored in the list as ArrayList
     * 
     * @return all notices in ArrayList format
     */
    public ArrayList<Notice> getAllNotices() {
        return new ArrayList<>(allNotice); 
    }
    
    /**
     * Returns all notices currently stored in the list for given user
     * 
     * @param username id of user we want to find notices for
     * @return notices of the given user
     */
    public List<Notice> getNoticesByUser(String username) {
        return this.getAllNotices().stream()
            .filter(notice -> notice.getAuthor().equals(username))
            .collect(Collectors.toList());
    }

    
}
    
    
    

