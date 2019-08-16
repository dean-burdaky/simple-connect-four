package util.exceptions;

public abstract class FactoryIsNullException extends ArgumentIsNullException {
	
	private static final String FACTORY_SUFFIX = "Factory";
	public static final String FACTORY_IS_NULL = FACTORY_SUFFIX + IS_NULL; 

	private static String decorateFactoryPrefix(String factoryPrefix) {
		return factoryPrefix + FACTORY_SUFFIX;
	}
	
	protected FactoryIsNullException(String factoryPrefix, Throwable cause) {
		super(decorateFactoryPrefix(factoryPrefix), cause);
	}

	protected FactoryIsNullException(String factoryPrefix) {
		super(decorateFactoryPrefix(factoryPrefix));
	}

	private static final long serialVersionUID = 2490095179728838628L;
	
	
}
