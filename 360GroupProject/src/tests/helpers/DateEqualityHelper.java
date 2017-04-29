/**
 *  This is a helper function meant to be used to compare Date Objects up to varying
 *  levels of accuracy from only the day to the millisecond level.
 */
package helpers;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ryan Tran
 * @date 4/29/17
 * @version 1.0
 */
public class DateEqualityHelper {

	/**
	 * This method accepts 2 date objects and compares them with accuracy down to the Day.
	 * (TimeZone-Month-Day-Year).
	 * @return True if Date objects are equal by day, else False if not equal
	 * @param theFirstDateObj the first date object to compare
	 * @param theSecondDateObj the second date object to compare to the first
	 */
	public static boolean compareDatesByDay(Date theFirstDateObj, Date theSecondDateObj) {
		boolean datesAreEqual = false;

		// This will format the Date to a format that is accurate up to the day
		// example output: 06-15-2017
		String dateFormatToDay = "XXX;MM-dd-yyyy";
		
		final SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatToDay);
		
		// format the dates to strings of dd-MM-yyyy format
		String formattedFirstDate = dateFormatter.format(theFirstDateObj);
		String formattedSecondDate = dateFormatter.format(theSecondDateObj);
		
		// check if the dates are equal
		datesAreEqual = formattedFirstDate.equals(formattedSecondDate);
		
		return datesAreEqual;
	}

	/**
	 * This method accepts 2 date objects and compares them with accuracy down to the Millisecond.
	 * (Timezone;Month-Day-Year;Hour:Minutes:Seconds:Milliseconds)
	 * @return True if Date objects are equal by day, else False if not equal
	 * @param theFirstDateObj the first date object to compare
	 * @param theSecondDateObj the second date object to compare to the first
	 */

	public static boolean compareDatesByMillisecond(Date theFirstDateObj, Date theSecondDateObj) {
		boolean datesAreEqual = false;

		// This will format the Date to a format that is accurate up to the millisecond
		// example output: -8:00;12-31-1990;02:23:22:255
		
		 String dateFormatToMilli = "XXX;MM-dd-yyyy;HH:mm:ss:SSS";
		final SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatToMilli);
		
		// format the dates to strings of dd-MM-yyyy;HH:mm:ss:SSS;XXX format
		String formattedFirstDate = dateFormatter.format(theFirstDateObj);
		String formattedSecondDate = dateFormatter.format(theSecondDateObj);
		
		// check if the dates are equal
		datesAreEqual = formattedFirstDate.equals(formattedSecondDate);
		
		System.out.println(formattedFirstDate);
		System.out.println(formattedSecondDate);
		
		return datesAreEqual;
	}

}
