package com.codigo.mshuamandagahexagonal.domain.aggregates.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBase {
    private int code;
    private String message;
    private Optional data;
}
