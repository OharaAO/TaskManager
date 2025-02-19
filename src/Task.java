class Task {
    //champ statique pour garder le compteur
    private static int instanceCounter;



    // Bloc statique : initialise le compteur à 0 à la première définition
    static {
        instanceCounter = 0; // Initialise le compteur
    }

    // Bloc d'instance : incrémente le compteur et attribue l'ID
    {
        instanceCounter++;
        this.id = "TASK-" + instanceCounter; // Générez un ID unique basé sur le compteur
    }


    protected final String id; // Identifiant unique généré automatiquement
    protected String title; // Titre de la tâche
    protected String description; // Description de la tâche
    protected boolean done; // Indicateur de complétion
    protected final String creator; // Référence à l'utilisateur créateur

    public Task(String title, String description, String creator, Boolean done) {

        this.title = title;
        this.description = description;
        this.done = false;
        this.creator = creator;
    }





    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' + '\n' +
                ", title='" + title + '\'' + '\n' +
                ", description='" + description + '\'' + '\n' +
                ", done=" + done + '\n' +
               // ", creator id=" + creator.getId() + '\'' + '\n' +
              //  ", creator=" + creator.getFirstName() + '\n' +
                '}';
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getCreator() {
       return creator;
    }

    public void setCreator(User creator) {

    }
}

