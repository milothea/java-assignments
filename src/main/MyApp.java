package main;

import main.constants.Sectors;
import main.models.MuseumTicket;
import main.models.Price;
import main.models.FootballTicket;
import main.models.Ticket;
import main.models.Admin;
import main.models.Client;
import main.service.FootballTicketService;
import main.service.MuseumTicketService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class MyApp {
    public static void main(String[] args) {
        FootballTicketService footballTicketService = new FootballTicketService();
        MuseumTicketService museumTicketService = new MuseumTicketService();
        ArrayList<Ticket> soldFootballTickets = new ArrayList<>();
        ArrayList<Ticket> soldMuseumTickets = new ArrayList<>();
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 1, 19, 30);
        String[] stadiums = {"PGE", "Dinamo", "Stadium"};
        String[] museums = {"Louvre", "POLIN", "Hermitage"};
        String[] exhibitions = {"Mona Lisa", "History of Poland", "The Winter Palace"};
        int[] maxWeights = {1000, 2100, 3500};
        int[] eventCodes = {123, 456, 789};
        int optionsNumber = stadiums.length;
        char[] sectors = Sectors.values();
        Admin admin = new Admin();
        Client client = new Client();

        admin.printRole();
        client.printRole();

        try {
            for (int i = 0; i < 10; i++) {
                int randomIdx = (int) (Math.random() * optionsNumber);
                String stadium = stadiums[randomIdx];
                int eventCode = eventCodes[randomIdx];
                String museum = museums[randomIdx];
                FootballTicket footballTicket;
                MuseumTicket museumTicket;

                if (i == 0) {
                    footballTicket = footballTicketService.buyTicket();
                    museumTicket = museumTicketService.buyTicket();
                } else if (i % 2 == 0) {
                    footballTicket = footballTicketService.buyTicket(stadium, eventCode, startTime);
                    museumTicket = museumTicketService.buyTicket(museum, eventCode, startTime);

                    footballTicket.shared(123456789);
                } else {
                    boolean isPromo = randomIdx % 2 == 0;
                    int maxWeight = maxWeights[randomIdx];
                    char sector = sectors[randomIdx];
                    String exhibition = exhibitions[randomIdx];
                    BigDecimal priceAmount = new BigDecimal(7.50 + optionsNumber);

                    footballTicket = footballTicketService.buyTicket(stadium, eventCode, startTime, isPromo, sector,
                            maxWeight, new Price(priceAmount));
                    museumTicket = museumTicketService.buyTicket(museum, eventCode, startTime, isPromo, new Price(priceAmount), exhibition);

                    footballTicket.shared("test@test.test");
                }

                soldFootballTickets.add(footballTicket);
                soldMuseumTickets.add(museumTicket);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        footballTicketService.printTickets(soldFootballTickets);
        museumTicketService.printTickets(soldMuseumTickets);

        try {
            Ticket ticket = footballTicketService.getTicketById(3, soldFootballTickets);

            admin.checkTicket(ticket, soldFootballTickets);
            client.getTicket();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
