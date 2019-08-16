package game;

import static game.CounterLineLengthToWinTooSmallException.THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL;

import util.exceptions.NumberIsLessThanLowerBoundException;

public final class ColumnCountTooSmallException extends NumberIsLessThanLowerBoundException {

	private static final long serialVersionUID = 6789417064185005201L;
	static final String COLUMN_COUNT = "Column count";

	ColumnCountTooSmallException() {
		super(COLUMN_COUNT, THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL);
	}
	
	ColumnCountTooSmallException(Throwable cause) {
		super(COLUMN_COUNT, THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL, cause);
	}
}
