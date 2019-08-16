package game;

import static game.RowCountTooSmallException.ROW_COUNT;
import static game.CounterLineLengthToWinTooSmallException.THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static util.exceptions.NumberIsLessThanLowerBoundException.IS_LESS_THAN;

import org.junit.jupiter.api.Test;

class RowCountTooSmallExceptionTest {
	
	@Test
	void testRowCountTooSmallException() {
		// Given
		// Above constant String used.
		// When
		RowCountTooSmallException exception = new RowCountTooSmallException();
		// Then
		assertEquals(ROW_COUNT + IS_LESS_THAN + THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL + ".", exception.getMessage(), "Exception message was not as expected.");
	}

	@Test
	void testRowCountTooSmallExceptionThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		RowCountTooSmallException exception = new RowCountTooSmallException(dummyCause);
		// Then
		assertEquals(ROW_COUNT + IS_LESS_THAN + THE_COUNTER_LINE_LENGTH_TO_WIN_LABEL + ".", exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

}
