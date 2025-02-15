import java.time.LocalDate;
import java.util.*;
import me.xdrop.jrand.JRand;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



//
// Classe principale pour exécuter le programme
public class Main {
    public static void main(String[] args) throws Exception {
        // initialise la connexion à la base de donnée H2
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();
        Connection connection = h2Embeddedmode.getConnection(); // Get connection from H2Embeddedmode


        TaskManager taskManager =  TaskManager.getInstance();
        DatabaseSeeder.generateUsers(10);

    }
}

