package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import model.MyThrownException;

/**
 * Represents an application user with a unique username.
 *
 * This entity is used to persist user information in the database. Each user
 * has a unique identifier (ID) and a username. The username must be unique and
 * comply with validation rules.
 *
 * @author Michal Walus
 * @version 1.0
 */
@Entity
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * The unique identifier for the user. This value is automatically
     * generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * The username of the user. This field is mandatory and must be unique.
     */
    @Column(nullable = false, unique = true)
    private String name;
    
    /**
     * Retrieves the unique identifier of the user.
     *
     * @return the unique identifier of the user
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Sets the unique identifier for the user.
     *
     * @param id the unique identifier to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the username for the user.
     *
     * The username must not be null, empty, or blank. Additionally, the
     * username must start with a letter.
     *
     * @param name the username to set
     * @throws MyThrownException if the username is invalid (e.g., null, blank,
     * or does not start with a letter)
     */
    public void setName(String name) throws MyThrownException {
        if (name == null || name.isBlank()) {
            throw new MyThrownException("You have to pass your username");
        }
        if (!Character.isLetter(name.charAt(0))) {
            throw new MyThrownException("Given username doesn't start with a letter");
        }
        this.name = name;
    }
    
    /**
     * Returns a string representation of the user, including the ID and
     * username.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
