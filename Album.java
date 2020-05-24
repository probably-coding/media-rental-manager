package mediaRentalManager;

/*
 * The Album class represents an album in the media database
 * The Album class extends the Media class, as it is a type of media
 * In addition to a title and number of copies inherited from the Media 
 * class, an album also has an artist and a list of songs
 */



public class Album extends Media {

	private String artist;
	private String songs;

	public Album(String title, int copiesAvailable, String artist, 
			String songs) {

		super(title, copiesAvailable);
		this.artist = artist;
		this.songs = songs;

	}

	public String getArtist() {
		return artist;
	}

	public String getSongs() {
		return songs;
	}

	public String toString() {

		return "Title: " + title + ", Copies Available: " + 
		copiesAvailable + ", Artist: " + artist + ", Songs: "
				+ songs;

	}

}
