package game;

public final class InvalidRowException extends InvalidGridComponentIndexException {

	private static final long serialVersionUID = -987716539857655904L;
	static final String ROW_POSITION = "Row position";

	public InvalidRowException(int row) {
		super(ROW_POSITION, row);
	}
	
	public InvalidRowException(int row, Throwable cause) {
		super(ROW_POSITION, row, cause);
	}

}
