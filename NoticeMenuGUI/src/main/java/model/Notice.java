
package model;

/**
 * Represents a notice created by a user in the system.
 * The class stores details such as the title, author (username), 
 * and the description (text) of the notice.
 * 
 * @author Michal Walus
 * @version 1.0
 */
public class Notice {
    
     /** The title of the notice. */
    private String title;
    /** The username of the person who created the notice. */
    private String author;
    /** The description or content of the notice. */
    private String text;

    
    /**
     * Constructs a new notice with the specified title, author, and description.
     * 
     * @param title The title of the notice.
     * @param author The username of the person who created the notice.
     * @param text The description or content of the notice.
     */
    public Notice(String title, String author, String text) {
        this.title = title;
        this.author = author;
        this.text = text;
    }
    
    
    /**
     * Returns the title of the notice.
     * 
     * @return The title of the notice.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title of the notice.
     * 
     * @param title The title to be assigned to the notice.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns the author (username) who created the notice.
     * 
     * @return The username of the notice's author.
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Sets the author (username) of the notice.
     * 
     * @param author The username of the person who created the notice.
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Returns the description or content of the notice.
     * 
     * @return The description of the notice.
     */
    public String getText() {
        return text;
    }
    
    /**
     * Sets the description or content of the notice.
     * 
     * @param text The description to be assigned to the notice.
     */
    public void setText(String text) {
        this.text = text;
    } 
    
}
