package cleancode.mission.day7.studycafe.io;

import java.util.List;

import cleancode.mission.day7.studycafe.model.StudyCafePassType;
import cleancode.mission.day7.studycafe.model.pass.Pass;
import cleancode.mission.day7.studycafe.model.pass.StudyCafeLockerPass;
import cleancode.mission.day7.studycafe.model.pass.StudyCafePass;

public class OutputHandler {

    public void showWelcomeMessage() {
        System.out.println("*** 프리미엄 스터디카페 ***");
    }

    public void showAnnouncement() {
        System.out.println("* 사물함은 고정석 선택 시 이용 가능합니다. (추가 결제)");
        System.out.println("* !오픈 이벤트! 2주권 이상 결제 시 10% 할인, 12주권 결제 시 15% 할인! (결제 시 적용)");
        System.out.println();
    }

    public void askPassTypeSelection() {
        System.out.println("사용하실 이용권을 선택해 주세요.");
        System.out.println("1. 시간 이용권(자유석) | 2. 주단위 이용권(자유석) | 3. 1인 고정석");
    }

    public void showPassListForSelection(List<StudyCafePass> passes) {
        System.out.println();
        System.out.println("이용권 목록");
        for (int index = 0; index < passes.size(); index++) {
            StudyCafePass pass = passes.get(index);
            System.out.println(String.format("%s. ", index + 1) + getPassDisplay(pass));
        }
    }

    public void askLockerPass(StudyCafeLockerPass lockerPass) {
        System.out.println();
        String askMessage = String.format(
            "사물함을 이용하시겠습니까? (%s)",
            getPassDisplay(lockerPass)
        );

        System.out.println(askMessage);
        System.out.println("1. 예 | 2. 아니오");
    }

    public void showPassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
        System.out.println();
        System.out.println("이용 내역");
        System.out.println("이용권: " + getPassDisplay(selectedPass));
        if (lockerPass != null) {
            System.out.println("사물함: " + getPassDisplay(lockerPass));
        }

        double discountRate = selectedPass.getDiscountRate();
        int discountPrice = (int) (selectedPass.getPrice() * discountRate);
        if (discountPrice > 0) {
            System.out.println("이벤트 할인 금액: " + discountPrice + "원");
        }

        int totalPrice = selectedPass.getPrice() - discountPrice + (lockerPass != null ? lockerPass.getPrice() : 0);
        System.out.println("총 결제 금액: " + totalPrice + "원");
        System.out.println();
    }

    public void showSimpleMessage(String message) {
        System.out.println(message);
    }

	 private String getPassDisplay(Pass pass) {
		 StudyCafePassType passType = pass.getPassType();
		 int duration = pass.getDuration();
		 int price = pass.getPrice();

		 if (passType == StudyCafePassType.HOURLY) {
			 return String.format("%s시간권 - %d원", duration, price);
		 }
		 if (passType == StudyCafePassType.WEEKLY) {
			 return String.format("%s주권 - %d원", duration, price);
		 }
		 if (passType == StudyCafePassType.FIXED) {
			 return String.format("%s주권 - %d원", duration, price);
		 }
		 return "";
	 }

}
