package com.naren.busbookingsystem.Service.Impl;

import com.naren.busbookingsystem.Dao.BusDao;
import com.naren.busbookingsystem.Entity.Bus;
import com.naren.busbookingsystem.EntityRecord.BusRegistrationRequest;
import com.naren.busbookingsystem.EntityRecord.BusUpdateRequest;
import com.naren.busbookingsystem.Exceptions.ResourceAlreadyExists;
import com.naren.busbookingsystem.Exceptions.ResourceNotFound;
import com.naren.busbookingsystem.Service.BusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final BusDao busDao;

    private Bus getBus(
            BusRegistrationRequest request) {
        return new Bus(
                request.name(),
                request.number(),
                request.route()
        );
    }

    @Override
    public void addBus(BusRegistrationRequest request) {
        log.info("addBus Method in BusServiceImpl called with request : {}", request);

        if (busDao.existsByName(request.name())) {
            throw new ResourceAlreadyExists(
                    "Bus Name already taken"
            );
        } else if (busDao.existsByNumber(request.number())) {
            throw new ResourceAlreadyExists(
                    "Bus Number already taken"
            );
        } else {
            Bus bus = getBus(request);
            busDao.registerBus(bus);
        }
    }

    @Override
    public List<Bus> getListOfBuses() {
        log.info("getCustomerList Method in BusServiceImpl called");
        return busDao.getAllBuses();
    }

    @Override
    public Bus findBusByName(String name) {
        log.info("findBusByName called in busService impl");
        return busDao.findBusByName(name);
    }


    @Override
    public void updateBus(BusUpdateRequest updateRequest,
                          Long id) {
        log.info("updateBus called in busService impl");
        Bus bus = busDao.getBusById(id)
                .orElseThrow(()
                        -> new ResourceNotFound(
                        "Bus with id [%s] not found".formatted(id))
                );
        boolean isPresent = false;

        if (updateRequest.name() != null &&
            !updateRequest.name().equals(bus.getName())) {
            if (busDao.existsByName(updateRequest.name())) {
                throw new ResourceAlreadyExists(
                        "Bus Name already taken"
                );
            }
            bus.setName(updateRequest.name());
            isPresent = true;
        }

        if (updateRequest.number() != null &&
            !updateRequest.number().equals(bus.getNumber())) {
            if (busDao.existsByNumber(updateRequest.number())) {
                throw new ResourceAlreadyExists(
                        "Bus Number already taken"
                );
            }
            bus.setNumber(updateRequest.number());
            isPresent = true;
        }

        if (updateRequest.route() != null &&
            !updateRequest.route().equals(bus.getRoute())) {
            bus.setRoute(updateRequest.route());
        }

        if (!isPresent) {
            throw new ResourceNotFound(
                    "Customer with id does not exist");
        }
        busDao.updateBus(bus);
    }

    @Override
    public Bus getBusById(Long id) {
        return busDao.getBusById(id)
                .orElseThrow(()
                        -> new ResourceNotFound(
                        "Bus with id [%s] not found".formatted(id))
                );
    }

    @Override
    public void removeBusById(Long busId) {
        if (!busDao.existsBusById(busId)) {
            throw new ResourceNotFound(
                    "Bus with id [%s] not found".formatted(busId)
            );
        }
        busDao.removeBusById(busId);
    }
}
