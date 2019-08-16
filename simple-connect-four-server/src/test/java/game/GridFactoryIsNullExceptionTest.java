package game;

import static game.GridFactoryIsNullException.FACTORY_PREFIX;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static util.exceptions.FactoryIsNullException.FACTORY_IS_NULL;

import org.junit.jupiter.api.Test;

import game.GridFactoryIsNullException;

class GridFactoryIsNullExceptionTest {

	@Test
	void testGridFactoryIsNullExceptionThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		GridFactoryIsNullException exception = new GridFactoryIsNullException(dummyCause);
		// Then
		assertEquals(FACTORY_PREFIX + FACTORY_IS_NULL, exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

	@Test
	void testGridFactoryIsNullException() {
		// Given
		// Imported constant String used.
		// When
		GridFactoryIsNullException exception = new GridFactoryIsNullException();
		// Then
		assertEquals(FACTORY_PREFIX + FACTORY_IS_NULL, exception.getMessage(), "Exception message was not as expected.");
	}

}
