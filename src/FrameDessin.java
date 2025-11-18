import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.border.Border;

public class FrameDessin extends JFrame implements MouseListener {

    private Border compound;
    private Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    private Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    
    private boolean[] revealed = new boolean[20];
    private GameDraw draw;
    private JPanel scorePanel = null;
    private JLabel llnbc, llnbp, lltemps;
    
    private Image[] images = new Image[10];
    private Image backimage = new ImageIcon("images/fond.png").getImage();
    private int[] order = { 10, 9, 1, 2, 4, 2, 7, 6, 5, 10, 3, 3, 8, 9, 1, 8, 6, 5, 4, 7 };
    private final boolean[] trouve = new boolean[20];
    
    private int numclic = 0, nbpairs = 0;
    private int clic1 = -1, clic2 = -1;
    
    // Chronomètre
    private boolean withTimer;
    private Timer gameTimer;
    private int secondsElapsed = 0;

    public FrameDessin(String titre, boolean withTimer) {
        super(titre);
        this.withTimer = withTimer;
        
        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
        
        loadImages();
        
        draw = new GameDraw();
        draw.setPreferredSize(new Dimension(605, 485));
        draw.setBorder(compound);
        draw.addMouseListener(this);
        
        scorePanel = getScorePanel();
        this.getContentPane().add(scorePanel, BorderLayout.SOUTH);
        this.getContentPane().add(draw, BorderLayout.CENTER);
        
        draw.repaint();
        
        // Démarrer le chronomètre si activé
        if (withTimer) {
            startTimer();
        }
        
        this.pack();
        this.setVisible(true);
    }
    
    private void startTimer() {
        gameTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsElapsed++;
                updateTimerDisplay();
            }
        });
        gameTimer.start();
    }
    
    private void updateTimerDisplay() {
        int minutes = secondsElapsed / 60;
        int seconds = secondsElapsed % 60;
        lltemps.setText(String.format("%02d:%02d", minutes, seconds));
    }
    
    private void stopTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }
    
    private void checkGameEnd() {
        if (nbpairs == 10) {
            stopTimer();
            String temps = withTimer ? lltemps.getText() : null;
            
            // Petit délai avant d'afficher le dialogue de fin
            Timer endTimer = new Timer(500, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    EndGameDialog endDialog = new EndGameDialog(FrameDessin.this, numclic, nbpairs, temps);
                    endDialog.setVisible(true);
                }
            });
            endTimer.setRepeats(false);
            endTimer.start();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xe = e.getX() / 120;
        int ye = e.getY() / 120;
        int position = ye * 5 + xe;
        
        if (position >= 20) return;
        
        if (trouve[position] || revealed[position]) {
            return;
        }
        
        if (clic1 == -1) { 
            clic1 = position;
            revealed[clic1] = true;
        }
        else if (clic2 == -1 && position != clic1) {
            clic2 = position;
            revealed[clic2] = true;
            
            numclic++;
            llnbc.setText(String.valueOf(numclic));
            
            if (order[clic1] == order[clic2]) {
                trouve[clic1] = true;
                trouve[clic2] = true;
                revealed[clic1] = true;
                revealed[clic2] = true;
                nbpairs++;
                llnbp.setText(String.valueOf(nbpairs));
                
                clic1 = -1;
                clic2 = -1;
                
                // Vérifier si la partie est terminée
                checkGameEnd();
            } else {
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        revealed[clic1] = false;
                        revealed[clic2] = false;
                        clic1 = -1;
                        clic2 = -1;
                        draw.repaint();
                    }
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
        
        draw.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}

    private JPanel getScorePanel() {
        JPanel result = new JPanel();
        result.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        
        JLabel lnbc = new JLabel(" Nombre de coups : ");
        lnbc.setPreferredSize(new Dimension(230, 35));
        lnbc.setForeground(Color.blue);
        lnbc.setBorder(compound);
        result.add(lnbc, gbc);
        
        llnbc = new JLabel("0", JLabel.CENTER);
        llnbc.setPreferredSize(new Dimension(35, 35));
        llnbc.setBackground(Color.blue);
        llnbc.setForeground(Color.white);
        llnbc.setBorder(compound);
        llnbc.setOpaque(true);
        result.add(llnbc, gbc);
        
        gbc.gridy = 1;
        JLabel lnbp = new JLabel(" Nombre de paires découvertes : ");
        lnbp.setPreferredSize(new Dimension(230, 35));
        lnbp.setForeground(Color.blue);
        lnbp.setBorder(compound);
        result.add(lnbp, gbc);
        
        llnbp = new JLabel("0", JLabel.CENTER);
        llnbp.setPreferredSize(new Dimension(35, 35));
        llnbp.setBackground(Color.blue);
        llnbp.setForeground(Color.white);
        llnbp.setBorder(compound);
        llnbp.setOpaque(true);
        result.add(llnbp, gbc);
        
        // Ajouter le chronomètre si activé
        if (withTimer) {
            gbc.gridy = 2;
            JLabel ltemps = new JLabel(" Temps écoulé : ");
            ltemps.setPreferredSize(new Dimension(230, 35));
            ltemps.setForeground(Color.blue);
            ltemps.setBorder(compound);
            result.add(ltemps, gbc);
            
            lltemps = new JLabel("00:00", JLabel.CENTER);
            lltemps.setPreferredSize(new Dimension(60, 35));
            lltemps.setBackground(Color.blue);
            lltemps.setForeground(Color.white);
            lltemps.setBorder(compound);
            lltemps.setOpaque(true);
            result.add(lltemps, gbc);
        }
        
        return result;
    }

    private void loadImages() {
        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageIO.read(new File("images/im" + (i + 1) + ".png"));
            }
        } catch (IOException ex) {
            System.err.println("Erreur lors du chargement des images: " + ex.getMessage());
        }
    }

    // Classe interne GameDraw
    private class GameDraw extends JPanel {
        
        public GameDraw() {
            try {
                backimage = ImageIO.read(new File("images/fond.png"));
            } catch (IOException ex) {
                System.err.println("Erreur chargement fond: " + ex.getMessage());
            }
        }
        
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            int largeur = 115, hauteur = 115;
            
            for (int ligne = 0; ligne < 4; ligne++) {
                for (int colonne = 0; colonne < 5; colonne++) {
                    int x = colonne * (largeur + 5);
                    int y = ligne * (hauteur + 5);
                    int position = ligne * 5 + colonne;
                    
                    if (revealed[position]) {
                        g.drawImage(images[order[position] - 1], x, y, largeur, hauteur, this);
                    } else {
                        g.drawImage(backimage, x, y, largeur, hauteur, this);
                    }
                }
            }
        }
    }
}