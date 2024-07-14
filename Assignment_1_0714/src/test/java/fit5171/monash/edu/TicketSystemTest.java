package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class TicketSystemTest {
    private TicketSystem ticketSystem;
    private Flight flight1;
    private Flight flight2;
    private Passenger passenger1;
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;

    @BeforeEach
    public void setup() {
        Flight.clearFlightRegistry();
        TicketCollection.clearTickets();
        ticketSystem = new TicketSystem();

        Airplane airplane1 = new Airplane(1, "Boeing 737", 10, 100, 20);
        Airplane airplane2 = new Airplane(2, "Boeing 787", 50, 150, 10);
        flight1 = new Flight(1, "Sydney", "Melbourne", "SYD-MEL", "Qantas",
            Timestamp.valueOf("2024-12-31 16:30:00"), Timestamp.valueOf("2024-12-31 18:30:00"), airplane1);
        flight2 = new Flight(2, "Melbourne", "Beijing", "MEL-BJ", "Qantas",
                Timestamp.valueOf("2024-12-31 10:30:00"), Timestamp.valueOf("2024-12-31 14:30:00"), airplane2);

        passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        ticket1 = new Ticket(123456, 300, flight1, false, passenger1);
        ticket2 = new Ticket(1234567, 300, flight2, false, passenger1);
        ticket3 = new Ticket(12345678, 300, flight1, false, passenger1);
        FlightCollection.addFlight(flight1);
        FlightCollection.addFlight(flight2);
        TicketCollection.addTicket(ticket1);
        TicketCollection.addTicket(ticket2);
        TicketCollection.addTicket(ticket3);
        ticket1.setTicketStatus(false);
        ticket2.setTicketStatus(false);
        ticket3.setTicketStatus(false);
    }

    @Test
    @DisplayName("Test buying transfer tickets")
    public void testBuyingTransferTickets() throws Exception {
        // 购买换乘票
        Exception exception = assertThrows(Exception.class, () -> {
            ticketSystem.chooseTicket("Beijing", "Sydney");
        });
        assertEquals("Successfully purchased transfer tickets.", exception.getMessage());
    }

    @Test
    @DisplayName("Test buying direct ticket")
    public void testBuyingDirectTicket() throws Exception {
        // 购买直达票
        Exception exception = assertThrows(Exception.class, () -> {
            ticketSystem.chooseTicket("Melbourne", "Sydney");
        });
        assertEquals("Successfully purchased a direct ticket.", exception.getMessage());
    }

    @Test
    @DisplayName("Test no possible variants")
    public void testNoPossibleVariants() {
        // 验证无可行航班的异常消息
        Exception exception = assertThrows(Exception.class, () -> {
            ticketSystem.chooseTicket("Beijing", "Shanghai");
        });
        assertEquals("There are no possible variants.", exception.getMessage());
    }



    @Test
    @DisplayName("Choose Already Booked Ticket Test")
    void chooseAlreadyBookedTicket_Test() {
        ticketSystem.bookTicket(ticket3);

        // 验证异常抛出
        Exception exception = assertThrows(Exception.class, () -> {
            ticketSystem.buyTicket(12345678);
        });

        // 验证异常消息
        assertEquals("Ticket already booked", exception.getMessage());

    }


    @Test
    @DisplayName("Validate Passenger Information Mismatch Test")
    public void validatePassengerInformationMismatch_Test() throws Exception {
        // 创建一个信息不匹配的Passenger对象
        Passenger mismatchedPassenger = new Passenger("Jane", "Smith", 30, "Woman", "jane.smith@example.com", "0412345679", "987654321", "1234567890123236", 321);
        // 验证信息匹配的情况
        assertTrue(ticketSystem.validatePassengerInformation(ticket1.getPassenger(), ticket1.getPassenger()));
        // 验证信息不匹配的情况
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.validatePassengerInformation(mismatchedPassenger, ticket1.getPassenger());
        });
        System.out.println(exception.getMessage());
        assertEquals("Passenger information does not match", exception.getMessage());

    }


    @Test
    @DisplayName("Validate Flight Information Test")
    public void testValidateFlightInformation() throws Exception {
        TicketSystem ticketSystem = new TicketSystem();
        Flight invalidFlight = new Flight();// 创建一个无效的 Flight 对象
        assertTrue(ticketSystem.validateFlightInformation(flight1));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.validateFlightInformation(invalidFlight);
        });
        assertEquals("Invalid flight information provided", exception.getMessage());
    }

    @Test
    @DisplayName("Validate Ticket Information Test")
    public void testValidateTicketInformation() throws Exception {
        TicketSystem ticketSystem = new TicketSystem();
        Ticket invalidTicket = new Ticket(0, 0, null, false, passenger1); // 创建一个无效的 Ticket 对象
        assertTrue(ticketSystem.validateTicketInformation(ticket1));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ticketSystem.validateTicketInformation(invalidTicket);
        });
        assertEquals("Invalid ticket information provided", exception.getMessage());
    }

    @Test
    @DisplayName("Display Correct Value when Buying Ticket Test")
    void displayCorrectValue_When_Buying_Ticket_Test() throws Exception {
        double ticketPrice = ticket3.getPrice();
        String displayMessage = ticketSystem.buyTicket(12345678);
        assertEquals("Ticket purchased successfully for $" + ticketPrice, displayMessage);
        double ticketPrice2 = (ticket1.getPrice() + ticket2.getPrice());
        String displayMessage2 = ticketSystem.buyTicket2(123456, 1234567);
        assertEquals("Ticket purchased successfully for $" + ticketPrice2, displayMessage2);
    }


}