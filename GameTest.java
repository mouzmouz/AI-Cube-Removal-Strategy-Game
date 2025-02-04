import java.util.Scanner;

public class GameTest {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int play=1;
		int[] squares= new int[2];
		int[] move = new int[2];
		int[] userMove = new int[2];
		Move systemMove = new Move(move);
		int turn=1;
		squares=Game.welcome(input);
		while(play==1) {
			System.out.println("Quick info:");
			System.out.println("1:pick up one white square");
			System.out.println("2:pick up one black square");
			System.out.println("3:pick up two whites and one black");
			System.out.println("4:pick up one white and two blacks");
			Game.printGameStatus(squares[0], squares[1]);
			turn=1;
			Game.minimax(squares[0], squares[1], 1, 9, "", turn, systemMove);
			System.out.println("The system made it's move!");
			System.out.println("Took "+systemMove.getMove()[0]+" white and "+systemMove.getMove()[1]+" black blocks");
			squares[0]-=systemMove.getMove()[0];
			squares[1]-=systemMove.getMove()[1];
			Game.printGameStatus(squares[0], squares[1]);
			play=Game.gameOver(squares[0], squares[1], turn, input);
			if(play==0) {
				break;
			}
			System.out.println("Now it's your turn!");
			turn=0;
			userMove=Game.userMove(squares[0], squares[1],input);
			squares[0]-=userMove[0];
			squares[1]-=userMove[1];
			play=Game.gameOver(squares[0], squares[1], turn, input);
		}
		input.close();
	}

}
