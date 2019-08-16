package game;

public final class InvalidColumnException extends InvalidGridComponentIndexException {

	private static final long serialVersionUID = -9014176531095229711L;
	static final String COLUMN_POSITION = "Column position";
	
	public InvalidColumnException(int column, Throwable cause) {
		super(COLUMN_POSITION, column, cause);
	}

	public InvalidColumnException(int column) {
		super(COLUMN_POSITION, column);
	}
	
}
