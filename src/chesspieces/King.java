package chesspieces;

import chessgame.Board;

public class King extends Piece {

    public King(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destX, int destY) {
        // Remember: a king can move one square up, right, left, or down, or
        // diagonally, but he can never put himself in danger of an oposing 
        // piece attacking him on the next turn. He cannot attack his own pieces.

        // WRITE CODE HERE
        Piece destPiece = board.getPiece(destX, destY);

        if (destPiece != null) {
            if ((destPiece.isWhite() && this.isWhite()) || (!destPiece.isWhite() && !this.isWhite())) {
                return false;
            }
        }

        if (((Math.abs(this.getX() - destX) != Math.abs((this.getY() - destY))) && ((this.getX() != destX) && (this.getY() != destY)))) {
            return false;
        }

        if ((Math.abs(this.getX() - destX) > 1) || (Math.abs(this.getY() - destY) > 1)) {
            return false;
        }

        return !inDanger(destX, destY);
    }
}
