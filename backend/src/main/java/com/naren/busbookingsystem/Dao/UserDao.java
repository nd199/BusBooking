package com.naren.busbookingsystem.Dao;


import com.naren.busbookingsystem.Entity.Person;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsCustomerById(Long id);

    void registerCustomer(Person person);

    void removeCustomerById(Long id);

    void updateCustomer(Person person);

    List<Person> getAllCustomers();

    Optional<Person> findUserByUserName(String userName);

    Optional<Person> getUserById(Long id);

}
