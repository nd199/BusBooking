package com.naren.busbookingsystem.auth;

import com.naren.busbookingsystem.Dto.Mapper.UserDTOMapper;
import com.naren.busbookingsystem.Dto.UserDTO;
import com.naren.busbookingsystem.Entity.Person;
import com.naren.busbookingsystem.Security.Jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserDTOMapper userDTOMapper;

    private final JWTUtil jwtUtil;

    public AuthenticationResponse login(AuthenticationRequest
                                                authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.userName(),
                        authenticationRequest.password()
                )
        );
        Person principal = (Person) authentication.getPrincipal();
        UserDTO userDTO = userDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(userDTO.userName(), userDTO.roles());
        return new AuthenticationResponse(userDTO, token);
    }

}
