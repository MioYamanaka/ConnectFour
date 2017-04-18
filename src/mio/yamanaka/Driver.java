package mio.yamanaka;

import java.util.Scanner;

public class Driver {
	public static void main(String[] args) {
		welcomeMessage();
		Scanner input = new Scanner(System.in);
		System.out.println("How many players? (1 or 2)");
		int players = validPlayers(input);
		Board board = buildBoard(input);
		int rounds = 0;
		// two-player version
		if (players == 2) {
			while (true) {
				// player 1
				rounds++;
				System.out.println("Round " + rounds);
				int choice1 = getInput(input, board);
				int row1 = board.getRowPosition(choice1);
				board.getBoard()[row1][choice1].setState(CellState.P1);
				board.display();
				if (rounds >= 4) {
					if (p1win(board)) {
						System.out.println("Player 1 Wins!");
						break;
					}
				}
				// player 2
				int choice2 = getInput(input, board);
				int row2 = board.getRowPosition(choice2);
				board.getBoard()[row2][choice2].setState(CellState.P2);
				board.display();
				if (rounds >= 4) {
					if (p2win(board)) {
						System.out.println("Player 2 Wins!");
						break;
					}
				}
			}
		}
	}

	private static boolean p1win(Board b) {
		// horizontal
		for (int i = 0; i < b.getRows(); i++) {
			int count = 0;
			for (int j = 0; j < b.getCols(); j++) {
				if (b.getBoard()[i][j].getState() == CellState.P1) {
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
				if (b.getBoard()[i][j].getState() == CellState.P1) {
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
						&& b.getBoard()[i][j].getState() == CellState.P1) {
					return true;
				}
			}
		}
		// diagonal left
		for (int i = 0; i < b.getRows() - 3; i++) {
			for (int j = b.getCols() - 1; j >= b.getCols() - 3; j--) {
				if (b.getBoard()[i][j].getState() == CellState.P1) {
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

	private static boolean p2win(Board b) {
		// horizontal
		for (int i = 0; i < b.getRows(); i++) {
			int count = 0;
			for (int j = 0; j < b.getCols(); j++) {
				if (b.getBoard()[i][j].getState() == CellState.P2) {
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
				if (b.getBoard()[i][j].getState() == CellState.P2) {
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
						&& b.getBoard()[i][j].getState() == CellState.P2) {
					return true;
				}
			}
		}
		// diagonal left
		for (int i = 0; i < b.getRows() - 3; i++) {
			for (int j = b.getCols() - 1; j >= b.getCols() - 3; j--) {
				if (b.getBoard()[i][j].getState() == CellState.P2) {
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
		System.out.println("Welcome to Connect Four!");
		System.out.println("Connect four of your colour to win!");
	}
	
	private static Board buildBoard(Scanner input) {
		System.out.println("How many columns should the board have?");
		int cols = input.nextInt();
		System.out.println("How many rows should the board have?");
		int rows = input.nextInt();
		Board board = new Board(rows, cols);
		return board;
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
}
