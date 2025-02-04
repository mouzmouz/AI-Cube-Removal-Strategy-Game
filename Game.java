import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	//welcome message and initializing board
	public static int[] welcome(Scanner input) {
		//flag==0 until correct input has been given
		int flag=0;
		int[] start = new int[2];
		System.out.println();
		System.out.println("Welcome to our game!");
		System.out.println("**In order to win the last square that is picked up must be white**");
		System.out.println();
		System.out.println("Please enter the number of white squares...");
		while(flag==0) {
			try {
				String white =input.nextLine();
				start[0]= Integer.parseInt(white);
				flag=1;
				//continue with the next
			}catch(NumberFormatException ex) {
				//catch to not crash the program if user input is not a number
				System.out.println("Please enter a number");
				flag=0;
			}
		}
		flag=0;
		System.out.println();
		System.out.println("Please enter the number of black squares...");
		while(flag==0) {
			try {
				String black =input.nextLine();
				start[1]= Integer.parseInt(black);
				flag=1;
			}catch(NumberFormatException ex) {
				System.out.println("Please enter a number");
				flag=0;
			}
		}
		System.out.println();
		System.out.println("**the game is played by typing one of the following : 1, 2, 3, 4**");
		System.out.println();
		return start;
	}
	
	//when a move that ends the game is played a certain value is returned
	public static int finalConditions(int turn,String cond) {
		int win=0;
		if(turn==0 ) { //MAX
			//MAX played the final move
			if(cond.equals("WIN")) {
				win=Integer.MAX_VALUE;
			}
			else if(cond.equals("TIE")) {
				win=0;
			}
			else if(cond.equals("LOSE")) {
				win=Integer.MIN_VALUE;
			}
			
		}
		else { //MIN
			//MIN played the final move
			if(cond.equals("WIN")) {
				win=Integer.MIN_VALUE;
			}
			else if(cond.equals("TIE")) {
				win=0;
			}
			else if(cond.equals("LOSE")) {
				win=Integer.MAX_VALUE;
			}
			
		}
		return win;
	}

	//create an arraylist containing all possible moves for the current board
	public static ArrayList<int[]> possibleMoves(int whiteSquare,int blackSquare){
		ArrayList<int[]> pMoves = new ArrayList<int[]>();
		if(whiteSquare>=2 && blackSquare>=1) {
			int[] moveWWB = {2,1};
			pMoves.add(moveWWB);
		}
		if(whiteSquare>=1 && blackSquare>=2) {
			int[] moveWBB = {1,2};
			pMoves.add(moveWBB);
		}
		if(whiteSquare>=1) {
			int [] moveW = {1,0};
			pMoves.add(moveW);		}
		if(blackSquare>=1) {
			int [] moveB = {0,1};
			pMoves.add(moveB);
		}
		return pMoves;
	}
	
	//main function with modified minimax algorithm
	public static int minimax(int ws,int bs,int turn,int depth,String cond,int moves,Move move) {
		int[] arr = new int[2];		//array that will have the best move
		if(depth==0 || ws==0) {		//depth represents number of recursions
			//if depth is 0 or if there are no moves left return the values
			return finalConditions(turn,cond);
		}
		if(turn==1) { //MAX
			int bestMove=Integer.MIN_VALUE;
			//create array with all the possible moves for the current turn
			ArrayList<int[]> canmove = new ArrayList<int[]>();
			canmove=possibleMoves(ws,bs);
			//iterate each move
			for(int [] x : canmove) {
				moves++;
				int newWs=ws-x[0];
				int newBs=bs-x[1];
				//check if game ends and updated condition for finalConditions
				if(newWs==0 && newBs==0) {
					cond="WIN";
				}
				else if(newWs==0 && newBs>0) {
					cond="TIE";
				}
				else if((newWs==2 && newBs ==1) || (newWs==1 && newBs==2) || (newWs==1 && newBs==0) ) {
					cond="LOSE";
				}
				//recursion
				int temp=minimax(newWs,newBs,0,depth-1,cond,moves,move);
				//if new move is better than the previous best move
				//temp-moves is used to find if a certain move reaches a win condition faster
				if(temp-moves>bestMove) {
					arr=x; 		//update the array with the new move
				}
				//update bestMove for the next recursion
				bestMove=Math.max(temp-moves, bestMove);
				//start from the beginning and try another move
				moves=0;
				cond="";
			}
			//set the best move in the array
			move.setMove(arr);
			return bestMove;
		}
		else { //MIN
			//same logic here
			int bestMove=Integer.MAX_VALUE;
			ArrayList<int[]> canmove = new ArrayList<int[]>();
			canmove=possibleMoves(ws,bs);
			for(int [] x : canmove) {
				moves++;
				int newWs=ws-x[0];
				int newBs=bs-x[1];
				if(newWs==0 && newBs==0) {
					cond="WIN";
				}
				else if(newWs==0 && newBs>0) {
					cond="TIE";
				}
				else if((newWs==2 && newBs ==1) || (newWs==1 && newBs==2) || (newWs==1 && newBs==0) ) {
					cond="LOSE";
				}
				int temp=minimax(newWs,newBs,1,depth-1,cond,moves,move);
				bestMove=Math.min(temp+moves, bestMove);
				moves=0;
				cond="";
			}
			return bestMove;
		}
	}
	
	//userMove lets the user play a legal move
	public static int[] userMove(int ws, int bs,Scanner input) {
		//turn==0 until correct input has been given
		int turn=0;
		int[] play=new int[2];
		while(turn==0) {
			try {
				//user plays by typing 1, 2, 3 or 4
				String player=input.nextLine();
				int move = Integer.parseInt(player);
				switch(move) {
				//take 1 white and 0 black squares
				case 1:
					if(ws>=1) {
						play[0]=1;
						play[1]=0;
						turn=1;
						break;
					}
					else {
						System.out.println("Not a valid move");
						break;
					}
				//take 0 white and 1 black squares
				case 2:
					if(bs>=1) {
						play[0]=0;
						play[1]=1;
						turn=1;
						break;
					}
					else {
						System.out.println("Not a valid move");
						break;
					}
				//take 2 white and 1 black squares
				case 3:
					if(ws>=2 && bs>=1) {
						play[0]=2;
						play[1]=1;
						turn=1;
						break;
					}
					else {
						System.out.println("Not a valid move");
						break;
					}
				//take 1 white and 2 black squares
				case 4:
					if(ws>=1 && bs>=2) {
						play[0]=1;
						play[1]=2;
						turn=1;
						break;
					}
					else {
						System.out.println("Not a valid move");
						break;
					}
				default:
					System.out.println("Not a valid move, try again");
					
				}
			//catch to not crash the program if user input is not a number
			}catch(NumberFormatException ex) {
				System.out.println("Please enter a number");
			}
		}
		return play;
	}
	
	//gameOver checks who won
	public static int gameOver(int ws,int bs,int turn,Scanner input) {
		if(ws==0 && bs>0) {
			System.out.println("It's a tie");
			return 0;
		}
		else if(ws==0 && bs==0 && turn==1) {
			System.out.println("System won");
			return 0;
		}
		else if(ws==0 && bs==0 && turn==0) {
			System.out.println("You won");
			return 0;
		}
		else {
			return 1;
		}
	}
	
	//print number of squares left
	public static void printGameStatus(int ws,int bs) {
		if(ws>=10 && bs >=10) {
			System.out.println("___________          ___________");
			System.out.println("|         |          |         |");
			System.out.println("|  Whites |          |  Blacks |");
			System.out.println("|         |          |         |");
			System.out.println("|    "+ws+"   |          |    "+bs+"   |");
			System.out.println("|_________|          |_________|");
		}
		else if(ws>=10 && bs<10) {
			System.out.println("___________          ___________");
			System.out.println("|         |          |         |");
			System.out.println("|  Whites |          |  Blacks |");
			System.out.println("|         |          |         |");
			System.out.println("|    "+ws+"   |          |    "+bs+"    |");
			System.out.println("|_________|          |_________|");
		}
		else if(ws<10 && bs>=10) {
			System.out.println("___________          ___________");
			System.out.println("|         |          |         |");
			System.out.println("|  Whites |          |  Blacks |");
			System.out.println("|         |          |         |");
			System.out.println("|    "+ws+"    |          |    "+bs+"   |");
			System.out.println("|_________|          |_________|");
		}
		else {
			System.out.println("___________          ___________");
			System.out.println("|         |          |         |");
			System.out.println("|  Whites |          |  Blacks |");
			System.out.println("|         |          |         |");
			System.out.println("|    "+ws+"    |          |    "+bs+"    |");
			System.out.println("|_________|          |_________|");
		}
	}
}
