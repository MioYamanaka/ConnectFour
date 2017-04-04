package mio.yamanaka;

public class Cell {
	private CellState state;
	
	public Cell(CellState cs) {
		state = cs;
	}
	
	public void setState (CellState cs){
		state = cs;
	}
	
	public CellState getState() {
		return state;
	}

	public String toString() {
		switch (state) {
		case P1:
			return "P1";
		case P2:
			return "P2";
		default:
			return "-";
		}
	}
}

