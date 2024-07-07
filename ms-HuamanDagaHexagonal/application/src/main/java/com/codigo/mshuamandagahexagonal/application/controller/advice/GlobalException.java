package com.codigo.mshuamandagahexagonal.application.controller.advice;

import com.codigo.mshuamandagahexagonal.application.personalizada.EmpleadoException;
import com.codigo.mshuamandagahexagonal.domain.aggregates.constants.Constants;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseBase;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.Optional;

@ControllerAdvice
@Log4j2
public class GlobalException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBase> manejandoExepciones(Exception ex){
        //Aqui manejo exclusivamente lo qeu sucede cuando capturo una exepcion general.
        log.error("Error manejado desde ******manejandoExepciones******* ");
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR_EXCEPTION, "ERROR INTERNO DEL SERVIDOR: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseBase> manejandoNullPointer(NullPointerException ex){
        //Aqui manejo exclusivamente lo qeu sucede cuando capturo una exepcion general.
        log.error("Error manejado desde ******manejandoNullPointer******* ");
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR_NULL_POINTER, "ERROR HAY UN DATO NULOOOOO: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ResponseBase> manejandoIoException(IOException ex){
        //Aqui manejo exclusivamente lo qeu sucede cuando capturo una exepcion general.
        log.error("Error manejado desde ******manejandoIoException******* ");
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR_IO_EXCEPTION, "ERROR HAY UN ERROR EN LA ESCRITURA O LECTURA: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseBase> manejandoRunTime(RuntimeException ex){
        //Aqui manejo exclusivamente lo qeu sucede cuando capturo una exepcion general.
        log.error("Error manejado desde ******manejandoRunTime******* ");
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR_RUN_TIME, "ERROR EN TIEMPO DE EJECUCION: " + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmpleadoException.class)
    public ResponseEntity<ResponseBase> manejandoEmpleadoException(EmpleadoException ex){
        //Aqui manejo exclusivamente lo qeu sucede cuando capturo una exepcion general.
        log.error("Error manejado desde ******manejandoEmpleadoException******* ");
        ResponseBase response = new ResponseBase(Constants.CODIGO_ERROR_EMPLEADO, "ERROR EN EL EMPLEADO" + ex.getMessage(), Optional.empty());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
