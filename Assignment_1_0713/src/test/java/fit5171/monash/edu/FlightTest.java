package fit5171.monash.edu;

import fit5171.monash.edu.Airplane;
import fit5171.monash.edu.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest{
    @BeforeEach
    void setUp() {
        Flight.clearFlightRegistry();  // 在每个测试开始前清理飞行注册
    }

    @AfterEach
    void tearDown() {
        Flight.clearFlightRegistry();  // 在每个测试后清理飞行注册
    }

    @Test
    void testValidFlightCreation() {
        // 用有效值创建航班以测试正常情况
        assertDoesNotThrow(() -> {
            Flight flight = new Flight(1, "Tokyo", "New York", "JL123", "Japan Airlines",
                    Timestamp.valueOf("2024-12-31 10:30:00"), Timestamp.valueOf("2024-12-31 14:30:00"), new Airplane(1, "Boeing 787", 50, 150, 10));
        });
    }

    @Test
    void testMissingFields() {
        // 测试当任一字段为null时应抛出异常
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(2, null, "London", "BA123", "British Airways",
                    Timestamp.valueOf("2024-12-31 15:30:00"), Timestamp.valueOf("2024-12-31 19:30:00"), new Airplane(1, "Boeing 787", 50, 150, 10));
        });
    }

    @Test
    void testFlightIDUniqueness() {
        // 首次创建航班
        Flight flight1 = new Flight(3, "Beijing", "Shanghai", "CZ456", "China Southern",
                Timestamp.valueOf("2024-12-31 16:30:00"), Timestamp.valueOf("2024-12-31 20:30:00"), new Airplane(9999, "Boeing 737", 10, 100, 20));

        // 尝试用同一个ID再次创建航班应抛出异常
        assertThrows(IllegalArgumentException.class, () -> {
            new Flight(3, "Beijing", "Shanghai", "CZ789", "China Southern",
                    Timestamp.valueOf("2024-12-31 21:30:00"), Timestamp.valueOf("2024-12-31 23:30:00"), new Airplane(9999, "Boeing 737", 10, 100, 20));
        });
    }

    @Test
    void testDateAndTimeFormat() {
        // 验证日期时间格式是否正确处理
        assertDoesNotThrow(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            sdf.setLenient(false);
            Timestamp dateFrom = new Timestamp(sdf.parse("31/12/24 10:30:00").getTime());
            Timestamp dateTo = new Timestamp(sdf.parse("31/12/24 14:30:00").getTime());

            new Flight(4, "Sydney", "Perth", "QF987", "Qantas",
                    dateFrom, dateTo, new Airplane(9999, "Boeing 737", 10, 100, 20));
        });
    }

    @Test
    void testInvalidDateAndTimeFormat() {
        // 验证日期时间格式不正确时抛出异常
        assertThrows(DateTimeParseException.class, () -> {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            sdf.setLenient(false);
            Timestamp dateFrom = new Timestamp(sdf.parse("2024-12-31 10:30:00").getTime());  // 错误的格式
            Timestamp dateTo = new Timestamp(sdf.parse("2024-12-31 14:30:00").getTime());  // 错误的格式

            new Flight(5, "Los Angeles", "San Francisco", "UA123", "United Airlines",
                    dateFrom, dateTo, new Airplane(9999, "Boeing 737", 10, 100, 20));
        });
    }
}
