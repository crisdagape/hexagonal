package com.codigo.mshuamandagahexagonal.domain.aggregates.constants;

public class Constants {
    public static final  String USU_ADMIN="CDAGA";
    public static final boolean ESTADO_ACTIVO = true;
    public static final boolean ESTADO_INACTIVO = false;

    public static final Integer CODIGO_EXITO=2000;
    public static final String MENSAJE_EXITO="Empleado creado Correctamente";
    public static final String MENSAJE_EXITO_ACTUALIZADO="Empleado actualizado Correctamente";
    public static final Integer CODIGO_ERROR = 2005;
    public static final String MENSAJE_ERROR="Error al crear el Empleado";
    public static final String REDIS_KEY_GUARDAR="API:CONSUMO:EXTERNA:";
    public static final String REDIS_KEY_LISTA="API:CONSUMO:EXTERNA:TODOS";
    public static final String MENSAJE_EXITOSO_DESDE_REDIS="Empleado Encontrado en REDIS";
    public static final String MENSAJE_ERROR_DESDE_REDIS="Empleado no Encontrado en REDIS";
    public static final String MENSAJE_EXITOSO_DESDE_BD="Empleado Encontrado en BD";
    public static final String MENSAJE_EXISTE_EMPLEADO_BD="Empleado ya existe en BD";
    public static final String MENSAJE_EXITO_DELETE="Empleado Eliminado de Redis";
    public static final String MENSAJE_NOEXISTE_DOC_BD="Empleado no Figura en la BD";

    public static final Integer CODIGO_ERROR_EXCEPTION=500;
    public static final Integer CODIGO_ERROR_NULL_POINTER=409;
    public static final Integer CODIGO_ERROR_IO_EXCEPTION=406;
    public static final Integer CODIGO_ERROR_RUN_TIME=400;
    public static final Integer CODIGO_ERROR_EMPLEADO=409;

}
