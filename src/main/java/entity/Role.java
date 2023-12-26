package entity;

import java.util.Arrays;
import java.util.Optional;

public enum Role {
    MASTER,
    MANAGER,
    RECEPTION,
    ADMIN;

    public static Optional<Role> find(String role) {
        return Arrays.stream(values())
                .filter(it -> it.name().equals(role))
                .findFirst();
    }
}
