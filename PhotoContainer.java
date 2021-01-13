import java.util.ArrayList;

/*PhotoContainer is a superclass that has subclasses albums and 
libraries. This contains methods that can be used by both subclasses.
*/
public abstract class PhotoContainer {
	//Fields
	protected String name;  //This is the name of the album
	protected ArrayList<Photo> photos; //List of photos that the album contains

	//Constructor
	public PhotoContainer(String name) { //creates the album given a name with an empty set of photos
		this.name = name;
		this.photos = new ArrayList<Photo>();
	}
	//Accessors

	/*
	 * getName has no parameter and returns the instance of the String name
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * getPhotos has no parameter and returns the instance of the photos hashset
	 */
	public ArrayList<Photo> getPhotos() {
		return this.photos;
	}

	//Mutators

	/*
	 * setName method takes in a string name and changes the objects name to 
	 * the given string
	 */
	public void setName(String name) {
		this.name = name;
	}

	//Other Methods

	/*
	 * addPhoto takes in a photo object as the parameter
	 * and adds it to the album only if the photo
	 * doesn't already exist in the album, if it successfully
	 * adds the photo, this method returns true, otherwise
	 * it returns false
	 */
	public boolean addPhoto(Photo p) {
		if(p != null) {
			if(this.photos.contains(p) == false) {
				this.photos.add(p);
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}

	/*
	 * hasPhoto method takes a Photo object as a parameter and returns
	 * true if the album already contains it and false if not
	 */
	public boolean hasPhoto(Photo p) {
		return this.photos.contains(p);
	}

	/*
	 * removePhoto method takes in a photo object as a parameter and 
	 * removes that photo from the album, if successful, the method
	 * returns true, otherwise it returns false
	 */
	public boolean removePhoto(Photo p) {


		if(this.photos.contains(p)) {
			this.photos.remove(p);
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * numPhotos takes no parameter and returns the number of
	 * photos in the current album
	 */
	public int numPhotos() {
		return this.photos.size();
	}
	public ArrayList<Photo> getPhotos(int rating){
		if(rating < 0 || rating > 5) {
			return null;
		}
		else {
			ArrayList<Photo> qualifyingPhotos = new ArrayList<Photo>();
			for(Photo p : this.photos) {
				if(p.getRating() >= rating) {
					qualifyingPhotos.add(p);
				}
			}
			return qualifyingPhotos;
		}
	}
	
	/*
	 * getPhotosInYear takes in a 4 digit year parameter and returns null if
	 * the year is invalid. Otherwise, it returns an arrayList of 
	 * photos whose years they were taken are equal to the year given
	 * . If none are qualifying, this method returns an empty arrayList
	 */
	public ArrayList<Photo> getPhotosInYear(int year){
		ArrayList<Photo> qualifyingPhotos = new ArrayList<Photo>();
		if(year < 0000 || year > 9999) {
			return null;
		}
		else {
			for(Photo p : this.photos) {
				if(DateLibrary.getYear(p.getDateTaken()) == year) {
					qualifyingPhotos.add(p);
				}
			}
			return qualifyingPhotos;
		}

	}
	
	/*
	 * getPhotosInMonth takes in a 2 digit month and a 4 digit year parameter and returns null if
	 * either is invalid. Otherwise, it returns an arrayList of 
	 * photos that were taken in the month and year given.
	 * If none are qualifying, this method returns an empty arrayList
	 */
	public ArrayList<Photo> getPhotosInMonth(int month, int year){
		ArrayList<Photo> qualifyingPhotos = new ArrayList<Photo>();
		if(year < 0000 || year > 9999 || month < 1 || month > 12) {
			return null;
		}
		else {
			for(Photo p : this.photos) {
				if(DateLibrary.getYear(p.getDateTaken()) == year && DateLibrary.getMonth(p.getDateTaken()) == month) {
					qualifyingPhotos.add(p);
				}
			}
			return qualifyingPhotos;
		}

	}
	
	/*
	 * getPhotosBetween takes in 2 dates and returns all of the photos that were taken
	 * between or on those two dates. If none were taken, it returns an empty arrayList.
	 * If any of the dates are invalid or if the first date is after the last date, 
	 * then this method returns null.
	 */
	public ArrayList<Photo> getPhotosBetween(String beginDate, String endDate){
		ArrayList<Photo> qualifyingPhotos = new ArrayList<Photo>();
		if(DateLibrary.compare(beginDate, endDate) >= 0 || !DateLibrary.isValidDate(beginDate) || !DateLibrary.isValidDate(endDate)) {
			return null;
		}
		else {
			for(Photo p : this.photos) {
				if(DateLibrary.compare(p.getDateTaken(), beginDate) >= 0 && DateLibrary.compare(p.getDateTaken(), endDate) <= 0) {
					qualifyingPhotos.add(p);
				}
			}
			return qualifyingPhotos;
		}
	}
	

	/*
	 * equals method takes in an object and returns true if it isn't null,
	 * it's an instance of the Album class, and its name equals the current
	 * current album name. Otherwise, it returns false
	 */
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		else {
			if(o instanceof Album) {
				Album alb = (Album) o;
				if(alb.getName().equals(this.name)) {
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}

	}

	/*
	 * toString returns a String with the album name and photos contained within
	 */
	public String toString() {
		return "Album Name: " + this.name + ". Contents: " + this.photos;
	}

	/*
	 * hashCode method takes in an album and returns a unique integer based on the album name.
	 */
	public int hashCode(Album a) {
		int sum = 0;
		String album = a.name;
		for (int i=0; i < album.length(); i++) {
			sum += album.charAt(i);
		}
		return sum;
	}

}
