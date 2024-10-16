package cleancode.studycafe.tobe.model.pass;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudyCafeSeatPassesTest {

	@DisplayName("입력한 패스 타입에 해당하는 스터디 카페 패스 목록을 조회할 수 있다")
	@Test
	void findPassBy() {

		// given
		StudyCafeSeatPass hourlySeatPass01 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 1, 10_000, 0);
		StudyCafeSeatPass hourlySeatPass02 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 2, 20_000, 0);
		StudyCafeSeatPass hourlySeatPass03 = StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 3, 30_000, 0);
		StudyCafeSeatPass weeklySeatPass01 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 1, 10_000, 0.1);
		StudyCafeSeatPass weeklySeatPass02 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 20_000, 0.2);
		StudyCafeSeatPass weeklySeatPass03 = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 3, 30_000, 0.3);
		StudyCafeSeatPass fixedSeatPass01 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 1, 10_000, 0.1);
		StudyCafeSeatPass fixedSeatPass02 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 2, 20_000, 0.2);
		StudyCafeSeatPass fixedSeatPass03 = StudyCafeSeatPass.of(StudyCafePassType.FIXED, 3, 30_000, 0.3);

		List<StudyCafeSeatPass> passes = List.of(
			hourlySeatPass01,
			hourlySeatPass02,
			hourlySeatPass03,
			weeklySeatPass01,
			weeklySeatPass02,
			weeklySeatPass03,
			fixedSeatPass01,
			fixedSeatPass02,
			fixedSeatPass03
		);
		StudyCafeSeatPasses studyCafeSeatPasses = StudyCafeSeatPasses.of(passes);

		// when
		List<StudyCafeSeatPass> findPasses = studyCafeSeatPasses.findPassBy(StudyCafePassType.HOURLY);

		// then
		assertThat(findPasses).hasSize(3)
			.contains(hourlySeatPass01)
			.contains(hourlySeatPass02)
			.contains(hourlySeatPass03)
			.doesNotContain(weeklySeatPass01)
			.doesNotContain(weeklySeatPass02)
			.doesNotContain(weeklySeatPass03)
			.doesNotContain(fixedSeatPass01)
			.doesNotContain(fixedSeatPass02)
			.doesNotContain(fixedSeatPass03);
	}
}
