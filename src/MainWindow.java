import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class MainWindow {
    private static volatile MainWindow Instance;
    private JFrame window;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel addTaskPanel;
    private JPanel updateTaskPanel;
    private JPanel containerPanel;
    private JPanel addUserPanel;
    private JPanel deleteTaskPanel;

    public static MainWindow getInstance() {
        if (Instance == null) {
            synchronized (MainWindow.class) {
                if (Instance == null) {
                    Instance = new MainWindow();
                }
            }
        }
        return Instance;
    }

    TaskManager taskManager = TaskManager.getInstance();

    private MainWindow() {
        initializeWindow();
        initializePanels();
    }

    private void initializeWindow() {
        window = new JFrame();
        window.setTitle("Task Manager");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout);
        window.add(containerPanel, BorderLayout.CENTER);
    }

    private void initializePanels() {
        mainPanel = createMainPanel();
        addTaskPanel = createAddTaskPanel();
        updateTaskPanel = createUpdateTaskPanel();
        addUserPanel = createAddUserPanel();
        deleteTaskPanel = createDeleteTaskPanel();

        containerPanel.add(mainPanel, "MainView");
        containerPanel.add(addTaskPanel, "AddTaskView");
        containerPanel.add(updateTaskPanel, "UpdateTaskView");
        containerPanel.add(addUserPanel, "AddUserView");
        containerPanel.add(deleteTaskPanel, "DeleteTaskView");

        cardLayout.show(containerPanel, "MainView");
    }

    private JPanel createMainPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.LIGHT_GRAY);
        addMainButtons(panel);
        return panel;
    }

    private void addMainButtons(JPanel panel) {
        JButton addTaskButton = createButton("Ajouter une tache", e -> showAddTaskView());
        panel.add(addTaskButton);

        panel.add(createButton("Supprimer une tache", e -> showDeleteTaskView()));

        panel.add(createButton("Modifier une tache", e -> showUpdateTaskView()));


        panel.add(createButton("Ajouter un utilisateur", e -> showAddUserView()));
        panel.add(createButton("Supprimer un utilisateur", e -> {}));
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBackground(Color.WHITE);
        button.addActionListener(listener);
        return button;
    }

    private JPanel createAddTaskPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 7, 7));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private JPanel createDeleteTaskPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 3, 3));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    private JPanel createUpdateTaskPanel() {
        JPanel panel = new JPanel(new GridLayout(7, 2, 3, 3));
        panel.setBackground(Color.WHITE);
        return panel;
    }
    private JPanel createAddUserPanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 3, 3));
        panel.setBackground(Color.WHITE);
        return panel;
    }

    public void show() {
        window.setVisible(true);
    }

    public void showAddTaskView() {
        addTaskPanel.removeAll();
        createTaskForm();
        cardLayout.show(containerPanel, "AddTaskView");
    }

    public void showDeleteTaskView() {
        deleteTaskPanel.removeAll();
        createDeleteTaskForm();
        cardLayout.show(containerPanel, "DeleteTaskView");

    }

    public void showUpdateTaskView() {
        updateTaskPanel.removeAll();
        updateTaskForm();
        cardLayout.show(containerPanel, "UpdateTaskView");
    }

    public void showAddUserView() {
        addUserPanel.removeAll();
        createAddUserForm();
        cardLayout.show(containerPanel, "AddUserView");
    }

    public void createAddUserForm() {
        JTextField usernameField = new JTextField(20);
        addUserPanel.add(new JLabel("Create New User"));
        addUserPanel.add(new JLabel());


        addUserPanel.add(new JLabel("Enter User First Name : "));
        addUserPanel.add(usernameField);

        JButton saveButton = createButton("Save", e -> {
            try {
                saveUser(usernameField);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton cancelButton = createButton("Cancel", e -> showMainView());

        addUserPanel.add(saveButton);
        addUserPanel.add(cancelButton);

        addUserPanel.revalidate();
        addUserPanel.repaint();
    }

    private void createDeleteTaskForm() {
        JTextField taskTitleField = new JTextField(20);

        deleteTaskPanel.add(new JLabel("Delete Task"));
        deleteTaskPanel.add(new JLabel());
        deleteTaskPanel.add(new JLabel("Enter Task Title : "));
        deleteTaskPanel.add(taskTitleField);
        JButton saveButton = createButton("Save", e -> {
            try {
                saveDeletedTask(taskTitleField);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });
        JButton cancelButton = createButton("Cancel", e -> showMainView());
        deleteTaskPanel.add(saveButton);
        deleteTaskPanel.add(cancelButton);
        deleteTaskPanel.revalidate();
        deleteTaskPanel.repaint();

    }

    private void createTaskForm() {
        JTextField taskTitleField = new JTextField(20);
        JTextField taskDescField = new JTextField(20);
        JTextField taskStatusField = new JTextField(20);

        JTextField firstNameField = new JTextField(20);

        addTaskPanel.add(new JLabel("Create a New Task"));
        addTaskPanel.add(new JLabel());

        addTaskPanel.add(new JLabel("Task Title:"));
        addTaskPanel.add(taskTitleField);

        addTaskPanel.add(new JLabel("Task Description:"));
        addTaskPanel.add(taskDescField);

        addTaskPanel.add(new JLabel("Task Status Done (True/False):"));
        addTaskPanel.add(taskStatusField);


        addTaskPanel.add(new JLabel("First Name:"));
        addTaskPanel.add(firstNameField);

        JButton saveButton = createButton("Save", e -> {
            try {
                saveTask(taskTitleField, taskDescField, taskStatusField, firstNameField);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton cancelButton = createButton("Cancel", e -> showMainView());

        addTaskPanel.add(saveButton);
        addTaskPanel.add(cancelButton);

        addTaskPanel.revalidate();
        addTaskPanel.repaint();
    }

    private void updateTaskForm() {
        JTextField oldTaskTitleField = new JTextField(20);
        JTextField taskTitleField = new JTextField(20);
        JTextField taskDescField = new JTextField(20);
        JTextField taskStatusField = new JTextField(20);

        updateTaskPanel.add(new JLabel("Update a Task"));
        updateTaskPanel.add(new JLabel());

        updateTaskPanel.add(new JLabel("Enter Old Task Title:"));
        updateTaskPanel.add(oldTaskTitleField);

        updateTaskPanel.add(new JLabel("Enter new Task Title:"));
        updateTaskPanel.add(taskTitleField);

        updateTaskPanel.add(new JLabel("Enter new Task Description:"));
        updateTaskPanel.add(taskDescField);

        updateTaskPanel.add(new JLabel("Task Status Done (True/False):"));
        updateTaskPanel.add(taskStatusField);

        JButton saveButton = createButton("Save", e -> {
            try {
                saveUpdatedTask(oldTaskTitleField, taskTitleField, taskDescField, taskStatusField);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton cancelButton = createButton("Cancel", e -> showMainView());

        updateTaskPanel.add(saveButton);
        updateTaskPanel.add(cancelButton);

        updateTaskPanel.revalidate();
        updateTaskPanel.repaint();

    }

    private void saveUser(JTextField usernameField) throws SQLException {
        String firstname = usernameField.getText();

        if (firstname.isEmpty()) {
            JOptionPane.showMessageDialog(window, "fritname field is required", "Error", JOptionPane.ERROR_MESSAGE);
        }else {
            taskManager.addUser(firstname);
            JOptionPane.showMessageDialog(window, "User has been added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            showMainView();


        }
    }


    private void saveTask(JTextField titleField, JTextField descField, JTextField statusField,  JTextField firstNameField) throws SQLException {
        String title = titleField.getText();
        String description = descField.getText();
        String status = statusField.getText();

        String firstName = firstNameField.getText();



        if (title.isEmpty() || description.isEmpty()) {
            JOptionPane.showMessageDialog(window, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            taskManager.addTask(title, description, status, firstName);
            JOptionPane.showMessageDialog(window, "Task saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showMainView();
        }
    }

    private void saveUpdatedTask(JTextField oldTaskTitleField,JTextField taskTitleField, JTextField taskDescField,JTextField taskStatusField ) throws SQLException {
        String oldTaskTitle = oldTaskTitleField.getText();
        String title = taskTitleField.getText();
        String taskDesc = taskDescField.getText();
        String taskStatus = taskStatusField.getText();
        if (oldTaskTitle.isEmpty() || title.isEmpty() || taskDesc.isEmpty() || taskStatus.isEmpty()) {
            JOptionPane.showMessageDialog(window, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);

        }else {
            taskManager.updateTask(oldTaskTitle, title, taskDesc, taskStatus);
            JOptionPane.showMessageDialog(window, "Task saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showMainView();
        }
    }

    private void saveDeletedTask(JTextField titleField) throws SQLException {
        String title = titleField.getText();
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(window, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);

        }else {
            taskManager.deleteTask(title);
            JOptionPane.showMessageDialog(window, "Task deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            showMainView();
        }
    }

    public void showMainView() {
        cardLayout.show(containerPanel, "MainView");
    }

    public void mainButton() {
    }
}