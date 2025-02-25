package org.energyepicode.auth;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private String cognome;
    private String email;
    private String avatar;
}
