package cleancode.mission.day7.studycafe;

import cleancode.mission.day7.studycafe.io.InputHandler;
import cleancode.mission.day7.studycafe.io.OutputHandler;
import cleancode.mission.day7.studycafe.io.StudyCafeFileHandler;

public class StudyCafeApplication {

    public static void main(String[] args) {
        StudyCafePassMachine studyCafePassMachine = new StudyCafePassMachine(
			  new InputHandler(),
			  new OutputHandler(),
			  new StudyCafeFileHandler()
		  );
        studyCafePassMachine.run();
    }

}
