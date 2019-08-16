package game;

import static game.Game.MINIMUM_COUNTER_LINE_LENGTH_TO_WIN;

import util.exceptions.NumberIsLessThanLowerBoundException;

public final class CounterLineLengthToWinTooSmallException extends NumberIsLessThanLowerBoundException {

	static final String COUNTER_LINE_LENGTH_TO_WIN_LABEL = "Counter line length to win";
	static final String LOWER_BOUND_LABEL = String.valueOf(MINIMUM_COUNTER_LINE_LENGTH_TO_WIN);
	static String THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL = "the " + COUNTER_LINE_LENGTH_TO_WIN_LABEL.toLowerCase();
	
	CounterLineLengthToWinTooSmallException() {
		super(COUNTER_LINE_LENGTH_TO_WIN_LABEL, LOWER_BOUND_LABEL);
	}
	
	CounterLineLengthToWinTooSmallException(Throwable cause) {
		super(COUNTER_LINE_LENGTH_TO_WIN_LABEL, LOWER_BOUND_LABEL, cause);
	}

	private static final long serialVersionUID = 379766941869922501L;

}
