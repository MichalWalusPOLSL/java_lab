
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Type;

/**
 * This class represents a collection of notices stored in an ArrayList. 
 * It provides methods to add, retrieve, and delete notices from the list.
 * 
 * @author Michal Walus
 * @version 1.4
 * @see Notice
 */
@Getter
public class NoticeList {
    
    /** The list that stores all notice objects. */
    private ArrayList<Notice> allNotice = new ArrayList<Notice>();
    
   /**
    * Adds a new notice to the list of notices.
    * Validates the input Notice object to ensure it is not null.
    * 
    * @param notice The Notice object to be added.
    * @throws MyThrownException if the Notice object is null or contains invalid fields.
    */
   public void addNotice(Notice notice) throws MyThrownException {
       if (notice == null) {
           throw new MyThrownException("The notice object cannot be null.");
       }

       validateNoticeFields(notice);
       this.allNotice.add(notice);
   }

   /**
    * Validates the fields of a Notice object.
    * Throws an exception if any field is invalid.
    *
    * @param notice The Notice object to validate.
    * @throws MyThrownException if any field in the Notice object is invalid.
    */
   private void validateNoticeFields(Notice notice) throws MyThrownException {
       if (notice.getTitle() == null || notice.getTitle().isBlank()) {
           throw new MyThrownException("Title cannot be empty or null.");
       }
       if (notice.getAuthor() == null || notice.getAuthor().isBlank()) {
           throw new MyThrownException("Author cannot be empty or null.");
       }
       if (notice.getType() == null) {
           throw new MyThrownException("Type cannot be null.");
       }
       if (notice.getText() == null || notice.getText().isBlank()) {
           throw new MyThrownException("Description cannot be empty or null.");
       }
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
     * Deletes a specific notice from the list.
     * Validates that the notice is not null and exists in the list before deletion.
     * 
     * @param notice The notice to be deleted.
     * @throws MyThrownException if the notice is null or does not exist in the list.
     */
    public void deleteOne(Notice notice) throws MyThrownException {
        if (notice == null) {
            throw new MyThrownException("The notice to be deleted cannot be null.");
        }
        if (!this.allNotice.contains(notice)) {
            throw new MyThrownException("The notice does not exist in the list.");
        }
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
     * Returns all notices currently stored in the list for given user
     * 
     * @param username id of user we want to find notices for
     * @return notices of the given user
     */
    public List<Notice> getNoticesByUser(String username) {
        return this.getAllNotice().stream()
            .filter(notice -> notice.getAuthor().equals(username))
            .collect(Collectors.toList());
    }

    
}
    
    
    

