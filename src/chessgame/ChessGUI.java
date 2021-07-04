package chessgame;

/**
 * @author Ryan Abeysinghe
 * 
 * Java GUI to represent the Chess Game
 */
public class ChessGUI {
    public BoardFrame boardframe;
    public static void main(String[] args) {
        ChessGUI gui = new ChessGUI();
        gui.boardframe = new BoardFrame();
        gui.boardframe.setVisible(true); 
    }
}
