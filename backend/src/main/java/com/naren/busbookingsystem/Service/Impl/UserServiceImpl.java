package com.naren.busbookingsystem.Service.Impl;

import com.naren.busbookingsystem.Dao.UserDao;
import com.naren.busbookingsystem.Dto.Mapper.UserDTOMapper;
import com.naren.busbookingsystem.Dto.UserDTO;
import com.naren.busbookingsystem.Entity.Person;
import com.naren.busbookingsystem.EntityRecord.UserRegistrationRequest;
import com.naren.busbookingsystem.EntityRecord.UserUpdateRequest;
import com.naren.busbookingsystem.Exceptions.PasswordMustBeDifferent;
import com.naren.busbookingsystem.Exceptions.ResourceAlreadyExists;
import com.naren.busbookingsystem.Exceptions.ResourceNotFound;
import com.naren.busbookingsystem.Exceptions.ResourceSame;
import com.naren.busbookingsystem.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.passay.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Transactional
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    private final UserDTOMapper userDTOMapper;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao,
                           UserDTOMapper userDTOMapper,
                           PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userDTOMapper = userDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private Person getCustomer(
            UserRegistrationRequest request) {
        return new Person(
                request.userName(),
                request.name(),
                request.email(),
                passwordEncoder.encode(request.password()),
                request.age(),
                request.gender()
        );
    }

    @Override
    public void AddNewUser(UserRegistrationRequest request) {
        log.info("AddNewUser Method in CustomerServiceImpl called with request : {}", request);

        PasswordData pd = new PasswordData(request.password());
        pd.setUsername(request.userName());
        Rule rule = new UsernameRule();
        PasswordValidator passwordValidator = new PasswordValidator(rule);
        RuleResult rs = passwordValidator.validate(pd);
        if (rs.isValid()) {

            if (request.userName().equals(request.name())) {
                throw new ResourceSame(
                        "Name and username must not be same "
                );
            } else {
                if (userDao.existsByUserName(request.userName())) {
                    throw new ResourceAlreadyExists(
                            "Username already taken"
                    );
                } else if (userDao.existsByEmail(request.email())) {
                    throw new ResourceAlreadyExists(
                            "Email already taken"
                    );
                } else {
                    Person person = getCustomer(request);
                    userDao.registerCustomer(person);
                }
            }
        } else {
            throw new PasswordMustBeDifferent(
                    "User-Name and Password must not be same"
            );
        }
    }

    @Override
    public List<UserDTO> getUsers() {
        log.info("getCustomerList Method in CustomerServiceImpl called");
        return userDao.getAllCustomers()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName)
                .map(userDTOMapper)
                .orElseThrow(
                        () -> new ResourceNotFound(
                                "Customer with userName %s does not exist".formatted(userName)
                        )
                );
    }


    @Override
    public void updateUser(UserUpdateRequest updateRequest,
                           Long id) {
        log.info("update customer called in customer service impl");
        Person person = userDao.getUserById(id)
                .orElseThrow(()
                        -> new ResourceNotFound(
                        "Customer with id [%s] not found".formatted(id))
                );
        boolean isPresent = false;

        if (updateRequest.userName() != null &&
            !updateRequest.userName()
                    .equals(person.getUsername())) {
            person.setUserName(updateRequest.userName());
            isPresent = true;
        }
        if (updateRequest.name() != null &&
            !updateRequest.name().equals(person.getName())) {
            person.setName(updateRequest.name());
            isPresent = true;
        }
        if (updateRequest.password() != null &&
            !updateRequest.password().equals(person.getPassword())) {
            person.setPassword(updateRequest.password());
            isPresent = true;
        }
        if (updateRequest.age() != null &&
            !updateRequest.age().equals(person.getAge())) {
            person.setAge(updateRequest.age());
            isPresent = true;
        }
        if (updateRequest.gender() != null) {
            person.setGender(updateRequest.gender());
            isPresent = true;
        }
        if (updateRequest.email() != null &&
            !updateRequest.email().equals(person.getEmail())) {
            if (userDao.existsByEmail(updateRequest.email())) {
                throw new ResourceAlreadyExists(
                        "Email already taken"
                );
            }
            person.setEmail(updateRequest.email());
        }

        if (!isPresent) {
            throw new ResourceNotFound(
                    "Customer with id does not exist");
        }
        userDao.updateCustomer(person);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userDao.getUserById(id)
                .map(userDTOMapper)
                .orElseThrow(()
                        -> new ResourceNotFound(
                        "Customer with id [%s] not found".formatted(id))
                );
    }

    @Override
    public void removeUserById(Long customerId) {
        if (!userDao.existsCustomerById(customerId)) {
            throw new ResourceNotFound(
                    "Customer with id [%s] not found".formatted(customerId)
            );
        }
        userDao.removeCustomerById(customerId);
    }


}
