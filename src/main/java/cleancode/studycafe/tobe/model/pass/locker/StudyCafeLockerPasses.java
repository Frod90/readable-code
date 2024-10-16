package cleancode.studycafe.tobe.model.pass.locker;

import java.util.List;
import java.util.Optional;

import cleancode.studycafe.tobe.model.pass.StudyCafeSeatPass;

public class StudyCafeLockerPasses {

	private final List<StudyCafeLockerPass> lockerPasses;

	private StudyCafeLockerPasses(List<StudyCafeLockerPass> passes) {
		this.lockerPasses = passes;
	}

	public static StudyCafeLockerPasses of(List<StudyCafeLockerPass> passes) {
		return new StudyCafeLockerPasses(passes);
	}

	public Optional<StudyCafeLockerPass> findLockerPassBy(StudyCafeSeatPass pass) {
		return lockerPasses.stream()
			.filter(pass::isSameDurationType)
			.findFirst();
	}
}
