package game;

public class PlayerStateException extends RuntimeException {

	private static final long serialVersionUID = 5261187695943844778L;

	public PlayerStateException(String message) {
		super(message);
	}

	public PlayerStateException(String message, Throwable cause) {
		super(message, cause);
	}

}