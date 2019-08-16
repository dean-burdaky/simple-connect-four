package game;

public final class PlayerDoesNotHaveTurnException extends GameStateException {

	private static final long serialVersionUID = 6327891473769867183L;
	private static final String PLAYER = "Player ";
	private static final String DOES_NOT_HAVE_TURN = " does not have turn.";
	
	private final int playerNumber;
	
	private static final String buildMessage(int playerNumber) {
		return PLAYER + playerNumber + DOES_NOT_HAVE_TURN;
	}

	public PlayerDoesNotHaveTurnException(int playerNumber, Throwable cause) {
		super(buildMessage(playerNumber), cause);
		this.playerNumber = playerNumber;
	}

	public PlayerDoesNotHaveTurnException(int playerNumber) {
		super(buildMessage(playerNumber));
		this.playerNumber = playerNumber;
	}
	
	public int getPlayerNumber() { return playerNumber; }
}
