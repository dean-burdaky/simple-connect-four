package util.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import static util.exceptions.FactoryIsNullException.FACTORY_IS_NULL;
import static util.exceptions.ArgumentIsNullExceptionTest.*;

import org.junit.jupiter.api.Test;

class FactoryIsNullExceptionTest {
	
	@Test
	void testFactoryIsNullExceptionStringThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		FactoryIsNullException exception = new FactoryIsNullException(DUMMY_PREFIX, dummyCause) { private static final long serialVersionUID = TEST_SERIAL_VERSION_UID; };
		// Then
		assertEquals(DUMMY_PREFIX + FACTORY_IS_NULL, exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

	@Test
	void testFactoryIsNullExceptionString() {
		// Given
		// Above constant String used.
		// When
		FactoryIsNullException exception = new FactoryIsNullException(DUMMY_PREFIX) { private static final long serialVersionUID = TEST_SERIAL_VERSION_UID; };
		// Then
		assertEquals(DUMMY_PREFIX + FACTORY_IS_NULL, exception.getMessage(), "Exception message was not as expected.");
	}

}
