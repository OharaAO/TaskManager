import java.time.LocalDate;
import java.util.*;
import me.xdrop.jrand.JRand;

import javax.swing.*;
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

        // Interface graphique
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.show();

            }
        });

        /*
        // Ajouter une tache
        try {
            List<Task> tasks = taskManager.addTask();
            System.out.println("Task added successfully: " + tasks);
        } catch (SQLException e) {
            e.printStackTrace();
        } */


        /*
        // Ajouter un utilisateur
        try {
            List<User> users = taskManager.addUser();
            System.out.println("User added succesfilly" + users);
        }catch (Exception e) {
            e.printStackTrace();
        } */

        /*
        // Supprimer une tache

        try {
            List<Task> tasks = taskManager.deleteTask();
            System.out.println("Task deleted ");
        }catch (Exception e) {
            e.printStackTrace();
        }
        */

        /*
        //Supprimer un utilisateur

        try {
            List<User> users = taskManager.deleteUser();
            System.out.println("users deleted ");
        }catch (Exception e) {
            e.printStackTrace();
        }

         */

        // mettre à jour une tache
        /*
        try {
            List<Task> tasks = taskManager.updateTask();
            System.out.println("tasks updated ");
        }catch (Exception e) {
            e.printStackTrace();
        }





        //DatabaseSeeder seeder = new DatabaseSeeder();
        //eeder.seed();
        //DatabaseSeeder.generateUsers(10);
        */
    }
}

