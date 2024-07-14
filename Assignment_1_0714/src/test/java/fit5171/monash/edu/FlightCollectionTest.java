package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class FlightCollectionTest {
    private Flight flight1;
    private Flight flight2;

    @BeforeEach
    public void setup() {
        Flight.clearFlightRegistry();
        FlightCollection.getFlights().clear();
        Airplane airplane = new Airplane(9999, "Boeing 737", 10, 100, 20);
        flight1 = new Flight(1, "Sydney", "Melbourne", "SYD-MEL", "Qantas", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 1000000), airplane);
        flight2 = new Flight(2, "Melbourne", "Brisbane", "MEL-BNE", "Virgin", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 1000000), airplane);
    }

    @Test
    @DisplayName("Add Flight Valid Test")
    void addFlight_Valid_Test() {
        assertEquals("Flight added successfully", FlightCollection.addFlight(flight1));
        assertEquals("Flight added successfully", FlightCollection.addFlight(flight2));
        assertEquals(2, FlightCollection.getFlights().size());
    }

    @Test
    @DisplayName("Add Flight Invalid City Test")
    void addFlight_Invalid_City_Test() {
        Flight invalidFlight = new Flight(3, "Sydn3y", "Melbourne", "SYD-MEL", "Qantas", new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis() + 1000000), new Airplane(9999, "Boeing 737", 10, 100, 20));
        assertEquals("Invalid city name provided", FlightCollection.addFlight(invalidFlight));
    }

    @Test
    @DisplayName("Get Flight Info by Cities Test")
    void getFlightInfo_By_Cities_Test() {
        FlightCollection.addFlight(flight1);
        FlightCollection.addFlight(flight2);
        Flight result = FlightCollection.getFlightInfo("Melbourne", "Sydney");
        assertNotNull(result);
        assertEquals("Qantas", result.getCompany());
    }

    @Test
    @DisplayName("Get Flight Info by City Test")
    void getFlightInfo_By_City_Test() {
        FlightCollection.addFlight(flight1);
        FlightCollection.addFlight(flight2);
        Flight result = FlightCollection.getFlightInfo("Melbourne");
        assertNotNull(result);
        assertEquals("Qantas", result.getCompany());
    }

    @Test
    @DisplayName("Get Flight Info by Flight ID Test")
    void getFlightInfo_By_Flight_ID_Test() {
        FlightCollection.addFlight(flight1);
        FlightCollection.addFlight(flight2);
        Flight result = FlightCollection.getFlightInfo(1);
        assertNotNull(result);
        assertEquals("Melbourne", result.getDepartFrom());
    }
}
