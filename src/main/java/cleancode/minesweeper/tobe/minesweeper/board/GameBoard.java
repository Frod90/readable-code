package cleancode.minesweeper.tobe.minesweeper.board;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell3;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.Cells;
import cleancode.minesweeper.tobe.minesweeper.board.cell.EmptyCell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.LandMineCell;
import cleancode.minesweeper.tobe.minesweeper.board.cell.NumberCell;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPositions;
import cleancode.minesweeper.tobe.minesweeper.board.position.RelativePosition;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.GameLevel;

public class GameBoard {

	private final int landMineCount;
	private final Cell3[][] board;
	private GameStatus gameStatus;

	public GameBoard(GameLevel gameLevel) {
		int rowSize = gameLevel.getRowSize();
		int columnSize = gameLevel.getColumnSize();
		board = new Cell3[rowSize][columnSize];

		landMineCount = gameLevel.getLandMineCount();
		initializeGameStatus();
	}

	public void initializeGame() {
		initializeGameStatus();
		CellPositions cellPositions = CellPositions.from(board);

		initializeEmptyCells(cellPositions);

		List<CellPosition> landMinePositions = cellPositions.extractRandomPositions(landMineCount);
		initializeLandMineCells(landMinePositions);

		List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);
		initializeNumberCells(numberPositionCandidates);
	}

	public void openAt(CellPosition cellPosition) {
		if (isLandMindCellAt(cellPosition)) {
			openOneCellAt(cellPosition);
			changeGameStatusToLose();
			return;
		}

		openSurroundCells(cellPosition);
		checkIfGamsIsOver();
	}

	public void flagAt(CellPosition cellPosition) {
		Cell3 cell = findCell(cellPosition);
		cell.flag();

		checkIfGamsIsOver();
	}

	public boolean isInvalidCellPosition(CellPosition cellPosition) {
		int rowSize = getRowSize();
		int columnSize = getColumnSize();

		return cellPosition.isRowIndexMoreThanOrEqual(rowSize)
			|| cellPosition.isColumnIndexMoreThanOrEqual(columnSize);
	}

	public boolean isInProgress() {
		return gameStatus == GameStatus.IN_PROGRESS;
	}

	public boolean isWinStatus() {
		return gameStatus == GameStatus.WIN;
	}

	public boolean isLoseStatus() {
		return gameStatus == GameStatus.LOSE;
	}

	public int getRowSize() {
		return board.length;
	}

	public int getColumnSize() {
		return board[0].length;
	}

	public CellSnapshot getSnapshot(CellPosition cellPosition) {
		Cell3 cell = findCell(cellPosition);
		return cell.getSnapshot();
	}

	private void initializeGameStatus() {
		gameStatus = GameStatus.IN_PROGRESS;
	}

	private void initializeEmptyCells(CellPositions cellPositions) {
		List<CellPosition> allPositions = cellPositions.getPositions();
		for (CellPosition position : allPositions) {
			updateCellAt(position, new EmptyCell());
		}
	}

	private void initializeLandMineCells(List<CellPosition> landMinePositions) {
		for (CellPosition position : landMinePositions) {
			updateCellAt(position, new LandMineCell());
		}
	}

	private void initializeNumberCells(List<CellPosition> numberPositionCandidates) {
		for (CellPosition numberPositionCandidate : numberPositionCandidates) {
			int count = countNearbyLandMines(numberPositionCandidate);
			if (count != 0) {
				updateCellAt(numberPositionCandidate, new NumberCell(count));
			}
		}
	}

	private int countNearbyLandMines(CellPosition cellPosition) {
		int rowSize = getRowSize();
		int columnSize = getColumnSize();

		long count = calculateSurroundedPosition(cellPosition, rowSize, columnSize).stream()
			.filter(this::isLandMindCellAt)
			.count();

		return (int)count;
	}

	private List<CellPosition> calculateSurroundedPosition(CellPosition cellPosition, int rowSize, int columnSize) {
		return RelativePosition.SURROUNDED_POSITION.stream()
			.filter(cellPosition::canCalculatePositionBy)
			.map(cellPosition::calculatePositionBy)
			.filter(position -> position.isRowIndexLessThan(rowSize))
			.filter(position -> position.isColumnIndexLessThan(columnSize))
			.toList();
	}

	private void updateCellAt(CellPosition cellPosition, Cell3 cell) {
		board[cellPosition.getRowIndex()][cellPosition.getColumnIndex()] = cell;
	}

	private void openSurroundCells(CellPosition cellPosition) {
		Deque<CellPosition> deque = new ArrayDeque<>();
		deque.push(cellPosition);

		while (!deque.isEmpty()) {
			openAndPushCellAt(deque);
		}
	}

	private void openAndPushCellAt(Deque<CellPosition> deque) {
		CellPosition currentCellPosition = deque.pop();

		if (isOpenedCell(currentCellPosition)) {
			return;
		}
		if (isLandMindCellAt(currentCellPosition)) {
			return;
		}

		openOneCellAt(currentCellPosition);

		if (doesCellHaveLandMineCount(currentCellPosition)) {
			return;
		}

		List<CellPosition> surroundCellPositions = calculateSurroundedPosition(currentCellPosition, getRowSize(),
			getColumnSize());
		for (CellPosition surroundCellPosition : surroundCellPositions) {
			deque.push(surroundCellPosition);
		}
	}

	private void openOneCellAt(CellPosition cellPosition) {
		findCell(cellPosition).open();
	}

	private boolean isOpenedCell(CellPosition cellPosition) {
		return findCell(cellPosition).isOpened();
	}

	private boolean isLandMindCellAt(CellPosition cellPosition) {
		return findCell(cellPosition).isLandMine();
	}

	private boolean doesCellHaveLandMineCount(CellPosition cellPosition) {
		return findCell(cellPosition).hasLandMineCount();
	}

	private void checkIfGamsIsOver() {
		if (isAllCellChecked()) {
			changeGameStatusToWin();
		}
	}

	private boolean isAllCellChecked() {
		Cells cells = Cells.from(board);
		return cells.isAllChecked();
	}

	private void changeGameStatusToWin() {
		gameStatus = GameStatus.WIN;
	}

	private void changeGameStatusToLose() {
		gameStatus = GameStatus.LOSE;
	}

	private Cell3 findCell(CellPosition cellPosition) {
		return board[cellPosition.getRowIndex()][cellPosition.getColumnIndex()];
	}
}
