package cleancode.studycafe.tobe.model.pass.locker;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

class StudyCafeLockerPassesTest {

	@DisplayName("입력한 스터디 카페 패스에 해당하는 락커 패스를 조회할 수 있다")
	@Test
	void findLockerPassBy() {

		// given
		StudyCafeLockerPass hourlyPass01 = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 1, 10_000);
		StudyCafeLockerPass hourlyPass02 = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 2, 20_000);
		StudyCafeLockerPass hourlyPass03 = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 3, 30_000);
		StudyCafeLockerPass weeklyPass01 = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 1, 10_000);
		StudyCafeLockerPass weeklyPass02 = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 2, 20_000);
		StudyCafeLockerPass weeklyPass03 = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 3, 30_000);
		StudyCafeLockerPass fixedPass01 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 1, 10_000);
		StudyCafeLockerPass fixedPass02 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 20_000);
		StudyCafeLockerPass fixedPass03 = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 30_000);

		List<StudyCafeLockerPass> passes = List.of(
			hourlyPass01,
			hourlyPass02,
			hourlyPass03,
			weeklyPass01,
			weeklyPass02,
			weeklyPass03,
			fixedPass01,
			fixedPass02,
			fixedPass03
		);
		StudyCafeLockerPasses studyCafeLockerPasses = StudyCafeLockerPasses.of(passes);

		StudyCafeSeatPass seatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 20_000, 0.2);

		// when
		Optional<StudyCafeLockerPass> optionalPass = studyCafeLockerPasses.findLockerPassBy(seatPass);
		StudyCafeLockerPass findPass = optionalPass.get();

		// then
		assertThat(findPass).isSameAs(weeklyPass02);
	}
}
