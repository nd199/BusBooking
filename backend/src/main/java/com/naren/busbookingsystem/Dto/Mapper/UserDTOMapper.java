package com.naren.busbookingsystem.Dto.Mapper;


import com.naren.busbookingsystem.Dto.UserDTO;
import com.naren.busbookingsystem.Entity.Person;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class UserDTOMapper implements Function<Person, UserDTO> {
    @Override
    public UserDTO apply(Person person) {
        return new UserDTO(
                person.getId(),
                person.getUsername(),
                person.getName(),
                person.getEmail(),
                person.getAge(),
                person.getGender(),
                person.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList())
        );
    }
}
