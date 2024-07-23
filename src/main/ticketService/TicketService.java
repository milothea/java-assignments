package main.ticketService;

import main.models.Ticket;
import main.models.Price;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class TicketService {
    ArrayList<Ticket> soldTickets = new ArrayList<>();
    public TicketService() {}

    public Ticket buyTicket() {
        Ticket newTicket = new Ticket();

        this.addTicketToSoldTickets(newTicket);

        return newTicket;
    }

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime) {
        Ticket newTicket = new Ticket(concertHall, eventCode, startTime);

        this.addTicketToSoldTickets(newTicket);

        return newTicket;
    }

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, Price ticketPrice) {

        Ticket newTicket = new Ticket(concertHall, eventCode, startTime, isPromo, stadiumSector, maxBackpackWeight, ticketPrice);

        this.addTicketToSoldTickets(newTicket);

        return newTicket;
    }

    public Ticket getTicketById(int id) {
        for (Ticket ticket : this.soldTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        System.out.println("Ticket with ID " + id + " not found.");
        return null;
    }

    private void addTicketToSoldTickets(Ticket ticket) {
        this.soldTickets.add(ticket);
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
