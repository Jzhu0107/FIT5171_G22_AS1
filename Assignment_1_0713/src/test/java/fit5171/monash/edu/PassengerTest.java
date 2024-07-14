package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {
    private Passenger passenger;

    @BeforeEach
    public void setup() {
        // Initialize a new Passenger instance before each test
        passenger = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
    }

    @Test
    @DisplayName("Constructor Valid Test")
    void constructor_Valid_Test() {
        assertEquals("John", passenger.getFirstName());
        assertEquals("Doe", passenger.getSecondName());
        assertEquals(30, passenger.getAge());
        assertEquals("Man", passenger.getGender());
        assertEquals("john.doe@example.com", passenger.getEmail());
        assertEquals("0412345678", passenger.getPhoneNumber());
        assertEquals("AB1234567", passenger.getPassport());
        assertEquals("1234567890123456", passenger.getCardNumber());
        assertEquals(123, passenger.getSecurityCode());
    }

    @Test
    @DisplayName("Constructor Missing Fields Test")
    void constructor_Missing_Fields_Test() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger(null, "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", null, 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, null, "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man", null, "0412345678", "AB1234567", "1234567890123456", 123);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", null, "AB1234567", "1234567890123456", 123);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", null, "1234567890123456", 123);
        });
    }

    @Test
    @DisplayName("Invalid Email Test")
    void invalid_Email_Test() {
        assertEquals("Invalid email provided", passenger.setEmail("invalid-email"));
    }

    @Test
    @DisplayName("Invalid Phone Number Test")
    void invalid_Phone_Number_Test() {
        assertEquals("Invalid phone number provided", passenger.setPhoneNumber("1234567890"));
    }

    @Test
    @DisplayName("Invalid Passport Test")
    void invalid_Passport_Test() {
        assertEquals("Invalid passport number provided", passenger.setPassport("1234567890"));
    }

    @Test
    @DisplayName("Valid Card Number Test")
    void valid_Card_Number_Test() {
        assertEquals("Successfully Set CardNumber", passenger.setCardNumber("1234567812345678"));
    }

    @Test
    @DisplayName("Invalid Card Number Test")
    void invalid_Card_Number_Test() {
        assertEquals("Invalid card number provided", passenger.setCardNumber("12345678"));
    }

    @Test
    @DisplayName("Valid Security Code Test")
    void valid_Security_Code_Test() {
        assertEquals("Successfully Set SecurityCode", passenger.setSecurityCode(123));
        assertEquals("Successfully Set SecurityCode", passenger.setSecurityCode(1234));
    }

    @Test
    @DisplayName("Invalid Security Code Test")
    void invalid_Security_Code_Test() {
        assertEquals("Invalid security code provided", passenger.setSecurityCode(12));
        assertEquals("Invalid security code provided", passenger.setSecurityCode(12345));
    }
}
