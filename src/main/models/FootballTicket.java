package main.models;

import main.constants.DefaultValues;

import java.time.LocalDateTime;

public class FootballTicket extends Ticket {
    private char stadiumSector = DefaultValues.STADIUM_SECTOR;
    private int maxBackpackWeight = DefaultValues.MAX_BACKPACK_WEIGHT;
    public FootballTicket() {
        super();
    }

    public FootballTicket(String concertHall, int eventCode, LocalDateTime startTime) {
        super(concertHall, eventCode, startTime);
    }

    public FootballTicket(String concertHall, int eventCode, LocalDateTime startTime, boolean isPromo, char stadiumSector, int maxBackpackWeight, Price ticketPrice) {
        super(concertHall, eventCode, startTime, isPromo, ticketPrice);
        this.stadiumSector = stadiumSector;
        this.maxBackpackWeight = maxBackpackWeight;
    }

    public char getStadiumSector() {
        return this.stadiumSector;
    }

    public int getMaxBackpackWeight() {
        return this.maxBackpackWeight;
    }

    public void setStadiumSector(char stadiumSector) {
        this.stadiumSector = stadiumSector;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    @Override
    public void print() {
        LocalDateTime startTime = this.getStartTime();
        LocalDateTime orderDate = this.getOrderDate();
        String promoInfo = this.getIsPromo() ? " (promo ticket)" : "";

        System.out.println("Ticket ID: " + this.getId() + ". Event code: " + this.getEventCode() + ".");
        System.out.println("Venue:  " + this.getVenuePlace());
        System.out.println( "Stadium sector: " + this.getStadiumSector());
        System.out.println("Start time: " + this.parseDateToString(startTime));
        System.out.println("Date of ticket purchase: " + this.parseDateToString(orderDate) + ". Price: " + this.getTicketPrice().getAmount() + promoInfo);
        System.out.println("Maximum allowed backpack weight: " + this.getMaxBackpackWeight() + "g");
    }

    public FootballTicket getTicketData() {
        return this;
    }
}
