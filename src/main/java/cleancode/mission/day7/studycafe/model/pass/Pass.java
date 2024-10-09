package cleancode.mission.day7.studycafe.model.pass;

import cleancode.mission.day7.studycafe.model.StudyCafePassType;

public interface Pass {

	StudyCafePassType getPassType();

	int getDuration();

	int getPrice();

	boolean hasDiscountBenefit();
}
