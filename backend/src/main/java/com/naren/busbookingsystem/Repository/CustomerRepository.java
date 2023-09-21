package com.naren.busbookingsystem.Repository;


import com.naren.busbookingsystem.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Person, Long> {

    boolean existsCustomerById(Long id);

    Optional<Person> getUserById(Long id);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<Person> getCustomerByUserName(String userName);

    void deleteCustomerById(Long id);
}
