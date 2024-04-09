package org.myungkeun.r2dbc_webflux_postgres_crud.services;

import lombok.RequiredArgsConstructor;
import org.myungkeun.r2dbc_webflux_postgres_crud.dto.CarDto;
import org.myungkeun.r2dbc_webflux_postgres_crud.entities.CarEntity;
import org.myungkeun.r2dbc_webflux_postgres_crud.repositories.CarRepository;
import org.myungkeun.r2dbc_webflux_postgres_crud.services.impl.CarService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Mono<CarDto> getCar(Integer carId) {
        return carRepository.findById(carId)
                .map(carEntity -> new CarDto(carEntity.getId(), carEntity.getBrand(), carEntity.getKilowatt()));
    }

    @Override
    public Mono<CarDto> createCar(CarDto carDto) {
        return carRepository.save(CarEntity.builder()
                        .brand(carDto.brand())
                        .kilowatt(carDto.kilowatt())
                        .build())
                .map(carEntity -> new CarDto(carEntity.getId(), carEntity.getBrand(), carEntity.getKilowatt()));

    }

    @Override
    public Mono<CarDto> updateCar(Integer carId, CarDto carDto) {
        return carRepository.save(CarEntity.builder()
                        .id(carId)
                        .brand(carDto.brand())
                        .kilowatt(carDto.kilowatt())
                        .build())
                .map(carEntity -> new CarDto(carEntity.getId(), carEntity.getBrand(), carEntity.getKilowatt()));
    }

    @Override
    public Mono<Void> deleteCar(Integer carId) {
        return carRepository.deleteById(carId);
    }

    @Override
    public Flux<CarDto> getAllCars() {
        return carRepository.findAll()
                .map(carEntity -> new CarDto(carEntity.getId(), carEntity.getBrand(), carEntity.getKilowatt()));
    }
}
