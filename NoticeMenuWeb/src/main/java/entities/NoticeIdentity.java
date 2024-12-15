package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.io.Serializable;
import model.Type;

/**
 * Represents the identity of a notice, including type and author.
 */
@Embeddable
public class NoticeIdentity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String author;

    public NoticeIdentity() {
    }

    public NoticeIdentity(Type type, String author) {
        this.type = type;
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "NoticeIdentity[type=" + type + ", author=" + author + "]";
    }
}
