package com.semillero.test1.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionDto {
    private String hora;
    private String mensaje;
    private String url;
    private int codeStatus;
}