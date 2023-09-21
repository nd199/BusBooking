package com.naren.busbookingsystem.Service;

import com.naren.busbookingsystem.Dto.UserDTO;
import com.naren.busbookingsystem.EntityRecord.UserRegistrationRequest;
import com.naren.busbookingsystem.EntityRecord.UserUpdateRequest;

import java.util.List;

public interface UserService {
    void AddNewUser(UserRegistrationRequest request);

    List<UserDTO> getUsers();

    UserDTO findUserByUserName(String userName);

    void updateUser(UserUpdateRequest request, Long id);

    UserDTO getUserById(Long id);

    void removeUserById(Long id);
}
