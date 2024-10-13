
package model;

/**
 * Class used to store details about notices created in the system by users.
 * It stores title of notice, nickname of person who created it and
 * description of notice.
 * <p>
 * title is a title of Notice
 * author is username
 * text stores the details of Notice
 * </p>
 * @author Michal Walus
 * @version 1.0
 */
public class Notice {
    private String title;
    private String author;
    private String text;

    /**
     * Returns title of notice.
     * @return title of notice
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title that user specified.
     * @param title title of notice
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Returns author who created the notice
     * @return username
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * Sets author of notice to information provided.
     * @param author author of notice
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    
    /**
     * Returns description of notice.
     * @return description
     */
    public String getText() {
        return text;
    }
    
    /**
     * Sets description of notice to information from user.
     * @param text description of notice
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Constructs a new notice with given information
     * @param title title of notice
     * @param author username
     * @param text description of notice
     */
    public Notice(String title, String author, String text) {
        this.title = title;
        this.author = author;
        this.text = text;
    }
    
    
    
}
