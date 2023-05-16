package ru.endienasg.artlandia.models.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthenticationResponse {
    private String username;
    private String token;
    private String refreshToken;
}
