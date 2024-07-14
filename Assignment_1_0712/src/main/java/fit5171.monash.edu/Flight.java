package fit5171.monash.edu;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Flight {
    //在 Flight 类中添加一个静态集合来跟踪已创建的航班实例，flight测试4
    private static Set<Integer> flightIDs = new HashSet<>(); // 存储所有航班ID以确保唯一性
    private int flightID;
    private String departTo;
    private String departFrom;
    private String code;
    private String company;
    private Timestamp dateFrom;
    private Timestamp dateTo;
    Airplane airplane;

    public Flight() {}

    public Flight(int flight_id, String departTo, String departFrom, String code, String company, Timestamp dateFrom, Timestamp dateTo, Airplane airplane) {
        // 检查所有传入的参数是否为 null
        if (departTo == null || departFrom == null || code == null || company == null || dateFrom == null || dateTo == null || airplane == null) {
            throw new IllegalArgumentException("All fields must be filled");
        }

        // 在添加航班之前检查ID的唯一性
        if (!flightIDs.add(flight_id)) {
            throw new IllegalArgumentException("Flight ID already exists in the system");
        }
            this.flightID=flight_id;
            this.departTo = departTo;
            this.departFrom = departFrom;
            this.code = code;
            this.company = company;
            this.airplane = airplane;
            this.dateTo = dateTo;
            this.dateFrom = dateFrom;
    }
//必须清理干净，不然报错
    public static void clearFlightRegistry() {
        flightIDs.clear();
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightid) {
        this.flightID = flightid;
    }

    public String getDepartTo() {
        return departTo;
    }

    public void setDepartTo(String departTo) {
        this.departTo = departTo;
    }

    public String getDepartFrom() {
        return departFrom;
    }

    public void setDepartFrom(String departFrom) {
        this.departFrom = departFrom;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Timestamp getDateFrom() {
        return dateFrom;
    }
//修改时间格式
    public void setDateFrom(Timestamp dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public String toString()
    {
            return "Flight{" + airplane.toString() +
                    ", date to=" +  getDateTo() + ", " + '\'' +
                    ", date from='" + getDateFrom() + '\'' +
                    ", depart from='" + getDepartFrom() + '\'' +
                    ", depart to='" + getDepartTo() + '\'' +
                    ", code=" + getCode() + '\'' +
                    ", company=" + getCompany() + '\'' +
                    ", code=" + getCode() + '\'' +
                    '}';
    }
}
