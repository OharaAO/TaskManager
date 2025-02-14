import java.util.UUID;
import java.time.LocalDate;
import java.util.*;

class User {
    // Champ statique pour garder le compteur
    private static int instanceCounter;

    private final long id; // Identifiant unique généré automatiquement
    private String firstName;
    private String lastName;// Prénom de l'utilisateur

    // Bloc statique : initialise le compteur à 0
    static {
        instanceCounter = 0;
    }






    public User(String firstName) {
        this.id =  instanceCounter++;
        this.firstName = firstName;
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
}