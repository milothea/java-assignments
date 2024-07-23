package main.models;

import main.constants.Sectors;
import main.constants.DefaultValues;

import java.time.LocalDateTime;
import java.util.Arrays;

public class Ticket {
    private static int ticketsCounter = 0;
    private final int id;
    private String concertHall = DefaultValues.EVENT_VENUE;
    private int eventCode = DefaultValues.EVENT_CODE;
    private LocalDateTime startTime;
    private boolean isPromo = DefaultValues.IS_PROMO;
    private char stadiumSector = DefaultValues.STADIUM_SECTOR;
    private int maxBackpackWeight = DefaultValues.MAX_BACKPACK_WEIGHT;
    private Price ticketPrice = new Price(DefaultValues.TICKET_PRICE);

    private final LocalDateTime orderDate;

    public Ticket() {
        if (!this.validateSector(stadiumSector)) {
            throw new IllegalArgumentException("Invalid stadium sector: " + stadiumSector + ". Please provide a valid sector from the list " + Arrays.toString(Sectors.values()));
        }

        if (concertHall.length() > 10) {
            throw new IllegalArgumentException("Concert hall name '" + concertHall + "' is too long. Please provide a name with 10 characters or less.");
        }

        if (Integer.toString(eventCode).length() != 3) {
            throw new IllegalArgumentException("Event code '" + eventCode + "' is incorrect. Please provide an event code containing exactly 3 digits.");
        }

        this.setTicketsCounter(getTicketsCounter() + 1);
        this.id = getTicketsCounter();
        this.orderDate = LocalDateTime.now();
    }

    public Ticket(String concertHall, int eventCode, LocalDateTime startTime) {
        this();
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.startTime = startTime;
    }

    public Ticket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, Price ticketPrice) {
        this(concertHall, eventCode, startTime);
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.ticketPrice = ticketPrice;
    }

    private boolean validateSector (char sector) {
        char[] validSectors = Sectors.values();

        return Arrays.binarySearch(validSectors, sector) >= 0;
    }

    public int getId() {
        return this.id;
    }

    public int getEventCode() {
        return this.eventCode;
    }

    public String getConcertHall() {
        return this.concertHall;
    }

    public char getStadiumSector() {
        return this.stadiumSector;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public boolean getIsPromo() {
        return this.isPromo;
    }

    public int getMaxBackpackWeight() {
        return this.maxBackpackWeight;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public Price getTicketPrice() {
        return this.ticketPrice;
    }

    private static int getTicketsCounter() {
        return ticketsCounter;
    }

    public void setTicketsCounter(int value) {
        this.ticketsCounter = value;
    }
}
