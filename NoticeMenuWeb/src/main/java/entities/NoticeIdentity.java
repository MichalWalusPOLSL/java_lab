package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import model.Type;

/**
 * Represents the identity of a notice, including its type and the associated author.
 * 
 * This class is an embeddable entity, used to represent a composite value object
 * in a relational database. It is part of the Notice entity.
 * 
 * @author Michal Walus
 * @version 1.0
 */
@Embeddable
public class NoticeIdentity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * The type of the notice. This is an enumerated value stored as a string in
     * the database.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;
    
    /**
     * The author associated with the notice. This is a many-to-one relationship
     * with the AppUser entity.
     */
    @ManyToOne(optional = false) 
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false) 
    private AppUser author;
    
    /**
     * Default constructor for the NoticeIdentity entity.
     */
    public NoticeIdentity() {
    }
    
    /**
     * Constructs a new NoticeIdentity with the specified type and author.
     *
     * @param type the type of the notice
     * @param author the author of the notice
     */
    public NoticeIdentity(Type type, AppUser author) {
        this.type = type;
        this.author = author;
    }
    
    /**
     * Retrieves the type of the notice.
     *
     * @return the type of the notice
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Sets the type of the notice.
     *
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }
    
    /**
     * Retrieves the author of the notice.
     *
     * @return the author of the notice
     */
    public AppUser getAuthor() {
        return author;
    }
    
    /**
     * Sets the author of the notice.
     *
     * @param author the author to set
     */
    public void setAuthor(AppUser author) {
        this.author = author;
    }
    
    /**
     * Returns a string representation of the notice identity, including its
     * type and the author's name.
     *
     * @return a string representation of the notice identity
     */
    @Override
    public String toString() {
        return "NoticeIdentity[type=" + type + ", author=" + (author != null ? author.getName() : "null") + "]";
    }
}
