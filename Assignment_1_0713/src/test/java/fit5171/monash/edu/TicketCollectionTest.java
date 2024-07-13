package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

class TicketCollectionTest {
    private Flight flight;
    private Passenger childPassenger;
    private Passenger elderPassenger;
    private Passenger adultPassenger;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        Flight.clearFlightRegistry();  // 清空航班ID注册表

        // 创建Flight对象时使用唯一ID
        flight = new Flight(1, "Tokyo", "New York", "JL123", "Japan Airlines",
                Timestamp.valueOf("2024-12-31 10:30:00"), Timestamp.valueOf("2024-12-31 14:30:00"), new Airplane(1, "Boeing 787", 50, 150, 10));

        // 确保使用有效的性别参数
        childPassenger = new Passenger("John", "Doe", 10, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        elderPassenger = new Passenger("Jane", "Doe", 65, "Woman", "jane.doe@example.com", "0412345678", "AB1234569", "1234567890123459", 123);
        adultPassenger = new Passenger("James", "Doe", 30, "Man", "james.doe@example.com", "0412345678", "AB1234560", "1234567890123459", 123);
    }

    @Test
    void testTicketStatusInitialization() {
        ticket = new Ticket(100, 200, flight, true, adultPassenger);
        assertFalse(ticket.ticketStatus(), "Ticket should initially be not purchased.");
    }

    @Test
    void testTicketPriceAndDiscountForChild() {
        ticket = new Ticket(101, 200, flight, false, childPassenger);
        int expectedPrice = (int) (200 * 0.5 * 1.12);
        assertEquals(expectedPrice, ticket.getPrice(), "Price should be discounted by 50% for children.");
    }

    @Test
    void testTicketPriceForElder() {
        ticket = new Ticket(102, 200, flight, false, elderPassenger);
        assertEquals(0, ticket.getPrice(), "Price should be free for elders.");
    }

    @Test
    void testTicketPriceForAdult() {
        ticket = new Ticket(103, 200, flight, false, adultPassenger);
        int expectedPrice = (int) (200 * 1.12);
        assertEquals(expectedPrice, ticket.getPrice(), "Price should remain the same for adults.");
    }
}
