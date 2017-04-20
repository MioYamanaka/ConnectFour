package mio.yamanaka;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		welcomeMessage();
		Scanner input = new Scanner(System.in);
		boolean playAgain = true;
		while (playAgain) {
			System.out.println("How many players? (1 or 2)");
			int players = validPlayers(input);
			Board board = new Board();
			board.display();
			int rounds = 0;
			boolean won = false;
			// two-player version
			if (players == 2) {
				while (!won) {
					rounds++;
					System.out.println("Round " + rounds);
					// player 1
					turn(board, CellState.P1, input);
					if (rounds >= 4) {
						if (win(board, CellState.P1)) {
							System.out.println("Player 1 Wins!\n");
							won = true;
							continue;
						}
					}
					// player 2
					turn(board, CellState.P2, input);
					if (rounds >= 4) {
						if (win(board, CellState.P2)) {
							System.out.println("Player 2 Wins!\n");
							won = true;
							continue;
						}
					}
				}
			} else {
				// one-player version
				// implement AI
				
				while (!won) {
					rounds++;
					System.out.println("Round " + rounds + "\n");
					// player 1
					turn(board, CellState.P1, input);
					if (rounds >= 4) {
						if (win(board, CellState.P1)) {
							System.out.println("Player 1 Wins!\n");
							won = true;
							continue;
						}
					} // AI
					
				}
			}
			System.out.println("Play again? (Y or N)");

			if (!playAgain(input)) {
				playAgain = false;
			}
		}
	}

	private static boolean win(Board b, CellState cs) {
		// horizontal
		for (int i = 0; i < b.getRows(); i++) {
			int count = 0;
			for (int j = 0; j < b.getCols(); j++) {
				if (b.getBoard()[i][j].getState() == cs) {
					count++;
					if (count >= 4) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		// vertical
		for (int j = 0; j < b.getCols(); j++) {
			int count = 0;
			for (int i = 0; i < b.getRows(); i++) {
				if (b.getBoard()[i][j].getState() == cs) {
					count++;
					if (count >= 4) {
						return true;
					}
				} else {
					count = 0;
				}
			}
		}
		// diagonal right
		for (int i = 0; i < b.getRows() - 3; i++) {
			for (int j = 0; j < b.getCols() - 3; j++) {
				if (b.getBoard()[i][j].getState() == b.getBoard()[i + 1][j + 1].getState()
						&& b.getBoard()[i][j].getState() == b.getBoard()[i + 2][j + 2].getState()
						&& b.getBoard()[i][j].getState() == b.getBoard()[i + 3][j + 3].getState()
						&& b.getBoard()[i][j].getState() == cs) {
					return true;
				}
			}
		}
		// diagonal left
		for (int i = 0; i < b.getRows() - 3; i++) {
			for (int j = b.getCols() - 1; j >= b.getCols() - 3; j--) {
				if (b.getBoard()[i][j].getState() == cs) {
					if (b.getBoard()[i][j].getState() == b.getBoard()[i + 1][j - 1].getState()
							&& b.getBoard()[i][j].getState() == b.getBoard()[i + 2][j - 2].getState()
							&& b.getBoard()[i][j].getState() == b.getBoard()[i + 3][j - 3].getState()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean validInput(int col, Board b) {
		if (col <= b.getCols() - 1) {
			return true;
		}
		return false;
	}
	
	private static int getInput(Scanner input, Board board) {
		System.out.println("Which column?");
		while (true) {
			int col = input.nextInt() - 1;
			if (validInput(col, board)) {
				if (!board.isColFull(col)) {
					return col;
				} else {
					System.out.println("This column is already full - try again.");
					continue;
				}
			} else {
				System.out.println("Invalid input - try again.");
				continue;
			}
		}
	}
	
	private static void welcomeMessage() {
		System.out.println("Welcome to Connect Four!\nConnect four of your colour to win!\n");
	}
	
	
	private static int validPlayers(Scanner input) {
		int players = 0;
		boolean valid = false;
		while (!valid) {
			players = input.nextInt();
			if (players == 1 || players == 2) {
				valid = true;
			} else {
				System.out.println("Please enter either 1 or 2.");
			}
		} return players;
	}
	
	private static boolean playAgain(Scanner input) {
		String choice = input.next();
		if (choice.equals("Y")) {
			return true;
		} return false;
	}
	
	private static void turn(Board board, CellState cs, Scanner input) {
		int choice = getInput(input, board);
		int row = board.getRowPosition(choice);
		board.getBoard()[row][choice].setState(cs);
		board.display();
	}
}