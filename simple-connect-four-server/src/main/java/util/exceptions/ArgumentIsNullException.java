package util.exceptions;

public abstract class ArgumentIsNullException extends IllegalArgumentException {
	
	private static final long serialVersionUID = -5832086468581149139L;
	
	static final String IS_NULL = " is null.";
	
	private static String decorateArgument(String argument) {
		return argument + IS_NULL;
	}

	protected ArgumentIsNullException(String className) {
		super(decorateArgument(className));
	}

	protected ArgumentIsNullException(String className, Throwable cause) {
		super(decorateArgument(className), cause);
	}

}