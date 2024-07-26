package main.service;

import main.constants.Sectors;
import main.models.Ticket;
import main.models.Price;

import main.exceptions.ObjectNotFoundException;
import main.exceptions.InvalidArgumentException;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TicketService {

    public TicketService() {}

    public Ticket buyTicket() {
        return new Ticket();
    }

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime) {
        this.validateHallName(concertHall);
        this.validateEventCode(eventCode);

        return new Ticket(concertHall, eventCode, startTime);
    }

    public Ticket buyTicket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, Price ticketPrice) {
        this.validateSector(stadiumSector);
        this.validateHallName(concertHall);
        this.validateEventCode(eventCode);

        return new Ticket(concertHall, eventCode, startTime, isPromo, stadiumSector, maxBackpackWeight, ticketPrice);
    }

    private void validateSector (char sector) throws InvalidArgumentException {
        char[] validSectors = Sectors.values();

        if (Arrays.binarySearch(validSectors, sector) == -1) {
            throw new InvalidArgumentException("Invalid stadium sector: " + sector +
                    ". Please provide a valid sector from the list " + Arrays.toString(Sectors.values()));
        }
    }

    private void validateHallName(String concertHall) throws InvalidArgumentException {
        if (concertHall.length() > 10) {
            throw new InvalidArgumentException("Concert hall name '" + concertHall +
                    "' is too long. Please provide a name with 10 characters or less.");
        }
    }

    private void validateEventCode(int eventCode) throws InvalidArgumentException {
        if (Integer.toString(eventCode).length() != 3) {
            throw new InvalidArgumentException("Event code '" + eventCode +
                    "' is incorrect. Please provide an event code containing exactly 3 digits.");
        }
    }

    public Ticket getTicketById(int id, ArrayList<Ticket> soldTickets) {
        for (Ticket ticket : soldTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        throw new ObjectNotFoundException("Ticket with ID " + id + " not found.");
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

    public ArrayList<Ticket> getTicketsBySector(char sector, ArrayList<Ticket> soldTickets) {
        ArrayList<Ticket> ticketsBySector = new ArrayList<>();

        for(Ticket ticket : soldTickets) {
            if (ticket.getStadiumSector() == sector) {
                ticketsBySector.add(ticket);
            }
        }

        return ticketsBySector;
    }

    public void printTicket(Ticket ticket) {
        LocalDateTime startTime = ticket.getStartTime();
        LocalDateTime orderDate = ticket.getOrderDate();
        String promoInfo = ticket.getIsPromo() ? " (promo ticket)" : "";

        System.out.println("Ticket ID: " + ticket.getId() + ". Event code: " + ticket.getEventCode());
        System.out.println("Venue:  " + ticket.getConcertHall() + ". Sector: " + ticket.getStadiumSector());
        System.out.println("Start time: " + this.parseDateToString(startTime));
        System.out.println("Maximum allowed backpack weight: " + ticket.getMaxBackpackWeight() + "g");
        System.out.println("Date of ticket purchase: " + this.parseDateToString(orderDate) + ". Price: " + ticket.getTicketPrice().getAmount() + promoInfo);
        System.out.println("-------------------------------");
    }

    public void printListOfTickets(ArrayList<Ticket> tickets) {
        if (tickets.size() == 0) {
            System.out.println("No tickets found.");
        } else {
            for (Ticket ticket : tickets) {
                this.printTicket(ticket);
            }
        }
    }
}
