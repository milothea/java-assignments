package main;

import main.constants.Sectors;
import main.models.Price;
import main.ticketService.TicketService;

import java.time.LocalDateTime;
import java.util.Random;

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

                ticketService.buyTicket(
                        concertHalls[randomIdx],
                        eventCodes[randomIdx],
                        startTime, isPromo,
                        sectors[randomIdx],
                        maxWeights[randomIdx],
                        price
                );
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        ticketService.printTickets();
        System.out.println(separator);
        System.out.println("Print tickets by ID - 1, 3, 5");
        System.out.println(separator);
        ticketService.printTicketById(1);
        ticketService.printTicketById(3);
        ticketService.printTicketById(5);
    }
}
