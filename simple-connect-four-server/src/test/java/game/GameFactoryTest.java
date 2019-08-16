package game;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import static game.Game.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameFactoryTest {
	
	static final String PLAYER_FACTORY_NULL = "PlayerFactory is null.";
	static final String GRID_FACTORY_NULL = "GridFactory is null.";
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> testGameFactoryArgumentProvider() {
		return Stream.of(
				Arguments.of(null, null, PLAYER_FACTORY_NULL),
				Arguments.of(mock(PlayerFactory.class), null, GRID_FACTORY_NULL),
				Arguments.of(null, mock(GridFactory.class), PLAYER_FACTORY_NULL)
				);
	}
	
	@Test
	void testGameFactoryWithValidArguments() {
		// Given
		PlayerFactory dummyPlayerFactory = mock(PlayerFactory.class);
		GridFactory dummyGridFactory = mock(GridFactory.class);
		// When
		new GameFactory(dummyPlayerFactory, dummyGridFactory);
		// Then
		// Pass
	}
	
	@ParameterizedTest(name = "testGameFactoryWithInvalidArguments(''{0}'', ''{1}'', ''{2}'') [{index}]")
	@MethodSource("testGameFactoryArgumentProvider")
	void testGameFactoryWithInvalidArguments(PlayerFactory playerFactory, GridFactory gridFactory, String expectedExceptionMessage) {
		// Given
		// Arguments
		// When
		Executable newGameFactory = () -> { new GameFactory(playerFactory, gridFactory); };
		// Then
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, newGameFactory);
		assertEquals(expectedExceptionMessage, exception.getMessage(), "Unexpected exception message.");	
	}
	
	@Test
	void testCreate() {
		// Given	
		PlayerFactory dummyPlayerFactory = mock(PlayerFactory.class);
		GridFactory dummyGridFactory = mock(GridFactory.class);
		GameFactory gameFactory = new GameFactory(dummyPlayerFactory, dummyGridFactory);
		// When
		Game game = gameFactory.create(DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT);
		// Then
		assertNotNull(game, "Game is null.");
	}

}
