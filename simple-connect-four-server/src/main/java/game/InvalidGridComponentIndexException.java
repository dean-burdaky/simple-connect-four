package game;

public abstract class InvalidGridComponentIndexException extends IllegalArgumentException {

	private static final long serialVersionUID = 6456304488675691376L;
	private static final String IS_INVALID = " is invalid.";

	protected static String buildMessage(String componentName, int componentIndex) {
		return componentName + " " + componentIndex + IS_INVALID;
	}

	public InvalidGridComponentIndexException(String componentName, int componentIndex) {
		super(buildMessage(componentName, componentIndex));
	}

	public InvalidGridComponentIndexException(String componentName, int componentIndex, Throwable cause) {
		super(buildMessage(componentName, componentIndex), cause);
	}

}