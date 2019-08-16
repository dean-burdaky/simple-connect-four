package game;

import static game.CounterLineLengthToWinTooSmallException.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import static util.exceptions.NumberIsLessThanLowerBoundException.IS_LESS_THAN;

import org.junit.jupiter.api.Test;

import game.CounterLineLengthToWinTooSmallException;

class CounterLineLengthToWinTooSmallExceptionTest {

	@Test
	void testCounterLineLengthToWinTooSmallException() {
		// Given
		// Above constant String used.
		// When
		CounterLineLengthToWinTooSmallException exception = new CounterLineLengthToWinTooSmallException();
		// Then
		assertEquals(COUNTER_LINE_LENGTH_TO_WIN_LABEL + IS_LESS_THAN + LOWER_BOUND_LABEL + ".", exception.getMessage(), "Exception message was not as expected.");
	}

	@Test
	void testCounterLineLengthToWinTooSmallExceptionThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		CounterLineLengthToWinTooSmallException exception = new CounterLineLengthToWinTooSmallException(dummyCause);
		// Then
		assertEquals(COUNTER_LINE_LENGTH_TO_WIN_LABEL + IS_LESS_THAN + LOWER_BOUND_LABEL + ".", exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

}
