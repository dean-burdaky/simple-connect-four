package game;

import static game.CounterLineLengthToWinTooSmallException.THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL;

import util.exceptions.NumberIsLessThanLowerBoundException;

public final class RowCountTooSmallException extends NumberIsLessThanLowerBoundException {

	private static final long serialVersionUID = -3337908883299482645L;
	static String ROW_COUNT = "Row count";
	RowCountTooSmallException() {
		super(ROW_COUNT, THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL);
	}
	
	RowCountTooSmallException(Throwable cause) {
		super(ROW_COUNT, THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL, cause);
	}

}
