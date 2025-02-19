import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class TaskManager {
    private static TaskManager instance; // Instance unique de la classe
    private List<Task> tasks = new ArrayList<>(); // Liste des tâches
    private List<User> users = new ArrayList<>(); // Liste des utilisateurs

    // Constructeur privé pour empêcher l'instanciation directe
    private TaskManager() {}

    // Méthode statique pour obtenir l'instance unique
    public static TaskManager getInstance() {
        if (instance == null) {
            synchronized (TaskManager.class) {
                if (instance == null) { // Double vérification de null
                    instance = new TaskManager();
                }
            }
        }
        return instance;
    }

    // Ajoute une tâche à la liste
    public List<Task> addTask() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();

        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection(); // Assuming h2Embeddedmode is a class that provides the connection

            System.out.println("Please enter the title: ");
            String title = scanner.nextLine();
            System.out.println("Please enter the description: ");
            String description = scanner.nextLine();
            System.out.println("Please enter creator name: ");
            String firstName = scanner.nextLine();
            System.out.println("Please enter the done status (true/false): ");
            boolean status = scanner.nextBoolean();
            scanner.nextLine(); // Consume the newline character

            Task task = new Task(title, description, firstName, status);
            tasks.add(task);

            String sql = "INSERT INTO TASKS (TITLE, DESCRIPTION, CREATOR, DONE) VALUES (?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setObject(3, firstName);
            stmt.setBoolean(4, status);

            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return tasks;
    }

    // Add Dated Task
    public List<Task> addDatedTask() throws SQLException  {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();
        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection();

            System.out.println("Please enter the title: ");
            String title = scanner.nextLine();
            System.out.println("Please enter the description: ");
            String description = scanner.nextLine();
            System.out.println("Please enter the done status (true/false): ");
            boolean status = scanner.nextBoolean();
            scanner.nextLine();
            System.out.println("Please enter the done date(YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.println("Please enter creator name: ");
            String firstName = scanner.nextLine();
            Task task = new Task(title, description, firstName, status);

            String sql = "INSERT INTO DATEDTASKS (TITLE, DESCRIPTION, DONE, DUEDATE, CREATOR) VALUES (?, ?, ?, ?,?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1,title);
            stmt.setString(2, description);
            stmt.setObject(3, status);
            stmt.setString(4, date);
            stmt.setObject(5, firstName);

            stmt.executeUpdate();
        }finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }
        return tasks;
    }

    //supprimer une DatedTask
    public List<Task> deleteDatedTask() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();
        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection();
            System.out.println("Please enter the title: ");
            String title = scanner.nextLine();
            String sql = "DELETE FROM DATEDTASKS WHERE TITLE = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return tasks;
    }

    // Supprimer une tache
    public List<Task> deleteTask() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();
        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection();
            System.out.println("Please enter the title: ");
            String title = scanner.nextLine();
            String sql = "DELETE FROM TASKS WHERE TITLE = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return tasks;
    }



    // Ajouter un Utilisateur
    public List<User> addUser() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();

        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection(); // Assuming h2Embeddedmode is a class that provides the connection

            System.out.println("Please enter the first name: ");
            String firstName = scanner.nextLine();

            User user = new User(firstName);
            users.add(user);
            String sql = "INSERT INTO USERS (USERNAME) VALUES (?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return users;
    }

    //Supprimer un utilisateur
    public List<User> deleteUser() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();

        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection();
            System.out.println("Please enter the first name: ");
            String firstName = scanner.nextLine();
            String sql = "DELETE FROM USERS WHERE USERNAME = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, firstName);
            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return users;


    }

    //Mettre à jour une tache
    List<Task> updateTask() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();

        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection();
            System.out.println("Please enter the title of task to update: ");
            String oldTitle = scanner.nextLine();
            System.out.println("Enter de new title of task to update: ");
            String title = scanner.nextLine();
            System.out.println("Please enter the description of task to update: ");
            String description = scanner.nextLine();
            System.out.println("Please enter the done status of task to update: ");
            boolean status = scanner.nextBoolean();
            scanner.nextLine();
            Task task = new Task(title, description, "", status);
            tasks.add(task);

            String sql = "UPDATE TASKS SET TITLE = ?, DESCRIPTION = ?, DONE = ? WHERE TITLE = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setBoolean(3, status);
            stmt.setString(4, oldTitle);
            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return tasks;
    }

    //Mettre à jour une dated task
    List<Task> updateDatedTask() throws SQLException {
        final H2Embeddedmode h2Embeddedmode = H2Embeddedmode.getInstance();

        Connection connection = null;
        PreparedStatement stmt = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = h2Embeddedmode.getConnection();
            System.out.println("Please enter the title of task to update: ");
            String oldTitle = scanner.nextLine();
            System.out.println("Enter de new title of task to update: ");
            String title = scanner.nextLine();
            System.out.println("Please enter the description of task to update: ");
            String description = scanner.nextLine();
            System.out.println("Please enter the done status of task to update: ");
            boolean status = scanner.nextBoolean();
            System.out.println("Please enter the Due date of task to update (YYYY-MM-DD): ");
            String date = scanner.next();
            scanner.nextLine();

            String sql = "UPDATE DATEDTASKS SET TITLE = ?, DESCRIPTION = ?, DONE = ?,DUEDATE = ? WHERE TITLE = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setBoolean(3, status);
            stmt.setString(4, date);
            stmt.setString(5, oldTitle);
            stmt.executeUpdate();

        } finally {
            // Close resources in finally block to ensure they are closed even if an exception occurs
            if (stmt != null) {
                stmt.close();
            }
            if (connection != null) {
                connection.close();
            }
            scanner.close();
        }

        return tasks;
    }






    }






