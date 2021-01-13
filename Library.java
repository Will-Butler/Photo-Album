import java.util.ArrayList;
import java.util.HashSet;

/*
 * This class creates a library object that has name, id, photos, and albums fields. It includes methods that can retrieve all
 * of these and edit all of them except for the id, which stays the same.
 */
public class Library extends PhotoContainer{
	//Fields
	private final int ID; //unchangeable id of the library
	private HashSet<Album> albums; //album to store photos
	
	//Constructors
	/*
	 * This creates new library objects with a name, id, and empty list of photos
	 */
	public Library(String name, int id) {
		super(name);
		this.ID = id;
		this.photos = new ArrayList<Photo>();
		this.albums = new HashSet<Album>();
	}	
	//Accessors

	/*
	 * this retrieves the ID of the given library
	 */
	public int getId() {
		return this.ID;
	}
	
	/*
	 * getAlbums method doesn't take a parameter and 
	 * returns a hashset of albums
	 */
	public HashSet<Album> getAlbums(){
		return this.albums;
	}
	
	//Other Methods
	/*
	 * this deletes a photo from the given library's list of photos as 
	 * well as any albums that have the photo in the list of albums.
	 * If it successfully deletes the photo, this returns true, if the 
	 * photo doesn't exist, this returns false.
	 */
	public boolean removePhoto(Photo p) {
		if(this.photos.contains(p)) {
			this.photos.remove(p);
			for(Album a: this.albums) {
				a.removePhoto(p);
			}
			return true;
		}
		return false;
	}
	
	/*
	 * getPhotos takes a rating as a parameter and returns null if the rating
	 * is greater than 5 or less than 0. Otherwise, it returns an arrayList of 
	 * photos whose ratings are greater than or equal. If none are qualifying, 
	 * this method returns an empty arrayList
	 */
	
	/*
	 * creatAlbum takes in a string album name and creates a new
	 * album with that name. It then tries to add that album to
	 * the list of albums and returns true if successful, false
	 * if not.
	 */
	public boolean createAlbum(String albumName) {
		Album a = new Album(albumName);
		boolean contains = false;
		for(Album b : this.albums) {
			if(a.equals(b)) {
				contains = true;
			}
		}
		if(!contains) {
			this.albums.add(a);
		}
		return !contains;
	}
	
	/*
	 * removeAlbum removes the given album from the list of albums
	 * and returns true if successful, false if not.
	 */
	public boolean removeAlbum(String albumName) {
		return this.albums.remove(getAlbumByName(albumName));
	}
	
	/*
	 * addPhotoToAlbum takes a photo object and a string album name
	 * as parameters and adds the photo to the given album if the 
	 * photo wasn't already in the album and it was in the library.
	 * If this happens, it returns true, otherwise false.
	 */
	public boolean addPhotoToAlbum(Photo p, String albumName) {
		if(this.photos.contains(p) && getAlbumByName(albumName).getPhotos().contains(p) == false) {
			getAlbumByName(albumName).addPhoto(p);
			return true;
		}
		else {
			return false;
		}
	}
	
	/*
	 * removePhotoFromAlbum takes a photo object and a string album name
	 * and removes the photo object from that album's list of photos. This 
	 * returns true if successful, false if not.
	 */
	public boolean removePhotoFromAlbum(Photo p, String albumName) {
		return getAlbumByName(albumName).getPhotos().remove(p);

	}
		
	/*
	 * getAlbumByName takes a string album name as a parameter
	 * and returns the album with that same name. If that album
	 * name is unfound in the set of albums, then this returns 
	 * null.
	 */
	private Album getAlbumByName(String albumName) {
		for(Album alb: this.albums) {
			if(alb.getName().equals(albumName)){
				return alb;
			}
		}
		return null;
	}
		
