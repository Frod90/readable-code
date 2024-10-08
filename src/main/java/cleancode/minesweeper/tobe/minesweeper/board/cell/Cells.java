package cleancode.minesweeper.tobe.minesweeper.board.cell;

import java.util.Arrays;
import java.util.List;

public class Cells {

	private final List<Cell3> cells;

	private Cells(List<Cell3> cells) {
		this.cells = cells;
	}

	public static Cells of(List<Cell3> cells) {
		return new Cells(cells);
	}

	public static Cells from(Cell3[][] cells) {

		List<Cell3> cellList = Arrays.stream(cells)
			.flatMap(Arrays::stream)
			.toList();

		return Cells.of(cellList);

	}

	public boolean isAllChecked() {
		return cells.stream()
			.allMatch(Cell3::isChecked);
	}
}
