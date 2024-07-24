package main.ticketService;

import main.ticket.Ticket;
import main.constants.Sectors;

import java.util.ArrayList;
import java.util.Arrays;
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

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, double ticketPrice) {
        if (!this.validateSector(stadiumSector)) {
            throw new IllegalArgumentException("Invalid stadium sector: " + stadiumSector + ". Please provide a valid sector from the list " + Arrays.toString(Sectors.values()));
        }

        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Concert hall name '" + concertHall + "' is too long. Please provide a name with 10 characters or less.");
        }

        if (Integer.toString(eventCode).length() != 3) {
            throw new IllegalArgumentException("Event code '" + eventCode + "' is incorrect. Please provide an event code containing exactly 3 digits.");
        }



        Ticket newTicket = new Ticket(concertHall, eventCode, startTime, isPromo, stadiumSector, maxBackpackWeight, ticketPrice);

        this.addTicketToSoldTickets(newTicket);

        return newTicket;
    }

    private void addTicketToSoldTickets(Ticket ticket) {this.soldTickets.add(ticket);}

    public Ticket getTicketBySector(char stadiumSector) {
        for (Ticket ticket : this.soldTickets) {
            if (ticket.getStadiumSector() == stadiumSector) return ticket;
        }

        System.out.println("Ticket with ID " + stadiumSector + " not found.");
        return null;
    }


    private boolean validateSector (char sector) {
        char[] validSectors = Sectors.values();

        return Arrays.binarySearch(validSectors, sector) >= 0;
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
