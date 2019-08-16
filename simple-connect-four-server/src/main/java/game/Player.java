package game;

public class Player {
	
	private final Game game;
	
	private boolean won = false;
	private boolean forfeited = false;
	private int countersPlayed = 0;
	
	Player (Game game) {
		if (game == null)
			throw new GameIsNullException();
		this.game = game;
	}
	
	public void play(int column) {
		checkPlayPrerequisites(column);
		takeCounter();
		Grid grid = getGame().getGrid();
		int row = grid.dropCounter(this, column);
		if (grid.formsWinningLine(getGame().getCounterLineLengthToWin(), this, row, column))
			getGame().win(this);
		else
			getGame().nextTurn();
	}

	private void checkPlayPrerequisites(int col) {
		if (!getGame().isParticipant(this))
			throw new PlayerIsNotParticipantException();
		else if (!getGame().hasTurn(this)) {
			if (getGame().isPlayerOne(this))
				throw new PlayerDoesNotHaveTurnException(1);
			else
				throw new PlayerDoesNotHaveTurnException(2);
		}
		else if (!hasCounter())
			throw new ZeroCountersException();
		else if (!getGame().getGrid().isValidColumn(col))
			throw new InvalidColumnException(col);
		else if (!getGame().getGrid().hasFreeSlotIn(col))
			throw new NoFreeSlotException(col);
	}
	
	public void forfeit() {}
	
	private void setCountersPlayed(int countersPlayed) { this.countersPlayed = countersPlayed; }
	int getCountersPlayed() { return countersPlayed; }
	boolean hasCounter() { return getCountersPlayed() > 0; }

	public void takeCounter() {}
	
	private final Game getGame() { return game; }

	void setWon(boolean won) { this.won = won; }
	boolean getWon() { return won; }
}
