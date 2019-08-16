package game;

public abstract class GameStateException extends RuntimeException {

	private static final long serialVersionUID = 6815492854363440051L;

	public GameStateException(String message, Throwable cause) {
		super(message, cause);
	}

	public GameStateException(String message) {
		super(message);
	}
}