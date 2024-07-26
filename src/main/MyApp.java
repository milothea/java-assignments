package main;

import main.constants.Sectors;
import main.ticket.Ticket;
import main.ticketService.TicketService;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MyApp {
    public static void main(String[] args) {
        String separator = "********";
        TicketService ticketService = new TicketService();

        String concertHall = "Cool place";
        int eventCode = 123;
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 1, 19, 30);
        boolean isPromo = true;
        int maxWeight = 1500;
        double price = 7.50;

        char[] sectors = Sectors.values();

        try {
            for(int i = 0; i < 10; i++) {
                int randomIdx = (int) (Math.random() * sectors.length);
                char sector = sectors[randomIdx];
                Ticket ticket;

                if (i == 0) {
                    ticket = ticketService.buyTicket();
                } else if (i % 2 == 0) {
                    ticket = ticketService.buyTicket(concertHall, eventCode, startTime);
                } else {
                    ticket = ticketService.buyTicket(concertHall, eventCode, startTime, isPromo, sector, maxWeight, price);
                }

                System.out.println("Ticket bought: ID " + ticket.getId());
            }

            ArrayList<Ticket> ticketsA= ticketService.getTicketsBySector(Sectors.A);
            ArrayList<Ticket> ticketsB= ticketService.getTicketsBySector(Sectors.B);
            ArrayList<Ticket> ticketsC= ticketService.getTicketsBySector(Sectors.C);

            System.out.println(separator);
            System.out.println("Tickets by sector A");
            System.out.println(separator);
            ticketService.printListOfTickets(ticketsA);
            System.out.println("Tickets by sector B");
            System.out.println(separator);
            ticketService.printListOfTickets(ticketsB);
            System.out.println("Tickets by sector C");
            System.out.println(separator);
            ticketService.printListOfTickets(ticketsC);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
