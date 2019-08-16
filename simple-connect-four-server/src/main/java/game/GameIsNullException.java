package game;

import util.exceptions.ArgumentIsNullException;

public final class GameIsNullException extends ArgumentIsNullException {
	
	private static final String GAME = "Game";

	protected GameIsNullException() {
		super(GAME);
	}

	public GameIsNullException(Throwable cause) {
		super(GAME, cause);
	}
	
	

}
