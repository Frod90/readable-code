package cleancode.studycafe.tobe.model.pass.locker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;

class StudyCafeLockerPassTest {

	private static StudyCafeLockerPass hourlyPass;
	private static StudyCafeLockerPass weeklyPass;
	private static StudyCafeLockerPass fixedPass;

	@BeforeAll
	static void setUp() {
		hourlyPass = StudyCafeLockerPass.of(StudyCafePassType.HOURLY, 10, 10_000);
		weeklyPass = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 2, 10_000);
		fixedPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 3, 10_000);
	}

	@DisplayName("같은 패스 타입인지 확인할 수 있다")
	@Test
	void isSamePassTypeInSameType() {

		// when, then
		assertTrue(fixedPass.isSamePassType(StudyCafePassType.FIXED));
	}

	@DisplayName("다른 패스 타입인지 확인할 수 있다")
	@Test
	void isSamePassTypeInDifferentType() {

		// when, then
		assertFalse(weeklyPass.isSamePassType(StudyCafePassType.HOURLY));
	}

	@DisplayName("같은 기간인지 확인할 수 있다")
	@Test
	void isSameDurationInSameDuration() {

		// when, then
		assertTrue(hourlyPass.isSameDuration(10));
	}

	@DisplayName("다른 기간인지 확인할 수 있다")
	@Test
	void isSameDurationInDifferentDuration() {

		// when, then
		assertFalse(weeklyPass.isSameDuration(10));
	}
}
