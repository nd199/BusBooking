package com.naren.busbookingsystem.Repository;

import com.naren.busbookingsystem.Entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus, Long> {
    boolean existsByNumber(String number);

    void deleteBusById(Long id);

    Optional<Bus> getBusByNumber(String number);

    boolean existsBusById(Long id);

    boolean existsByName(String name);

    Bus findByName(String name);
}
