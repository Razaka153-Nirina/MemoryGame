import java.awt.*;
import javax.swing.*;

public class MemoryGame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                WelcomeDialog welcome = new WelcomeDialog();
                welcome.setVisible(true);
            }
        });
    }
}