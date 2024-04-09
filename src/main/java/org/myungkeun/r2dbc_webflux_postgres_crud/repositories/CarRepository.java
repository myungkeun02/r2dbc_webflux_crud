package org.myungkeun.r2dbc_webflux_postgres_crud.repositories;

import org.myungkeun.r2dbc_webflux_postgres_crud.entities.CarEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface CarRepository extends R2dbcRepository<CarEntity, Integer> {

}
