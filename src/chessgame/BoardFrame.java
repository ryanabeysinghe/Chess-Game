package chessgame;

import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JFrame;

public class BoardFrame extends JFrame {
    Component component;
    
    public BoardFrame() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setTitle("Chess Game");
        this.setResizable(false);
        component = new Board();
        this.add(component, BorderLayout.CENTER);
        
        this.setLocation(500, 200);
        this.pack();
        this.setVisible(true);
    }
}
