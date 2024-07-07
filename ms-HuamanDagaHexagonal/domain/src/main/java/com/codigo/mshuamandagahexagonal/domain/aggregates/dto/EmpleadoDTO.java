package com.codigo.mshuamandagahexagonal.domain.aggregates.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EmpleadoDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private int edad;
    private String cargo;
    private String tipoDoc;
    private String numDoc;
    private String departamento;
    private double salario;
    private String telefono;
    private String correo;
    private boolean estado;
    private String direccion;
    private Timestamp dateCrea;
    private String usuaCrea;
    private Timestamp dateUdpate;
    private String usuaUpdate;
    private Timestamp dateDelete;
    private String usuaDelete;
}
