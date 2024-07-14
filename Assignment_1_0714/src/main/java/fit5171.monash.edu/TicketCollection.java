package fit5171.monash.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TicketCollection {

    private static List<Ticket> tickets = new ArrayList<>();

    public static List<Ticket> getTickets() {
        return new ArrayList<>(tickets);  // 返回票据的副本以保持封装性
    }

    public static void addTicket(Ticket ticket) {
        // 确保票据不重复
        if (tickets.stream().anyMatch(t -> t.getTicket_id() == ticket.getTicket_id())) {
            throw new IllegalArgumentException("Ticket with this ID already exists.");
        }
        tickets.add(ticket);
    }

    public static Ticket getTicketInfo(int ticketId) {
        // 使用Optional防止null值
        return tickets.stream()
                .filter(ticket -> ticket.getTicket_id() == ticketId)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No ticket found with ID: " + ticketId));
    }

    public static void clearTickets() {
        tickets.clear();  // 清空票据列表
    }
    public static void getAllTickets(String city1, String city2) {
    // 筛选出所有 status 为 false 且符合 city1 到 city2 的票据
    List<Ticket> availableTickets = tickets.stream()
            .filter(ticket -> !ticket.ticketStatus() &&
                              ticket.getFlight().getDepartFrom().equals(city1) &&
                              ticket.getFlight().getDepartTo().equals(city2))
            .toList();

    // 如果没有可用的票据，则输出 "No tickets available"
    if (availableTickets.isEmpty()) {
        System.out.println("No tickets available.");
        return;
    }

    // 输出所有 status 为 false 且符合 city1 到 city2 的票据详细信息
    availableTickets.forEach(ticket -> {
        System.out.println("Ticket ID: " + ticket.getTicket_id() + ", Price: " + ticket.getPrice() +
                ", Flight From: " + ticket.getFlight().getDepartFrom() + " To: " + ticket.getFlight().getDepartTo() +
                ", Flight From: " + ticket.getFlight().getDateFrom() + " To: " + ticket.getFlight().getDateTo());
    });
}


}
