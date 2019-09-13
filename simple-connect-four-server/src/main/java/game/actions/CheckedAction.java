package game.actions;

import game.Player;

public final class CheckedAction extends Action {
	
	private final Action nestedAction;
	
	public CheckedAction(Action nestedAction) {
		super(nestedAction.getActor());
		this.nestedAction = nestedAction;
	}
	
	@Override
	public final boolean check() {
		return getNestedAction().check();
	}

	@Override
	public final void act() {
		if (!check()) throw new ActionCheckFailedException();
		getNestedAction().act();
	}
	
	private Action getNestedAction() { return nestedAction; }

}
