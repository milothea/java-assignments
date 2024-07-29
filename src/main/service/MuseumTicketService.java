package main.service;

import main.models.MuseumTicket;
import main.models.Price;
import main.models.Ticket;

import main.exceptions.ObjectNotFoundException;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class MuseumTicketService extends TicketPrinter implements TicketService {

    public MuseumTicketService() {}

    public MuseumTicket buyTicket() {
        return new MuseumTicket();
    }

    public MuseumTicket buyTicket(String museum, int eventCode, LocalDateTime startTime) {
        this.validator.validateVenueName(museum);
        this.validator.validateEventCode(eventCode);

        return new MuseumTicket(museum, eventCode, startTime);
    }

    public MuseumTicket buyTicket(String museum, int eventCode, LocalDateTime startTime, boolean isPromo, Price ticketPrice, String exhibitionName) {
        this.validator.validateVenueName(museum);
        this.validator.validateEventCode(eventCode);

        return new MuseumTicket(museum, eventCode, startTime, isPromo, ticketPrice, exhibitionName);
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
