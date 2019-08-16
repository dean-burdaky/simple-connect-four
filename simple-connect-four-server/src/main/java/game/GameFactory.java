package game;

public class GameFactory {
	
	private final PlayerFactory playerFactory;
	private final GridFactory gridFactory;
	
	public GameFactory(PlayerFactory playerFactory, GridFactory gridFactory) {
		if (playerFactory == null)
			throw new PlayerFactoryIsNullException();
		else if (gridFactory == null)
			throw new GridFactoryIsNullException();
		
		this.playerFactory = playerFactory;
		this.gridFactory = gridFactory;
	}
	
	private final PlayerFactory getPlayerFactory() {
		return playerFactory;
	}
	
	private final GridFactory getGridFactory() {
		return gridFactory;
	}
	
	public Game create(int counterLineLengthToWin, int rowCount, int columnCount) {
		return new Game(getPlayerFactory(), getGridFactory(), counterLineLengthToWin, rowCount, columnCount);
	}
}
