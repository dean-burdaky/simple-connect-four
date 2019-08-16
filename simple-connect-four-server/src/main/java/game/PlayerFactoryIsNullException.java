package game;

import util.exceptions.FactoryIsNullException;

public final class PlayerFactoryIsNullException extends FactoryIsNullException {

	private static final long serialVersionUID = 2562374706303929610L;
	static final String FACTORY_PREFIX = "Player";

	PlayerFactoryIsNullException(Throwable cause) {
		super(FACTORY_PREFIX, cause);
	}

	PlayerFactoryIsNullException() {
		super(FACTORY_PREFIX);
	}

}
