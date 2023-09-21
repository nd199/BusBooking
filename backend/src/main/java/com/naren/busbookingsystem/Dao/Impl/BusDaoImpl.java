package com.naren.busbookingsystem.Dao.Impl;

import com.naren.busbookingsystem.Dao.BusDao;
import com.naren.busbookingsystem.Entity.Bus;
import com.naren.busbookingsystem.Repository.BusRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Slf4j
@RequiredArgsConstructor
public class BusDaoImpl implements BusDao {

    private final BusRepository busRepository;

    @Override
    public boolean existsByNumber(String number) {
        log.info("BusDaoImpl method on existByNumber called : {}", number);
        log.info("busRepository method on existByNumber called : {}", number);
        return busRepository.existsByNumber(number);
    }

    @Override
    public boolean existsByName(String name) {
        log.info("BusDaoImpl method on existByName called : {}", name);
        log.info("busRepository method on existByName called : {}", name);
        return busRepository.existsByName(name);
    }

    @Override
    public boolean existsBusById(Long id) {
        log.info("BusDaoImpl method on existByCustomerName called : {}", id);
        log.info("busRepository method on existByCustomerName called : {}", id);
        return busRepository.existsBusById(id);
    }

    @Override
    public void registerBus(Bus bus) {
        log.info("BusDaoImpl method on registerCustomer called : {}", bus);
        log.info("BusDaoImpl method on saveCustomer called : {}", bus);
        busRepository.save(bus);
    }

    @Override
    public void removeBusById(Long id) {
        log.info("BusDaoImpl method on removeCustomer called : {}", id);
        log.info("busRepository method on deleteByCustomerNameAndEmail" +
                 " called : {}", id);
        busRepository.deleteBusById(id);
    }

    @Override
    public void updateBus(Bus bus) {
        log.info("BusDaoImpl method on updateUser called : {}", bus);
        busRepository.save(bus);
    }


    @Override
    public List<Bus> getAllBuses() {
        log.info("BusDaoImpl method on getAllAdmins" +
                 " called ");
        log.info("busRepository method on findAll" +
                 " called ");
        return busRepository.findAll();
    }

    @Override
    public Optional<Bus> findBusByNumber(String number) {
        log.info("BusDaoImpl method on findBusByNumber called : {}", number);
        return busRepository.getBusByNumber(number);
    }

    @Override
    public Optional<Bus> getBusById(Long id) {
        log.info("BusDaoImpl method on getBusById called : {}", id);
        return busRepository.findById(id);
    }

    @Override
    public Bus findBusByName(String name) {
        log.info("BusDaoImpl method on findBusByName called : {}", name);
        return busRepository.findByName(name);
    }
}
