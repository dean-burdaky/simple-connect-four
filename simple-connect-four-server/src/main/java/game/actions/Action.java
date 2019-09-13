package game.actions;

import game.Player;

abstract class Action {
	
	private final Player actor;
	
	protected Action(Player actor) { this.actor = actor; }
	
	public abstract boolean check();
	public abstract void act();
	
	protected final Player getActor() { return actor; }
}
