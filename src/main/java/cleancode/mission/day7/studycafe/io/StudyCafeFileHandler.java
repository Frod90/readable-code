package cleancode.mission.day7.studycafe.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import cleancode.mission.day7.studycafe.model.StudyCafePassType;
import cleancode.mission.day7.studycafe.model.pass.PassInfo;
import cleancode.mission.day7.studycafe.model.pass.StudyCafeLockerPass;
import cleancode.mission.day7.studycafe.model.pass.StudyCafePass;

public class StudyCafeFileHandler {

	public List<StudyCafePass> readStudyCafePasses() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/pass-list.csv"));
			List<StudyCafePass> studyCafePasses = new ArrayList<>();
			for (String line : lines) {
				String[] values = line.split(",");
				StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
				int duration = Integer.parseInt(values[1]);
				int price = Integer.parseInt(values[2]);
				double discountRate = Double.parseDouble(values[3]);

				PassInfo passInfo = PassInfo.of(studyCafePassType, duration, price);
				StudyCafePass studyCafePass = StudyCafePass.of(passInfo, discountRate);
				studyCafePasses.add(studyCafePass);
			}

			return studyCafePasses;
		} catch (IOException e) {
			throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
		}
	}

	public List<StudyCafeLockerPass> readLockerPasses() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("src/main/resources/cleancode/studycafe/locker.csv"));
			List<StudyCafeLockerPass> lockerPasses = new ArrayList<>();
			for (String line : lines) {
				String[] values = line.split(",");
				StudyCafePassType studyCafePassType = StudyCafePassType.valueOf(values[0]);
				int duration = Integer.parseInt(values[1]);
				int price = Integer.parseInt(values[2]);

				PassInfo passInfo = PassInfo.of(studyCafePassType, duration, price);
				StudyCafeLockerPass lockerPass = StudyCafeLockerPass.of(passInfo);
				lockerPasses.add(lockerPass);
			}

			return lockerPasses;
		} catch (IOException e) {
			throw new RuntimeException("파일을 읽는데 실패했습니다.", e);
		}
	}

}
