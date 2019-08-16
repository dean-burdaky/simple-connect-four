package game;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.verification.VerificationMode;

class PlayerTest {

	@Test
	void testPlayer() {
		fail("Not yet implemented");
	}
	
	private InOrder playInOrder;
	
	// TODO Need to test validation checks of play()!

	@Test
	void testPlayWin() {
		testPlayValid(true);
	}
	
	@Test
	void testPlayNextTurn() {
		testPlayValid(false);
	}
	
	@Test
	void testPlayPlayerNotParticipant() {
		// Given 
		int col = 0;
		Game mockGame = mock(Game.class);
		Player player = spy(new Player(mockGame));
		doReturn(false).when(mockGame).isParticipant(player);
		// Test
		assertThrows(PlayerIsNotParticipantException.class, () -> { player.play(col); });
	}
	
	@Test
	void testPlayNotPlayerOnesTurn() {
		// Given 
		int col = 0;
		Game mockGame = mock(Game.class);
		Player player = spy(new Player(mockGame));
		doReturn(true).when(mockGame).isParticipant(player);
		doReturn(false).when(mockGame).hasTurn(player);
		doReturn(true).when(mockGame).isPlayerOne(player);
		// Test
		PlayerDoesNotHaveTurnException exception = assertThrows(PlayerDoesNotHaveTurnException.class, () -> { player.play(col); });
		verify(mockGame).isParticipant(player);
		assertEquals(1, exception.getPlayerNumber(), "Exception not thrown for player one!");
	}
	
	@Test
	void testPlayNotPlayerTwosTurn() {
		// Given 
		int col = 0;
		Game mockGame = mock(Game.class);
		Player player = spy(new Player(mockGame));
		doReturn(true).when(mockGame).isParticipant(player);
		doReturn(false).when(mockGame).hasTurn(player);
		doReturn(false).when(mockGame).isPlayerOne(player);
		// Test
		PlayerDoesNotHaveTurnException exception = assertThrows(PlayerDoesNotHaveTurnException.class, () -> { player.play(col); });
		verify(mockGame).isParticipant(player);
		assertEquals(2, exception.getPlayerNumber(), "Exception not thrown for player one!");
	}
	
	@Test
	void testPlayPlayerHasNoCounters() {
		// Given 
		int col = 0;
		Game mockGame = mock(Game.class);
		Player player = spy(new Player(mockGame));
		doReturn(true).when(mockGame).isParticipant(player);
		doReturn(true).when(mockGame).hasTurn(player);
		doReturn(false).when(player).hasCounter();
		// Test
		assertThrows(ZeroCountersException.class, () -> { player.play(col); });
		playInOrder = inOrder(mockGame, player);
		playInOrder.verify(mockGame).isParticipant(player);
		playInOrder.verify(mockGame).hasTurn(player);
	}
	
	@Test
	void testPlayInvalidColumnPosition() {
		// Given 
		int col = -1;
		Game mockGame = mock(Game.class);
		Player player = spy(new Player(mockGame));
		Grid mockGrid = mock(Grid.class);
		doReturn(mockGrid).when(mockGame).getGrid();
		doReturn(true).when(mockGame).isParticipant(player);
		doReturn(true).when(mockGame).hasTurn(player);
		doReturn(true).when(player).hasCounter();
		doReturn(false).when(mockGrid).isValidColumn(col);
		// Test
		assertThrows(InvalidColumnException.class, () -> { player.play(col); });
		playInOrder = inOrder(mockGame, mockGrid, player);
		playInOrder.verify(mockGame).isParticipant(player);
		playInOrder.verify(mockGame).hasTurn(player);
		playInOrder.verify(player).hasCounter();
	}
	
	@Test
	void testPlayNoFreeSlot() {
		// Given 
		int col = 0;
		Game mockGame = mock(Game.class);
		Player player = spy(new Player(mockGame));
		Grid mockGrid = mock(Grid.class);
		doReturn(mockGrid).when(mockGame).getGrid();
		doReturn(true).when(mockGame).isParticipant(player);
		doReturn(true).when(mockGame).hasTurn(player);
		doReturn(true).when(player).hasCounter();
		doReturn(true).when(mockGrid).isValidColumn(col);
		doReturn(false).when(mockGrid).hasFreeSlotIn(col);
		// Test
		assertThrows(NoFreeSlotException.class, () -> { player.play(col); });
		playInOrder = inOrder(mockGame, mockGrid, player);
		playInOrder.verify(mockGame).isParticipant(player);
		playInOrder.verify(mockGame).hasTurn(player);
		playInOrder.verify(player).hasCounter();
	}
	
	private void testPlayValid(boolean win) {
		// Given
		int row = 0, column = 0;
		Game mockGame = mock(Game.class);
		Grid mockGrid = mock(Grid.class);
		Player player = spy(new Player(mockGame));
		// Mock Behaviour Injection
		doReturn(mockGrid).when(mockGame).getGrid();
		passRequiredChecks(column, mockGame, mockGrid, player);
		doReturn(row).when(mockGrid).dropCounter(player, column);
		doReturn(win).when(mockGrid).formsWinningLine(mockGame.getCounterLineLengthToWin(), player, row, column);
		// When
		player.play(column);
		// Then
		playInOrder = inOrder(mockGame, mockGrid, player);
		verifyChecksBefore(column, mockGame, mockGrid, player);
		verifyActions(column, mockGrid, player);
		verifyWinCheck(row, column, mockGrid, mockGame, player);
		if (win) verifyWinOnly(mockGame, player);
		else verifyNextTurnOnly(mockGame);
		playInOrder.verifyNoMoreInteractions();
	}

	private void passRequiredChecks(int column, Game mockGame, Grid mockGrid, Player player) {
		doReturn(true).when(mockGame).isParticipant(player);
		doReturn(true).when(mockGame).hasTurn(player);
		doReturn(true).when(player).hasCounter();
		doReturn(true).when(mockGrid).isValidColumn(column);
		doReturn(true).when(mockGrid).hasFreeSlotIn(column);
	}

	private void verifyWinCheck(int row, int column, Grid mockGrid, Game game, Player player) {
		playInOrder.verify(mockGrid).formsWinningLine(game.getCounterLineLengthToWin(), player, row, column);
	}

	private void verifyWinOnly(Game mockGame, Player player) {
		playInOrder.verify(mockGame).win(player);
		playInOrder.verify(mockGame, never()).nextTurn();
	}
	
	private void verifyNextTurnOnly(Game mockGame) {
		playInOrder.verify(mockGame, never()).win(any(Player.class));
		playInOrder.verify(mockGame).nextTurn();
	}

	private void verifyActions(int column, Grid mockGrid, Player player) {
		playInOrder.verify(player).takeCounter();
		playInOrder.verify(mockGrid).dropCounter(player, column);
	}

	private void verifyChecksBefore(int column, Game mockGame, Grid mockGrid, Player player) {
		playInOrder.verify(mockGame).isParticipant(player);
		playInOrder.verify(mockGame).hasTurn(player);
		playInOrder.verify(player).hasCounter();
		playInOrder.verify(mockGrid).isValidColumn(column);
		playInOrder.verify(mockGrid).hasFreeSlotIn(column);
	}

	@Test
	void testForfeit() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCountersPlayed() {
		fail("Not yet implemented");
	}

}
