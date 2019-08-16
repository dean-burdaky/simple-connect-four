package game;

public class GridFactory {

	public Grid create(int rowCount, int columnCount) {
		return new Grid(rowCount, columnCount);
	}

}
