package game;

public class Grid {
	
	private enum Direction {
		S2N,
		SW2NE,
		W2E,
		NW2SE
	}
	
	public static final int DEFAULT_ROW_COUNT = 6;
	public static final int DEFAULT_COLUMN_COUNT = 7;
	
	private final Player grid[][];
	private final int freeSlotIndices[];
	private final int rowCount, columnCount;
	private int fCol;
	
	Grid(int rowCount, int columnCount) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.grid = new Player[rowCount][columnCount];
		this.freeSlotIndices = new int[columnCount];
	}
	
	private Player[][] getGrid() { return grid; }
	
	private void setSlot(int row, int col, Player player) {
		getGrid()[row][col] = player;
	}
	
	Player getSlot(int row, int col) {		
		return getGrid()[row][col];
	}
	
	private int[] getFreeSlotIndices() { return freeSlotIndices; }
	
	private void setFreeSlotIndex(int col, int freeSlotIndex) {
		getFreeSlotIndices()[col] = freeSlotIndex;
	}
	
	int getFreeSlotIndex(int col) {
		return getFreeSlotIndices()[col];
	}
	
	public int getRowCount() { return rowCount; }
	
	public int getColumnCount() { return columnCount; }

	boolean isValidColumn(int column) { return column >= 0 && column < getColumnCount(); }
	boolean isValidRow(int row) { return row >= 0 && row < getRowCount(); }
	
	boolean isFreeSlot(int row, int column) { return row >= getFreeSlotIndex(column); }
	boolean hasFreeSlotIn(int column) { return isFreeSlot(getRowCount()-1, column); }
	
	private int setLowestFreeSlot(int column, Player player)
	{
			int row = getFreeSlotIndex(column);
			setSlot(row, column, player);
			setFreeSlotIndex(column, row + 1);
			return row;
	}

	int dropCounter(Player player, int column) {
		if (player == null)
			throw new NullPointerException("Cannot drop no counter into grid.");
		else if (!isValidColumn(column))
			throw new InvalidColumnException(column);
		else if (!hasFreeSlotIn(column))
			throw new NoFreeSlotException(column);
		
		return setLowestFreeSlot(column, player);
	}
	
	private int rowDispInDirection(Direction direction, boolean forward) {
		if (direction != null && direction != Direction.S2N)
			return forward ? 1 : -1;
		else
			return 0;
	}
	
	private int columnDispInDirection(Direction direction, boolean forward) {
		int disp = forward ? 1 : -1;
		switch(direction) {
		case S2N:
		case SW2NE: return disp;
		case NW2SE: return -disp;
		default: return 0;
		}
	}
	
	private int countConsecutiveCounters(Direction direction, boolean forward, int counterLineLengthToWin, Player player, int row, int column) {
		int count = 0;
		int dRow = row + rowDispInDirection(direction, forward);
		int dCol = column + columnDispInDirection(direction, forward);
		while (count + 1 < counterLineLengthToWin && isValidRow(dRow) && isValidColumn(dCol) && getSlot(dRow, dCol) == player) {
			count++;
			dRow += rowDispInDirection(direction, forward);
			dCol += columnDispInDirection(direction, forward);
		}
		return count;
	}

	boolean formsWinningLine(int counterLineLengthToWin, Player player, int row, int column) {
		if (getSlot(row, column) != player)
			return false;
		
		for (Direction direction : Direction.values())
			if (formsWinningLineInDirection(counterLineLengthToWin, player, row, column, direction))
				return true;
		
		return false;
	}

	private boolean formsWinningLineInDirection(int counterLineLengthToWin, Player player, int row, int column, Direction direction) {
		int forward = countConsecutiveCounters(direction, true, counterLineLengthToWin, player, row, column);
		if (forward + 1 >= counterLineLengthToWin) return true;
		
		int backward = countConsecutiveCounters(direction, false, counterLineLengthToWin - forward, player, row, column);
		if (forward + backward + 1 >= counterLineLengthToWin) return true;
		
		return false;
	}
}
