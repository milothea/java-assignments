package main.models;

import main.constants.DefaultValues;

import java.time.LocalDateTime;

public class MuseumTicket extends Ticket {
    private String exhibitionName = DefaultValues.DEFAULT_EXHIBITION;
    public MuseumTicket() {
        super();
    }

    public MuseumTicket(String museum, int eventCode, LocalDateTime startTime) {
        super(museum, eventCode, startTime);
    }

    public MuseumTicket(String museum, int eventCode, LocalDateTime startTime, boolean isPromo, Price ticketPrice, String exhibitionName) {
        super(museum, eventCode, startTime, isPromo, ticketPrice);
        this.exhibitionName = exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public String getExhibitionName() {
        return this.exhibitionName;
    }

    @Override
    public MuseumTicket getTicketData() {
        return this;
    }

    public void print() {
        LocalDateTime startTime = this.getStartTime();
        LocalDateTime orderDate = this.getOrderDate();
        String promoInfo = this.getIsPromo() ? " (promo ticket)" : "";

        System.out.println("Ticket ID: " + this.getId() + ". Event code: " + this.getEventCode() + ".");
        System.out.println("Venue:  " + this.getVenuePlace() + ". Exhibition: " + this.getExhibitionName() + ".");
        System.out.println("Start time: " + this.parseDateToString(startTime));
        System.out.println("Date of ticket purchase: " + this.parseDateToString(orderDate) + ". Price: " + this.getTicketPrice().getAmount() + promoInfo);
    }
}
