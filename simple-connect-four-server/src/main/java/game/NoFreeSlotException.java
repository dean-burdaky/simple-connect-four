package game;

public final class NoFreeSlotException extends IllegalArgumentException {

	private static final long serialVersionUID = 6688265699659017842L;
	private static final String NO_FREE_SLOT_AT_COLUMN = "No free slot at column ";
	
	private static String buildMessage(int column) {
		return NO_FREE_SLOT_AT_COLUMN + column + ".";
	}
	
	public NoFreeSlotException(int column, Throwable cause) {
		super(buildMessage(column), cause);
	}

	public NoFreeSlotException(int column) {
		super(buildMessage(column));
	}
	
	
}
