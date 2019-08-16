package game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.mockito.Mockito.*;

import static game.Game.*;
import static game.GameFactoryTest.*;

import java.util.ArrayList;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GameTest {
	
	private static final String COUNTER_LINE_LENGTH_TOO_SMALL = "Counter line length to win is less than 2.";
	private static final String ROW_COUNT_TOO_SMALL = "Row count is less than the counter line length to win.";
	private static final String COLUMN_COUNT_TOO_SMALL = "Column count is less than the counter line length to win.";
	private static final int COUNTER_COUNT_FOR_DEFAULT_GAME = Grid.DEFAULT_ROW_COUNT * Grid.DEFAULT_COLUMN_COUNT;
	
	@SuppressWarnings("unused")
	private static Stream<Arguments> testGameInvalidArgumentsProvider() {
		return Stream.of(
				Arguments.of(mock(PlayerFactory.class), null, DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT, GRID_FACTORY_NULL),
				Arguments.of(null, mock(GridFactory.class), DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT, PLAYER_FACTORY_NULL),
				Arguments.of(mock(PlayerFactory.class), mock(GridFactory.class), MINIMUM_COUNTER_LINE_LENGTH_TO_WIN - 1, Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT, COUNTER_LINE_LENGTH_TOO_SMALL),
				Arguments.of(mock(PlayerFactory.class), mock(GridFactory.class), DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, DEFAULT_COUNTER_LINE_LENGTH_TO_WIN - 1, Grid.DEFAULT_COLUMN_COUNT, ROW_COUNT_TOO_SMALL),
				Arguments.of(mock(PlayerFactory.class), mock(GridFactory.class), DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, Grid.DEFAULT_ROW_COUNT, DEFAULT_COUNTER_LINE_LENGTH_TO_WIN - 1, COLUMN_COUNT_TOO_SMALL)
				);
	}
	
	@Test
	void testGameWithValidArguments() {
		// Given
		PlayerFactory spiedMockPlayerFactory = spy(new MockPlayerFactory(null));
		GridFactory dummyGridFactory = mock(GridFactory.class);
		// When
		Game game = new Game(spiedMockPlayerFactory, dummyGridFactory, DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT);
		// Then
		verify(spiedMockPlayerFactory, times(2)).create(game);
		verify(dummyGridFactory, times(1)).create(Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT);
		assertNotEquals(game.getPlayerOne(), game.getPlayerTwo(), "Player one and player two are the same!");
	}
	
	@ParameterizedTest(name = "testGameWithInvalidArguments(''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'') [{index}]")
	@MethodSource("testGameInvalidArgumentsProvider")
	void testGameWithInvalidArguments(PlayerFactory playerFactory, GridFactory gridFactory, int counterLineLengthToWin, int rowCount, int columnCount, String expectedExceptionMessage) {
		// Given
		// Arguments
		// When
		Executable newGame = () -> { new Game(playerFactory, gridFactory, counterLineLengthToWin, rowCount, columnCount); };
		// Then
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, newGame);
		assertEquals(expectedExceptionMessage, exception.getMessage(), "Unexpected exception message.");	
	}
	
	private static Game setupDefaultGame() {
		PlayerFactory dummyPlayerFactory = mock(PlayerFactory.class);	
		return setupDefaultGame(dummyPlayerFactory);
	}
	
	private static Game setupDefaultGame(PlayerFactory playerFactory) {
		GridFactory dummyGridFactory = mock(GridFactory.class);
		return new Game(playerFactory, dummyGridFactory, DEFAULT_COUNTER_LINE_LENGTH_TO_WIN, Grid.DEFAULT_ROW_COUNT, Grid.DEFAULT_COLUMN_COUNT);
	}
	
	@Test
	void testStart() {
		// Given
		Game game = setupDefaultGame();
		assumeFalse(game.getStarted(), "Game considered already started.");
		// When
		game.start();
		// Then
		assertTrue(game.getStarted(), "Game has not started!");
	}

	@Test
	void testEndGivenStarted() {
		// Given
		Game game = setupDefaultGame();
		game.start();
		assumeFalse(game.getEnded(), "Game considered already ended.");
		// When
		game.end();
		// Then
		assertTrue(game.getEnded(), "Game has not ended!");
		assertEquals(game.getPlayerOne(), game.getCurrentPlayer(), "Current player is not player one initially.");
	}
	
	@Test
	void testEndGivenNotStarted() {
		// Given
		Game game = setupDefaultGame();
		assumeFalse(game.getStarted(), "Game considered already started.");
		assumeFalse(game.getEnded(), "Game considered already ended.");
		// When
		Executable gameEnd = () -> { game.end(); };
		// Then
		assertThrows(GameNotStartedYetException.class, gameEnd);
	}
	
	private static Game setupDefaultGameWithSeparatePlayers(MockPlayerSetup mockPlayerSetup) {
		PlayerFactory mockPlayerFactory = new MockPlayerFactory(mockPlayerSetup);
		return setupDefaultGame(mockPlayerFactory);
	}
	
	@Test
	void testSetPlayerOneAsCurrent() {
		// Given
		Game game = setupDefaultGameWithSeparatePlayers(null);
		// When
		game.setPlayerOneAsCurrent();
		// Then
		assertEquals(game.getPlayerOne(), game.getCurrentPlayer(), "Current player was not set to player one!");
	}
	
	@Test
	void testSetPlayerTwoAsCurrent() {
		// Given
		Game game = setupDefaultGameWithSeparatePlayers(null);
		// When
		game.setPlayerTwoAsCurrent();
		// Then
		assertEquals(game.getPlayerTwo(), game.getCurrentPlayer(), "Current player was not set to player two!");
	}
	
	@Test
	void testHasTurn() {
		// Given
		Game game = setupDefaultGameWithSeparatePlayers(null);
		Player playerOne = game.getPlayerOne();
		Player playerTwo = game.getPlayerTwo();
		game.setPlayerOneAsCurrent();
		// Test
		assertTrue(game.hasTurn(playerOne), "Player one does not have turn!");
		assertFalse(game.hasTurn(playerTwo), "Player two has turn!");
	}

	@Test
	void testNextTurnWithPlayerOneStarting() {
		// Given
		Game game = setupDefaultGameWithSeparatePlayers(null);
		Player playerTwo = game.getPlayerTwo();
		game.setPlayerOneAsCurrent();
		// When
		game.nextTurn();
		// Then
		assertEquals(playerTwo, game.getCurrentPlayer(), "New current player is not player two!");
	}
	
	@Test
	void testNextTurnWithPlayerTwoStarting() {
		// Given
		Game game = setupDefaultGameWithSeparatePlayers(null);
		Player playerOne = game.getPlayerOne();
		game.setPlayerTwoAsCurrent();
		// When
		game.nextTurn();
		// Then
		assertEquals(playerOne, game.getCurrentPlayer(), "New current player is not player one!");
	}

	@Test
	void testHasAllCountersBeenPlayedValid() {
		// Given
		MockPlayerSetup playerPlayedAllCounters = (Player mockPlayer, Game game) -> { when(mockPlayer.getCountersPlayed()).thenReturn(COUNTER_COUNT_FOR_DEFAULT_GAME / 2); };
		Game game = setupDefaultGameWithSeparatePlayers(playerPlayedAllCounters);
		// Test
		assertTrue(game.hasAllCountersBeenPlayed(), "All counters not considered played.");
	}
	
	@Test
	void testHasAllCountersBeenPlayedInvalid() {
		// Given
		Game game = setupDefaultGameWithSeparatePlayers(null);
		Player playerOne = game.getPlayerOne();
		Player playerTwo = game.getPlayerTwo();
		when(playerOne.getCountersPlayed()).thenReturn(COUNTER_COUNT_FOR_DEFAULT_GAME / 2);
		when(playerTwo.getCountersPlayed()).thenReturn(COUNTER_COUNT_FOR_DEFAULT_GAME / 2 - 1);
		// Test
		assertFalse(game.hasAllCountersBeenPlayed(), "All counters considered played.");
	}
}
