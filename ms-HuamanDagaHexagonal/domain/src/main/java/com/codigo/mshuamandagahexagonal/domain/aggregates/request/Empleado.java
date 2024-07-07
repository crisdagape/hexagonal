package com.codigo.mshuamandagahexagonal.domain.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Empleado {
    private String numDoc;
    private String cargo;
    private double salario;
    private String telefono;
    private String correo;
    private int edad;
    private String departamento;

    private Empleado(EmpleadoBuilder builder){
        this.numDoc=builder.numDoc;
    }

    public static class EmpleadoBuilder{
        private final String numDoc;
        private String cargo;
        private double salario;
        private String telefono;
        private String correo;
        private int edad;
        private String departamento;

        public EmpleadoBuilder(String numDoc) {
            this.numDoc = numDoc;
        }
        public EmpleadoBuilder  cargo(String cargo){
            this.cargo=cargo;
            return this;
        }
        public EmpleadoBuilder  salario(double salario){
            this.salario=salario;
            return this;
        }
        public EmpleadoBuilder  telefono(String telefono){
            this.telefono=telefono;
            return this;
        }
        public EmpleadoBuilder  correo(String correo){
            this.correo=correo;
            return this;
        }
        public EmpleadoBuilder  edad(Integer edad){
            this.edad=edad;
            return this;
        }
        public EmpleadoBuilder  departamento(String departamento){
            this.departamento=departamento;
            return this;
        }
        public Empleado build() {
            return new Empleado(this);
        }
    }
}