// Placeholder for Task class











/*
    public void addDatedTask(Task task) {
        if (users.stream().noneMatch(user -> user.getFirstName().equals(task.getCreator().getFirstName())))
            users.add(task.getCreator());
        tasks.add(task);
    }
    // Ajoute un utilisateur à la liste
    public static void addUser(User user) {
        users.add(user);
    }

    // Supprime une tâche en fonction de son identifiant
    public void removeTask(String taskId) {
        // Supprime la tâche et vérifie si elle existait
        boolean isRemoved = tasks.removeIf(task -> task.getId().equals(taskId));

        // Si aucune tâche n'a été supprimée, lève une exception
        if (!isRemoved) {
            throw new EntityNotFoundException("Task ID: " + taskId + " does not exist.");
        }
    }
    //Supprime un utilisateur de la liste en fonction de son identifiant
    public void removeUser(String userId) {
        // Supprime l'utilisateur et vérifie s'il existait
        boolean isRemoved = users.removeIf(user -> user.getId().equals(userId));

        // Si aucun utilisateur n'a été supprimé, lève une exception
        if (!isRemoved) {
            throw new EntityNotFoundException("User ID: " + userId + " does not exist.");
        }
    }


    // Met à jour les informations d'une tâche existante
    public void updateTask(String taskId, String title, String description, boolean done) {
        for (Task task : tasks) {
            if (task.getId().equals(taskId)) {
                task.setTitle(title);
                task.setDescription(description);
                task.setDone(done);
            }
            else {
                throw new EntityNotFoundException("Task ID:"+ taskId + " does not exist,");
            }
        }
    }

    // Retourne la liste des tâches
    public List<Task> listTasks() {
        return new ArrayList<>(tasks);
    }

    //Retourne la liste des utilisateurs
    public List<User> listUsers() {
        return new ArrayList<>(users);
    }


}


/* Explication du Singleton

1. **Instance Unique :**
    - Une variable statique `private static TaskManager instance` est ajoutée pour stocker l'instance unique de la classe.

2. **Constructeur Privé :**
    - Le constructeur de la classe est défini comme `private` pour empêcher toute instanciation directe en dehors de la classe.

3. **Accès à l'Instance :**
    - Une méthode statique `getInstance()` est ajoutée. Cette méthode suit un schéma de double vérification (`double-checked locking`) pour garantir que l'instance est créée une seule fois même dans un environnement multithreadé.

4. **Synchronisation :**
    - Le bloc `synchronized` garantit que deux threads ne peuvent pas simultanément créer une instance lors du premier accès.

 */
