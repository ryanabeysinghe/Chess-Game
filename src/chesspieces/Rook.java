package chesspieces;

import chessgame.Board;

public class Rook extends Piece {

    public Rook(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x, y, is_white, file_path, board);
    }
    
    @Override
    public boolean canMove(int destination_x, int destination_y)
    {
        // Remember: A rook can move as many squares as he wants either forward,
        // backward, or sideways without jumping any pieces. He cannot attack
        // his own pieces.
        
                // WRITE CODE HERE
        
        /* If there is a piece at the destination, and it is our own piece,
         * don't let us move there. This will not allow the Rook to overlap 
         * any of the same color pieces on the board. 
         */ 
        
        Piece possiblePiece = board.getPiece(destination_x, destination_y); 
        
        if (possiblePiece != null) {
            if (possiblePiece.isWhite() && this.isWhite()) {
                return false; 
            }
            
            if (possiblePiece.isBlack() && this.isBlack()) {
                return false; 
            }
        }
        
        // If the Rook is trying to move any direction other than a straight line, don't let it move. 
        if (this.getX() != destination_x && this.getY() != destination_y) {
            return false; 
        }
        
        //Find out what direction the Rook is trying to move in. 
        String direction = "";
        if (destination_y > this.getY()) {
            direction = "south";
        }
        
        if (destination_y < this.getY()) {
            direction = "north"; 
        }
        
        if (destination_x > this.getX()) {
            direction = "east"; 
        }
        
        if (destination_x < this.getX()) {
            direction = "west"; 
        }
        
        /* Whatever horizontal and vertical direction the rook is trying to
           move in, make sure that the Rook will not move if there are the 
           same color pieces in the way.   
        */
        
        if(direction.equals("south")) {
            int spacesToMove = Math.abs(destination_y - this.getY()); 
            for (int i = 1; i < spacesToMove; i++) {
                Piece possibleSpaces = board.getPiece(this.getX(), this.getY() + i);
                if (possibleSpaces != null) {
                    return false; 
                }
            }
        }
        
        if(direction.equals("north")) {
            int spacesToMove = Math.abs(destination_y - this.getY()); 
            for (int i = 1; i < spacesToMove; i++) {
                Piece possibleSpaces = board.getPiece(this.getX(), this.getY() - i);
                if (possibleSpaces != null) {
                    return false; 
                }
            }
        }
        
        if(direction.equals("east")) {
            int spacesToMove = Math.abs(destination_x - this.getX()); 
            for (int i = 1; i < spacesToMove; i++) {
                Piece possibleSpaces = board.getPiece(this.getX() + i, this.getY());
                if (possibleSpaces != null) {
                    return false; 
                }
            }
        }
        
        if(direction.equals("west")) {
            int spacesToMove = Math.abs(destination_x - this.getX()); 
            for (int i = 1; i < spacesToMove; i++) {
                Piece possibleSpaces = board.getPiece(this.getX() - i, this.getY());
                if (possibleSpaces != null) {
                    return false; 
                }
            }
        }
        
        return true;
    }
}
