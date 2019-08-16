package util.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static util.exceptions.ArgumentIsNullException.IS_NULL;

import org.junit.jupiter.api.Test;

class ArgumentIsNullExceptionTest {
	
	static final long TEST_SERIAL_VERSION_UID = -5541959438414914588L;
	static final String DUMMY_PREFIX= "Test";

	@Test
	void testArgumentIsNullExceptionStringThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		ArgumentIsNullException exception = new ArgumentIsNullException(DUMMY_PREFIX, dummyCause) { private static final long serialVersionUID = TEST_SERIAL_VERSION_UID; };
		// Then
		assertEquals(DUMMY_PREFIX + IS_NULL, exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

	@Test
	void testArgumentIsNullExceptionString() {
		// Given
		// Above constant String used.
		// When
		ArgumentIsNullException exception = new ArgumentIsNullException(DUMMY_PREFIX) { private static final long serialVersionUID = TEST_SERIAL_VERSION_UID; };
		// Then
		assertEquals(DUMMY_PREFIX + IS_NULL, exception.getMessage(), "Exception message was not as expected.");
	}

}
