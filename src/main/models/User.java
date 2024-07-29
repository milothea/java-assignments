package main.models;

import main.constants.UserRoles;

public abstract class User {
    private final UserRoles role;

    public User(UserRoles role) {
        this.role = role;
    }

    public UserRoles getRole() {
        return this.role;
    }

    public void printRole() {
        System.out.println("User has role - '" + this.getRole() + "'.");
    }
}
