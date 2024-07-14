package fit5171.monash.edu;

import java.util.Scanner;

public class TicketSystem {

    Passenger passenger;
    Ticket ticket;
    Flight flight;
    Scanner in;

    public TicketSystem() {
        // 构造方法，用于初始化实例变量
        passenger = new Passenger();
        ticket = new Ticket(123456, 300, null, false, passenger);
        flight = new Flight();
        in = new Scanner(System.in);
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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
            System.out.println("This ticket does not exist.");
            throw new Exception("This ticket does not exist.");
        } else {
            // 获取航班ID
            flight_id = validTicket.getFlight().getFlightID();
            if (validTicket.status) {
                System.out.println("Ticket already booked");
            throw new Exception("Ticket already booked");
            } else {
                // Passenger inputPassenger = inputPassengerInformation();
                // validatePassengerInformation(validTicket.getPassenger(), inputPassenger);
                Passenger passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
                Passenger inputPassenger = passenger1;
                validatePassengerInformation(validTicket.getPassenger(), inputPassenger);

                    // 询问用户是否购买
                    System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                    // int purch = in.nextInt();
                    int purch = 1; // 测试时默认购买
                    if (purch == 0) {
                        System.out.println("Purchase cancelled.");
                        throw new Exception("Purchase cancelled."); // 用户选择不购买
                    } else {
                        // 获取航班信息
                        flight = FlightCollection.getFlightInfo(flight_id);
                        assert flight != null;
                        int airplane_id = flight.getAirplane().getAirplaneID();
                        Airplane airplane = Airplane.getAirplaneInfo(airplane_id);

                        // 设置票的信息
                        ticket = TicketCollection.getTicketInfo(ticket_id);
                        ticket.setPassenger(inputPassenger);
                        ticket.setTicket_id(ticket_id);
                        ticket.setFlight(flight);
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


                        return "Ticket purchased successfully for $" + (double)ticket.getPrice();
                    }
            }
        }
    }

    // 购买转机票的方法
    public String buyTicket2(int ticket_id_first, int ticket_id_second) throws Exception {
        int flight_id_first = 0;
        int flight_id_second = 0;

        // 获取票的信息
        Ticket validTicketfirst = TicketCollection.getTicketInfo(ticket_id_first);
        Ticket validTicketSecond = TicketCollection.getTicketInfo(ticket_id_second);

        // 验证票的有效性
        if (validTicketfirst == null || validTicketSecond == null) {
            System.out.println("This ticket does not exist.");
            throw new Exception("This ticket does not exist.");
        } else {
            // 获取航班ID
            flight_id_first = validTicketfirst.getFlight().getFlightID();
            flight_id_second = validTicketSecond.getFlight().getFlightID();

            // Passenger inputPassenger = inputPassengerInformation();
            Passenger passenger1 = new Passenger("John", "Doe", 30, "Man", "john.doe@example.com", "0412345678", "AB1234567", "1234567890123456", 123);
                Passenger inputPassenger = passenger1;
                validatePassengerInformation(validTicketfirst.getPassenger(), inputPassenger);
                validatePassengerInformation(validTicketSecond.getPassenger(), inputPassenger);

                System.out.println("Do you want to purchase?\n 1-YES 0-NO");
                // int purch = in.nextInt();
                int purch = 1; // 测试时默认购买
                if (purch == 0) {
                    System.out.println("Purchase cancelled.");
                        throw new Exception("Purchase cancelled."); // 用户选择不购买
                } else {
                    Flight flight_first = FlightCollection.getFlightInfo(flight_id_first);
                    int airplane_id_first = flight_first.getAirplane().getAirplaneID();
                    Airplane airplane_first = Airplane.getAirplaneInfo(airplane_id_first);

                    Flight flight_second = FlightCollection.getFlightInfo(flight_id_second);
                    int airplane_id_second = flight_second.getAirplane().getAirplaneID();
                    Airplane airpairplane_second = Airplane.getAirplaneInfo(airplane_id_second);

                    Ticket ticket_first = TicketCollection.getTicketInfo(ticket_id_first);
                    Ticket ticket_second = TicketCollection.getTicketInfo(ticket_id_second);

                    ticket_first.setPassenger(inputPassenger);
                    ticket_first.setTicket_id(ticket_id_first);
                    ticket_first.setFlight(flight_first);
                    ticket_first.setClassVip(ticket_first.getClassVip());
                    ticket_first.setTicketStatus(true);

                    if (ticket_first.getClassVip()) {
                        airplane_first.setBusinessSitsNumber(airplane_first.getBusinessSitsNumber() - 1);
                    } else {
                        airplane_first.setEconomySitsNumber(airplane_first.getEconomySitsNumber() - 1);
                    }

                    ticket_second.setPassenger(inputPassenger);
                    ticket_second.setTicket_id(ticket_id_second);
                    ticket_second.setFlight(flight_first);
                    ticket_second.setClassVip(ticket_second.getClassVip());
                    ticket_second.setTicketStatus(true);

                    if (ticket_second.getClassVip()) {
                        airpairplane_second.setBusinessSitsNumber(airpairplane_second.getBusinessSitsNumber() - 1);
                    } else {
                        airpairplane_second.setEconomySitsNumber(airpairplane_second.getEconomySitsNumber() - 1);
                    }

                    System.out.println("Your bill: " + (double)(ticket_first.getPrice() + ticket_second.getPrice()) + "\n");

                    System.out.println("Enter your card number:");
                    String cardNumber = "";
                    passenger.setCardNumber(cardNumber);

                    System.out.println("Enter your security code:");
                    Integer securityCode = 0;
                    passenger.setSecurityCode(securityCode);

                    return "Ticket purchased successfully for $" + (double)(ticket_first.getPrice() + ticket_second.getPrice());
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
            TicketCollection.getAllTickets(city1, city2);

            // 提示用户输入要选择的票的ID
            // System.out.println("\nEnter ID of ticket you want to choose:");
            // int ticket_id = in.nextInt(); // 读取用户输入的票ID
            System.out.println("\nEnter ID of ticket you want to choose: 123456");
            int ticket_id = 12345678; // 测试默认购买12345678

            // 调用 buyTicket 方法购买这张票
            buyTicket(ticket_id);
            throw new Exception("Successfully purchased a direct ticket.");
        } else {
            // 如果没有找到直达航班，寻找转机航班
            Flight depart_to = FlightCollection.getFlightInfo(city2);

            // 获取连接城市，即从 city1 出发到达 city2 的中转城市
            if (depart_to != null) {
                String connectCity = depart_to.getDepartFrom();
                Flight flightConnectingTwoCities = FlightCollection.getFlightInfo(city1, connectCity);


                // 如果找到这样的中转航班
                if (flightConnectingTwoCities != null) {
                    System.out.println("There is a special way to go there. And it is a transfer way, like above. '" + city1 + "'--'" + connectCity + "'--'" + city2 + "'");

                    // 获取两个航班的ID
                    idFirst = depart_to.getFlightID();
                    idSecond = flightConnectingTwoCities.getFlightID();

                    // 显示第一段航班的所有未预定的票据
                    System.out.println("\nAvailable tickets for the first leg (from " + city1 + " to " + connectCity + "):");
                    TicketCollection.getAllTickets(city1, connectCity);

                    // System.out.println("\nEnter ID of the first ticket you want to choose:");
                    // int ticket_id_first = in.nextInt(); // 读取用户输入的第一张票ID
                    System.out.println("\nEnter ID of the first ticket you want to choose: 1234567");
                    int ticket_id_first = 1234567; // 读取用户输入的第一张票ID

                    // 验证第一张票据是否属于第一段航班
                    Ticket ticketFirst = TicketCollection.getTicketInfo(ticket_id_first);
                    if (ticketFirst == null || ticketFirst.getFlight().getFlightID() != flightConnectingTwoCities.getFlightID()) {
                        System.out.println("This ticket does not belong to the first leg of the journey.");
                        return;
                    }

                    // 显示第二段航班的所有未预定的票据
                    System.out.println("\nAvailable tickets for the second leg (from " + connectCity + " to " + city2 + "):");
                    TicketCollection.getAllTickets(connectCity, city2);

                    // System.out.println("\nEnter ID of the second ticket you want to choose:");
                    // int ticket_id_second = in.nextInt(); // 读取用户输入的第二张票ID
                    System.out.println("\nEnter ID of the second ticket you want to choose: 123456");
                    int ticket_id_second = 123456; // 读取用户输入的第二张票ID

                    // 验证第二张票据是否属于第二段航班
                    Ticket ticketSecond = TicketCollection.getTicketInfo(ticket_id_second);
                    if (ticketSecond == null || ticketSecond.getFlight().getFlightID() != depart_to.getFlightID()) {
                        System.out.println("This ticket does not belong to the second leg of the journey.");
                        return;
                    }

                    // 递增计数器
                    counter++;

                    // 调用 buyTicket 方法购买这两张票
                    buyTicket2(ticket_id_first, ticket_id_second);
                    throw new Exception("Successfully purchased transfer tickets.");
                }
            }

            // 如果没有找到任何航班，输出提示信息
            if (counter == 1) {
                System.out.println("There are no possible variants.");
                throw new Exception("There are no possible variants.");
            }
            return;
        }
    }

    // 预订票的方法
    public void bookTicket(Ticket ticket) {
        ticket.setTicketStatus(true);
    }

    private Passenger inputPassengerInformation() {
        Passenger passenger = new Passenger();

        System.out.println("Enter your First Name: ");
        String firstName = in.next();
        passenger.setFirstName(firstName);

        System.out.println("Enter your Second name:");
        String secondName = in.next();
        passenger.setSecondName(secondName);

        System.out.println("Enter your age:");
        Integer age = in.nextInt();
        passenger.setAge(age);

        System.out.println("Enter your gender: ");
        String gender = in.next();
        passenger.setGender(gender);

        System.out.println("Enter your e-mail address");
        String email = in.next();
        passenger.setEmail(email);

        System.out.println("Enter your phone number (+7):");
        String phoneNumber = in.next();
        passenger.setPhoneNumber(phoneNumber);

        System.out.println("Enter your passport number:");
        String passportNumber = in.next();
        passenger.setPassport(passportNumber);

        return passenger;
    }


    public boolean validatePassengerInformation(Passenger ticketPassenger, Passenger inputPassenger) throws Exception {
        if (ticketPassenger == null || inputPassenger == null) {
            System.out.println("Passenger information does not match.");
            throw new IllegalArgumentException("Passenger information does not match");
        }
        if (!ticketPassenger.getFirstName().equals(inputPassenger.getFirstName()) ||
                !ticketPassenger.getSecondName().equals(inputPassenger.getSecondName()) ||
                ticketPassenger.getAge() != inputPassenger.getAge() ||
                !ticketPassenger.getGender().equals(inputPassenger.getGender()) ||
                !ticketPassenger.getEmail().equals(inputPassenger.getEmail()) ||
                !ticketPassenger.getPhoneNumber().equals(inputPassenger.getPhoneNumber()) ||
                !ticketPassenger.getPassport().equals(inputPassenger.getPassport())) {
            System.out.println("Passenger information does not match.");
            throw new IllegalArgumentException("Passenger information does not match");
        }else return true;
    }



    public boolean validateFlightInformation(Flight flight) throws Exception {
        if (flight == null) {
            System.out.println("Flight information is missing.");
            throw new IllegalArgumentException("Flight information is missing");
        }
        if (flight.getDepartFrom() == null || flight.getDepartFrom().isEmpty() ||
            flight.getDepartTo() == null || flight.getDepartTo().isEmpty() ||
            flight.getCode() == null || flight.getCode().isEmpty() ||
            flight.getCompany() == null || flight.getCompany().isEmpty() ||
            flight.getDateFrom() == null || flight.getDateTo() == null ||
            flight.getAirplane() == null) {
            System.out.println("Invalid flight information provided.");
            throw new IllegalArgumentException("Invalid flight information provided");
        }
        return true;
    }

    public boolean validateTicketInformation(Ticket ticket) throws Exception {
        if (ticket == null) {
            System.out.println("Ticket information is missing.");
            throw new IllegalArgumentException("Ticket information is missing");
        }
        if (ticket.getFlight() == null ||
            ticket.getPassenger() == null ||
            ticket.getTicket_id() <= 0 ||
            ticket.getPrice() < 0) {
            System.out.println("Invalid ticket information provided.");
            throw new IllegalArgumentException("Invalid ticket information provided");
        }
        return true;
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