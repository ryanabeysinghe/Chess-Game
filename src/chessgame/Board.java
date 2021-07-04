package chessgame;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;

import chesspieces.Knight;
import chesspieces.Pawn;
import chesspieces.Rook;
import chesspieces.Queen;
import chesspieces.Bishop;
import chesspieces.King;
import chesspieces.Piece;

@SuppressWarnings("serial")
public class Board extends JComponent {
        
    public int turnCounter = 0;
    private static Image NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

    private final int squareWidth = 65;
    public ArrayList<Piece> whitePieces;
    public ArrayList<Piece> blackPieces;
    

    public ArrayList<DrawingShape> staticShapes;
    public ArrayList<DrawingShape> pieceGraphics;

    public Piece activePiece;

    private final int rowsOfBoard = 8;
    private final int colsOfBoard = 8;
    private Integer[][] gridChessBoard;
    private String boardFilePath = "images" + File.separator + "board.png";
    private String activeSquareFilePath = "images" + File.separator + "active_square.png";

    public void initGrid() {
        for (int i = 0; i < rowsOfBoard; i++) {
            for (int j = 0; j < colsOfBoard; j++) {
                gridChessBoard[i][j] = 0;
            }
        }

        //Image white_piece = loadImage("images/white_pieces/" + piece_name + ".png");
        //Image black_piece = loadImage("images/black_pieces/" + piece_name + ".png");  

        whitePieces.add(new Rook(0, 0, true, "rookPiece.png", this));
        whitePieces.add(new Rook(7, 0, true, "rookPiece.png", this));
        whitePieces.add(new Knight(1, 0, true, "knightPiece.png", this));
        whitePieces.add(new Knight(6, 0, true, "knightPiece.png", this));
        whitePieces.add(new Bishop(2, 0, true, "bishopPiece.png", this));
        whitePieces.add(new Bishop(5, 0, true, "bishopPiece.png", this));
        whitePieces.add(new King(3, 0, true, "kingPiece.png", this));
        whitePieces.add(new Queen(4, 0, true, "queenPiece.png", this));
        whitePieces.add(new Pawn(0, 1, true, "pawnPiece.png", this));
        whitePieces.add(new Pawn(1, 1, true, "pawnPiece.png", this));
        whitePieces.add(new Pawn(2, 1, true, "pawnPiece.png", this));
        whitePieces.add(new Pawn(3, 1, true, "pawnPiece.png", this));
        whitePieces.add(new Pawn(4, 1, true, "pawnPiece.png", this));
        whitePieces.add(new Pawn(5, 1, true, "pawnPiece.png", this));
        whitePieces.add(new Pawn(6, 1, true, "pawnPiece.png", this));
        //whitePieces.add(new Pawn(7, 1, true, "pawnPiece.png", this));

        blackPieces.add(new Rook(0, 7, false, "rookPiece.png", this));
        blackPieces.add(new Rook(7, 7, false, "rookPiece.png", this));
        blackPieces.add(new Knight(1, 7, false, "knightPiece.jpg", this));
        blackPieces.add(new Knight(6, 7, false, "knightPiece.jpg", this));
        blackPieces.add(new Bishop(2, 7, false, "bishopPiece.png", this));
        blackPieces.add(new Bishop(5, 7, false, "bishopPiece.png", this));
        blackPieces.add(new King(3, 7, false, "kingPiece.jpg", this));
        blackPieces.add(new Queen(4, 7, false, "queenPiece.png", this));
        blackPieces.add(new Pawn(0, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(1, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(2, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(3, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(4, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(5, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(6, 6, false, "pawnPiece.png", this));
        blackPieces.add(new Pawn(7, 6, false, "pawnPiece.png", this));
    }

    public Board() {

        gridChessBoard = new Integer[rowsOfBoard][colsOfBoard];
        staticShapes = new ArrayList();
        pieceGraphics = new ArrayList();
        whitePieces = new ArrayList();
        blackPieces = new ArrayList();

        initGrid();

        this.setBackground(new Color(37, 13, 84));
        this.setPreferredSize(new Dimension(520, 520));
        this.setMinimumSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(1000, 1000));

        this.addMouseListener(mouseAdapter);
        this.addComponentListener(componentAdapter);
        this.addKeyListener(keyAdapter);


        
        this.setVisible(true);
        this.requestFocus();
        drawBoard();
    }


    private void drawBoard() {
        pieceGraphics.clear();
        staticShapes.clear();
        //initGrid();
        
        Image board = loadImage(boardFilePath);
        staticShapes.add(new DrawingImage(board, new Rectangle2D.Double(0, 0, board.getWidth(null), board.getHeight(null))));
        if (activePiece != null) {
            Image active_square = loadImage("images" + File.separator + "active_square.png");
            staticShapes.add(new DrawingImage(active_square, new Rectangle2D.Double(squareWidth * activePiece.getX(), squareWidth * activePiece.getY(), active_square.getWidth(null), active_square.getHeight(null))));
        }
        for (int i = 0; i < whitePieces.size(); i++) {
            int COL = whitePieces.get(i).getX();
            int ROW = whitePieces.get(i).getY();
            Image piece = loadImage("images" + File.separator + "white_pieces" + File.separator + whitePieces.get(i).getFilePath());  
            pieceGraphics.add(new DrawingImage(piece, new Rectangle2D.Double(squareWidth * COL, squareWidth * ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        for (int i = 0; i < blackPieces.size(); i++) {
            int COL = blackPieces.get(i).getX();
            int ROW = blackPieces.get(i).getY();
            Image piece = loadImage("images" + File.separator + "black_pieces" + File.separator + blackPieces.get(i).getFilePath());  
            pieceGraphics.add(new DrawingImage(piece, new Rectangle2D.Double(squareWidth * COL, squareWidth * ROW, piece.getWidth(null), piece.getHeight(null))));
        }
        
        this.repaint();
    }

    
    public Piece getPiece(int x, int y) {
        for (Piece p : whitePieces) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        for (Piece p : blackPieces) {
            if (p.getX() == x && p.getY() == y) {
                return p;
            }
        }
        
        return null;
    }

    private MouseAdapter mouseAdapter = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {

                
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int d_X = e.getX();
            int d_Y = e.getY();  
            int Clicked_Row = d_Y / squareWidth;
            int Clicked_Column = d_X / squareWidth;
            boolean is_whites_turn = true;
            if (turnCounter % 2 == 1) {
                is_whites_turn = false;
            }
            
            Piece clicked_piece = getPiece(Clicked_Column, Clicked_Row);
            
            if (activePiece == null && clicked_piece != null && 
                    ((is_whites_turn && clicked_piece.isWhite()) || (!is_whites_turn && clicked_piece.isBlack()))) {
                activePiece = clicked_piece;
            }
            else if (activePiece != null && activePiece.getX() == Clicked_Column && activePiece.getY() == Clicked_Row) {
                activePiece = null;
            }
            else if (activePiece != null && activePiece.canMove(Clicked_Column, Clicked_Row) 
                    && ((is_whites_turn && activePiece.isWhite()) || (!is_whites_turn && activePiece.isBlack()))) {
                // if piece is there, remove it so we can be there
                if (clicked_piece != null) {
                    if (clicked_piece.isWhite()) {
                        whitePieces.remove(clicked_piece);
                    } else {
                        blackPieces.remove(clicked_piece);
                    }
                }
                // do move
                activePiece.setX(Clicked_Column);
                activePiece.setY(Clicked_Row);
                
                // if piece is a pawn set has_moved to true
                if (activePiece.getClass().equals(Pawn.class)) {
                    Pawn castedPawn = (Pawn)(activePiece);
                    castedPawn.setHasMoved(true);
                }
                
                
                activePiece = null;
                turnCounter++;
            }
            
            drawBoard();
        }

        @Override
        public void mouseDragged(MouseEvent e) {		
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
        }	

        
    };

    private void adjustShapePositions(double dx, double dy) {
        staticShapes.get(0).adjustPosition(dx, dy);
        this.repaint();
    } 
        
        
      
    private Image loadImage(String imageFile) {
        try {
            return ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            return NULL_IMAGE;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        drawBackground(g2);
        drawShapes(g2);
    }

    private void drawBackground(Graphics2D g2) {
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
       

    private void drawShapes(Graphics2D g2) {
        for (DrawingShape shape : staticShapes) {
            shape.draw(g2);
        }	
        for (DrawingShape shape : pieceGraphics) {
            shape.draw(g2);
        }
    }

    private ComponentAdapter componentAdapter = new ComponentAdapter() {

        @Override
        public void componentHidden(ComponentEvent e) {

        }

        @Override
        public void componentMoved(ComponentEvent e) {

        }

        @Override
        public void componentResized(ComponentEvent e) {

        }

        @Override
        public void componentShown(ComponentEvent e) {

        }	
    };

    private KeyAdapter keyAdapter = new KeyAdapter() {

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }	
    };

}

interface DrawingShape {
    boolean contains(Graphics2D g2, double x, double y);
    void adjustPosition(double dx, double dy);
    void draw(Graphics2D g2);
}


class DrawingImage implements DrawingShape {

    public Image image;
    public Rectangle2D rect;

    public DrawingImage(Image image, Rectangle2D rect) {
            this.image = image;
            this.rect = rect;
    }

    @Override
    public boolean contains(Graphics2D g2, double x, double y) {
            return rect.contains(x, y);
    }

    @Override
    public void adjustPosition(double dx, double dy) {
            rect.setRect(rect.getX() + dx, rect.getY() + dy, rect.getWidth(), rect.getHeight());	
    }

    @Override
    public void draw(Graphics2D g2) {
            Rectangle2D bounds = rect.getBounds2D();
            g2.drawImage(image, (int)bounds.getMinX(), (int)bounds.getMinY(), (int)bounds.getMaxX(), 
                    (int)bounds.getMaxY(), 0, 0, image.getWidth(null), image.getHeight(null), null);
    }	
}
