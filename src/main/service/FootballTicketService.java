package main.service;

import main.models.FootballTicket;
import main.models.Price;

import main.exceptions.ObjectNotFoundException;
import main.models.Ticket;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class FootballTicketService extends TicketPrinter implements TicketService {
    public FootballTicketService() {}

    public FootballTicket buyTicket() {
        return new FootballTicket();
    }

    public FootballTicket buyTicket(String stadium, int eventCode, LocalDateTime startTime) {
        this.validator.validateVenueName(stadium);
        this.validator.validateEventCode(eventCode);

        return new FootballTicket(stadium, eventCode, startTime);
    }

    public FootballTicket buyTicket(String stadium, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, Price ticketPrice) {
        this.validator.validateSector(stadiumSector);
        this.validator.validateVenueName(stadium);
        this.validator.validateEventCode(eventCode);

        return new FootballTicket(stadium, eventCode, startTime, isPromo, stadiumSector, maxBackpackWeight, ticketPrice);
    }

    public Ticket getTicketById(int id, ArrayList<Ticket> soldTickets) throws ObjectNotFoundException {
        for (Ticket ticket : soldTickets) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        throw new ObjectNotFoundException("Ticket with ID " + id + " not found.");
    }
}
