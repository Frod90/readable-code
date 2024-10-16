package cleancode.studycafe.tobe.model.pass;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

class StudyCafeSeatPassTest {

	private static StudyCafeSeatPass hourlySeatPass;
	private static StudyCafeSeatPass weeklySeatPass;
	private static StudyCafeSeatPass fixedSeatPass;

	@BeforeAll
	static void setUp() {
		hourlySeatPass = cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass.of(StudyCafePassType.HOURLY, 10, 10_000,
			0);
		weeklySeatPass = cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 2, 100_000,
			0.1);
		fixedSeatPass = cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass.of(StudyCafePassType.FIXED, 4, 200_000,
			0.2);
	}

	@DisplayName("같은 패스 타입인지 확인할 수 있다")
	@Test
	void isSamePassTypeInSamePassType() {

		// when, then
		assertTrue(hourlySeatPass.isSamePassType(StudyCafePassType.HOURLY));
	}

	@DisplayName("다른 패스 타입인지 확인할 수 있다")
	@Test
	void isSamePassTypeInDifferentPassType() {

		// when, then
		assertFalse(hourlySeatPass.isSamePassType(StudyCafePassType.FIXED));
	}

	@DisplayName("락커 패스의 패스 타입과 기간이 둘 다 같은 경우만 같은 기간으로 판단한다")
	@Test
	void isSameDurationTypeInSameDuration() {

		//given
		StudyCafeLockerPass weeklyLockerPass = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 2, 10_000);

		// when, then
		assertTrue(weeklySeatPass.isSameDurationType(weeklyLockerPass));
	}

	@DisplayName("락커 패스의 패스 타입이 다르면, 같은 기간이라도 다른 기간이라고 판단한다")
	@Test
	void isSameDurationTypeInDifferentPassType() {

		// given
		StudyCafeLockerPass fixedLockerPass = StudyCafeLockerPass.of(StudyCafePassType.FIXED, 2, 10_000);

		// when, then
		assertFalse(weeklySeatPass.isSameDurationType(fixedLockerPass));
	}

	@DisplayName("락커 패스의 기간이 다르면, 같은 패스 타입이여도 다른 기간이라고 판단한다")
	@Test
	void isSameDurationTypeInDifferentDuration() {

		//given
		StudyCafeLockerPass weeklyLockerPass = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 10, 10_000);

		// when, then
		assertFalse(weeklySeatPass.isSameDurationType(weeklyLockerPass));
	}

	@DisplayName("할인 금액을 계산할 수 있다")
	@Test
	void getDiscountPrice() {

		// when, then
		assertEquals(40_000, fixedSeatPass.getDiscountPrice());
	}
}
