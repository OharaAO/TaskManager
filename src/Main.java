import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;



//
// Classe principale pour exécuter le programme
public class Main  {
    public static void main(String[] args) throws Exception {
        // initialise la connexion à la base de donnée H2
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();
        Connection connection = h2Embeddedmode.getConnection(); // Get connection from H2Embeddedmode


        TaskManager taskManager =  TaskManager.getInstance();

        // Interface graphique
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainWindow mainWindow = MainWindow.getInstance();
                mainWindow.mainButton();
                mainWindow.show();

            }
        });
            // crée un server
        try (ServerSocket server = new ServerSocket(8080)) {
            while (true) {
                Socket client = server.accept(); // initialise le server
                BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream())); // transformer le flux de donnée en texte



            }
        }




        // Ajouter une DatedTask
        /*
        try {
            List<Task> tasks = taskManager.addDatedTask();
            System.out.println("Task added successfully: " + tasks);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        /*
        // Ajouter un utilisateur
        try {
            List<User> users = taskManager.addUser();
            System.out.println("User added succesfilly" + users);
        }catch (Exception e) {
            e.printStackTrace();
        } */

        /*
        //Supprimer une DatedTask
        try {
            List<Task> tasks = taskManager.deleteDatedTask();
            System.out.println("Task deleted ");
        }catch (Exception e) {
            e.printStackTrace();
        }

         */

        // Mettre a jour une Datedtask
        /*
        try {
            List<Task> tasks = taskManager.updateDatedTask();
            System.out.println("tasks updated ");
        }catch (Exception e) {
            e.printStackTrace();
        }
        */
        // Supprimer une tache
        /*
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

