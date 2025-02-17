import javax.swing.*;
import java.awt.*;

public class MainWindow {
    private JFrame window;

    public MainWindow() {
        window = new JFrame();
        window.setTitle("Task Manager");
        window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        window.setSize(800, 600);
        window.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setBackground(Color.LIGHT_GRAY);
        window.add(panel, BorderLayout.CENTER);

        Button button = new Button("Ajouter une tache");
        button.setBackground(Color.WHITE);
        panel.add(button);

        Button button1 = new Button("Supprimer une tache");
        button.setBackground(Color.WHITE);
        panel.add(button1);

        Button button3 = new Button("Modifier une tache");
        button.setBackground(Color.WHITE);
        panel.add(button3);

        Button button4 = new Button("Ajouter un utilisateur");
        button.setBackground(Color.WHITE);
        panel.add(button4);

        Button button5 = new Button("Supprimer un utilisateur");
        button.setBackground(Color.WHITE);
        panel.add(button5);
    }

    public void show() {
        window.setVisible(true);
    }

}
