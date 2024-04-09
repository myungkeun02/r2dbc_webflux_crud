package org.myungkeun.r2dbc_webflux_postgres_crud.services.impl;

import org.myungkeun.r2dbc_webflux_postgres_crud.dto.CarDto;
import reactor.core.publisher.Mono;

public interface CarService {
    Mono<CarDto> getCar(Integer carId);

    Mono<CarDto> createCar(CarDto carDto);

    Mono<CarDto> updateCar(Integer carId, CarDto carDto);

    Mono<Void> deleteCar(Integer carId);
}
