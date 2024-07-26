package main;

import main.models.Ticket;
import main.constants.Sectors;
import main.models.Price;
import main.ticketService.TicketService;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.ArrayList;

public class MyApp {
    public static void main(String[] args) {
        String separator = "*******************";
        Random random = new Random();
        TicketService ticketService = new TicketService();
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 1, 19, 30);
        String[] concertHalls = {"Concert hall", "Nightclub", "Stadium"};
        int[] maxWeights = {1000, 2100, 3500};
        int[] eventCodes = {123, 456, 789};
        char[] sectors = Sectors.values();

        try {
            for (int i = 0; i < 10; i++) {
                Price price = new Price(7.50 + random.nextInt(10));
                int randomIdx = random.nextInt(3);
                boolean isPromo = randomIdx % 2 == 0;
                String concertHall = concertHalls[randomIdx];
                int eventCode = eventCodes[randomIdx];
                char sector = sectors[randomIdx];
                int maxWeight = maxWeights[randomIdx];
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
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
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
            System.out.println(separator);
            System.out.println("Print tickets by ID - 1, 3, 5");
            System.out.println(separator);
            ticketService.printTicketById(1);
            ticketService.printTicketById(3);
            ticketService.printTicketById(5);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
