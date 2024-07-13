package fit5171.monash.edu;

import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class TicketSystem {

    Passenger passenger;
    Ticket ticket;
    Flight flight;
    Scanner in;

    public TicketSystem() {
        // 构造方法，用于初始化实例变量
        passenger = new Passenger();
        ticket = new Ticket();
        flight = new Flight();
        in = new Scanner(System.in);
    }

    // 显示票的信息
    public void showTicket() {
        try {
            System.out.println("You have bought a ticket for flight " + ticket.flight.getDepartFrom() + " - " + ticket.flight.getDepartTo() + "\n\nDetails:");
            System.out.println(this.ticket.toString());
        } catch (NullPointerException e) {
            return; // 捕获空指针异常，防止程序崩溃
        }
    }

    // 购买直达航班的票的方法
    public String buyTicket(int ticket_id) throws Exception {
        int flight_id = 0;

        // 选择票
        Ticket validTicket = TicketCollection.getTicketInfo(ticket_id);

        // 验证票的有效性
        if (validTicket == null) {
            return "This ticket does not exist.";
        } else {
            // 获取航班ID
            flight_id = validTicket.getFlight().getFlightID();

            try {
                // 读取乘客信息
                System.out.println("Enter your First Name: ");
                String firstName = "";
                passenger.setFirstName(firstName);

                System.out.println("Enter your Second name:");
                String secondName = "";
                passenger.setSecondName(secondName); //setting passengers info

                System.out.println("Enter your age:");
                Integer age = 0;
                in.nextLine();
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = "";
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address");
                String email = "";
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = "";
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = "";
                passenger.setPassport(passportNumber);

                // 询问用户是否购买
                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0) {
                    return "Purchase cancelled."; // 用户选择不购买
                } else {
                    // 获取航班信息
                    flight = FlightCollection.getFlightInfo(flight_id);
                    int airplane_id = flight.getAirplane().getAirplaneID();
                    Airplane airplane = Airplane.getAirplaneInfo(airplane_id);

                    // 设置票的信息
                    ticket = TicketCollection.getTicketInfo(ticket_id);
                    ticket.setPassenger(passenger);
                    ticket.setTicket_id(ticket_id);
                    ticket.setFlight(flight);
                    ticket.setPrice(ticket.getPrice());
                    ticket.setClassVip(ticket.getClassVip());
                    ticket.setTicketStatus(true);

                    // 更新飞机座位数
                    if (ticket.getClassVip()) {
                        airplane.setBusinessSitsNumber(airplane.getBusinessSitsNumber() - 1);
                    } else {
                        airplane.setEconomySitsNumber(airplane.getEconomySitsNumber() - 1);
                    }

                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    // 读取支付信息
                    System.out.println("Enter your card number:");
                    String cardNumber = "";
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    Integer securityCode = 0;
                    passenger.setSecurityCode(securityCode);

                    double price = calculateTicketPrice(flight);
                    return "Ticket purchased successfully for $" + price;
                }
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
                return "Purchase failed due to invalid input.";
            }
        }
    }

    // 购买转机票的方法
    public void buyTicket(int ticket_id_first, int ticket_id_second) throws Exception {
        int flight_id_first = 0;
        int flight_id_second = 0;

        // 获取票的信息
        Ticket validTicketfirst = TicketCollection.getTicketInfo(ticket_id_first);
        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_second);

        // 验证票的有效性
        if (validTicketfirst == null || validTicketSecond == null) {
            System.out.println("This ticket does not exist.");
            return;
        } else {
            // 获取航班ID
            flight_id_first = validTicketfirst.getFlight().getFlightID();
            flight_id_second = validTicketSecond.getFlight().getFlightID();

            try {
                // 读取乘客信息
                System.out.println("Enter your First Name: ");
                String firstName = in.nextLine();
                passenger.setFirstName(firstName);

                System.out.println("Enter your Second name:");
                String secondName = in.nextLine();
                passenger.setSecondName(secondName); // 设置乘客信息

                System.out.println("Enter your age:");
                Integer age = in.nextInt();
                in.nextLine(); // 消耗换行符
                passenger.setAge(age);

                System.out.println("Enter your gender: ");
                String gender = in.nextLine();
                passenger.setGender(gender);

                System.out.println("Enter your e-mail address");
                String email = in.nextLine();
                passenger.setEmail(email);

                System.out.println("Enter your phone number (+7):");
                String phoneNumber = in.nextLine();
                passenger.setPhoneNumber(phoneNumber);

                System.out.println("Enter your passport number:");
                String passportNumber = in.nextLine();
                passenger.setPassport(passportNumber);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                int purch = in.nextInt();
                if (purch == 0) {
                    return;
                } else {
                    Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);
                    int airplane_id_first = flight_first.getAirplane().getAirplaneID();
                    Airplane airplane_first = Airplane.getAirplaneInfo(airplane_id_first);

                    Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);
                    int airplane_id_second = flight_second.getAirplane().getAirplaneID();
                    Airplane airpairplane_second = Airplane.getAirplaneInfo(airplane_id_second);

                    Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);
                    Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                    ticket_first.setPassenger(passenger);
                    ticket_first.setTicket_id(ticket_id_first);
                    ticket_first.setFlight(flight_first);
                    ticket_first.setPrice(ticket_first.getPrice());
                    ticket_first.setClassVip(ticket_first.getClassVip());
                    ticket_first.setTicketStatus(true);

                    if (ticket_first.getClassVip()) {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }

                    ticket_second.setPassenger(passenger);
                    ticket_second.setTicket_id(ticket_id_second);
                    ticket_second.setFlight(flight_first);
                    ticket_second.setPrice(ticket_second.getPrice());
                    ticket_second.setClassVip(ticket_second.getClassVip());
                    ticket_second.setTicketStatus(true);

                    if (ticket_second.getClassVip()) {
                        airpairplane_second.setBusinessSitsNumber(airpairplane_second.getBusinessSitsNumber() - 1);
                    } else {
                        airpairplane_second.setEconomySitsNumber(airpairplane_second.getEconomySitsNumber() - 1);
                    }

                    ticket.setPrice(ticket_first.getPrice() + ticket_second.getPrice());

                    System.out.println("Your bill: " + ticket.getPrice() + "\n");

                    System.out.println("Enter your card number:");
                    String cardNumber = in.next();
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    Integer securityCode = in.nextInt();
                    passenger.setSecurityCode(securityCode);
                }
            } catch (PatternSyntaxException patternException) {
                patternException.printStackTrace();
            }
        }
    }

    // 选择票的方法
    public void chooseTicket(String city1, String city2) throws Exception {
        int counter = 1; // 初始化计数器，用于记录转机次数
        int idFirst = 0; // 初始化第一个航班的ID
        int idSecond = 0; // 初始化第二个航班的ID

        Flight flight = new Flight(); // 创建一个新的 Flight 实例

        // 搜索从 city1 到 city2 的直达航班
        flight = FlightCollection.getFlightInfo(city1, city2);

        // 如果找到直达航班
        if (flight != null) {
            // 显示所有票的信息
            TicketCollection.getAllTickets();

            // 提示用户输入要选择的票的ID
            System.out.println("\nEnter ID of ticket you want to choose:");
            int ticket_id = in.nextInt(); // 读取用户输入的票ID

            // 调用 buyTicket 方法购买这张票
            buyTicket(ticket_id);
        } else {
            // 如果没有找到直达航班，寻找转机航班
            Flight depart_to = FlightCollection.getFlightInfo(city2);

            // 获取连接城市，即从 city1 出发到达 city2 的中转城市
            String connectCity = depart_to.getDepartFrom();
            // 搜索从 city1 到达连接城市的航班
            Flight flightConnectingTwoCities = FlightCollection.getFlightInfo(city1, connectCity);

            // 如果找到这样的中转航班
            if (flightConnectingTwoCities != null) {
                System.out.println("There is a special way to go there. And it is a transfer way, like above. Way №" + counter);

                // 获取两个航班的ID
                idFirst = depart_to.getFlightID();
                idSecond = flightConnectingTwoCities.getFlightID();

                // 递增计数器
                counter++;

                // 调用 buyTicket 方法购买这两张票
                buyTicket(idFirst, idSecond);
            }

            // 如果没有找到任何航班，输出提示信息
            if (counter == 1) {
                System.out.println("There are no possible variants.");
            }
            return;
        }
    }

        // 预订票的方法
    public void bookTicket(Ticket ticket) {
        TicketCollection.getTickets().add(ticket);
    }

    // 验证乘客信息的方法
    public boolean validatePassengerInformation(Passenger passenger) {
        return passenger.getEmail() != null && passenger.getPhoneNumber() != null;
    }

    // 验证航班信息的方法
    public boolean validateFlightInformation(Flight flight) {

        return flight.getDepartFrom() != null && flight.getDepartTo() != null;
    }

    // 验证票务信息的方法
    public boolean validateTicketInformation(Ticket ticket) {
        return ticket.getFlight() != null && ticket.getPassenger() != null;
    }

    // 计算票价的方法
    public double calculateTicketPrice(Flight flight) {
        return flight.getAirplane().getBusinessSitsNumber() * 10 + flight.getAirplane().getEconomySitsNumber() * 5;
    }

    public static void main(String[] args) {
        TicketSystem system = new TicketSystem();

        try {
            // 示例用法
            system.chooseTicket("Shanghai", "Beijing");
            system.buyTicket(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}