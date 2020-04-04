package utils;

import java.util.Calendar;

/**
 * This is a utility class for Time calculation and manipulation.
 *
 * All time are managed by the Calendar object.
 *
 * Partially referenced from
 * http://www.java2s.com/Code/Java/Data-Type/Gettheminutesdifference.htm
 */
public class TimeUtils {

	public final static long SECOND_MILLIS = 1000;

	public final static long MINUTE_MILLIS = SECOND_MILLIS * 60;

	public final static long HOUR_MILLIS = MINUTE_MILLIS * 60;

	public final static long DAY_MILLIS = HOUR_MILLIS * 24;

	public final static long YEAR_MILLIS = DAY_MILLIS * 365;

	/**
	 * Calculates the difference between two dates.
	 *
	 * The returned value is positive if the first date is later than
	 * the second date, vice versa
	 *
	 * @param date1 - the first date
	 * @param date2 - the second date
	 * @return - the minute difference between the first date and the second date
	 */
	public static int minutesDiff(Calendar date1, Calendar date2) {
		if (date1 == null || date2 == null) {
			return 0;
		}

		int minute1 = (int) (date1.getTime().getTime() / MINUTE_MILLIS);

		int minute2 = (int) (date2.getTime().getTime() / MINUTE_MILLIS);

		return minute1 - minute2;
	}
}
