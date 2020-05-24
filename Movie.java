package mediaRentalManager;

/*
 * The Movie class represents a movie in the media database
 * The Movie class extends the Media class, as it is a type of media
 * In addition to a title and number of copies inherited from the Media 
 * class, a movie also has a rating
 */


public class Movie extends Media {

	private String rating;

	public Movie(String title, int copiesAvailable, String rating) {

		super(title, copiesAvailable);
		this.rating = rating;

	}

	public String getRating() {
		return rating;
	}

	public String toString() {

		return "Title: " + title + ", Copies Available: " + 
		copiesAvailable + ", Rating: " + rating;

	}

}
