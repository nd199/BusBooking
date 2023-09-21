package com.naren.busbookingsystem.Service;


import com.naren.busbookingsystem.Entity.Bus;
import com.naren.busbookingsystem.EntityRecord.BusRegistrationRequest;
import com.naren.busbookingsystem.EntityRecord.BusUpdateRequest;

import java.util.List;

public interface BusService {
    void addBus(BusRegistrationRequest request);

    List<Bus> getListOfBuses();

    Bus findBusByName(String name);

    void updateBus(BusUpdateRequest request, Long id);

    Bus getBusById(Long id);

    void removeBusById(Long id);
}
