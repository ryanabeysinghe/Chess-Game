package chesspieces;

import chessgame.Board;

public class Pawn extends Piece {

    private boolean has_moved;
    
    public Pawn(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
        has_moved = false;
    }
    
    public void setHasMoved(boolean has_moved)
    {
        this.has_moved = has_moved;
    }
    
    public boolean getHasMoved()
    {
        return has_moved;
    }
    
    @Override
    public boolean canMove(int destX, int destY)
    {
        // Remember: A pawn may only move towards the oponent's side of the board.
        // If the pawn has not moved yet in the game, for its first move it can 
        // move two spaces forward. Otherwise, it may only move one space. 
        // When not attacking it may only move straight ahead.
        // When attacking it may only move space diagonally forward

                // WRITE CODE HERE
        
        boolean hm = this.getHasMoved();
        int sy = this.getY();
        int sx = this.getX();

        int ly = Math.abs(destY - sy);
        int lx = Math.abs(destX - sx);

        char dec = 0;

        Piece pPiece = board.getPiece(destX, destY);

        if (pPiece != null) {
            if (pPiece.isWhite() && this.isWhite()) {
                return false;
            } else if (pPiece.isBlack() && this.isBlack()) {
                return false;
            }
        }

        if (lx > 1 || ly > 2) {
            return false;
        }
        if (lx == 1 && ly != 1) {
            return false;
        }

        if (sy > destY) {
            dec = 'n';
        }
        if (sy < destY) {
            dec = 's';
        }
        if (has_moved && ly > 1) {
            return false;
        }

        if (dec == 'n') {
            if (this.isWhite()) {
                return false;
            }
            if (pPiece == null && lx == 1) {
                return false;
            }
            if (lx != 1) {
                Piece nPiece = board.getPiece(sx, sy - 1);
                Piece nPiece1 = board.getPiece(sx, sy - 2);
                if (nPiece != null) {
                    return false;
                }
                if (ly == 2 && nPiece1 != null) {
                    return false;
                }
            }
        }
        if (dec == 's') {
            if (this.isBlack()) {
                return false;
            }
            if (pPiece == null && lx == 1) {
                return false;
            }
            if (lx != 1) {
                Piece nPiece = board.getPiece(sx, sy + 1);
                Piece nPiece1 = board.getPiece(sx, sy + 2);
                if (nPiece != null) {
                    return false;
                }
                if (ly == 2 && nPiece1 != null) {
                    return false;
                }
            }
        }

        return true;
    }
}
