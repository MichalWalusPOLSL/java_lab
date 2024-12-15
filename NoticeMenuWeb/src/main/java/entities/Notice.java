package entities;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * Represents a notice created by a user in the system.
 * Includes title, identity (type and author), and text description.
 * 
 * This class is a JPA entity for persistence in a relational database.
 */
@Entity
public class Notice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Embedded
    private NoticeIdentity identity;

    @Column(nullable = false, length = 500)
    private String text;

    // Constructors
    public Notice() {
    }

    public Notice(String title, NoticeIdentity identity, String text) {
        this.title = title;
        this.identity = identity;
        this.text = text;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NoticeIdentity getIdentity() {
        return identity;
    }

    public void setIdentity(NoticeIdentity identity) {
        this.identity = identity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // hashCode, equals, toString
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Notice)) {
            return false;
        }
        Notice other = (Notice) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Notice[ id=" + id + ", title=" + title + ", author=" + identity.getAuthor() + " ]";
    }
}
