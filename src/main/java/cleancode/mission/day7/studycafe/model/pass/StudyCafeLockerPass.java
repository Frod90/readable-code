package cleancode.mission.day7.studycafe.model.pass;

import cleancode.mission.day7.studycafe.model.StudyCafePassType;

public class StudyCafeLockerPass implements Pass {

	private final PassInfo passInfo;

	private StudyCafeLockerPass(PassInfo passInfo) {
		this.passInfo = passInfo;
	}

	public static StudyCafeLockerPass of(PassInfo passInfo) {
		return new StudyCafeLockerPass(passInfo);
	}

	@Override
	public StudyCafePassType getPassType() {
		return passInfo.getPassType();
	}

	@Override
	public int getDuration() {
		return passInfo.getDuration();
	}

	@Override
	public int getPrice() {
		return passInfo.getPrice();
	}

	@Override
	public boolean hasDiscountBenefit() {
		return false;
	}

}
