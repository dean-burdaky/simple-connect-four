package game;

import static org.mockito.Mockito.mock;

public class MockGridFactory extends GridFactory {
	private final MockGridSetup mockGridSetup;
	
	public MockGridFactory(MockGridSetup mockGridSetup) {
		this.mockGridSetup = mockGridSetup;
	}

	@Override
	public Grid create(int rowCount, int columnCount) {
		Grid mockGrid = mock(Grid.class);
		if (mockGridSetup != null) mockGridSetup.setup(mockGrid, rowCount, columnCount);
		return mockGrid;
	}
	
	
}
