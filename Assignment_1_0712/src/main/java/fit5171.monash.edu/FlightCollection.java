package fit5171.monash.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FlightCollection {
	private static List<Flight> flights = new ArrayList<>();
	private static final Pattern CITY_NAME_PATTERN = Pattern.compile("^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");

	public static List<Flight> getFlights() {
		return flights;
	}

	public static String addFlight(Flight flight) {
		if (flight == null) {
			return "Flight cannot be null";
		}
		if (!isValidCityName(flight.getDepartFrom()) || !isValidCityName(flight.getDepartTo())) {
			return "Invalid city name provided";
		}
		flights.add(flight);
		return "Flight added successfully";
	}

	public static Flight getFlightInfo(String departFrom, String departTo) {
		for (Flight flight : flights) {
			if (flight.getDepartFrom().equalsIgnoreCase(departFrom) &&
					flight.getDepartTo().equalsIgnoreCase(departTo)) {
				return flight;
			}
		}
		return null;
	}

	public static Flight getFlightInfo(String city) {
		for (Flight flight : flights) {
			if (flight.getDepartFrom().equalsIgnoreCase(city) ||
					flight.getDepartTo().equalsIgnoreCase(city)) {
				return flight;
			}
		}
		return null;
	}

	public static Flight getFlightInfo(int flightID) {
		for (Flight flight : flights) {
			if (flight.getFlightID() == flightID) {
				return flight;
			}
		}
		return null;
	}

	private static boolean isValidCityName(String cityName) {
		return cityName != null && CITY_NAME_PATTERN.matcher(cityName).matches();
	}
}
