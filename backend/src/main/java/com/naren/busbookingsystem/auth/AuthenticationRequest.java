package com.naren.busbookingsystem.auth;

public record AuthenticationRequest(
        String userName,
        String password
) {
}
