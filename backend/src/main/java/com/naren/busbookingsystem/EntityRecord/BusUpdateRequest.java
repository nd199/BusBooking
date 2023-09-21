package com.naren.busbookingsystem.EntityRecord;

import com.naren.busbookingsystem.Entity.Route;

public record BusUpdateRequest(
        String name,
        String number,
        Route route
) {
}
