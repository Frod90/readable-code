package cleancode.minesweeper.tobe.minesweeper.board.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell3;

public class CellPositions {

	private final List<CellPosition> positions;

	private CellPositions(List<CellPosition> positions) {
		this.positions = positions;
	}

	public static CellPositions of(List<CellPosition> positions) {
		return new CellPositions(positions);
	}

	public static CellPositions from(Cell3[][] board) {

		List<CellPosition> cellPositions = new ArrayList<>();

		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board[0].length; column++) {
				cellPositions.add(CellPosition.of(row, column));
			}
		}

		return of(cellPositions);
	}

	public List<CellPosition> getPositions() {
		return new ArrayList<>(positions);
	}

	public List<CellPosition> extractRandomPositions(int count) {

		List<CellPosition> cellPositions = new ArrayList<>(positions);

		Collections.shuffle(cellPositions);
		return cellPositions.subList(0, count);
	}

	public List<CellPosition> subtract(List<CellPosition> positionListToSubtract) {
		List<CellPosition> cellPositions = new ArrayList<>(positions);
		CellPositions positionsToSubtract = CellPositions.of(positionListToSubtract);

		return cellPositions.stream()
			.filter(positionsToSubtract::doesNotContain)
			.toList();
	}

	private boolean doesNotContain(CellPosition position) {
		return !positions.contains(position);
	}
}
