package main;

import main.ticket.Ticket;
import main.ticketService.TicketService;

import java.time.LocalDateTime;

public class MyApp {
    public static void main(String[] args) {
        TicketService ticketService = new TicketService();

        String concertHall = "Cool place";
        int eventCode = 123;
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 1, 19, 30);
        boolean isPromo = true;
        char stadiumSector = 'B';
        int maxWeight = 1500;
        double price = 7.50;

        Ticket emptyTicket = ticketService.buyTicket();
        Ticket limitedTicket = ticketService.buyTicket(concertHall, eventCode, startTime);
        Ticket fullTicket = ticketService.buyTicket(concertHall, eventCode, startTime, isPromo, stadiumSector, maxWeight, price);

        System.out.println("Ticket created with no arguments and filled with default values:");
        ticketService.printTicket(emptyTicket);
        System.out.println("Ticket created with limited number of arguments and supplemented with default values:");
        ticketService.printTicket(limitedTicket);
        System.out.println("Ticket created with full list of arguments:");
        ticketService.printTicket(fullTicket);

        try {
            int wrongEventCode = 4567;

            System.out.println("Ticket created with wrong number of digits for event code:");
            ticketService.buyTicket(concertHall, wrongEventCode, startTime, isPromo, stadiumSector, maxWeight, price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            String wrongConcertHall = "Some super cool place with a very long name";

            System.out.println("Ticket created with too long concert hall name:");
            ticketService.buyTicket(wrongConcertHall, eventCode, startTime, isPromo, stadiumSector, maxWeight, price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            char wrongStadiumSector = 'D';

            System.out.println("Ticket created with a wrong stadium sector:");
            ticketService.buyTicket(concertHall, eventCode, startTime, isPromo, wrongStadiumSector, maxWeight, price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }


    }
}
