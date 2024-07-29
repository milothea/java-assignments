package main.models;

import main.constants.UserRoles;

public class Client extends User {
    public Client() {
        super(UserRoles.CLIENT);
    }

    public void getTicket() {
        System.out.println("Client has got a ticket.");
    }
}
