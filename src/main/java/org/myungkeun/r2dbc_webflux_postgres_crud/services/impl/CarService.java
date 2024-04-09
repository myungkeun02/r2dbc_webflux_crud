package org.myungkeun.r2dbc_webflux_postgres_crud.services.impl;

import org.myungkeun.r2dbc_webflux_postgres_crud.dto.ApiResponseDto;
import org.myungkeun.r2dbc_webflux_postgres_crud.dto.CarDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CarService {
    Mono<ApiResponseDto<CarDto>> getCar(Integer carId);

    Mono<ApiResponseDto<CarDto>> createCar(CarDto carDto);

    Mono<ApiResponseDto<CarDto>> updateCar(Integer carId, CarDto carDto);

    Mono<ApiResponseDto<Object>> deleteCar(Integer carId);

    Flux<ApiResponseDto<CarDto[]>> getAllCars();
}
