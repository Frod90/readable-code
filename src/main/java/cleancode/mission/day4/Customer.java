package cleancode.mission.day4;

public class Customer {

	private final String name;

	public Customer(String name) {
		this.name = name;
	}

	public boolean isNotExist() {
		return name == null || name.isBlank();
	}
}
