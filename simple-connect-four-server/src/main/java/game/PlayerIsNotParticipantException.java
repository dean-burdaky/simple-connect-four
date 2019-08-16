package game;

public class PlayerIsNotParticipantException extends IllegalArgumentException {

	private static final long serialVersionUID = -5575823171190741658L;
	private static final String PLAYER_IS_NOT_PARTICIPANT = "Player is not participant.";

	public PlayerIsNotParticipantException(Throwable throwable) {
		super(PLAYER_IS_NOT_PARTICIPANT, throwable);
	}

	public PlayerIsNotParticipantException() {
		super(PLAYER_IS_NOT_PARTICIPANT);
	}
	
	
}
