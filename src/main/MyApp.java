package main;

import main.constants.Sectors;
import main.constants.SeparatorMessage;
import main.models.Price;
import main.models.Ticket;
import main.service.TicketService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class MyApp {
    public static void main(String[] args) {
        TicketService ticketService = new TicketService();
        ArrayList<Ticket> soldTickets = new ArrayList<>();
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 1, 19, 30);
        String[] concertHalls = {"Concert hall", "Nightclub", "Stadium"};
        int[] maxWeights = {1000, 2100, 3500};
        int[] eventCodes = {123, 456, 789};
        int optionsNumber = concertHalls.length;
        char[] sectors = Sectors.values();

        try {
            for (int i = 0; i < 10; i++) {
                int randomIdx = (int) (Math.random() * optionsNumber);
                String concertHall = concertHalls[randomIdx];
                int eventCode = eventCodes[randomIdx];
                Ticket ticket;

                if (i == 0) {
                    ticket = ticketService.buyTicket();
                } else if (i % 2 == 0) {
                    ticket = ticketService.buyTicket(concertHall, eventCode, startTime);
                } else {
                    boolean isPromo = randomIdx % 2 == 0;
                    int maxWeight = maxWeights[randomIdx];
                    char sector = sectors[randomIdx];
                    BigDecimal priceAmount = new BigDecimal(7.50 + optionsNumber);

                    ticket = ticketService.buyTicket(concertHall, eventCode, startTime, isPromo, sector,
                            maxWeight, new Price(priceAmount));
                }

                soldTickets.add(ticket);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            ArrayList<Ticket> ticketsA= ticketService.getTicketsBySector(Sectors.A, soldTickets);
            ArrayList<Ticket> ticketsB= ticketService.getTicketsBySector(Sectors.B, soldTickets);
            ArrayList<Ticket> ticketsC= ticketService.getTicketsBySector(Sectors.C, soldTickets);

            System.out.println(SeparatorMessage.getSeparator());
            System.out.println("Tickets by sector A");
            System.out.println(SeparatorMessage.getSeparator());
            ticketService.printListOfTickets(ticketsA);
            System.out.println("Tickets by sector B");
            System.out.println(SeparatorMessage.getSeparator());
            ticketService.printListOfTickets(ticketsB);
            System.out.println("Tickets by sector C");
            System.out.println(SeparatorMessage.getSeparator());
            ticketService.printListOfTickets(ticketsC);
            System.out.println(SeparatorMessage.getSeparator());

            Ticket ticketOne = ticketService.getTicketById(1, soldTickets);
            Ticket ticketThree = ticketService.getTicketById(3, soldTickets);
            Ticket ticketFive = ticketService.getTicketById(5, soldTickets);

            System.out.println("Print tickets by ID - 1, 3, 5");
            System.out.println(SeparatorMessage.getSeparator());

            ticketService.printTicket(ticketOne);
            ticketService.printTicket(ticketThree);
            ticketService.printTicket(ticketFive);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
