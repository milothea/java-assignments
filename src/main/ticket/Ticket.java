package main.ticket;

import java.time.LocalDateTime;
import main.constants.DefaultValues;

public class Ticket {
    private static int ticketsCounter = 0;
    private final int id;
    private String concertHall = DefaultValues.EVENT_VENUE;
    private int eventCode = DefaultValues.EVENT_CODE;
    private LocalDateTime startTime;
    private boolean isPromo = DefaultValues.IS_PROMO;
    private char stadiumSector = DefaultValues.STADIUM_SECTOR;
    private int maxBackpackWeight = DefaultValues.MAX_BACKPACK_WEIGHT;
    private double ticketPrice = DefaultValues.TICKET_PRICE;

    private final LocalDateTime orderDate;

    public Ticket() {
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

    public Ticket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, double ticketPrice) {
        this(concertHall, eventCode, startTime);
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
        this.ticketPrice = ticketPrice;
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

    public double getTicketPrice() {
        return this.ticketPrice;
    }
    private static int getTicketsCounter() {
        return ticketsCounter;
    }
    public void setTicketsCounter(int value) {
        this.ticketsCounter = value;
    }
}
