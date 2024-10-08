package cleancode.minesweeper.tobe.minesweeper.io.sign;

import java.util.Arrays;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshot;
import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;

public enum CellSignProvider implements CellSignProvidable {

	EMPTY(CellSnapshotStatus.EMPTY) {
		@Override
		public String provide(CellSnapshot cellSnapshot) {
			return EMPTY_SIGN;
		}
	},
	FLAG(CellSnapshotStatus.FLAG) {
		@Override
		public String provide(CellSnapshot cellSnapshot) {
			return FLAG_SIGN;
		}
	},
	LAND_MINE(CellSnapshotStatus.LAND_MINE) {
		@Override
		public String provide(CellSnapshot cellSnapshot) {
			return LAND_MIND_SIGN;
		}
	},
	NUMBER(CellSnapshotStatus.NUMBER) {
		@Override
		public String provide(CellSnapshot cellSnapshot) {
			return String.valueOf(cellSnapshot.getNearbyLandMineCount());
		}
	},
	UNCHECKED(CellSnapshotStatus.UNCHECKED) {
		@Override
		public String provide(CellSnapshot cellSnapshot) {
			return UNCHECKED_SIGN;
		}
	},
	;

	private static final String EMPTY_SIGN = "■";
	private static final String FLAG_SIGN = "⚑";
	private static final String LAND_MIND_SIGN = "☼";
	private static final String UNCHECKED_SIGN = "□";

	private final CellSnapshotStatus status;

	CellSignProvider(CellSnapshotStatus status) {
		this.status = status;
	}

	@Override
	public boolean supports(CellSnapshot cellSnapshot) {
		return cellSnapshot.isSameStatus(status);
	}

	public static String finsCellSignFrom(CellSnapshot snapshot) {
		CellSignProvider cellSignProvider = findBy(snapshot);
		return cellSignProvider.provide(snapshot);
	}

	private static CellSignProvider findBy(CellSnapshot snapshot) {
		return Arrays.stream(values())
			.filter(Provider -> Provider.supports(snapshot))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("확인할 수 없는 셀입니다."));
	}

}
