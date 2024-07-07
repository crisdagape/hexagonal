package com.codigo.mshuamandagahexagonal.infraestructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Entity
@Table(name = "empleado")
@Getter
@Setter
public class EmpleadoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nombre", nullable = false, length = 150)
    private String nombre;
    @Column(name = "apellido", nullable = false, length = 150)
    private String apellido;
    @Column(name = "edad")
    private Integer edad;
    @Column(name = "cargo", length = 45)
    private String cargo;
    @Column(name = "tipo_documento", nullable = false, length = 150)
    private String tipoDoc;
    @Column(name = "numero_documento", nullable = false, length = 150)
    private String numDoc;
    @Column(name = "departamento", length = 150)
    private String departamento;
    @Column(name = "salario")
    private Double salario;
    @Column(name = "telefono", length = 150)
    private String telefono;
    @Column(name = "correo", length = 150)
    private String correo;
    @Column(name = "estado", nullable = false)
    private Boolean estado;
    @Column(name = "direccion", length = 150)
    private String direccion;
    @Column(name = "date_create")
    private Timestamp dateCrea;
    @Column(name = "usua_crea", length = 45)
    private String usuaCrea;
    @Column(name = "date_update")
    private Timestamp dateUdpate;
    @Column(name = "usua_update", length = 45)
    private String usuaUpdate;
    @Column(name = "date_delete")
    private Timestamp dateDelete;
    @Column(name = "usua_delet", length = 45)
    private String usuaDelete;
}
