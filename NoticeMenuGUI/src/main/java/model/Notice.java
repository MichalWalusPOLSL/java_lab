
package model;
import lombok.Data;
import lombok.AllArgsConstructor;

/**
 * Represents a notice created by a user in the system.
 * The class stores details such as the title, author (username), 
 * and the description (text) of the notice.
 * 
 * @author Michal Walus
 * @version 1.0
 */

@Data
@AllArgsConstructor
public class Notice {
    
     /** The title of the notice. */
    private String title;
    /** The username of the person who created the notice. */
    private String author;
    /** The description or content of the notice. */
    private String text;

    
}
