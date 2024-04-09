package org.myungkeun.r2dbc_webflux_postgres_crud.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Builder
@Table("car")
public class CarEntity {
    @Id
    private Integer id;

    private String brand;

    private Integer kilowatt;
}
