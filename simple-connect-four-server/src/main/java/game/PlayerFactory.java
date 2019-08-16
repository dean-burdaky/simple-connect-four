package game;

public class PlayerFactory {
	
	public Player create(Game game) {
		return new Player(game);
	}
}
