import java.time.LocalDate;

class DatedTask extends Task {
    LocalDate dueDate; // Date d'échéance de la tâche

    public DatedTask(String title, String description, User creator, LocalDate dueDate) {
        super(title, description, creator);
        this.dueDate = dueDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
