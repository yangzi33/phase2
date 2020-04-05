package alert;

import event.Event;
import user.CalendarManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Manages the alerts stored in the system
 */
public class AlertManager {

	// =============== Constants ===============
	public static String ALERT_DATE_MASK = "dd-MM-yyyy HH:mm";

	// =============== Local Storage ===============
	public static HashMap<String, ArrayList<Alert>> allAlerts = new HashMap<>();


	public  HashMap<String, ArrayList<Alert>> getAllAlerts(){
		return allAlerts;
	}
	/**
	 * Reads the text alert data and store them into the alerts list.
	 *
	 * @return - the list of all the alerts stored in the event data txt file.
	 */

	public static HashMap<String, ArrayList<Alert>> readAlertData(String path, CalendarManager calendarManager) {
		HashMap<String, ArrayList<Alert>> alerts = new HashMap<>();
		File alertFile = new File(path);
		if (alertFile.exists()) {
			try {
				Scanner scanner = new Scanner(alertFile);
				while (scanner.hasNextLine()) {
					String currLine = scanner.nextLine().trim();
					String[] attributes = currLine.split(",");
					if (attributes.length == 3) {
						String eventId = attributes[0].trim();
						String alertTime = attributes[1].trim();
						String alertFrequency = attributes[2].trim();

						Alert currAlert = new Alert(eventId, alertTime, alertFrequency);
						if (alerts.containsKey(eventId)) {
							alerts.get(eventId).add(currAlert);
						} else {
							alerts.put(eventId,
									new ArrayList<>(Collections.singletonList(currAlert)));
						}
					} else {
						System.out.println("Invalid Line:");
						System.out.println(currLine);
					}
				}
				scanner.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("eventData file not found. No event is created.");
		}
		calendarManager.alertManager.allAlerts = alerts;
		return alerts;
	}

	/**
	 * Writes the alerts list into the alert data file.
	 ** @return - true if the write was successful, false otherwise.
	 */
	public static boolean writeAlertData(String path, CalendarManager calendarManager) {
		try {
			File alertFile = new File(path);
			if (!alertFile.exists()) {
				if (!alertFile.createNewFile()) {
					return false;
				}
			}
			FileWriter writer = new FileWriter(alertFile);
			for (ArrayList<Alert> currAlerts : calendarManager.alertManager.getAllAlerts().values()) {
				for (Alert currAlert : currAlerts) {
					List<String> currLineLst = Arrays.asList(
							currAlert.getEventID(),
							currAlert.getDateString(),
							currAlert.getFrequencyString()
					);
					writer.write(String.join(",", currLineLst) +
							System.lineSeparator());
				}
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Prints all the stored alerts
	 */
	public static void viewAllAlerts(CalendarManager calendarManager) {
		int count = 0;
		for (Map.Entry<String, ArrayList<Alert>> entry : calendarManager.alertManager.getAllAlerts().entrySet()) {
			ArrayList<Alert> currAlerts = entry.getValue();
			for (Alert alert : currAlerts) {
				count += 1;
				System.out.println("alert.Alert" + count + ": " + alert.getDateString());
				System.out.println("Frequency is: " + alert.getFrequencyString());
				System.out.println("event.Event id is: " + alert.getEventID());
				System.out.println("-----------------------------------------------------");
			}
		}
	}

	/**
	 * Get the list of alerts corresponding to the event id
	 *
	 * @param eventId - the target event id
	 * @return the list of alerts corresponding to the event id
	 */
	public static ArrayList<Alert> getAlertByEvent(String eventId) {
		// if the alert is not stored, return an empty list as default value
		return allAlerts.getOrDefault(eventId, new ArrayList<>());
	}

	/**
	 * Returns the list of all current alerts. If there is no alert that should
	 * go off, return an empty array list.
	 *
	 * @return - the list of all current alerts
	 */
	public static ArrayList<Alert> checkCurrentAlerts(CalendarManager calendarManager) {
		ArrayList<Alert> currentAlerts = new ArrayList<>();
		Calendar now = Calendar.getInstance();
		for (ArrayList<Alert> currAlerts : calendarManager.alertManager.getAllAlerts().values()) {
			for (Alert currAlert : currAlerts) {
				if (currAlert.shouldAlert(now)) {
					currentAlerts.add(currAlert);
				}
			}
		}
		return currentAlerts;
	}


	private static Alert createAlertsInfo(String eventID){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input alert time, in a format of dd-MM-yyyy HH:MM");
		String alertTime = scanner.nextLine().trim();
		while (!Event.TimeFormateCHecker(alertTime)){		//If user did not enter time in the required format
			System.out.println("Please input alert time, in a format of dd-MM-yyyy HH:MM");
			alertTime = scanner.nextLine().trim();
		}
		System.out.println("Please input is that a frequency alert or not, in a format of Y orN");
		String alertType = scanner.nextLine().trim();

		String frequency;
		if(alertType.toLowerCase().equals("y")) {
			System.out.println("Please input frequency type, day,hour,minute");
			frequency = scanner.nextLine().trim();
			boolean formatCorrect = false;
			while (!formatCorrect) {
				switch (frequency.toLowerCase()) {
					case "minute":
						formatCorrect = true;
						break;
					case "hour":
						formatCorrect = true;
						break;
					case "day":
						formatCorrect = true;
						break;
					default:
						System.out.println("Please input frequency type, day,hour,minute");
						frequency = scanner.nextLine().trim();
						break;
				}
			}
		}
		else {
			frequency = "none";
		}

		return new Alert(eventID, alertTime, frequency);

	}


	public static void createAlerts(CalendarManager c){
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> allEventID = new ArrayList<>();
		System.out.println("Please choose the event that you want to create an alert: \n event.Event name; event.Event ID");
		for (Event event: c.eventManager.allEvents){
			allEventID.add(event.getEventId());
			System.out.println(event.getEventName() + "; " + event.getEventId());
		}
		System.out.println("Please ONLY enter the event ID: ");
		String eventID = scanner.nextLine().trim();
		while (!allEventID.contains(eventID)){
			System.out.println("Please ONLY enter the event ID: ");
			eventID = scanner.nextLine().trim();
		}

		Alert newlyAlert = createAlertsInfo(eventID);

		if (c.alertManager.allAlerts.containsKey(newlyAlert.getEventID())) {
			c.alertManager.allAlerts.get(newlyAlert.getEventID()).add(newlyAlert);
		} else {
			c.alertManager.allAlerts.put(newlyAlert.getEventID(),
					new ArrayList<>(Collections.singletonList(newlyAlert)));
		}

	}

	/** This is a method for the option <view all event></view>
	 *
	 * @param eventID - the target event id
	 */
	public static void displayAlertByID(String eventID){
		if(allAlerts.containsKey(eventID)){
			ArrayList<Alert> alerts = allAlerts.get(eventID);
			for(int i = 0; i < alerts.size(); i++){
				System.out.println("alert.Alert time " + (i+1) + ": " + alerts.get(i).getDateString() + ", frequency: " +
						alerts.get(i).getFrequencyString());
			}
		}
		else{
			System.out.println("alert.Alert: none");
		}
	}

	/** This is the method which is combined with </viewAlerts> for <View by alert></View>
	 *
	 * @param alerts - the arrayList of alerts of target event
	 */
	public static void displayAlerts(ArrayList<Alert> alerts){
		int n = alerts.size();
		if(n  == 0){
			System.out.println("You don't have any alert yet!");
		}else{
			for (int i = 0; i < n; i++){
				int number = i + 1;
				System.out.println("alert.Alert" + number + ": " + alerts.get(i).getDateString());
				System.out.println("Frequency is: " + alerts.get(i).getFrequencyString());
				System.out.println("event.Event id is: " + alerts.get(i).getEventID());
				System.out.println("-----------------------------------------------------");
			}
		}
	}


	public static void viewAlerts(CalendarManager c) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<String> allEventID = new ArrayList<>();
		System.out.println("Please choose the event that you want to do for an alert: \n event.Event name; event.Event ID");
		for (Event event: c.eventManager.allEvents){
			allEventID.add(event.getEventId());
			System.out.println(event.getEventName() + "; " + event.getEventId());
		}

		System.out.println("Please ONLY enter the event ID: ");
		String eventID = scanner.nextLine().trim();
		while (!allEventID.contains(eventID)){
			System.out.println("Please ONLY enter the event ID: ");
			eventID = scanner.nextLine().trim();
		}

		displayAlerts(getAlertByEvent(eventID));

	}


//	public static void changeAlerts() {
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Please enter the info of the old alert");
//		ArrayList<event.Event> filteredEventbyName = searching.searchByName();
//		if (filteredEventbyName == null){		//The case that there is no event has the same name with inputted name
//			System.out.println("No such event");
//			return;
//		}
//		ArrayList<event.Event> filteredEventbyTime = searching.searchByStartTIme(filteredEventbyName);
//		if (filteredEventbyTime == null){
//			System.out.println("No such event");
//			return;
//		}
//
//		System.out.println("Please enter the old alert time");
//		String oldTime = scanner.nextLine().trim();
//		System.out.println("Please enter the old alert frequency");
//		String oldFrequency = scanner.nextLine().trim();
//
//		ArrayList<alert.Alert> AlertsLst = allAlerts.remove(filteredEventbyTime.get(0).getEventId());
//		for (int i = 0; i < AlertsLst.size(); i++){
//			if (AlertsLst.get(i).getDateString().equals(oldTime) &&
//					AlertsLst.get(i).getFrequencyString().equals(oldFrequency)){
//
//				System.out.println("Find it !!");
//				alert.Alert oldAlert = AlertsLst.remove(i);
//				alert.Alert newAlert = createAlertsInfo(oldAlert.getEventID());
//				AlertsLst.add(newAlert);
//
//				allAlerts.put(newAlert.getEventID(), AlertsLst);
//
//				System.out.println("alert.Alert changed!");
//				if (writeAlertData(allAlerts)){
//					System.out.println("Writing data success");
//				}
//				else{
//					System.out.println("Cannot solve the path, writing data failed, alert change failed");
//				}
//				return;
//
//			}
//		}
//		System.out.println("Cannot find the alert with entered info");
//
//
//	}
//



}
