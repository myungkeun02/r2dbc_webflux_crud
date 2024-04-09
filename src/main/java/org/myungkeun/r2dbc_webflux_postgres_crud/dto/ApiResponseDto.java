package org.myungkeun.r2dbc_webflux_postgres_crud.dto;

import lombok.*;

public record ApiResponseDto<T>(int status, String message, T data) {}
