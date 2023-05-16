package ru.endienasg.artlandia.models.responses;

import lombok.Getter;
import lombok.Setter;
import ru.endienasg.artlandia.models.entities.Role;

@Getter
@Setter
public class UserRegistrationResponse {
    private String username;
    private Role role;
    private String message;
    private int exitCode;
}
