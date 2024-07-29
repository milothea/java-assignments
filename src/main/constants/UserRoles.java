package main.constants;

public enum UserRoles {
    ADMIN("ADMIN"),
    CLIENT("CLIENT");

    private final String role;

    UserRoles(String role) {
        this.role = role;
    }
}
