package fit5171.monash.edu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirplaneTest {
    private int airplaneID_test;
    private String airplaneModel_test;
    private int businessSitsNumber_test;
    private int economySitsNumber_test;
    private int crewSitsNumber_test;
    private Airplane airplaneTest;

    @BeforeEach
    public void setup() {
        airplaneID_test = 9999;
        airplaneModel_test = "Boeing 737";
        businessSitsNumber_test = 10;
        economySitsNumber_test = 100;
        crewSitsNumber_test = 20;
        airplaneTest = new Airplane(airplaneID_test, airplaneModel_test, businessSitsNumber_test, economySitsNumber_test, crewSitsNumber_test);
    }

    @Test
    @DisplayName("getAirplaneID Valid Test")
    void getAirplaneID_Valid_Test() {
        assertEquals(9999, airplaneTest.getAirplaneID());
    }

    @Test
    @DisplayName("setAirplaneID Valid Test")
    void setAirplaneID_Valid_Test() {
        assertEquals("AirplaneID successfully set", airplaneTest.setAirplaneID(5000));
        assertEquals(5000, airplaneTest.getAirplaneID());
    }

    @Test
    @DisplayName("setAirplaneID Error Test")
    void setAirplaneID_error_Test() {
        assertEquals("Type error", airplaneTest.setAirplaneID(20000));
    }

    @Test
    @DisplayName("getAirplaneModel Valid Test")
    void getAirplaneModel_Valid_Test() {
        assertEquals("Boeing 737", airplaneTest.getAirplaneModel());
    }

    @Test
    @DisplayName("setAirplaneModel Valid Test")
    void setAirplaneModel_Valid_Test() {
        assertEquals("AirplaneModels successfully set", airplaneTest.setAirplaneModel("Boeing 777"));
        assertEquals("Boeing 777", airplaneTest.getAirplaneModel());
    }

    @Test
    @DisplayName("setAirplaneModel Error Test")
    void setAirplaneModel_Error_Test() {
        assertEquals("Type error", airplaneTest.setAirplaneModel("Error Test"));
    }

    @Test
    @DisplayName("getBusinessSitsNumber Valid Test")
    void getBusinessSitsNumber_Valid_Test() {
        assertEquals(10, airplaneTest.getBusinessSitsNumber());
    }

    @Test
    @DisplayName("setBusinessSitsNumber Valid Test")
    void setBusinessSitsNumber_Valid_Test() {
        assertEquals("AirplaneModels successfully set", airplaneTest.setBusinessSitsNumber(15));
        assertEquals(15, airplaneTest.getBusinessSitsNumber());
    }

    @Test
    @DisplayName("setBusinessSitsNumber Error Test")
    void setBusinessSitsNumber_Error_Test() {
        assertEquals("Type error", airplaneTest.setBusinessSitsNumber(500));
    }

    @Test
    @DisplayName("getEconomySitsNumber Valid Test")
    void getEconomySitsNumber_Valid_Test() {
        assertEquals(100, airplaneTest.getEconomySitsNumber());
    }

    @Test
    @DisplayName("setEconomySitsNumber Valid Test")
    void setEconomySitsNumber_Valid_Test() {
        assertEquals("economySitsNumber successfully set", airplaneTest.setEconomySitsNumber(150));
        assertEquals(150, airplaneTest.getEconomySitsNumber());
    }

    @Test
    @DisplayName("setEconomySitsNumber Error Test")
    void setEconomySitsNumber_Error_Test() {
        airplaneTest.setEconomySitsNumber(150);
        assertEquals("Type error", airplaneTest.setEconomySitsNumber(500));
    }

    @Test
    @DisplayName("getCrewSitsNumber Valid Test")
    void getCrewSitsNumber_Valid_Test() {
        assertEquals(20, airplaneTest.getCrewSitsNumber());
    }

    @Test
    @DisplayName("setCrewSitsNumber Valid Test")
    void setCrewSitsNumber_Valid_Test() {
        assertEquals("crewSitsNumber successfully set", airplaneTest.setCrewSitsNumber(15));
        assertEquals(15, airplaneTest.getCrewSitsNumber());
    }

    @Test
    @DisplayName("setCrewSitsNumber Error Test")
    void setCrewSitsNumber_Error_Test() {
        assertEquals("Type error", airplaneTest.setCrewSitsNumber(500));
    }
}