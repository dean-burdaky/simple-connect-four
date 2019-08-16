package game;

import static org.mockito.Mockito.mock;

public class MockPlayerFactory extends PlayerFactory {
	
	private final MockPlayerSetup mockPlayerSetup;
	
	public MockPlayerFactory(MockPlayerSetup mockPlayerSetup) {
		this.mockPlayerSetup = mockPlayerSetup;
	}
	
	@Override
	public Player create(Game game) {
		Player mockPlayer = mock(Player.class);
		if (mockPlayerSetup != null) mockPlayerSetup.setup(mockPlayer, game);
		return mockPlayer;
	}
	
	

}
