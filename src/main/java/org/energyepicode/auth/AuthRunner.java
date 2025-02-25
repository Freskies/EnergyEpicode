package org.energyepicode.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class AuthRunner implements ApplicationRunner {

    @Autowired
    private AppUserService appUserService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Creazione dell'utente admin se non esiste
        Optional<AppUser> adminUser = appUserService.findByUsername("admin");
        if (adminUser.isEmpty()) {
            appUserService.registerUser(
                    "admin",
                    "Admin Name",
                    "Admin Last Name",
                    "admin@example.com",
                    "admin-avatar.png",
                    "adminpwd",
                    Set.of(Role.ROLE_ADMIN)
            );
        }

        // Creazione dell'utente user se non esiste
        Optional<AppUser> normalUser = appUserService.findByUsername("user");
        if (normalUser.isEmpty()) {
            appUserService.registerUser(
                    "user",
                    "User Name",
                    "User Last Name",
                    "user@example.com",
                    "user-avatar.png",
                    "userpwd",
                    Set.of(Role.ROLE_USER)
            );
        }

        // Creazione dell'utente seller se non esiste
        Optional<AppUser> normalSeller = appUserService.findByUsername("seller");
        if (normalSeller.isEmpty()) {
            appUserService.registerUser(
                    "seller",
                    "Seller Name",
                    "Seller Last Name",
                    "seller@example.com",
                    "seller-avatar.png",
                    "sellerpwd",
                    Set.of(Role.ROLE_SELLER)
            );
        }
    }
}