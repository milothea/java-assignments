package main.ticketService;

import main.ticket.Ticket;
import main.constants.Sectors;
import java.util.Arrays;
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
        if (!this.validateSector(stadiumSector)) {
            throw new IllegalArgumentException("Invalid stadium sector: " + stadiumSector + ". Please provide a valid sector from the list " + Arrays.toString(Sectors.values()));
        }

        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Concert hall name '" + concertHall + "' is too long. Please provide a name with 10 characters or less.");
        }

        if (Integer.toString(eventCode).length() > 3) {
            throw new IllegalArgumentException("Event code '" + eventCode + "' is too long. Please provide an event code containing up to 3 digits.");
        }



        return new Ticket(concertHall, eventCode, startTime, isPromo, stadiumSector, maxBackpackWeight, ticketPrice);
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
