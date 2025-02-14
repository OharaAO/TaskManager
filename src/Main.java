import java.time.LocalDate;
import java.util.*;
import me.xdrop.jrand.JRand;

//
// Classe principale pour exécuter le programme
public class Main {
    public static void main(String[] args) {
        TaskManager taskManager =  TaskManager.getInstance();
        Scanner scanner = new Scanner(System.in);
        System.out.println(taskManager.listTasks().toString());
        System.out.println(JRand.bool().gen());
        System.out.println(JRand.);

        int action = 1;
//Boucle pour demander à l'utilisateur ce qu'il souhaite faire.
        while (action  >0 && action < 9) {

            System.out.println("Bonjour que voulez vous faire ?");
            System.out.println("1: créer une tache");
            System.out.println("2: modifier une tache");
            System.out.println("3: supprimer une tache");
            System.out.println("4: lister les taches");
            System.out.println("5: Afficher la liste des utilisateurs");
            System.out.println("6: Ajouter un utilisateur");
            System.out.println("7: Supprimer un utilisateur");
            System.out.println("8: quitter");
            action = scanner.nextInt();

            //Créer une tache
            if (action == 1) {
                System.out.println("Entrez le titre de la tâche :");
                String title = scanner.next();
                System.out.println("Entrez la description de la tâche :");
                String description = scanner.next();
                System.out.println("Entrez votre nom :");
                String creatorName = scanner.next();
                User creator = new User(
                        creatorName);

                Task task1 = new Task(
                       title,
                       description,
                       creator
                );
                taskManager.addTask(task1);
                System.out.println("Tâche créée avec succès !");
                System.out.println((task1).toString());
                System.out.println(taskManager.listTasks().toString());

            //Modifeier une tache
            } else if (action == 2) {
                System.out.println(taskManager.listTasks());
                System.out.println("Quelle tache voulez vous modifier ?");
                System.out.println("Entrez l'ID de la tâche à modifier :");
                String taskId = scanner.next();
                System.out.println("Entrez le nouveau titre :");
                String title = scanner.next();
                System.out.println("Entrez la nouvelle description :");
                String description = scanner.next();
                System.out.println("Est-ce que la tâche est terminée ? (true/false) :");
                boolean done = scanner.nextBoolean();
                try {
                    taskManager.updateTask(taskId, title, description, done);
                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(taskManager.listTasks().toString());


            }
            //Supprimer une tache.
            else if (action == 3) {
                System.out.println(taskManager.listTasks());
                System.out.println("Quelle tache voulez vous supprimer ?");
                System.out.println("Entrer l'Id de la tache à supprimer :");
                String taskId = scanner.next();
                try {
                    taskManager.removeTask(taskId);
                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(taskManager.listTasks().toString());

            }
            //Afficher la lidste des taches
            else if (action == 4) {
                System.out.println("voici la liste des taches");
                System.out.println(taskManager.listTasks().toString());
            }
            // Afficher la liste des utilisateurs
            else if (action == 5) {
                System.out.println("voici la liste des utilisateurs");
                System.out.println(taskManager.listUsers().toString());
            }
            //Ajouter un utilisateur
            else if (action == 6) {
                System.out.println("Entrez lz nom de l'utilisateur :");
                String firstName = scanner.next();
                User user = new User(firstName);

                User user1 = new User(
                        firstName
                );
                taskManager.addUser(user1);
                System.out.println("Utilisateur crée avec succès !");
                System.out.println(taskManager.listUsers().toString());
            }
            //Supprimer un utilisateur de la liste
            else if (action == 7) {
                System.out.println(taskManager.listUsers().toString());
                System.out.println("Quel utilisateur voulez vous supprimer");
                System.out.println("Entrz l'Id");
                String userId = scanner.next();
                try {
                    taskManager.removeUser(userId);
                } catch (EntityNotFoundException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(taskManager.listUsers().toString());
            }


            //Quitter /fermer le programme
            else if (action == 8){
                System.out.println("Quitter");
                break;
            }
            //Afficher une erruer si on entre une donnée invalide
            else {
                System.out.println("Action invalide");
            }


        }





    }
}

