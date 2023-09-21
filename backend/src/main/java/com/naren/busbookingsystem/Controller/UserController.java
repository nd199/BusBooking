package com.naren.busbookingsystem.Controller;

import com.naren.busbookingsystem.Dto.UserDTO;
import com.naren.busbookingsystem.EntityRecord.UserRegistrationRequest;
import com.naren.busbookingsystem.EntityRecord.UserUpdateRequest;
import com.naren.busbookingsystem.Security.Jwt.JWTUtil;
import com.naren.busbookingsystem.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final JWTUtil jwtUtil;

    @PostMapping("/users")
    public ResponseEntity<?> addCustomer(
            @RequestBody UserRegistrationRequest request) {
        log.info("Request for adding" +
                 " User in UserController called : {}", request);
        userService.AddNewUser(request);
        String token = jwtUtil.issueToken(request.userName(),
                "USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION,
                        token)
                .build();
    }


    @GetMapping("/users/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers() {
        log.info("Request for getting all the Users " +
                 "method called in UserController");
        return userService.getUsers();
    }


    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") Long id,
                           @RequestBody UserUpdateRequest request) {
        log.info("Request for updating existing " +
                 " User called in UserController" +
                 " with id: {}", id);
        userService.updateUser(request, id);
    }

    @DeleteMapping("/users/{id}")
    public void removeUserById(@PathVariable("id") Long id) {
        log.info("Request for removing" +
                 " User called in UserController" +
                 " with username , email : {}", id);
        userService.removeUserById(id);
    }
}