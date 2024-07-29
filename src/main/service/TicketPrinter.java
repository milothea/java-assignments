package main.service;

import main.constants.SeparatorMessage;
import main.models.Ticket;

import java.util.ArrayList;

public abstract class TicketPrinter {
    public void printTickets(ArrayList<Ticket> tickets) {
        if (tickets.size() == 0) {
            System.out.println("No tickets found.");
        } else {
            for (Ticket ticket : tickets) {
                ticket.print();
                System.out.println(SeparatorMessage.getSeparator());
            }
        }
    }
}
