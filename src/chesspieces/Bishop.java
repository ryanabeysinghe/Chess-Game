package chesspieces;

import chessgame.Board;

public class Bishop extends Piece {

    public Bishop(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x, y, is_white, file_path, board);
    }
    
    @Override
    public boolean canMove(int destX, int destY)
    {
        // Remember: For attacking or just moving, a bishop is allowed to move 
        // as many squares diagonally as it wants without jumping over another 
        // piece. The Bishop cannot attack his own pieces.
        
                // WRITE CODE HERE
        int sx = this.getX();
    	int sy = this.getY();
    	
    	int ly = Math.abs(destY - sy);
    	int lx = Math.abs(destX - sx);
    	
    	String dec = "";
    	
        Piece pPiece = board.getPiece(destX, destY);
        if( pPiece != null) {
            if(pPiece.isWhite() && this.isWhite()) {
                return false;
            } else if(pPiece.isBlack() && this.isBlack()) {
                return false;
            }
        }
        
        if (lx != ly) {
            return false;
        }

        int l = lx;

        if (destY > sy && destX < sx) {
            dec = "sw";
        }
        if (destY < sy && destX < sx) {
            dec = "nw";
        }
        if (destY > sy && destX > sx) {
            dec = "se";
        }
        if (destY < sy && destX > sx) {
            dec = "ne";
        }

        if (dec.contains("se")) {
            for (int i = 1; i < l; i++) {
                Piece nPiece = board.getPiece(sx + i, sy + i);
                if (nPiece != null) {
                    return false;
                }
            }
        }
        if (dec.contains("ne")) {
            for (int i = 1; i < l; i++) {
                Piece nPiece = board.getPiece(sx + i, sy - i);
                if (nPiece != null) {
                    return false;
                }
            }
        }
        if (dec.contains("sw")) {
            for (int i = 1; i < l; i++) {
                Piece nPiece = board.getPiece(sx - i, sy + i);
                if (nPiece != null) {
                    return false;
                }
            }
        }
        if (dec.contains("nw")) {
            for (int i = 1; i < l; i++) {
                Piece nPiece = board.getPiece(sx - i, sy - i);
                if (nPiece != null) {
                    return false;
                }
            }
        }

        
        return true;
    }
}
