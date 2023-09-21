package com.naren.busbookingsystem.Dto;

import com.naren.busbookingsystem.Entity.Gender;

import java.util.List;

public record UserDTO(
        Long id,
        String userName,
        String name,
        String email,
        Integer age,
        Gender gender,
        List<String> roles
) {
}
