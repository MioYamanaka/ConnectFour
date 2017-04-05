package mio.yamanaka;

public class Board {
	private Cell[][] board;
	private int rows;
	private int cols;

	public Board(int aRows, int aCols) {
		rows = aRows;
		cols = aCols;

		board = new Cell[rows][cols]; // only allocates storage, Cells are not
										// constructed

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new Cell(CellState.EMPTY);
			}
		}
	}

	public CellState getState(int x, int y) {
		return board[x][y].getState();
	}

	public boolean isValid(int x, int y) {
		if (x >= 0 && y >= 0 && x < rows && y < cols) {
			return true;
		}
		return false;
	}
	
	public boolean isColFull(int col){
		return board[0][col].getState() != CellState.EMPTY;
	}
	
	public int getRowPosition(int col){
		for (int i = rows - 1; i >= 0; i--){
			if (board[i][col].getState() == CellState.EMPTY){
				return i;
			}
		} return 0;
	}
	
	// DONE ALREADY
	public void display() {
		System.out.println("BOARD");
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.printf("%s ", board[i][j]);
			}
			System.out.println();
		}
	}
	
	public int getRows(){
		return rows;
	}
	
	public int getCols(){
		return cols;
	}
	
	public Cell[][] getBoard(){
		return board;
	}
}
