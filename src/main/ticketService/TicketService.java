package main.ticketService;

import main.ticket.Ticket;

import java.time.LocalDateTime;

public class TicketService {
    public TicketService() {}

    public Ticket buyTicket() {
        return new Ticket();
    }

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime) {
        return new Ticket(concertHall, eventCode, startTime);
    }

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, double ticketPrice) {
        return new Ticket(concertHall, eventCode, startTime, isPromo, stadiumSector, maxBackpackWeight, ticketPrice);
    }

    private String parseDateToString(LocalDateTime date) {
        if (date == null) {
            return "TBC";
        }

        int minutes = date.getMinute();
        String parsedMinutes = minutes >= 10 ? Integer.toString(minutes) : '0' + Integer.toString(minutes);

        return date.getDayOfMonth() + "-" +
                date.getMonthValue() + "-" +
                date.getYear() + " " +
                date.getHour() + ":" +
                parsedMinutes;
    }

    public void printTicket(Ticket ticket) {
        LocalDateTime startTime = ticket.getStartTime();
        LocalDateTime orderDate = ticket.getOrderDate();
        String promoInfo = ticket.getIsPromo() ? " (promo ticket)" : "";

        System.out.println("Ticket ID: " + ticket.getId() + ". Event code: " + ticket.getEventCode());
        System.out.println("Venue:  " + ticket.getConcertHall() + ". Sector: " + ticket.getStadiumSector());
        System.out.println("Start time: " + this.parseDateToString(startTime));
        System.out.println("Maximum allowed backpack weight: " + ticket.getMaxBackpackWeight() + "g");
        System.out.println("Date of ticket purchase: " + this.parseDateToString(orderDate) + ". Price: " + ticket.getTicketPrice() + promoInfo);
        System.out.println("-------------------------------");
    }
}
