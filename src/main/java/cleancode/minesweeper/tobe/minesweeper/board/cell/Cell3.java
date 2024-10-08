package cleancode.minesweeper.tobe.minesweeper.board.cell;

public interface Cell3 {

	boolean hasLandMineCount();

	CellSnapshot getSnapshot();

	void open();

	void flag();

	boolean isChecked();

	boolean isOpened();

	boolean isLandMine();
}
