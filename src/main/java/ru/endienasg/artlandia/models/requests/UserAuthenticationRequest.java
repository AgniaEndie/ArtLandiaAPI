package ru.endienasg.artlandia.models.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationRequest {
    private String username;
    private String password;
}
