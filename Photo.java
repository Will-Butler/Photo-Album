import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/*
 * This class creates a photo object with filename, caption, rating, and dateTaken fields. This object also has a variety of methods to 
 * obtain and change these fields, in addition to an equals, toString, and hashcode method.
 */
public class Photo implements Comparable<Photo>{
	//fields
	private final String caption;  //description of the photo
	private final String filename; //name of the photo
	private int rating;			//rating of the photo
	private final String dateTaken; //date that the photo was taken
	protected BufferedImage imageData; //contains image's data

	//constructors
	public Photo(String name, String cap) { //This constructor only takes a filename and caption as parameters
		this.filename = name;				//and sets them equal to the instance variables while defaulting
		this.caption = cap;					//the rating to 1.
		this.rating = 1;
		this.dateTaken = "1901-01-01";
	}

	public Photo(String filename, String caption, String dateTaken, int rating) { //This constructor takes a filename, caption, and rating
		this.filename = filename;
		this.caption = caption;
		if(DateLibrary.isValidDate(dateTaken)) {     //if the date that the photo is taken is invalid, it defaults to 1901-01-01
			this.dateTaken = dateTaken;
		}
		else {
			this.dateTaken = "1901-01-01";
		}
		if(rating > 5||rating < 1) {	//but if the rating is outside the bounds, it defaults it 
			this.rating = 1;			//to 1, while setting the other two parameters to the instance variables.
		}
		else {
			this.rating = rating;
		}
	
		

	}
	//methods
	/*
	 * getFilename has no parameter and returns the objects instance filename variable
	 */
	public String getFilename() {
		return this.filename;
	}
	/*
	 * getCaption has no parameter and returns the objects instance caption variable
	 */
	public String getCaption() {
		return this.caption;
	}
	/*
	 * getRating has no parameter and returns the objects instance rating variable
	 */
	public int getRating() {
		return this.rating;
	}
	/*
	 * getDateTaken has no parameter and returns the objects instance dateTaken variable
	 */
	public String getDateTaken() {
		return this.dateTaken;
	}
	/*
	 * getImageData has no parameter and returns the reference to the objects image data
	 */
	public BufferedImage getImageData() {
		return this.imageData;
	}
	/*
	 * setRating takes a new rating as an integer and sets this as the 
	 * objects new rating as long as it isn't the same as the old rating
	 * and within the bounds. This method returns true if a new rating is 
	 * set and false if it isn't.
	 */
	public boolean setRating(int newRating) {
		if(newRating != this.rating && newRating <= 5 && newRating >= 1) {
			this.rating = newRating;
			return true;
		}
		else {
			return false;
		}
	}

	/*
	 * loadImageData takes the filename of a photo object as the parameter
	 * and attempts to load the image data form the file and store it into the 
	 * imageData field. If successful, this returns true, otherwise false
	 */
	public boolean loadImageData(String filename) {
		try {
			BufferedImage img = ImageIO.read(new File(filename));
			this.imageData = img;
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	/*
	 * hashCode() method takes in a photo object and returns a unique
	 * integer based on the photos filename
	 */
	public int hashCode(Photo p) {
		int sum = 0;
		String photoName = p.filename;
		String[] photoHashCodes = new String[500];
		for (int i=0; i < photoName.length(); i++) {
			sum += photoName.charAt(i);
			if(photoHashCodes[sum%500] == null) {
			photoHashCodes[sum%500] = p.filename;
			}
			else{
				while(photoHashCodes[sum%500] != null ) {
				sum +=1;
				}
				photoHashCodes[sum%500] = p.filename;
			}
		}
		return sum;
	}
	/*
	 * equals takes an object, ensures that it's of the Photo class, and if so, 
	 * compares the filename and caption to the current photo object filename
	 * and caption. If they're both the same, this returns true, else it returns false.
	 */
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o instanceof Photo) {
			Photo p = (Photo) o;
			if(p.getFilename().equals(this.filename) && p.getCaption().equals(this.caption)) {
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
	 * toString doesn't take a parameter, it returns the filename and caption of 
	 * a photo object if an object in the photo class is printed.
	 */
	public String toString() {
		return "Filename: " + this.filename + "  Caption: " + this.caption;
	}
	/*
	 * this method takes a photo object and sorts the given object
	 * and the called object by the date they were taken. If these 
	 * are the same, then it sorts the objects by caption, alphabetically.
	 * This returns 0 if they're the same, a positive number if the called
	 * object is bigger, and a negative number if the passed object is bigger.
	 */
	public int compareTo(Photo p) {
		int retVal = this.dateTaken.compareTo(p.dateTaken);
		if(retVal != 0) {
			return retVal;
		}
		retVal = this.caption.compareTo(p.caption);
		return retVal;
	}

	public static void main(String[] args) {
		
		//this creates photos to get tested on
		Photo a = new Photo("Vacation", "With my family", "2004-08-23", 2);
		Photo b = new Photo("LastYear", "With my friends","2018-02-19", 2);
		ArrayList<Photo> list = new ArrayList<>();
		
		
		
		System.out.println("Photo a: " + a);		
		System.out.println("Photo a's initial rating: " + a.getRating()); //This will show a's rating as 2
		a.setRating(4);					   								  //This changes the rating to 3
		System.out.println("Photo a's changed rating: " + a.getRating()); //This will show a's rating as 3
		
		
		System.out.println("Photo b: " + b);
		System.out.println("Photo b's initial rating: " + b.getRating()); //This will show b's rating as 2
		b.setRating(3);					   								  //This changes the rating to 4
		System.out.println("Photo b's changed rating: " + b.getRating()); //This will show b's rating as 4
		
		System.out.println("Does a = b?..." + a.equals(b)); //This compares the filename and caption of photo a and b (prints false)
		
		Photo c = new Photo("Vacation", "With my family", "2017-28-19", 5); //tests toString of a photo
		System.out.println("Photo c: " + c);

		System.out.println("Does a = c?..." + a.equals(c)); //tests whether photo a and c are equal
		
		Photo opt = new Photo("yee", "yaa", "2000-13-15", 4);
		System.out.println(opt.getDateTaken());
		
		list.add(a);
		list.add(b);
		System.out.println(b.compareTo(a));
		

	}

}
