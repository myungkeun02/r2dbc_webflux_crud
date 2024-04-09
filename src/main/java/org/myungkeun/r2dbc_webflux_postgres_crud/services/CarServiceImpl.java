package org.myungkeun.r2dbc_webflux_postgres_crud.services;

import lombok.RequiredArgsConstructor;
import org.myungkeun.r2dbc_webflux_postgres_crud.dto.ApiResponseDto;
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
    public Mono<ApiResponseDto<CarDto>> getCar(Integer carId) {
        return carRepository.findById(carId)
                .map(carEntity -> new CarDto(carEntity.getId(), carEntity.getBrand(), carEntity.getKilowatt()))
                .map(carDto -> new ApiResponseDto<>(200, "성공", carDto))
                .switchIfEmpty(Mono.just(new ApiResponseDto<>(404, "차량을 찾을 수 없습니다", null)))
                .onErrorResume(throwable -> Mono.just(new ApiResponseDto<>(500, "내부 서버 오류", null)));
    }

    @Override
    public Mono<ApiResponseDto<CarDto>> createCar(CarDto carDto) {
        return carRepository.save(CarEntity.builder()
                        .brand(carDto.brand())
                        .kilowatt(carDto.kilowatt())
                        .build())
                .map(savedCarEntity -> new CarDto(savedCarEntity.getId(), savedCarEntity.getBrand(), savedCarEntity.getKilowatt()))
                .map(savedCarDto -> new ApiResponseDto<>(201, "차량이 성공적으로 생성되었습니다", savedCarDto))
                .onErrorResume(throwable -> Mono.just(new ApiResponseDto<>(500, "내부 서버 오류", null)));
    }

    @Override
    public Mono<ApiResponseDto<CarDto>> updateCar(Integer carId, CarDto carDto) {
        return carRepository.findById(carId)
                .flatMap(existingCarEntity -> {
                    existingCarEntity.setBrand(carDto.brand());
                    existingCarEntity.setKilowatt(carDto.kilowatt());
                    return carRepository.save(existingCarEntity);
                })
                .map(updatedCarEntity -> new CarDto(updatedCarEntity.getId(), updatedCarEntity.getBrand(), updatedCarEntity.getKilowatt()))
                .map(updatedCarDto -> new ApiResponseDto<>(200, "차량이 성공적으로 업데이트되었습니다", updatedCarDto))
                .switchIfEmpty(Mono.just(new ApiResponseDto<>(404, "차량을 찾을 수 없습니다", null)))
                .onErrorResume(throwable -> Mono.just(new ApiResponseDto<>(500, "내부 서버 오류", null)));
    }

    @Override
    public Mono<ApiResponseDto<Object>> deleteCar(Integer carId) {
        return carRepository.deleteById(carId)
                .then(Mono.just(new ApiResponseDto<>(204, "차량이 성공적으로 삭제되었습니다", null)))
                .onErrorResume(throwable -> Mono.just(new ApiResponseDto<>(500, "내부 서버 오류", null)));
    }

    @Override
    public Flux<ApiResponseDto<CarDto[]>> getAllCars() {
        return carRepository.findAll()
                .map(carEntity -> new CarDto(carEntity.getId(), carEntity.getBrand(), carEntity.getKilowatt()))
                .collectList()
                .map(carDtoList -> {
                    CarDto[] carDtoArray = carDtoList.toArray(new CarDto[0]);
                    return new ApiResponseDto<>(200, "성공", carDtoArray);
                })
                .flux()
                .onErrorResume(throwable -> Mono.just(new ApiResponseDto<>(500, "내부 서버 오류", null)));
    }
}