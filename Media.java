package mediaRentalManager;

/*
 * The Media class represents a media item in the media database
 * A media item has a title and a number of copies
 * A media can be a movie or an album (see respective classes)
 */



public class Media implements Comparable {

	protected String title;
	protected int copiesAvailable;

	public Media() {

		this.title = "";
		this.copiesAvailable = 0;

	}

	public Media(String title) {

		this.title = title;
	}

	public Media(String title, int copiesAvailable) {

		this.title = title;
		this.copiesAvailable = copiesAvailable;

	}

	public String getTitle() {
		return title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void adjustCopiesAvailable(boolean add) {

		if (add) {
			this.copiesAvailable++;
		} else {
			this.copiesAvailable--;
		}

	}

	@Override
	public int compareTo(Object o) {

		Media temp = (Media) o;

		return this.getTitle().compareTo(temp.getTitle());
	}

}
