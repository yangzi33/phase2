package alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import utils.TimeUtils;

import static alert.AlertManager.ALERT_DATE_MASK;

public class Alert {

	private String eventID;

	private String dateString;

	private Calendar date;

	private String frequencyString;

	private int frequencySeconds;

	public Alert(String eventId, String dateString, String frequencyString) {
		this.eventID = eventId;
		this.dateString = dateString;
		this.frequencyString = frequencyString;
		this.frequencySeconds = 0;

		SimpleDateFormat formatter = new SimpleDateFormat(ALERT_DATE_MASK);
		this.date = Calendar.getInstance();
		try {
			this.date.setTime(formatter.parse(dateString));
		} catch (ParseException e) {
			e.printStackTrace();
		}


		switch (frequencyString.toLowerCase()) {
			case "minute":
				frequencySeconds = 60;
				break;
			case "hour":
				frequencySeconds = 3600;
				break;
			case "day":
				frequencySeconds = 86400;
				break;
			case "none":
				break;
		}


	}

	/**
	 * Returns true if the target time triggers the alert
	 *
	 * @param targetDate - the target date
	 * @return - true if the targetDate + k * (frequencySeconds) == date,
	 * false otherwise
	 */
	public boolean shouldAlert(Calendar targetDate) {
		// while target date is earlier than the alert date
		if (frequencySeconds != 0) {
			while (TimeUtils.minutesDiff(targetDate, date) < 0) {
				targetDate.add(Calendar.SECOND, frequencySeconds);
			}
		}
		return TimeUtils.minutesDiff(date, targetDate) == 0;
	}

	public String getEventID() {
		return eventID;
	}

	public String getDateString() {
		return dateString;
	}

	public String getFrequencyString() {
		return frequencyString;
	}

	public Calendar getDate() {
		return date;
	}

	public int getFrequencySeconds() {
		return frequencySeconds;
	}
}

