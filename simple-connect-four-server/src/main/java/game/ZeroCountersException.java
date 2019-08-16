package game;

public final class ZeroCountersException extends PlayerStateException {

	private static final long serialVersionUID = -1656985350147568697L;
	private static final String PLAYER_HAS_NO_COUNTERS = "Player has no counters.";

	public ZeroCountersException() {
		super(PLAYER_HAS_NO_COUNTERS);
	}
	
	public ZeroCountersException(Throwable throwable) {
		super(PLAYER_HAS_NO_COUNTERS, throwable);
	}
}
