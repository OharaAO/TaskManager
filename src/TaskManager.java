import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

class TaskManager {
    private static TaskManager instance; // Instance unique de la classe
    private List<Task> tasks = new ArrayList<>(); // Liste des tâches
    private static List<User> users = new ArrayList<>(); // Liste des utilisateurs
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
    public void addTask(Task task) {
        if (users.stream().noneMatch(user -> user.getFirstName().equals(task.getCreator().getFirstName())))
            users.add(task.getCreator());
        tasks.add(task);
    }

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
