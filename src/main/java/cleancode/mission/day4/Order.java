package cleancode.mission.day4;

import java.util.List;

public class Order {

	private final List<Item> items;
	private final Customer customer;

	public Order(List<Item> items, Customer customer) {
		this.items = items;
		this.customer = customer;
	}

	public boolean hasNoItems() {
		return items == null || items.isEmpty();
	}

	public boolean hasInvalidTotalPrice() {
		return calculateTotalPrice() <= 0;
	}

	public long calculateTotalPrice() {
		return items.stream().mapToLong(Item::getPrice)
			.sum();
	}

	public boolean hasNoCustomerInfo() {
		return customer == null || customer.isNotExist();
	}

}
