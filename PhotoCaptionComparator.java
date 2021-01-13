import java.util.Comparator;

/*
 * this class creates a method that implements the comparator interface
 * which allows it to compare two photo objects, first by caption in alphabetical order, 
 * then by rating in descending order if caption is equal. This returns 0 if the objects
 * are equal, 1 if the first one is bigger, and -1 if the first one is smaller.
 */
public class PhotoCaptionComparator implements Comparator<Photo>{
	public int compare(Photo a, Photo b) {
		int retVal = a.getCaption().compareTo(b.getCaption());
		if(retVal != 0) {
			return retVal;
		}
		retVal = b.getRating() - a.getRating();
		return retVal;
	}
}
