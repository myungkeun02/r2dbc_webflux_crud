package org.myungkeun.r2dbc_webflux_postgres_crud.controllers;

import org.myungkeun.r2dbc_webflux_postgres_crud.dto.CarDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.nio.file.Path;

@RestController
@RequestMapping("/car")
public class CarController {
    @GetMapping("/{carId}")
    Mono<CarDto> getCar(@PathVariable("carId") Integer carId) {
        return Mono.just(new CarDto(carId, "BMW", 200));
    }

    @PostMapping
    Mono<CarDto> createCar(@RequestBody CarDto carDto) {
        return Mono.just(carDto);
    }

    @PutMapping("{carId}")
    Mono<CarDto> updateCar(@PathVariable("carId") Integer carId, @RequestBody CarDto carDto) {
        return Mono.just(carDto);
    }

    @DeleteMapping("/{carId}")
    Mono<String> deleteCar(@PathVariable("carId") Integer carId) {
        return Mono.just("deleted");
    }
}
