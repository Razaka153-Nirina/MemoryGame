import java.awt.*;
import javax.swing.*;

public class WelcomeDialog extends JDialog {
    
    public WelcomeDialog() {
        super((Frame)null, "Bienvenue au Memory Game", true);
        setLayout(new BorderLayout(10, 10));
        setSize(400, 250);
        setLocationRelativeTo(null);
        
        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Titre
        JLabel titleLabel = new JLabel(" MEMORY GAME ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel subtitleLabel = new JLabel("Trouvez toutes les paires !");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(subtitleLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Question
        JLabel questionLabel = new JLabel("Choisissez votre mode de jeu :");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(questionLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        
        JButton btnWithoutTimer = new JButton("Sans chronomètre");
        btnWithoutTimer.setPreferredSize(new Dimension(150, 40));
        btnWithoutTimer.setBackground(new Color(76, 175, 80));
        btnWithoutTimer.setForeground(Color.WHITE);
        btnWithoutTimer.setFocusPainted(false);
        btnWithoutTimer.setFont(new Font("Arial", Font.BOLD, 12));
        
        JButton btnWithTimer = new JButton("Avec chronomètre");
        btnWithTimer.setPreferredSize(new Dimension(150, 40));
        btnWithTimer.setBackground(new Color(33, 150, 243));
        btnWithTimer.setForeground(Color.WHITE);
        btnWithTimer.setFocusPainted(false);
        btnWithTimer.setFont(new Font("Arial", Font.BOLD, 12));
        
        btnWithoutTimer.addActionListener(e -> startGame(false));
        btnWithTimer.addActionListener(e -> startGame(true));
        
        buttonPanel.add(btnWithoutTimer);
        buttonPanel.add(btnWithTimer);
        
        mainPanel.add(buttonPanel);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    private void startGame(boolean withTimer) {
        this.dispose();
        FrameDessin f = new FrameDessin("Memory Game", withTimer);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}