import java.awt.*;
import javax.swing.*;

public class EndGameDialog extends JDialog {
    
    public EndGameDialog(JFrame parent, int numCoups, int nbPaires, String temps) {
        super(parent, "Félicitations !", true);
        setLayout(new BorderLayout(10, 10));
        setSize(350, 280);
        setLocationRelativeTo(parent);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(245, 245, 245));
        
        // Titre
        JLabel titleLabel = new JLabel("🏆 PARTIE TERMINÉE ! 🏆");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(255, 152, 0));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Résultats
        JLabel resultLabel = new JLabel("Vos résultats :");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(resultLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        
        JLabel coupsLabel = new JLabel("Nombre de coups : " + numCoups);
        coupsLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        coupsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel pairesLabel = new JLabel("Paires découvertes : " + nbPaires + "/10");
        pairesLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        pairesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(coupsLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        mainPanel.add(pairesLabel);
        
        if (temps != null && !temps.equals("--:--")) {
            mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            JLabel tempsLabel = new JLabel("Temps écoulé : " + temps);
            tempsLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            tempsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainPanel.add(tempsLabel);
        }
        
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        
        JButton btnNewGame = new JButton("Nouvelle partie");
        btnNewGame.setPreferredSize(new Dimension(140, 35));
        btnNewGame.setBackground(new Color(76, 175, 80));
        btnNewGame.setForeground(Color.WHITE);
        btnNewGame.setFocusPainted(false);
        
        JButton btnQuit = new JButton("Quitter");
        btnQuit.setPreferredSize(new Dimension(140, 35));
        btnQuit.setBackground(new Color(244, 67, 54));
        btnQuit.setForeground(Color.WHITE);
        btnQuit.setFocusPainted(false);
        
        btnNewGame.addActionListener(e -> {
            this.dispose();
            parent.dispose();
            WelcomeDialog welcome = new WelcomeDialog();
            welcome.setVisible(true);
        });
        
        btnQuit.addActionListener(e -> {
            this.dispose();
            parent.dispose();
            System.exit(0);
        });
        
        buttonPanel.add(btnNewGame);
        buttonPanel.add(btnQuit);
        
        mainPanel.add(buttonPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
}