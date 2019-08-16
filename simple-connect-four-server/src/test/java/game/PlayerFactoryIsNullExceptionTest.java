package game;

import static game.PlayerFactoryIsNullException.FACTORY_PREFIX;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static util.exceptions.FactoryIsNullException.FACTORY_IS_NULL;

import org.junit.jupiter.api.Test;

import game.PlayerFactoryIsNullException;

class PlayerFactoryIsNullExceptionTest {

	@Test
	void testPlayerFactoryIsNullExceptionThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		PlayerFactoryIsNullException exception = new PlayerFactoryIsNullException(dummyCause);
		// Then
		assertEquals(FACTORY_PREFIX + FACTORY_IS_NULL, exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

	@Test
	void testPlayerFactoryIsNullException() {
		// Given
		// Imported constant String used.
		// When
		PlayerFactoryIsNullException exception = new PlayerFactoryIsNullException();
		// Then
		assertEquals(FACTORY_PREFIX + FACTORY_IS_NULL, exception.getMessage(), "Exception message was not as expected.");
	}


}
