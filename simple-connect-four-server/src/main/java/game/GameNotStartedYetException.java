package game;

public final class GameNotStartedYetException extends IllegalArgumentException {

	private static final long serialVersionUID = -7633774461331396444L;
	static final String GAME_NOT_STARTED_YET = "Game not started yet.";

	GameNotStartedYetException(Throwable cause) {
		super(GAME_NOT_STARTED_YET, cause);
	}

	GameNotStartedYetException() {
		super(GAME_NOT_STARTED_YET);
	}

}
