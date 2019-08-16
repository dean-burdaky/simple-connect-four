package game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class PlayerFactoryTest {

	@Test
	void testCreate() {
		// Given
		Game game = mock(Game.class);
		PlayerFactory playerFactory = new PlayerFactory();
		// When
		Player player = playerFactory.create(game);
		// Then
		assertNotNull(player, "Player is null.");
	}

}
