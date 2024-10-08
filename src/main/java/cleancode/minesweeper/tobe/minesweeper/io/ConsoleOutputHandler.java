package cleancode.minesweeper.tobe.minesweeper.io;

import java.util.List;
import java.util.stream.IntStream;

import cleancode.minesweeper.tobe.minesweeper.board.GameBoard;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.io.sign.CellSignFinder;
import cleancode.minesweeper.tobe.minesweeper.io.sign.CellSignProvider;

public class ConsoleOutputHandler implements OutputHandler {

	private final CellSignFinder cellSignFinder = new CellSignFinder();

	@Override
	public void showGameStartComments() {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		System.out.println("지뢰찾기 게임 시작!");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}

	@Override
	public void showBoard(GameBoard board) {

		String alphabets = generateColumnAlphabets(board);

		System.out.println("    " + alphabets);
		for (int row = 0; row < board.getRowSize(); row++) {
			System.out.printf("%2d  ", row + 1);
			for (int column = 0; column < board.getColumnSize(); column++) {
				CellPosition cellPosition = CellPosition.of(row, column);

				CellSnapshot snapshot = board.getSnapshot(cellPosition);
				String cellSign = CellSignProvider.finsCellSignFrom(snapshot);

				System.out.print(cellSign + " ");
			}
			System.out.println();
		}
	}

	private String generateColumnAlphabets(GameBoard board) {
		List<String> alphabets = IntStream.range(0, board.getColumnSize())
			.mapToObj(index -> (char)('a' + index))
			.map(Object::toString)
			.toList();

		return String.join(" ", alphabets);
	}

	@Override
	public void showGameWinningComment() {
		System.out.println("지뢰를 모두 찾았습니다. GAME CLEAR!");
	}

	@Override
	public void showGameLosingComment() {
		System.out.println("지뢰를 밟았습니다. GAME OVER!");
	}

	@Override
	public void showCommentForSelectingCell() {
		System.out.println();
		System.out.println("선택할 좌표를 입력하세요. (예: a1)");
	}

	@Override
	public void showCommentForUserAction() {
		System.out.println("선택한 셀에 대한 행위를 선택하세요. (1: 오픈, 2: 깃발 꽂기)");
	}

	@Override
	public void showExceptionMessage(Exception exception) {
		System.out.println(exception.getMessage());
	}

	@Override
	public void showSimpleMessage(String message) {
		System.out.println(message);
	}
}
