package cleancode.minesweeper.tobe.minesweeper.board.cell;

public abstract class Cell2 {

	protected static final String FLAG_SIGN = "⚑";
	protected static final String UNCHECKED_SIGN = "□";

	protected boolean isFlagged;
	protected boolean isOpened;

	public abstract boolean hasLandMineCount();

	public abstract String getSign();

	public abstract boolean isLandMine();

	public void open() {
		this.isOpened = true;
	}

	public void flag() {
		this.isFlagged = true;
	}

	public boolean isChecked() {
		return isFlagged || isOpened;
	}

	public boolean isOpened() {
		return isOpened;
	}
}
