import java.util.ArrayList;
//This class creates an album object with a name and arraylist of photos. This object has methods to add, 
//compare, and take away photos from the album, and exists in groups within the library class.
public class Album extends PhotoContainer {
	
	//Constructor
	public Album(String name) { //creates the album given a name with an empty arraylist of photos
		super(name);
		this.photos = new ArrayList<Photo>();
	}


}
