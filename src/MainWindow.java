import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainWindow {
    private static volatile MainWindow Instance;
    private JFrame window;
    private CardLayout cardLayout; // Layout manager to switch between panels
    private JPanel mainPanel; // Panel for the main view
    private JPanel addTaskPanel; // Panel for the "Add Task" view
    private JPanel containerPanel;

    // Singleton instance getter
    public static MainWindow getInstance() {
        if (Instance == null) {
            synchronized (MainWindow.class) {
                if (Instance == null) { // Double-check locking
                    Instance = new MainWindow();
                }
            }
        }
        return Instance;
    }

    // Private constructor for singleton
    private MainWindow() {
        window = new JFrame();
        window.setTitle("Task Manager");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);

        // Initialize CardLayout and main container panel
        cardLayout = new CardLayout();
        containerPanel = new JPanel(cardLayout); // Initialize the container panel
        window.add(containerPanel, BorderLayout.CENTER);

        // Create the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        mainPanel.setBackground(Color.LIGHT_GRAY);
        containerPanel.add(mainPanel, "MainView");

        // Create the "Add Task" panel
        addTaskPanel = new JPanel();
        addTaskPanel.setLayout(new GridLayout(5, 2, 10, 10));
        addTaskPanel.setBackground(Color.WHITE);
        containerPanel.add(addTaskPanel, "AddTaskView");

        // Show the main panel by default
        cardLayout.show(containerPanel, "MainView");
    }

    // Method to create and add buttons to the main panel
    public void mainButton() {
        JButton addTaskButton = new JButton("Ajouter une tache");
        addTaskButton.setBackground(Color.WHITE);
        addTaskButton.setToolTipText("Ajouter une tache");
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddTaskView(); // Switch to the "Add Task" view
            }
        });
        mainPanel.add(addTaskButton);

        JButton button1 = new JButton("Supprimer une tache");
        button1.setBackground(Color.WHITE);
        button1.setToolTipText("Supprimer une tache");
        mainPanel.add(button1);

        JButton button3 = new JButton("Modifier une tache");
        button3.setBackground(Color.WHITE);
        button3.setToolTipText("Modifier une tache");
        mainPanel.add(button3);

        JButton button4 = new JButton("Ajouter un utilisateur");
        button4.setBackground(Color.WHITE);
        button4.setToolTipText("Ajouter un utilisateur");
        mainPanel.add(button4);

        JButton button5 = new JButton("Supprimer un utilisateur");
        button5.setBackground(Color.WHITE);
        button5.setToolTipText("Supprimer un utilisateur");
        mainPanel.add(button5);
    }

    // Method to show the window
    public void show() {
        window.setVisible(true);
    }

    // Method to show the "Add Task" view
    public void showAddTaskView() {
        // Clear the "Add Task" panel before adding components
        addTaskPanel.removeAll();

        // Add components to the "Add Task" panel
        JLabel titleLabel = new JLabel("Create a New Task");
        titleLabel.setFont(new Font("Sans-serif", Font.BOLD, 18));
        addTaskPanel.add(titleLabel);
        addTaskPanel.add(new JLabel()); // Empty cell for layout

        JLabel taskTitleLabel = new JLabel("Task Title:");
        JTextField taskTitleField = new JTextField(20);
        addTaskPanel.add(taskTitleLabel);
        addTaskPanel.add(taskTitleField);

        JLabel taskDescLabel = new JLabel("Task Description:");
        JTextField taskDescField = new JTextField(20);
        addTaskPanel.add(taskDescLabel);
        addTaskPanel.add(taskDescField);

        JLabel dueDateLabel = new JLabel("Due Date (YYYY-MM-DD):");
        JTextField dueDateField = new JTextField(20);
        addTaskPanel.add(dueDateLabel);
        addTaskPanel.add(dueDateField);

        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get user input
                String title = taskTitleField.getText();
                String description = taskDescField.getText();
                String dueDate = dueDateField.getText();

                // Validate input
                if (title.isEmpty() || description.isEmpty() || dueDate.isEmpty()) {
                    JOptionPane.showMessageDialog(window, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Save the task (you can add your logic here)
                    JOptionPane.showMessageDialog(window, "Task saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    showMainView(); // Switch back to the main view
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainView(); // Switch back to the main view
            }
        });

        addTaskPanel.add(saveButton);
        addTaskPanel.add(cancelButton);

        // Refresh the panel
        addTaskPanel.revalidate();
        addTaskPanel.repaint();

        // Switch to the "Add Task" view
        cardLayout.show(containerPanel, "AddTaskView");
    }

    // Method to show the main view
    public void showMainView() {
        // Switch back to the main view
        cardLayout.show(containerPanel, "MainView");
    }
}