	/*
	 * this checks whether or not the id's of 2 libraries are equal.
	 * If so, it returns true, otherwise it returns false
	 */
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o instanceof Library) {
			Library l = (Library) o;
			if(l.getId() == this.ID) {
				return true;
			}
		}
		return false;
	}
	
	
	/*
	 * this produces a string with the library name, id, and list of photos it contains
	 */
	public String toString() {
		return "Library Name: " + this.name + "; Library ID:" + this.ID + "; Library Photos: " + this.photos + "; Albums: " + this.albums;
	}
	/*
	 * this checks how many photos are the same between 2 libraries
	 * and returns a list with the similar photos between them.
	 */
	public static ArrayList<Photo> commonPhotos(Library a, Library b){
		ArrayList<Photo> h = new ArrayList<>();
		for(Photo pic1 : b.getPhotos()) {
			for(Photo pic2 : a.getPhotos()) {
				if(pic1.equals(pic2)) {
					h.add(pic1);
			}
		}

		}
		return h;
	}
	/*
	 * this takes 2 libraries and checks for similarity among their photos.
	 * It then returns a fraction of the total photos that are the same
	 * between the two libraries.
	 */
	public static double similarity(Library x, Library y) {
		double comPhotCount = 0;
		for(Photo pic : x.getPhotos()) {
			for(Photo pic2 : y.getPhotos()) {
				if(pic.equals(pic2)) {
					comPhotCount++;
				}
			}
		}
		if (comPhotCount > 0) {
			return comPhotCount/(Math.min(x.getPhotos().size(), y.getPhotos().size()));
		}
		else {
			return 0.0;
		}
	}


	public static void main(String[] args) {
		Library a = new Library("LastSummer", 12345); //This creates 3 libraries with varying id's and names.
		Library b = new Library("ThisSummer", 54321);
		Library c = new Library("Winter", 54321);
	
	
		Photo p = new Photo("Pool", "Brother swimming", "2001-11-09", 4);  //this creates multiple photo objects to test with varying names, captions, dates, and ratings
		Photo p1 = new Photo("Hiking", "Sister on rocks","2001-11-09", 3);
		Photo p2 = new Photo("Pool", "Brother swimming","2009-12-25", 5);
		Photo p3 = new Photo("Pool", "Brother swimming","2018-12-22", 2);
		Photo p4 = new Photo("Rafting", "Family pic","2011-01-09", 1);
	
		a.addPhoto(p); //These add the photo objects to various libraries to be tested on by other methods
		a.addPhoto(p4);
		b.addPhoto(p1);
		b.addPhoto(p2);
		b.addPhoto(p3); //returns false since p2 is already in the library and is the same as p3
		c.addPhoto(p1);
		c.addPhoto(p4);

	
	
		System.out.println("Photo p1 in a: " + a.hasPhoto(p1)); //Should be false since library a doesn't have photo p1
		System.out.println("Photo p1 in b: " + b.hasPhoto(p1)); //Should be true since library b has photo p1
	
		System.out.println("Delete photo p2 from library b: " + b.removePhoto(p2)); //Should be true since library b has photo p2
		System.out.println("Delete photo p3 from library c: " + c.removePhoto(p3)); //Should be false since library c doesn't have photo p3		
		
		System.out.println("Number of photos in a: " + a.numPhotos()); //Should return 2 since library a has 2 photos 
		System.out.println("Number of photos in b: " + b.numPhotos()); //Should return 1 since library b has 1 photo
	
		System.out.println("Library a ID equal to library b ID: " + a.equals(b)); //Should return false since library a has a different id than library b
		System.out.println("Library b ID equal to library c ID: " + b.equals(c)); //Should return true since library b has the same id as library c
	
		System.out.println(a); //both of these return Strings created by the toString method with filename, id, and the photos in the album
		System.out.println(b); 
		
		System.out.println("Common photos between library a & b: " + commonPhotos(a, b)); //returns an empty since no photos are similar
		System.out.println("Common photos between library b & c: " + commonPhotos(b, c)); //returns 1 photo that the 2 libraries share
	
		System.out.println("Fraction of photos between library a & c that are the same: " + similarity(a, c)); //returns 0.5 since 1 photo is the same and the smaller album of the 2 has 2 photos (1/2 = .5)
		System.out.println("Fraction of photos between library a & b that are the same: " + similarity(a, b));// returns 0 since none of the photos are the same

	}
}
