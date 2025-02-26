

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Embeddedmode {

    private static volatile H2Embeddedmode instance; // Instance unique de la classe
    private Connection connection;

    // Constructeur privé pour empêcher l'instanciation directe
    private H2Embeddedmode() {
        try {
            // Initialize the connection
            connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            initializeDatabase(); // Ensure the required table exists
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à H2", e);
        }
    }

    public static H2Embeddedmode getInstance() {
        if (instance == null) {
            synchronized (H2Embeddedmode.class) {
                if (instance == null) { // Double vérification de null
                    instance = new H2Embeddedmode();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    private void initializeDatabase() {
        try (Statement stmt = connection.createStatement()) {
            // Create the USERS table if it doesn't exist
            String sql = "CREATE TABLE IF NOT EXISTS USERS (" +
                    "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "USERNAME VARCHAR(255) NOT NULL)";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'initialisation de la base de données", e);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la fermeture de la connexion: " + e.getMessage());
            }
        }
    }
}