package entities;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Represents a notice created by a user in the system.
 *
 * A notice consists of a title, an identity (author and type), and a text
 * description. This class is a JPA entity that is mapped to a relational
 * database for persistence.
 *
 * The class also provides methods to access and modify the notice's properties,
 * as well as overrides for equality, hashCode, and string representation.
 *
 * @author Michal Walus
 * @version 1.0
 */
@Entity
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * The unique identifier of the notice. This value is automatically
     * generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The title of the notice. This field is mandatory.
     */
    @Column(nullable = false)
    private String title;
    
    /**
     * The identity of the notice, including the author and type of notice. This
     * is an embedded field that represents a composite value object.
     */
    @Embedded
    private NoticeIdentity identity;
    
    /**
     * The text content of the notice. This field is mandatory and is limited to
     * 500 characters.
     */
    @Column(nullable = false, length = 500)
    private String text;
    
    /**
     * Default constructor for the Notice entity.
     */
    public Notice() {
    }
    
    /**
     * Constructs a new Notice with the given title, identity, and text.
     *
     * @param title the title of the notice
     * @param identity the identity of the notice, including author and type
     * @param text the text content of the notice
     */
    public Notice(String title, NoticeIdentity identity, String text) {
        this.title = title;
        this.identity = identity;
        this.text = text;
    }
    
    /**
     * Retrieves the unique identifier of the notice.
     *
     * @return the unique identifier of the notice
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Sets the unique identifier for the notice.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Retrieves the title of the notice.
     *
     * @return the title of the notice
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Sets the title of the notice.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Retrieves the identity of the notice.
     *
     * @return the identity of the notice, including author and type
     */
    public NoticeIdentity getIdentity() {
        return identity;
    }
    
    /**
     * Sets the identity of the notice.
     *
     * @param identity the identity to set
     */
    public void setIdentity(NoticeIdentity identity) {
        this.identity = identity;
    }
    
    /**
     * Retrieves the text content of the notice.
     *
     * @return the text content of the notice
     */
    public String getText() {
        return text;
    }
    
    /**
     * Sets the text content of the notice.
     *
     * @param text the text content to set
     */
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Computes the hash code for this notice, based on its unique identifier.
     *
     * @return the hash code of the notice
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    
    /**
     * Compares this notice to another object for equality.
     *
     * @param object the object to compare with
     * @return true if the objects are equal; false otherwise
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Notice)) {
            return false;
        }
        Notice other = (Notice) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }
    
    /**
     * Returns a string representation of the notice, including its ID, title,
     * and author.
     *
     * @return a string representation of the notice
     */
    @Override
    public String toString() {
        return "Notice[ id=" + id + ", title=" + title + ", author=" + identity.getAuthor() + " ]";
    }
}
