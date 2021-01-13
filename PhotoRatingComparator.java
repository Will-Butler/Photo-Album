import java.util.Comparator;

/*
 * this class creates a method that implements the comparator interface
 * which allows it to compare two photo objects, first by rating in descending order, then 
 * by caption in alphabetical order if rating is equal. This returns 0 if the objects
 * are equal, 1 if the first one is bigger, and -1 if the first one is smaller.
 */
public class PhotoRatingComparator implements Comparator<Photo>{
	public int compare(Photo a, Photo b) {
		int retVal = b.getRating() - a.getRating();
		if(retVal != 0) {
			return retVal;
		}
		retVal = a.getCaption().compareTo(b.getCaption());
		return retVal;
	}
}
