package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonTest {
    private Person person;

    @BeforeEach
    public void setup() {
        // Initialize a new Person instance before each test
        person = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
    }

    @Test
    @DisplayName("Constructor Valid Test")
    void constructor_Valid_Test() {
        assertEquals("Successfully created a person", person.Person("John", "Doe", 30, "Man"));
        assertEquals("John", person.getFirstName());
        assertEquals("Doe", person.getSecondName());
        assertEquals(30, person.getAge());
        assertEquals("Man", person.getGender());
    }

    @Test
    @DisplayName("Constructor Missing Fields Test")
    void constructor_Missing_Fields_Test() {
        assertEquals("All fields are required to create a person", person.Person(null, "Doe", 30, "Man"));
        assertEquals("All fields are required to create a person", person.Person("John", null, 30, "Man"));
        assertEquals("All fields are required to create a person", person.Person("John", "Doe", 30, null));
    }

    @Test
    @DisplayName("Constructor Invalid Gender Test")
    void constructor_Invalid_Gender_Test() {
        assertEquals("Invalid gender provided", person.Person("John", "Doe", 30, "InvalidGender"));
    }

    @Test
    @DisplayName("Constructor Invalid Name Test")
    void constructor_Invalid_Name_Test() {
        assertEquals("First name must only contain alphabet letters and not start with a number or symbol", person.Person("John1", "Doe", 30, "Man"));
        assertEquals("Second name must only contain alphabet letters and not start with a number or symbol", person.Person("John", "Doe1", 30, "Man"));
    }

    @Test
    @DisplayName("Getters and Setters Test")
    void getters_and_setters_Test() {
        person.setFirstName("Jane");
        assertEquals("Successfully Set FirstName", person.setFirstName("Jane"));
        assertEquals("Jane", person.getFirstName());

        person.setSecondName("Smith");
        assertEquals("Successfully Set SecondName", person.setSecondName("Smith"));
        assertEquals("Smith", person.getSecondName());

        assertEquals("Successfully Set Age", person.setAge(25));
        assertEquals(25, person.getAge());

        person.setGender("Woman");
        assertEquals("Successfully Set Gender", person.setGender("Woman"));
        assertEquals("Woman", person.getGender());
    }

    @Test
    @DisplayName("Invalid Gender Setter Test")
    void invalid_gender_setter_Test() {
        assertEquals("Invalid gender provided", person.setGender("InvalidGender"));
    }

    @Test
    @DisplayName("Invalid First Name Setter Test")
    void invalid_first_name_setter_Test() {
        assertEquals("First name must only contain alphabet letters and not start with a number or symbol", person.setFirstName("John1"));
    }

    @Test
    @DisplayName("Invalid Second Name Setter Test")
    void invalid_second_name_setter_Test() {
        assertEquals("Second name must only contain alphabet letters and not start with a number or symbol", person.setSecondName("Doe1"));
    }

    @Test
    @DisplayName("Invalid Age Setter Test")
    void invalid_age_setter_Test() {
        assertEquals("Invalid Age provided", person.setAge(0));
    }
}
