package cleancode.mission.day4;

public class OrderService {

	private final LogPrinter logPrinter;

	public OrderService() {
		logPrinter = new LogPrinter(this.getClass());
	}

	public boolean validateOrder(Order order) {
		if (order.hasNoItems()) {
			logPrinter.printInfo("주문 항목이 없습니다.");
			return false;
		}
		if (order.hasInvalidTotalPrice()) {
			logPrinter.printInfo("올바르지 않은 총 가격입니다.");
			return false;
		}
		if (order.hasNoCustomerInfo()) {
			logPrinter.printInfo("사용자 정보가 없습니다.");
			return false;
		}

		return true;
	}
}
