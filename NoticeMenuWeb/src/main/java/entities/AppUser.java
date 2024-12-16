package entities;

import jakarta.persistence.*;
import java.io.Serializable;
import model.MyThrownException;

/**
 * Represents an application user with a unique username.
 * 
 * @author Michal Walus
 * @version 1.0
 */
@Entity
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws MyThrownException {
        if (name == null || name.isBlank()) {
            throw new MyThrownException("You have to pass your username");
        }
        if (!Character.isLetter(name.charAt(0))) {
            throw new MyThrownException("Given username doesn't start with a letter");
        }
        this.name = name;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
