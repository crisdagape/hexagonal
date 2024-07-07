package com.codigo.mshuamandagahexagonal.infraestructure.util;

import com.codigo.mshuamandagahexagonal.infraestructure.entity.EmpleadoEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class Util {
    public static String convertirAString(EmpleadoEntity empleado){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(empleado);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
    public static String convertirAStringArray(List<EmpleadoEntity> empleado){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(empleado);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }

    public static <T> T convertirDesdeString(String json, Class<T> tipoClase){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(json,tipoClase);
        }catch (JsonProcessingException e){
            throw new RuntimeException(e);
        }
    }
}
