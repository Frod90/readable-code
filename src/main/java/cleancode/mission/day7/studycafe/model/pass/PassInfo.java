package cleancode.mission.day7.studycafe.model.pass;

import java.util.Objects;

import cleancode.mission.day7.studycafe.model.StudyCafePassType;

public class PassInfo {

	private final StudyCafePassType passType;
	private final int duration;
	private final int price;

	private PassInfo(StudyCafePassType passType, int duration, int price) {
		this.passType = passType;
		this.duration = duration;
		this.price = price;
	}

	public static PassInfo of(StudyCafePassType passType, int duration, int price) {
		return new PassInfo(passType, duration, price);
	}

	public StudyCafePassType getPassType() {
		return passType;
	}

	public int getDuration() {
		return duration;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PassInfo passInfo = (PassInfo)o;
		return duration == passInfo.duration && price == passInfo.price && passType == passInfo.passType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(passType, duration, price);
	}
}
