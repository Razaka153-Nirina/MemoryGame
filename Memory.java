
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Memory extends JFrame {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrameDessin f = new FrameDessin("Memory");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.pack();
                f.setVisible(true);
            }
        });
    }

    private Image im1,im2,im3,im4,im5,im6,im7,im8,im9,im10;
    private int i;
    private FrameDessin panneau;
    public Memory()
    {
        i=0;
        panneau = new FrameDessin("Memory");
        panneau.addMouseListener(panneau);
        try{
            im1 = ImageIO.read(new File("im1.png"));
            im2 = ImageIO.read(new File("im2.png"));
            im3 = ImageIO.read(new File("im3.png"));
            im4 = ImageIO.read(new File("im4.png"));
            im5 = ImageIO.read(new File("im5.png"));
            im6 = ImageIO.read(new File("im6.png"));
            im7 = ImageIO.read(new File("im7.png"));
            im8 = ImageIO.read(new File("im8.png"));
            im9 = ImageIO.read(new File("im9.png"));
            im10 = ImageIO.read(new File("im10.png"));
        } catch (IOException ex) {
        }
    }


}

class FrameDessin extends JFrame implements MouseListener {
    
    Border compound;
    Border redline = BorderFactory.createLineBorder(Color.red);
    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    Border loweredbevel = BorderFactory.createLoweredBevelBorder();
    private Image im;

    private boolean[] revealed = new boolean[20]; // Suivi des cartes retournées

    private GameDraw draw;
    private JPanel scorePanel=null;
    private JLabel llnbc,llnbp;
    
    private Image[] images = new Image[10]; 
    private Image backimage = new ImageIcon("fond.png").getImage(); 
    private int[] order = { 10, 9, 1, 2, 4, 2, 7, 6, 5, 10, 3, 3, 8, 9, 1, 8, 6, 5, 4, 7 };
    private final boolean[] trouve = { false, false, false, false, false, false, false, false, false, false, false, false,
            false, false, false, false, false, false, false, false };

    private int numclic = 0, nbpairs = 0, nbcoups=0;
    private int  clic1, clic2, clic3;
    public FrameDessin(String titre) {
        super(titre);
        
        compound = BorderFactory.createCompoundBorder(raisedbevel, loweredbevel);
        
        //load images
        loadImages();
        
        //create panels
        draw = new GameDraw();
        draw.setPreferredSize(new Dimension(605, 485));
        draw.setBorder(compound);
        draw.addMouseListener(this);
        
        scorePanel = getScorePanel();
        this.getContentPane().add(scorePanel,BorderLayout.SOUTH);
        
        this.getContentPane().add(draw,BorderLayout.CENTER);
        
        draw.repaint();
        
        this.pack();
        this.setVisible(true);
        

    }
  
    @Override
    public void mouseClicked(MouseEvent e) {
        int xe = e.getX() / 120;
        int ye = e.getY() / 120;
        int position = ye*5+xe;
        
        /*TODO*/
    //    if (!trouve[position] && !revealed[position]) { 
    //         revealed[position] = true; // la carte est revelee
    //         // draw.repaint(); // Redessiner l'interface
    //         System.out.println("test");
    //    } else 
    if (trouve[position] || revealed[position]) {
        return; // Ne rien faire si la carte est déjà trouvée ou révélée
    }
        if (order[clic1] == order[clic1])
         {
            trouve[clic1] = true;
            clic1 = 0;
        }
         else{
            revealed[clic1] = false;
            clic1 = 0;
            draw.repaint();
        }
    
        draw.repaint();
    
    }

       
        
    

    @Override
    public void mousePressed(MouseEvent e) {
     }

    @Override
    public void mouseReleased(MouseEvent e) {
     }

    @Override
    public void mouseEntered(MouseEvent e) {
     }

    @Override
    public void mouseExited(MouseEvent e) {
     }
    
    private JPanel getScorePanel()
    {
        JPanel result = new JPanel();
        result.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridheight=1;
        gbc.gridwidth=1;
        JLabel lnbc = new JLabel(" Nombre de coups : ");
        lnbc.setPreferredSize(new Dimension(230, 35));
        lnbc.setForeground(Color.blue);//new Color(20,20,202));
        lnbc.setBorder(compound);
        result.add(lnbc, gbc);
        
        gbc.gridwidth = 1;
        llnbc = new JLabel("0", JLabel.CENTER);
        llnbc.setPreferredSize(new Dimension(35, 35));
        llnbc.setBackground(Color.blue);
        llnbc.setForeground(Color.white);
        llnbc.setBorder(compound);
        llnbc.setOpaque(true);
        result.add(llnbc, gbc);
        
        gbc.gridwidth = 1;
        gbc.gridy=1;
        JLabel lnbp = new JLabel(" Nombre de paires découvertes : ");
        lnbp.setPreferredSize(new Dimension(230, 35));
        lnbp.setForeground(Color.blue);
        lnbp.setBorder(compound);
        result.add(lnbp, gbc);

        gbc.gridwidth = 1;
        llnbp = new JLabel("0", JLabel.CENTER);
        llnbp.setPreferredSize(new Dimension(35, 35));
        llnbp.setBackground(Color.blue);
        llnbp.setForeground(Color.white);
        llnbp.setBorder(compound);
        llnbp.setOpaque(true);
        result.add(llnbp, gbc);
        
        return result;
    }
    
    
    private class GameDraw extends JPanel
    {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
             int x=5, y=5;
            int largeur=115, hauteur=115;

            for (int ligne = 0; ligne < 4; ligne++) 
            {
                for (int colonne = 0; colonne < 5; colonne++) 
                {
                   x = colonne * (largeur + 5);
                   y = ligne * (hauteur + 5);

                   int position = ligne * 5 + colonne; // Calculate position based on row and column
                   if (revealed[position])
                   {
                          g.drawImage(images[order[position] -1 ], x, y, largeur, hauteur, this);
                     } else {
                          g.drawImage(backimage, x, y, largeur, hauteur, this);

                   }
                   // g.drawImage(backimage,x, y,largeur, hauteur, this);
                }

            }



           // if (im != null) {
            //    g.drawImage(im, 0, 0, this);
           // }
            /*TODO*/
        }
        public GameDraw() {
            try {
                backimage = ImageIO.read(new File("fond.png"));
            } catch (IOException ex) {
             
            }
        }
        
       
    }
    
    //chargement des images
    private void loadImages()
    {
        /* TODO  CHARGER LES IMAGES */
        try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageIO.read(new File("images/im" + (i + 1) + ".png"));
            }
        } catch (IOException ex) {
            
        }
    }
    
    
    
    

    

}//Fin classe FrameDessin

