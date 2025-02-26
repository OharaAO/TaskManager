import java.util.UUID;
import java.time.LocalDate;
import java.util.*;

class User {


    protected final long id ; // Identifiant unique généré automatiquement
    protected String firstName;
    protected String lastName;// Prénom de l'utilisateur








    public User(String firstName) {
        this.id = getId();
        this.firstName = firstName;
        this.lastName = firstName;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +

                '}';
    }
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}