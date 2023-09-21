package com.naren.busbookingsystem.Dao.Impl;

import com.naren.busbookingsystem.Dao.UserDao;
import com.naren.busbookingsystem.Entity.Person;
import com.naren.busbookingsystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository("jpa")
@Slf4j
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {

    private final CustomerRepository customerRepository;

    @Override
    public boolean existsByEmail(String email) {
        log.info("CustomerDaoImpl method on existByEmail called : {}", email);
        log.info("customerRepository method on existByEmail called : {}", email);
        return customerRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUserName(String userName) {
        log.info("CustomerDaoImpl method on existByCustomerName called : {}", userName);
        log.info("customerRepository method on existByCustomerName called : {}", userName);
        return customerRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsCustomerById(Long id) {
        log.info("CustomerDaoImpl method on existByCustomerName called : {}", id);
        log.info("customerRepository method on existByCustomerName called : {}", id);
        return customerRepository.existsCustomerById(id);
    }

    @Override
    public void registerCustomer(Person person) {
        log.info("CustomerDaoImpl method on registerCustomer called : {}", person);
        log.info("CustomerDaoImpl method on saveCustomer called : {}", person);
        customerRepository.save(person);
    }

    @Override
    public void removeCustomerById(Long id) {
        log.info("CustomerDaoImpl method on removeCustomer called : {}", id);
        log.info("customerRepository method on deleteByCustomerNameAndEmail" +
                 " called : {}", id);
        customerRepository.deleteCustomerById(id);
    }

    @Override
    public void updateCustomer(Person person) {
        log.info("CustomerDaoImpl method on updateUser called : {}", person);
        customerRepository.save(person);
    }


    @Override
    public List<Person> getAllCustomers() {
        log.info("CustomerDaoImpl method on getAllAdmins" +
                 " called ");
        log.info("customerRepository method on findAll" +
                 " called ");
        return customerRepository.findAll();
    }

    @Override
    public Optional<Person> findUserByUserName(String userName) {
        return customerRepository.getCustomerByUserName(userName);
    }

    @Override
    public Optional<Person> getUserById(Long id) {
        return customerRepository.findById(id);
    }
}
