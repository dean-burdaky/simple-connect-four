package game;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.*;
import static game.Grid.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GridTest {
	
	private static Grid provideGrid() {
		return new Grid(DEFAULT_ROW_COUNT, DEFAULT_COLUMN_COUNT);
	}
	
	private static Stream<Arguments> provideFailingDropCounterTestData() {
		Grid spyGrid = spy(provideGrid());
		when(spyGrid.hasFreeSlotIn(0)).thenReturn(false);
		return Stream.of(
				Arguments.of(provideGrid(), null, 0, NullPointerException.class),
				Arguments.of(provideGrid(), mock(Player.class), -1, InvalidColumnException.class),
				Arguments.of(spyGrid, mock(Player.class), 0, NoFreeSlotException.class));
	}

	@ParameterizedTest
	@MethodSource("provideFailingDropCounterTestData")
	void testDropCounterFailed(Grid grid, Player player, int column, Class<Exception> exceptionClass) {
		Executable dropCounterExecutable = ()->{ grid.dropCounter(player, column); };
		assertThrows(exceptionClass, dropCounterExecutable, "Did not throw expected exception!");
	}
	
	@Test
	void testDropCounterSuccess() {
		// Given
		int column = 0;
		int expectedRow = 0;
		Grid grid = provideGrid();
		assumeTrue(grid.isFreeSlot(expectedRow, column));
		Player mockPlayer = mock(Player.class);
		// When
		int row = grid.dropCounter(mockPlayer, column);
		// Then
		assertEquals(expectedRow, row, "Row of free slot filled was not as expected.");
		assertEquals(mockPlayer, grid.getSlot(row, column), "Counter do not belong to same Player.");
		assertNull(grid.getSlot(row + 1, column), "Row above is not free!");
		assertEquals(row + 1, grid.getFreeSlotIndex(column), "Free slot index not set to row above!");
	}

}
