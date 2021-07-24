package chesspieces;

import chessgame.Board;

public class Queen extends Piece {

    public Queen(int x, int y, boolean is_white, String file_path, Board board)
    {
        super(x,y,is_white,file_path, board);
    }
    
    @Override
    public boolean canMove(int destX, int destY) {
        // Remember: A Queen can move as many squares as she wants forward, 
        // backward, sideways, or diagonally, without jumping over any pieces.
        // She cannot attack her own pieces.

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

        return true;
    }
}
