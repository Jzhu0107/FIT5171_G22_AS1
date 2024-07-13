package fit5171.monash.edu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

     public static void getAllTickets() {
     //display all available tickets from the Ticket collection
    }

    public static void clearTickets() {
        tickets.clear();  // 清空票据列表
    }
}
