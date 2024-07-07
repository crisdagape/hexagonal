package com.codigo.mshuamandagahexagonal.domain.aggregates.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEmpleado {
    private String numDoc;
    private String cargo;
    private double salario;
    private String telefono;
    private String correo;
    private int edad;
    private String departamento;

}
