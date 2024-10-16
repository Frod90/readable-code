package cleancode.studycafe.tobe.model.order;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cleancode.studycafe.tobe.model.pass.StudyCafePassType;
import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;
import cleancode.studycafe.tobe.model.pass.locker.StudyCafeLockerPass;

class StudyCafePassOrderTest {

	@DisplayName("총 금액을 계산할 수 있다")
	@Test
	void getTotalPrice() {

		// given
		StudyCafeSeatPass studyCafeSeatPass = StudyCafeSeatPass.of(StudyCafePassType.WEEKLY, 6, 60_000, 0.1);
		StudyCafeLockerPass studyCafeLockerPass = StudyCafeLockerPass.of(StudyCafePassType.WEEKLY, 6, 30_000);
		StudyCafePassOrder passOrder = StudyCafePassOrder.of(studyCafeSeatPass, studyCafeLockerPass);

		// when
		int totalPrice = passOrder.getTotalPrice();

		// then
		assertEquals(84_000, totalPrice);

	}
}
