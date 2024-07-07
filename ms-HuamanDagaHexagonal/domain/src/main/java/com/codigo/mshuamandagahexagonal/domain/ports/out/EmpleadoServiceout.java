package com.codigo.mshuamandagahexagonal.domain.ports.out;

import com.codigo.mshuamandagahexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.Empleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseBase;

import java.util.ArrayList;

public interface EmpleadoServiceout {
    ResponseBase crearPersonaOut(Empleado empleado);
    ResponseBase GetEmpeladoOut(String  numDoc);

    ResponseBase GetAllOut();

    ResponseBase UpdateEmpeladoOut(RequestEmpleado empleado);

    boolean deleteEmpleadoOut(String numDoc);
}
