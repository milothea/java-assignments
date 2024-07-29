package main.models;

import main.constants.DefaultValues;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Ticket {
    private final int id;
    private static int ticketsCounter = 0;
    private String venuePlace = DefaultValues.EVENT_VENUE;
    private int eventCode = DefaultValues.EVENT_CODE;
    protected LocalDateTime startTime;
    private boolean isPromo = DefaultValues.IS_PROMO;
    private Price ticketPrice = new Price(DefaultValues.TICKET_PRICE);

    private final LocalDateTime orderDate;

    public Ticket() {
        this.setTicketsCounter(getTicketsCounter() + 1);
        this.id = getTicketsCounter();
        this.orderDate = LocalDateTime.now();
    }

    public Ticket(String venuePlace, int eventCode, LocalDateTime startTime) {
        this();
        this.venuePlace = venuePlace;
        this.eventCode = eventCode;
        this.startTime = startTime;
    }

    public Ticket(String venuePlace, int eventCode, LocalDateTime startTime, boolean isPromo, Price ticketPrice) {
        this(venuePlace, eventCode, startTime);
        this.isPromo = isPromo;
        this.ticketPrice = ticketPrice;
    }

    public int getId() {
        return this.id;
    }

    public int getEventCode() {
        return this.eventCode;
    }

    public String getVenuePlace() {
        return this.venuePlace;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public boolean getIsPromo() {
        return this.isPromo;
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

    private void setTicketsCounter(int value) {
        this.ticketsCounter = value;
    }

    protected String parseDateToString(LocalDateTime date) {
        if (date == null) {
            return "TBC";
        }

        int minutes = date.getMinute();
        String parsedMinutes = minutes >= 10 ? Integer.toString(minutes) : '0' + Integer.toString(minutes);

        return date.getDayOfMonth() + "-" +
                date.getMonthValue() + "-" +
                date.getYear() + " " +
                date.getHour() + ":" +
                parsedMinutes;
    }

    public void shared(String mail) {
        System.out.println("Ticket with ID " + this.getId() + " is shared by mail: " + mail);
    }

    public void shared(int phone) {
        System.out.println("Ticket with ID " + this.getId() + " is shared by phone: " + phone);
    }

    public abstract Ticket getTicketData();

    public abstract void print();

    @Override
    public String toString() {
        return "Ticket with ID " + this.getId() + " for event No. " + this.getEventCode();
    }

    public int hashCode() {
        return Objects.hash(id, venuePlace, eventCode, startTime, isPromo, orderDate, ticketPrice);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        Ticket ticket = (Ticket) obj;

        return this.id == ticket.id &&
                this.venuePlace.equals(ticket.venuePlace) &&
                this.eventCode == ticket.eventCode &&
                this.startTime.equals(ticket.startTime) &&
                this.isPromo == ticket.isPromo &&
                this.orderDate.equals(ticket.orderDate) &&
                this.ticketPrice.equals(ticket.ticketPrice);
    }
}
