package util.exceptions;

public abstract class NumberIsLessThanLowerBoundException extends IllegalArgumentException {

	private static final long serialVersionUID = 8807934994530146401L;
	
	public static final String IS_LESS_THAN = " is less than ";
	
	private static final String decorateLabels(String numberLabel, String lowerBoundLabel) {
		return numberLabel + IS_LESS_THAN + lowerBoundLabel + ".";
	}

	protected NumberIsLessThanLowerBoundException(String numberLabel, String lowerBoundLabel) {
		super(decorateLabels(numberLabel, lowerBoundLabel));
	}

	protected NumberIsLessThanLowerBoundException(String numberLabel, String lowerBoundLabel, Throwable cause) {
		super(decorateLabels(numberLabel, lowerBoundLabel), cause);
	}

}