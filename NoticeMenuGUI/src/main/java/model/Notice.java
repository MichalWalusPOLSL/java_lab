package model;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * Represents a notice created by a user in the system.
 * The class stores details such as the title, author (username), 
 * and the description (text) of the notice.
 * 
 * @author Michal Walus
 * @version 1.3
 */
@Data
public class Notice {

    /** The title of the notice. */
    private String title;

    /** The type of the notice. */
    private Type type; 

    /** The username of the person who created the notice. */
    private String author;

    /** The description or content of the notice. */
    private String text;

    
    /** Enum type for different categories of notices. */
    public enum Type {
        SALE,
        RENTAL,
        SERVICE,
        HIRE,
        OTHER
    }
    
    /**
     * Constructs a new Notice with validation.
     *
     * @param title The title of the notice.
     * @param author The username of the author.
     * @param type The type of the notice.
     * @param text The description or content of the notice.
     * @throws MyThrownException if any input is invalid.
     */
    public Notice(String title, String author, Type type, String text) throws MyThrownException {
        validateInputs(title, author, type, text);
        this.title = title;
        this.author = author;
        this.type = type;
        this.text = text;
    }

    /**
     * Validates the inputs for creating a Notice.
     * Throws an exception if any field is invalid.
     *
     * @param title The title of the notice.
     * @param author The username of the author.
     * @param type The type of the notice.
     * @param text The description or content of the notice.
     * @throws MyThrownException if any input is invalid.
     */
    private void validateInputs(String title, String author, Type type, String text) throws MyThrownException {
        if (title == null || title.isBlank()) {
            throw new MyThrownException("Title cannot be empty or null.");
        }
        if (author == null || author.isBlank()) {
            throw new MyThrownException("Author cannot be empty or null.");
        }
        if (type == null) {
            throw new MyThrownException("Type cannot be null.");
        }
        if (text == null || text.isBlank()) {
            throw new MyThrownException("Description cannot be empty or null.");
        }
    }

}

