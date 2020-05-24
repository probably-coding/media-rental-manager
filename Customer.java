package mediaRentalManager;

import java.util.ArrayList;

/*
 * The Customer class represents a customer in the customer database
 * A customer has a name, an address, and a rental plan
 */

public class Customer implements Comparable {

	protected String name;
	protected String address;
	protected String plan;

	protected ArrayList<String> requests;
	protected ArrayList<String> rented;

	public Customer() {

		this.name = "";
		this.address = "";
		this.plan = "";
		requests = new ArrayList<String>();
		rented = new ArrayList<String>();

	}

	public Customer(String name, String address, String plan) {

		this.name = name;
		this.address = address;
		this.plan = plan;
		requests = new ArrayList<String>();
		rented = new ArrayList<String>();

	}

	public Customer(String name) {

		this.name = name;
		requests = new ArrayList<String>();
		rented = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public String getPlan() {
		return plan;
	}

	public String toString() {

		return "Name: " + name + ", Address: " + address + ", Plan: " +
		plan + "\n" + "Rented: " + rented + "\nQueue: "
				+ requests;

	}

	@Override
	public int compareTo(Object o) {

		Customer temp = (Customer) o;

		return this.getName().compareTo(temp.getName());
	}

}
