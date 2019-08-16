package game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.internal.util.MockUtil.*;

import org.junit.jupiter.api.Test;

class MockPlayerFactoryTest {

	@Test
	void testCreateWithSetup() {
		// Given
		MockPlayerSetup mockMockPlayerSetup = mock(MockPlayerSetup.class);
		MockPlayerFactory mockPlayerFactory = new MockPlayerFactory(mockMockPlayerSetup);
		// When
		Player player = mockPlayerFactory.create(null);
		// Then
		verify(mockMockPlayerSetup, times(1)).setup(any(Player.class), nullable(Game.class));
		assertNotNull(player);
		isMock(player);
	}
	
	@Test
	void testCreateWithoutSetup() {
		// Given
		MockPlayerFactory mockPlayerFactory = new MockPlayerFactory(null);
		// When
		Player player = mockPlayerFactory.create(null);
		// Then
		assertNotNull(player);
		isMock(player);
	}
}
