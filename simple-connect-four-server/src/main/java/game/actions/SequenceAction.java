package game.actions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import game.Player;

abstract class SequenceAction extends Action {
	
	private final Vector<Action> sequence;
	
	private Set<Player> findDistinctActors() {
		Set<Player> distinctActors = new HashSet<Player>();
		Iterator<Action> iterator = getSequence().iterator();
		while (iterator.hasNext()) distinctActors.add(iterator.next().getActor());
		return distinctActors;
	}

	public SequenceAction(Action action1, Action action2, Action... actions) {
		super(action1.getActor());
		sequence = new Vector<Action>();
		addActions(action1, action2, actions);
		Set<Player> distinctActors = findDistinctActors();
		if (distinctActors.size() > 1)
			throw new MismatchedActorsException(distinctActors);
	}

	private void addActions(Action action1, Action action2, Action... actions) {
		getSequence().add(action1);
		getSequence().add(action2);
		for (Action action : actions) getSequence().add(action);
	}

	@Override
	public boolean check() {
		Iterator<Action> iterator = getSequence().iterator();
		while (iterator.hasNext()) if (!iterator.next().check()) return false;
		return true;
	}

	@Override
	public void act() {
		Iterator<Action> iterator = getSequence().iterator();
		while (iterator.hasNext()) iterator.next().act();
	}
	
	private Vector<Action> getSequence() {
		return sequence;
	}

}
