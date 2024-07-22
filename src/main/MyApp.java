package main;

import main.ticket.Ticket;
import main.ticketService.TicketService;

import java.time.LocalDateTime;

public class MyApp {
    public static void main(String[] args) {
        TicketService ticketService = new TicketService();

        String eventVenue = "Custom cool place";
        int eventCode = 123;
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 1, 19, 30);
        boolean isPromo = true;
        char stadiumSector = 'D';
        int maxWeight = 1500;
        double price = 7.50;

        Ticket emptyTicket = ticketService.buyTicket();
        Ticket limitedTicket = ticketService.buyTicket(eventVenue, eventCode, startTime);
        Ticket fullTicket = ticketService.buyTicket(eventVenue, eventCode, startTime, isPromo, stadiumSector, maxWeight, price);

        ticketService.printTicket(emptyTicket);
        ticketService.printTicket(limitedTicket);
        ticketService.printTicket(fullTicket);
    }
}
