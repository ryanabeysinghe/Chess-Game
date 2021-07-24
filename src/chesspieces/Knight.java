package chesspieces;

import chessgame.Board;

public class Knight extends Piece {

    public Knight(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destX, int destY)
    {
        // Remember: a knight can move in any L shape and can jump over anyone
        // in order to do so. He cannot attack his own pieces.
        // By an L shape, I mean it can move to a square that is 2 squares away
        // horizontally and 1 square away vertically, or 1 square away horizontally
        // and 2 squares away vertically.
        // some examples:
        //
        //  * *       * * *           *       *
        //  *             *       * * *       *
        //  *                                 * *
            
                // WRITE CODE HERE
        int sx = this.getX();
    	int sy = this.getY();
    	
    	int ly = Math.abs(destY - sy);
    	int lx = Math.abs(destX - sx);
    	
        Piece pPiece = board.getPiece(destX, destY);
        if( pPiece != null) {
        	if(pPiece.isWhite() && this.isWhite()) {
        		return false;
        	}
        	else if(pPiece.isBlack() && this.isBlack()) {
        		return false;
        	}
        }
        
        if(((lx == 2 && ly == 1)||(ly == 2 && lx == 1))) {
        	return true;
        }
                
        return true;
    }
}
