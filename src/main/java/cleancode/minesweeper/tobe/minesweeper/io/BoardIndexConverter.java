package cleancode.minesweeper.tobe.minesweeper.io;

import cleancode.minesweeper.tobe.minesweeper.exception.GameException;

public class BoardIndexConverter {

	public static final char BASE_CHAR_FOR_COLUMN = 'a';

	public int getSelectedColumnIndex(String cellInput) {
		char cellInputColumn = cellInput.charAt(0);
		return convertColumnFrom(cellInputColumn);
	}

	public int getSelectedRowIndex(String cellInput) {
		String cellInputRow = cellInput.substring(1);
		return convertRowFrom(cellInputRow);
	}

	private int convertColumnFrom(char cellInputColumn) {
		int columnIndex = cellInputColumn - BASE_CHAR_FOR_COLUMN;
		if (columnIndex < 0) {
			throw new GameException("잘못된 입력입니다.");
		}

		return columnIndex;
	}

	private int convertRowFrom(String cellInputRow) {
		int rowIndex = Integer.parseInt(cellInputRow) - 1;
		if (rowIndex < 0) {
			throw new GameException("잘못된 행 입력");
		}

		return rowIndex;
	}

}
