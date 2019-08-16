package game;

import static game.Grid.DEFAULT_COLUMN_COUNT;
import static game.Grid.DEFAULT_ROW_COUNT;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GridFactoryTest {

	@Test
	void testCreate() {
		// Given
		GridFactory gridFactory = new GridFactory();
		// When
		Grid grid = gridFactory.create(DEFAULT_ROW_COUNT, DEFAULT_COLUMN_COUNT);
		// Then
		assertNotNull(grid, "Grid is null.");
	}

}
