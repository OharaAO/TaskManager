import me.xdrop.jrand.JRand;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DatabaseSeeder {

    private static final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();

    public void seed() {
        try {
            List<User> users = generateUsers(10);
            System.out.println(users.size() + " users have been inserted into the database.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            h2Embeddedmode.closeConnection(); // Close the connection when done
        }
    }

    static List<User> generateUsers(int number) throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connection = h2Embeddedmode.getConnection();

        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO USERS (USERNAME) VALUES (?)")) {
            for (int i = 0; i < number; i++) {
                String firstName = JRand.firstname().gen(); // Generate a first name
                User user = new User(firstName);
                users.add(user);

                stmt.setString(1, firstName);
                stmt.executeUpdate();
            }
        }
        return users;
    }





}