package com.naren.busbookingsystem.auth;


import com.naren.busbookingsystem.Dto.UserDTO;

public record AuthenticationResponse(UserDTO userDTO, String token) {

}
