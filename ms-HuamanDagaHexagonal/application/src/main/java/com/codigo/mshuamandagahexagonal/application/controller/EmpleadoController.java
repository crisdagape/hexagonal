package com.codigo.mshuamandagahexagonal.application.controller;

import com.codigo.mshuamandagahexagonal.domain.aggregates.constants.Constants;
import com.codigo.mshuamandagahexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.Empleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.mshuamandagahexagonal.domain.ports.in.EmpleadoServiceIn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/v1/empleado")
@RequiredArgsConstructor
public class EmpleadoController {
    private final EmpleadoServiceIn empleadoServiceIn;

    @PostMapping("/crear")
    public ResponseEntity<ResponseBase> register(@RequestBody RequestEmpleado requestEmpleado){
        Empleado empleado = new Empleado.EmpleadoBuilder(requestEmpleado.getNumDoc())
                                                        .cargo(requestEmpleado.getCargo())
                                                        .telefono(requestEmpleado.getTelefono())
                                                        .departamento(requestEmpleado.getDepartamento()).
                                                        build();

        ResponseBase responseBase = empleadoServiceIn.crearEmpleadoIn(empleado);
        if(responseBase.getCode() == Constants.CODIGO_EXITO)
        {
            return ResponseEntity.ok(responseBase);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @GetMapping("/buscar/{numDoc}")
    public ResponseEntity<ResponseBase> getEmpleado(@PathVariable String numDoc){
        ResponseBase responseBase  = empleadoServiceIn.GetEmpeladoIn(numDoc);
        if(responseBase.getCode() == Constants.CODIGO_EXITO)
        {
            return ResponseEntity.ok(responseBase);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<ResponseBase> getall(){
         ResponseBase responseBase   = empleadoServiceIn.GetAllIn();
         if(responseBase.getCode()==Constants.CODIGO_EXITO)
         {
             return ResponseEntity.ok(responseBase);
         }
         else {
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
         }
    }
    @PutMapping("/actualizar")
    public ResponseEntity<ResponseBase> actualizarempleado(@RequestBody RequestEmpleado empleadoRequest){
        ResponseBase responseBase = empleadoServiceIn.UpdateEmpeladoIn(empleadoRequest);
        if(responseBase.getCode()== Constants.CODIGO_EXITO)
        {
            return ResponseEntity.ok(responseBase);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBase);
        }
    }

    @DeleteMapping("/{numDoc}")
    public ResponseBase deleteempleado(@PathVariable String numDoc){
        if(empleadoServiceIn.deleteEmpleadoIn(numDoc)) {
            return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO_DELETE, Optional.of( "numero del dni eliminado "+ numDoc));
        }
        else{
            return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR_DESDE_REDIS, Optional.empty());
        }
    }


}
