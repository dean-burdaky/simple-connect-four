package util.exceptions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static util.exceptions.NumberIsLessThanLowerBoundException.IS_LESS_THAN;

import org.junit.jupiter.api.Test;

public class NumberIsLessThanLowerBoundExceptionTest {
	
	private static final long TEST_SERIAL_VERSION_UID = 3534330130433648665L;
	public static final String DUMMY_LABEL1 = "Test1";
	public static final String DUMMY_LABEL2 = "Test2";

	@Test
	void testNumberIsLessThanLowerBoundExceptionStringString() {
		// Given
		// Above constant String used.
		// When
		NumberIsLessThanLowerBoundException exception = new NumberIsLessThanLowerBoundException(DUMMY_LABEL1, DUMMY_LABEL2) { private static final long serialVersionUID = TEST_SERIAL_VERSION_UID; };
		// Then
		assertEquals(DUMMY_LABEL1 + IS_LESS_THAN + DUMMY_LABEL2 + ".", exception.getMessage(), "Exception message was not as expected.");
	}

	@Test
	void testNumberIsLessThanLowerBoundExceptionStringStringThrowable() {
		// Given
		Throwable dummyCause = mock(Throwable.class);
		// When
		NumberIsLessThanLowerBoundException exception = new NumberIsLessThanLowerBoundException(DUMMY_LABEL1, DUMMY_LABEL2, dummyCause) { private static final long serialVersionUID = TEST_SERIAL_VERSION_UID; };
		// Then
		assertEquals(DUMMY_LABEL1 + IS_LESS_THAN + DUMMY_LABEL2 + ".", exception.getMessage(), "Exception message was not as expected.");
		assertEquals(dummyCause, exception.getCause(), "Throwable cause is retained.");
	}

}
