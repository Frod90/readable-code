package cleancode.mission.day7.studycafe;

import java.util.List;

import cleancode.mission.day7.studycafe.exception.AppException;
import cleancode.mission.day7.studycafe.io.InputHandler;
import cleancode.mission.day7.studycafe.io.OutputHandler;
import cleancode.mission.day7.studycafe.io.StudyCafeFileHandler;
import cleancode.mission.day7.studycafe.model.StudyCafePassType;
import cleancode.mission.day7.studycafe.model.pass.StudyCafeLockerPass;
import cleancode.mission.day7.studycafe.model.pass.StudyCafePass;

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

			outputHandler.askPassTypeSelection();
			StudyCafePassType studyCafePassType = inputHandler.getPassTypeSelectingUserAction();

			StudyCafePass selectedPass = selectCafePassBy(studyCafePassType);
			StudyCafeLockerPass lockerPass = findLockerPassBy(selectedPass);

			handlePassOrderSummary(selectedPass, lockerPass);

		} catch (AppException e) {
			outputHandler.showSimpleMessage(e.getMessage());
		} catch (Exception e) {
			outputHandler.showSimpleMessage("알 수 없는 오류가 발생했습니다.");
		}
	}

	private void handlePassOrderSummary(StudyCafePass selectedPass, StudyCafeLockerPass lockerPass) {
		if (lockerPass == null) {
			outputHandler.showPassOrderSummary(selectedPass, null);
			return;
		}

		outputHandler.askLockerPass(lockerPass);
		boolean lockerSelection = inputHandler.getLockerSelection();
		outputHandler.showPassOrderSummary(selectedPass, lockerSelection ? lockerPass : null);
	}

	private StudyCafeLockerPass findLockerPassBy(StudyCafePass selectedPass) {
		return studyCafeFileHandler.readLockerPasses().stream()
			.filter(option -> option.getPassType() == selectedPass.getPassType() &&
				option.getDuration() == selectedPass.getDuration())
			.findFirst()
			.orElse(null);
	}

	private StudyCafePass selectCafePassBy(StudyCafePassType studyCafePassType) {
		List<StudyCafePass> studyCafePasses = studyCafeFileHandler.readStudyCafePasses().stream()
			.filter(studyCafePass -> studyCafePass.getPassType() == studyCafePassType)
			.toList();

		outputHandler.showPassListForSelection(studyCafePasses);
		return inputHandler.getSelectPass(studyCafePasses);
	}
}
