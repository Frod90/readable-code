package cleancode.mission.day4;

import java.util.logging.Logger;

public class LogPrinter {

	private final Logger log;

	public LogPrinter(Class<?> clazz) {
		this.log = Logger.getLogger(clazz.getName());
	}

	public void printInfo(String message) {
		log.info(message);
	}
}
