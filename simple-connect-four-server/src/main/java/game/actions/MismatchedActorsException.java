package game.actions;

import java.util.HashSet;
import java.util.Set;

import game.Player;

class MismatchedActorsException extends RuntimeException {

	private static final String GIVEN_ACTORS_ARE_MISMATCHED_DISTINCT_ACTORS_FOUND = "Given actors are mismatched. Distinct actors found: ";
	
	private static String generateMessage(Set<Player> distinctActors) {
		return GIVEN_ACTORS_ARE_MISMATCHED_DISTINCT_ACTORS_FOUND + distinctActors;
	}

	public MismatchedActorsException(Set<Player> distinctActors) {
		super(generateMessage(distinctActors));
	}

	public MismatchedActorsException(Set<Player> distinctActors, Throwable cause) {
		super(generateMessage(distinctActors), cause);
	}

}
