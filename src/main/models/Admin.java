package main.models;

import main.constants.UserRoles;

import java.util.ArrayList;

public class Admin extends User {
    public Admin() {
        super(UserRoles.ADMIN);
    }

    public void checkTicket(Ticket ticket, ArrayList<Ticket> soldTickets) {
        if (soldTickets.contains(ticket)) {
            System.out.println("Admin has checked ticket with ID " + ticket.getId() + " and it's VALID.");
        } else {
            System.out.println("Admin has checked ticket with ID " + ticket.getId() + " and it's INVALID.");
        }
    }
}
