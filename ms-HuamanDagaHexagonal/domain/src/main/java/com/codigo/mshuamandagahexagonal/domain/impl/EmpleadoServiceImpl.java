package com.codigo.mshuamandagahexagonal.domain.impl;


import com.codigo.mshuamandagahexagonal.domain.aggregates.request.Empleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.mshuamandagahexagonal.domain.ports.in.EmpleadoServiceIn;
import com.codigo.mshuamandagahexagonal.domain.ports.out.EmpleadoServiceout;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoServiceIn {
    private final EmpleadoServiceout empleadoServiceout;
    @Override
    public ResponseBase crearEmpleadoIn(Empleado empleado) {
        return empleadoServiceout.crearPersonaOut(empleado);
    }

    @Override
    public ResponseBase GetEmpeladoIn(String numDoc) {
        return empleadoServiceout.GetEmpeladoOut(numDoc);
    }

    @Override
    public ResponseBase GetAllIn() {
        return empleadoServiceout.GetAllOut();
    }

    @Override
    public ResponseBase UpdateEmpeladoIn(RequestEmpleado empleado) {
        return empleadoServiceout.UpdateEmpeladoOut(empleado);
    }

    @Override
    public boolean deleteEmpleadoIn(String numDoc) {

        return empleadoServiceout.deleteEmpleadoOut(numDoc);


    }
}
