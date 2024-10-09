package cleancode.mission.day7.studycafe.model.pass;

import cleancode.mission.day7.studycafe.model.StudyCafePassType;

public final class StudyCafePass implements Pass {

	private final PassInfo passInfo;
	private final double discountRate;

	private StudyCafePass(PassInfo passInfo, double discountRate) {
		this.passInfo = passInfo;
		this.discountRate = discountRate;
	}

	public static StudyCafePass of(PassInfo passInfo, double discountRate) {
		return new StudyCafePass(passInfo, discountRate);
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
		return discountRate > 0;
	}

	public double getDiscountRate() {
		return discountRate;
	}

}
