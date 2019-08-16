package game;

import util.exceptions.FactoryIsNullException;

public final class GridFactoryIsNullException extends FactoryIsNullException {

	private static final long serialVersionUID = -8961978465849636824L;
	static final String FACTORY_PREFIX = "Grid";

	GridFactoryIsNullException(Throwable cause) {
		super(FACTORY_PREFIX, cause);
	}

	GridFactoryIsNullException() {
		super(FACTORY_PREFIX);
	}

}
