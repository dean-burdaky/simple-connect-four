package game;

import static game.GameNotStartedYetException.GAME_NOT_STARTED_YET;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

class GameNotStartedYetExceptionTest {

	@Test
	void testGameNotStartedYetExceptionThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		GameNotStartedYetException exception = new GameNotStartedYetException(dummyCause);
		// Then
		assertEquals(GAME_NOT_STARTED_YET, exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

	@Test
	void testGameNotStartedYetException() {
		// Given
		// When
		GameNotStartedYetException exception = new GameNotStartedYetException();
		// Then
		assertEquals(GAME_NOT_STARTED_YET, exception.getMessage(), "Exception message was not as expected.");
	}

}
