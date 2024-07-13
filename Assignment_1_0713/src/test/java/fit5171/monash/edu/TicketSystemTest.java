package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class TicketSystemTest {
    private TicketSystem ticketSystem;
    private Flight flight1;
    private Passenger passenger1;
    private Ticket ticket1;

    @BeforeEach
    public void setup() {
        Flight.clearFlightRegistry();
        TicketCollection.clearTickets();
        ticketSystem = new TicketSystem();

        Airplane airplane = new Airplane(9999, "Boeing 737", 10, 100, 20);
        flight1 = new Flight(1, "Sydney", "Melbourne", "SYD-MEL", "Qantas",
            Timestamp.valueOf("2024-12-31 10:30:00"), Timestamp.valueOf("2024-12-31 14:30:00"), new Airplane(1, "Boeing 787", 50, 150, 10));

        passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        ticket1 = new Ticket(123456, 300, flight1, false, passenger1);
    }

    @Test
    @DisplayName("Choose Ticket with Valid City Test")
    void chooseTicket_Valid_City_Test() {
        assertDoesNotThrow(() -> ticketSystem.chooseTicket("Sydney", "Melbourne"));
    }

    @Test
    @DisplayName("Choose Already Booked Ticket Test")
    void chooseAlreadyBookedTicket_Test() {
        ticketSystem.bookTicket(ticket1);
        Exception exception = assertThrows(Exception.class, () -> {
            ticketSystem.chooseTicket("Sydney", "Melbourne");
        });
        assertEquals("Ticket already booked", exception.getMessage());
    }

    @Test
    @DisplayName("Validate Passenger Information Test")
    void validatePassengerInformation_Test() {
        Passenger invalidPassenger = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        assertFalse(ticketSystem.validatePassengerInformation(invalidPassenger));
        assertTrue(ticketSystem.validatePassengerInformation(passenger1));
    }

    @Test
    @DisplayName("Validate Flight Information Test")
    void validateFlightInformation_Test() {
        assertTrue(ticketSystem.validateFlightInformation(flight1));
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(2, null, "London", "BA123", "British Airways",
                    Timestamp.valueOf("2024-12-31 15:30:00"), Timestamp.valueOf("2024-12-31 19:30:00"), new Airplane(1, "Boeing 787", 50, 150, 10));
        });
    }

    @Test
    @DisplayName("Validate Ticket Information Test")
    void validateTicketInformation_Test() {
        assertTrue(ticketSystem.validateTicketInformation(ticket1));
        Ticket invalidTicket = new Ticket(0, 0, null, false, null);
        assertFalse(ticketSystem.validateTicketInformation(invalidTicket));
    }

    @Test
    @DisplayName("Display Correct Value when Buying Ticket Test")
    void displayCorrectValue_When_Buying_Ticket_Test() throws Exception {
        double ticketPrice = ticketSystem.calculateTicketPrice(flight1);
        String displayMessage = ticketSystem.buyTicket(12345);
        assertEquals("Ticket purchased successfully for $" + ticketPrice, displayMessage);
    }
}