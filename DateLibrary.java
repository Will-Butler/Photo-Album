/*
 * DateLibrary helps create, change, and compare dates given as strings 
 * and occasionally converted to integers for coding purposes.
 */
public class DateLibrary {
	/*
	 * Tests to confirm date is all digits with dashes in 
	 * the right place, and a length of 10. Returns false otherwise.
	 */
	public static boolean isValidDateFormat(String date) {
		if(date.length() == 10 && date.charAt(4) == '-' && date.charAt(7) == '-'  ){
			try {
				Integer.parseInt(date.substring(0, 4));
			}
			catch(Exception numberFormat){
				return false;
			}
			try {
				Integer.parseInt(date.substring(5, 7));
			}
			catch(Exception numberFormat){
				return false;
			}
			try {
				Integer.parseInt(date.substring(8));
				return true;
			}
			catch(Exception numberFormat){
				return false;
			}
		}
		else {
			return false;
		}
	}
	/*
	 * Given a date, returns the year, returns -1 if it's the wrong
	 * date format or too big or too small of a year.
	 */
	public static int getYear(String date) {
		if(isValidDateFormat(date) == false) {
			return -1;
		}
		else {
			int year = Integer.parseInt(date.substring(0, 4));
			if(year > 9999 || year < 0001) {
				return -1;
			}
			else {
				return year;
			}
		}
	}
	/*
	 * Given a date, returns the month, returns -1 if it's the wrong
	 * date format or too big or too small of a month.
	 */
	public static int getMonth(String date) {
		if(isValidDateFormat(date) == false) {
			return -1;
		}
		else {
			int month = Integer.parseInt(date.substring(5, 7));
			if(month > 12 || month < 1) {
				return -1;
			}
			else {
				return month;
			}
		}
	}
	/*
	 * Given a date, returns the day, returns -1 if it's the wrong
	 * date format or too big or too small of a day.
	 */
	public static int getDay(String date) {
		if(isValidDateFormat(date) == false) {
			return -1;
		}
		else {
			int day = Integer.parseInt(date.substring(8));
			if(day > 31 || day < 1) {
				return -1;
			}
			else {
				return day;
			}
		}
	}
	/*
	 * Given a year, returns true if it's a leap year 
	 * (divisible by 4 and not 100 unless also 400), 
	 * returns false if it's not a leap year.
	 */
	public static boolean isLeapYear(int year) {
		if(year % 4 == 0) {
			if(year % 100 == 0 && year % 400 != 0) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}
	/*
	 * Given a date, returns true if it's a valid
	 * date in the calendar year, returns false if not.
	 * Checks for leap years as well.
	 */
	public static boolean isValidDate(String date) {
		if(isValidDateFormat(date) == true && getYear(date) > 0000 && getYear(date) <= 2020 && getMonth(date) > 0 && getMonth(date) < 13){
			//Months with 31 days
			if(getMonth(date) == 1 || getMonth(date) == 3 || getMonth(date) == 5 || getMonth(date) == 7 || getMonth(date) == 8 || getMonth(date) == 10 || getMonth(date) == 12) {
				if(getDay(date) > 0 && getDay(date) < 32) {
					return true;
				}
				else {
					return false;
				}
			}
			//Months with 30 days
			else if(getMonth(date) == 4 || getMonth(date) == 6 || getMonth(date) == 9 || getMonth(date) == 11) {
				if(getDay(date) > 0 && getDay(date) < 31) {
					return true;
				}
				else {
					return false;
				}
			}
			//February
			else if(getMonth(date) == 2 && isLeapYear(getYear(date)) == false){
			//If it isn't leap year
				if(getDay(date) < 29 && getDay(date) > 0 ) {
					return true;
				}
				else {
					return false;
				}
			}
			//If it's leap year
			else {
				if(getDay(date) < 30 && getDay(date) > 0 ) {
					return true;
				}
				else {
					return false;
				}
			}
		}
		else {
			return false;
		}
	}
	
	/*
	 * Given 2 dates, returns a negative number if the first
	 * date comes first, and a positive number if the second date comes first.
	 * Returns 0 if the dates are the same day of if either
	 * are in an invalid date format.
	 */
	public static int compare(String date1, String date2) {
		if(isValidDate(date1) == false|| isValidDate(date2) == false) {
			return 0;
		}
		else {
			return date1.compareTo(date2);
		}
	}

	public static void main(String[] args) {
		
		//Creates a variety of dates below to test
		String date1 = "1234-56-78";
		String date2 = "2001-06-18";
		String date3 = "abcd-56-78";
		String date4 = "1234-ef-78";
		String date5 = "1234-56-gh";
		String date6 = "1234_56-78";
		String date7 = "1234-56-780";
		String date8 = "2000-06-17";


		
		System.out.println(isValidDateFormat(date1)); //returns true
		System.out.println(isValidDateFormat(date7)); //returns false
		
		System.out.println(getYear(date1)); //returns 1234 as the year
		System.out.println(getYear(date3)); //returns -1 since abcd isn't a valid year

		System.out.println(getMonth(date2)); //returns 6
		System.out.println(getMonth(date4)); //returns -1 since ef isn't a valid month

		System.out.println(getDay(date6)); //returns -1 since 78 isn't a valid day
		System.out.println(getDay(date2)); //returns 18

		System.out.println(isLeapYear(2000)); //returns true since 2000 is divisible by 4 & 400
		System.out.println(isLeapYear(2001)); //returns false since 2001 isn't divisible be 4

		System.out.println(isValidDate(date6)); //returns false since there's an underscore
		System.out.println(isValidDate(date2)); //returns true

		System.out.println(compare(date5, date8)); //returns 0 since date5 is invalid
		System.out.println(compare(date8, date2)); //returns -1 since the first date comes before the 2nd date

		
		

	}

}
