package org.myungkeun.r2dbc_webflux_postgres_crud.controllers;

import lombok.RequiredArgsConstructor;
import org.myungkeun.r2dbc_webflux_postgres_crud.dto.ApiResponseDto;
import org.myungkeun.r2dbc_webflux_postgres_crud.dto.CarDto;
import org.myungkeun.r2dbc_webflux_postgres_crud.services.impl.CarService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {
    private final CarService carService;

    @GetMapping("/{carId}")
    Mono<ApiResponseDto<CarDto>> getCar(@PathVariable("carId") Integer carId) {
        return carService.getCar(carId);
    }

    @PostMapping
    Mono<ApiResponseDto<CarDto>> createCar(@RequestBody CarDto carDto) {
        return carService.createCar(carDto);
    }

    @PutMapping("{carId}")
    Mono<ApiResponseDto<CarDto>> updateCar(@PathVariable("carId") Integer carId, @RequestBody CarDto carDto) {
        return carService.updateCar(carId, carDto);
    }

    @DeleteMapping("/{carId}")
    Mono<ApiResponseDto<Object>> deleteCar(@PathVariable("carId") Integer carId) {
        return carService.deleteCar(carId);
    }

    @GetMapping("/all")
    Flux<ApiResponseDto<CarDto[]>> getAllCars() {
        return carService.getAllCars();
    }

}
