package com.codigo.mshuamandagahexagonal.domain.ports.in;

import com.codigo.mshuamandagahexagonal.domain.aggregates.request.Empleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseBase;

public interface EmpleadoServiceIn {
    ResponseBase crearEmpleadoIn(Empleado empleado);
    ResponseBase GetEmpeladoIn(String  numDoc);

    ResponseBase GetAllIn();

    ResponseBase UpdateEmpeladoIn(RequestEmpleado empleado);

    boolean deleteEmpleadoIn(String numDoc);
}
