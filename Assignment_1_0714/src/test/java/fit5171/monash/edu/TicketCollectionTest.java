package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TicketCollectionTest {
    private Flight flight;
    private Passenger childPassenger;
    private Ticket ticket;

    @BeforeEach


    void setUp() {

        TicketCollection.clearTickets();  // 确保每次测试前票务集合为空
        Flight.clearFlightRegistry();// 清空航班ID注册表确保没有ID冲突

        flight = new Flight(1, "Tokyo", "New York", "JL123", "Japan Airlines",
                Timestamp.valueOf("2024-12-31 10:30:00"), Timestamp.valueOf("2024-12-31 14:30:00"), new Airplane(1, "Boeing 787", 50, 150, 10));
        childPassenger = new Passenger("John", "Doe", 10, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);

        ticket = new Ticket(100, 200, flight, true, childPassenger);
    }

    @Test
    void testAddTicketValidation() {
        TicketCollection.addTicket(ticket);
        assertThrows(IllegalArgumentException.class, () -> TicketCollection.addTicket(ticket), "Adding the same ticket again should throw an exception.");
    }

    @Test
    void testGetTicket() {
        TicketCollection.addTicket(ticket);
        Ticket retrievedTicket = TicketCollection.getTicketInfo(100);
        assertNotNull(retrievedTicket, "Should retrieve the ticket with ID 100.");
        assertEquals(ticket, retrievedTicket, "The retrieved ticket should match the original ticket.");
    }

    @AfterEach
    void tearDown() {
        TicketCollection.clearTickets();  // 清理所有票务信息，确保测试独立性
    }
}
