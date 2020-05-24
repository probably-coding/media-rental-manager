package mediaRentalManager;

import java.util.*;

public class MediaRentalManager implements MediaRentalManagerInt {

	private int limitedPlanMax;
	protected List<Customer> customerDatabase;
	protected List<Media> mediaDatabase;

	public MediaRentalManager() {

		this.limitedPlanMax = 2;
		this.customerDatabase = new ArrayList<Customer>();
		this.mediaDatabase = new ArrayList<Media>();

	}

	@Override
	public void addAlbum(java.lang.String title, int copiesAvailable, 
			java.lang.String artist, java.lang.String songs) {

		mediaDatabase.add(new Album(title, copiesAvailable, artist, songs));

	}

	@Override
	public void addCustomer(java.lang.String name, java.lang.String address, 
			java.lang.String plan) {

		customerDatabase.add(new Customer(name, address, plan));

	}

	@Override
	public void addMovie(java.lang.String title, int copiesAvailable,
			java.lang.String rating) {

		mediaDatabase.add(new Movie(title, copiesAvailable, rating));

	}

	@Override
	public boolean addToQueue(java.lang.String customerName,
			java.lang.String mediaTitle) {

		// if media already exists in queue, return false; else add to queue

		int index = -1;

		for (int i = 0; i < customerDatabase.size(); i++) {
			if (customerDatabase.get(i).getName() == customerName) {
				index = i;
			}

		}

		if (index == -1) {
			return false;
		}

		for (int i = 0; i < customerDatabase.get(index).requests.size(); i++) {
			if (customerDatabase.get(index).requests.get(i) == mediaTitle) {
				return false;
			}
		}

		customerDatabase.get(index).requests.add(mediaTitle);
		return true;

	}

	@Override
	public java.lang.String getAllCustomersInfo() {

		String customerInfo = "***** Customers' Information *****\n";

		Collections.sort(customerDatabase);

		for (int i = 0; i < customerDatabase.size(); i++) {
			customerInfo += customerDatabase.get(i).toString() + "\n";
		}

		return customerInfo;

	}

	@Override
	public java.lang.String getAllMediaInfo() {

		String mediaInfo = "***** Media Information *****\n";

		Collections.sort(mediaDatabase);

		for (int i = 0; i < mediaDatabase.size(); i++) {
			mediaInfo += mediaDatabase.get(i).toString() + "\n";
		}

		return mediaInfo;

	}

	@Override
	public java.lang.String processRequests() {

		String processedRequests = "";

		Collections.sort(customerDatabase);

		// go through each customer
		for (int i = 0; i < customerDatabase.size(); i++) {

			Customer c = customerDatabase.get(i);

			int size = c.requests.size();
			int x = 0;

			if (c.getPlan() == "LIMITED") {

				// go through customer's request queue
				for (int j = 0; j < size; j++) {

					for (int k = 0; k < mediaDatabase.size(); k++) {

						Media m = mediaDatabase.get(k);

						String mediaTitle = m.getTitle();

						System.out.println(c.requests.size());
						if (mediaTitle.equals(c.requests.get(x))) {

							if (m.getCopiesAvailable() > 0) {

								if (c.rented.size() < limitedPlanMax) {

									c.requests.remove(x);

									c.rented.add(mediaTitle);

									m.adjustCopiesAvailable(false);

									processedRequests += "Sending " + 
									mediaTitle + " to " + c.getName() + "\n";

									break;

								}
							} else {
								x++;
							}

						}
					}

				}

			} else if (c.getPlan() == "UNLIMITED") { // unlimited plan case

				// go through customer's request queue
				for (int j = 0; j < size; j++) {

					// check if a copy of media is available
					for (int k = 0; k < mediaDatabase.size(); k++) {

						Media m = mediaDatabase.get(k);

						String mediaTitle = m.getTitle();

						// System.out.println(c.requests.get(j));

						if (mediaTitle.equals(c.requests.get(x))) {
							if (m.getCopiesAvailable() > 0) {

								c.requests.remove(x);

								c.rented.add(mediaTitle);

								m.adjustCopiesAvailable(false);

								processedRequests += "Sending " + 
								mediaTitle + " to " + c.getName() + "\n";

								break;

							} else {
								x++;
							}
						}

					}

				}

			}

		}

		return processedRequests;

	}

	@Override
	public boolean removeFromQueue(java.lang.String customerName, 
			java.lang.String mediaTitle) {

		// return false if customerName not found

		for (int i = 0; i < customerDatabase.size(); i++) {
			if (customerDatabase.get(i).getName() == customerName) {
				customerDatabase.get(i).requests.remove(mediaTitle);
				return true;
			}
		}

		return false;

	}

	@Override
	public boolean returnMedia(java.lang.String customerName,
			java.lang.String mediaTitle) {

		// return false if mediaTitle is not found in rented

		// first check if customer exists in database
		int index = -1;

		for (int i = 0; i < customerDatabase.size(); i++) {
			if (customerDatabase.get(i).getName().equals(customerName)) {
				index = i;
			}
		}

		if (index == -1) {
			return false;
		}

		Customer c = customerDatabase.get(index);

		// then check if customer has mediaTitle in rented

		for (int i = 0; i < c.rented.size(); i++) {
			if (c.rented.get(i).equals(mediaTitle)) {
				c.rented.remove(mediaTitle);

				for (int j = 0; j < mediaDatabase.size(); j++) {
					if (mediaDatabase.get(j).getTitle().equals(mediaTitle)) {
						mediaDatabase.get(j).adjustCopiesAvailable(true);
					}
				}

				return true;

			}
		}

		return false;

	}

	@Override
	public java.util.ArrayList<java.lang.String> searchMedia(
			java.lang.String title, java.lang.String rating,
			java.lang.String artist, java.lang.String songs) {

		ArrayList<java.lang.String> sorted = new ArrayList<java.lang.String>();

		boolean titleCond = false, ratingCond = false, 
				artistCond = false, songsCond = false;

		for (Media m : mediaDatabase) {

			titleCond = (title == null || m.getTitle().equals(title));

			if (m instanceof Movie) {

				Movie mov = (Movie) m;

				ratingCond = (rating == null || mov.getRating().equals(rating));

				artistCond = (artist == null);

				songsCond = (songs == null);

			} else if (m instanceof Album) {

				Album al = (Album) m;

				artistCond = (artist == null || al.getArtist().equals(artist));

				songsCond = (songs == null || 
						al.getSongs().indexOf(songs) != -1);

				ratingCond = (rating == null);

			}

			if (titleCond && ratingCond && artistCond && songsCond) {
				sorted.add(m.getTitle());
			}

		}

		Collections.sort(sorted);

		return sorted;

	}

	@Override
	public void setLimitedPlanLimit(int value) {

		this.limitedPlanMax = value;

	}

}
