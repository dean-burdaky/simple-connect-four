package game;

import static game.CounterLineLengthToWinTooSmallException.THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL;
import static game.ColumnCountTooSmallException.COLUMN_COUNT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static util.exceptions.NumberIsLessThanLowerBoundException.IS_LESS_THAN;

import org.junit.jupiter.api.Test;

class ColumnCountTooSmallExceptionTest {

	@Test
	void testColumnCountTooSmallException() {
		// Given
		// Above constant String used.
		// When
		ColumnCountTooSmallException exception = new ColumnCountTooSmallException();
		// Then
		assertEquals(COLUMN_COUNT + IS_LESS_THAN + THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL + ".", exception.getMessage(), "Exception message was not as expected.");
	}

	@Test
	void testColumnCountTooSmallExceptionThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		ColumnCountTooSmallException exception = new ColumnCountTooSmallException(dummyCause);
		// Then
		assertEquals(COLUMN_COUNT + IS_LESS_THAN + THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL + ".", exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

}
