package com.naren.busbookingsystem.EntityRecord;

import com.naren.busbookingsystem.Entity.Gender;

public record UserUpdateRequest(
        String userName,
        String name,
        String email,
        String password,
        Gender gender,
        Integer age
) {
}
