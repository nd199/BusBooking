package com.naren.busbookingsystem.Dao;


import com.naren.busbookingsystem.Entity.Bus;

import java.util.List;
import java.util.Optional;

public interface BusDao {

    boolean existsByNumber(String number);

    boolean existsByName(String name);

    boolean existsBusById(Long id);

    void registerBus(Bus bus);

    void removeBusById(Long id);

    void updateBus(Bus bus);

    List<Bus> getAllBuses();

    Optional<Bus> findBusByNumber(String number);

    Optional<Bus> getBusById(Long id);

    Bus findBusByName(String name);
}
