package cleancode.mission.day7.studycafe;

import java.util.List;

import cleancode.mission.day7.studycafe.exception.AppException;
import cleancode.mission.day7.studycafe.io.InputHandler;
import cleancode.mission.day7.studycafe.io.OutputHandler;
import cleancode.mission.day7.studycafe.io.StudyCafeFileHandler;
import cleancode.mission.day7.studycafe.model.StudyCafeLockerPass;
import cleancode.mission.day7.studycafe.model.StudyCafePass;
import cleancode.mission.day7.studycafe.model.StudyCafePassType;

public class StudyCafePassMachine {

	private final InputHandler inputHandler;
	private final OutputHandler outputHandler;
	private final StudyCafeFileHandler studyCafeFileHandler;

	public StudyCafePassMachine(InputHandler inputHandler, OutputHandler outputHandler,
		StudyCafeFileHandler studyCafeFileHandler) {
		this.inputHandler = inputHandler;
		this.outputHandler = outputHandler;
		this.studyCafeFileHandler = studyCafeFileHandler;
	}

	public void run() {
		try {
			outputHandler.showWelcomeMessage();
			outputHandler.showAnnouncement();

			StudyCafePassType studyCafePassType = selectStudyCafePassType();
			StudyCafePass selectedPass = selectStudyCafePass(studyCafePassType);

			if (studyCafePassType.isNotSameAs(StudyCafePassType.FIXED)) {
				outputHandler.showPassOrderSummary(selectedPass, null);
				return;
			}

			StudyCafeLockerPass lockerPass = findLockerPass(selectedPass);
			boolean lockerSelection = doesSelected(lockerPass);
			if (lockerSelection) {
				outputHandler.showPassOrderSummary(selectedPass, lockerPass);
				return;
			}

			outputHandler.showPassOrderSummary(selectedPass, null);

		} catch (AppException e) {
			outputHandler.showSimpleMessage(e.getMessage());
		} catch (Exception e) {
			outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
		}
	}

	private boolean doesSelected(StudyCafeLockerPass lockerPass) {
		outputHandler.askLockerPass(lockerPass);
		return inputHandler.getLockerSelection();
	}

	private StudyCafeLockerPass findLockerPass(StudyCafePass selectedPass) {
		List<StudyCafeLockerPass> lockerPasses = studyCafeFileHandler.readLockerPasses();
		return lockerPasses.stream()
			.filter(option ->
				option.getPassType() == selectedPass.getPassType()
					&& option.getDuration() == selectedPass.getDuration()
			)
			.findFirst()
			.orElseThrow(() -> new AppException("락커를 찾을 수 없습니다."));
	}

	private StudyCafePassType selectStudyCafePassType() {
		outputHandler.askPassTypeSelection();
		return inputHandler.getPassTypeSelectingUserAction();
	}

	private StudyCafePass selectStudyCafePass(StudyCafePassType studyCafePassType) {
		List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses();
		List<StudyCafePass> selectedCafePasses = studyCafePasses.stream()
			.filter(studyCafePass -> studyCafePass.hasPassTypeis(studyCafePassType))
			.toList();
		outputHandler.showPassListForSelection(selectedCafePasses);
		return inputHandler.getSelectPass(selectedCafePasses);
	}

}
