//created to take the best move from minimax()

public class Move {
	
	private int[] move = new int[2];
	
	public Move(int[] move) {
		this.move=move;
	}
	
	
	public int[] getMove() {
		return move;
	}
	
	public void setMove(int[] newMove) {
		this.move=newMove;
	}
}
