package main.service;

import main.exceptions.ObjectNotFoundException;
import main.models.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface TicketService {
    ValidationService validator = new ValidationServiceImpl();

    Ticket buyTicket();

    Ticket buyTicket(String venue, int eventCode, LocalDateTime startTime);

    Ticket getTicketById(int id, ArrayList<Ticket> soldTickets) throws ObjectNotFoundException;
}
