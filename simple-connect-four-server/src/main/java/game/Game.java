package game;

public class Game {
	
	public static final int MINIMUM_COUNTER_LINE_LENGTH_TO_WIN = 2;
	public static final int DEFAULT_COUNTER_LINE_LENGTH_TO_WIN = 4;
	private final Player playerOne, playerTwo;
	private final Grid grid;
	private final int counterLineLengthToWin;
	private final int counterCount;
	
	private Player currentPlayer = null;
	private boolean started, ended = false;
	
	Game(PlayerFactory playerFactory, GridFactory gridFactory, int counterLineLengthToWin, int rowCount, int columnCount) {
		validateGameArgs(playerFactory, gridFactory, counterLineLengthToWin, rowCount, columnCount);
		
		this.playerOne = playerFactory.create(this);
		this.playerTwo = playerFactory.create(this);
		this.grid = gridFactory.create(rowCount, columnCount);
		this.counterLineLengthToWin = counterLineLengthToWin;
		this.counterCount = rowCount * columnCount;
	}

	private void validateGameArgs(PlayerFactory playerFactory, GridFactory gridFactory, int counterLineLengthToWin,
			int rowCount, int columnCount) {
		if (playerFactory == null) {
			throw new PlayerFactoryIsNullException();
		}
		else if (gridFactory == null) {
			throw new GridFactoryIsNullException();
		}
		else if (counterLineLengthToWin < MINIMUM_COUNTER_LINE_LENGTH_TO_WIN) {
			throw new CounterLineLengthToWinTooSmallException();
		}
		else if (rowCount < counterLineLengthToWin) {
			throw new RowCountTooSmallException();
		}
		else if (columnCount < counterLineLengthToWin) {
			throw new ColumnCountTooSmallException();
		}
	}
	
	void start() {
		setCurrentPlayer(getPlayerOne());
		setStarted(true);
	}
	
	void end() {
		if (!getStarted())
			throw new GameNotStartedYetException();
		
		setCurrentPlayer(null);
		setEnded(true);
	}
	
	boolean hasTurn(Player player) { return player == getCurrentPlayer(); }
	
	void nextTurn() {
		if (getCurrentPlayer() == getPlayerOne())
			setPlayerTwoAsCurrent();
		else
			setPlayerOneAsCurrent();
	}
	
	boolean hasAllCountersBeenPlayed() {
		return getPlayerOne().getCountersPlayed() + getPlayerTwo().getCountersPlayed() == getCounterCount();
	}
	
	boolean isPlayerOne(Player player) { return player == getPlayerOne(); }
	boolean isPlayerTwo(Player player) { return player == getPlayerTwo(); }
	boolean isParticipant(Player player) { return isPlayerOne(player) || isPlayerTwo(player); }
	
	Player getPlayerOne() { return playerOne; }
	
	Player getPlayerTwo() { return playerTwo; }
	
	Grid getGrid() { return grid; }
	
	int getCounterLineLengthToWin() { return counterLineLengthToWin; }
	
	int getCounterCount() { return counterCount; }
	
	void setPlayerOneAsCurrent() { setCurrentPlayer(getPlayerOne()); }
	void setPlayerTwoAsCurrent() { setCurrentPlayer(getPlayerTwo()); }
	
	private void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }
	Player getCurrentPlayer() { return currentPlayer; }
	
	private void setStarted(boolean started) { this.started = started; }
	boolean getStarted() { return started; }
	
	private void setEnded(boolean ended) { this.ended = ended; }
	boolean getEnded() { return ended; }

	public void win(Player player) {
		player.setWon(true);
		end();
	}
}
