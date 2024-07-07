package com.codigo.mshuamandagahexagonal.infraestructure.dao;

import com.codigo.mshuamandagahexagonal.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,Long> {
    EmpleadoEntity findByNumDoc(String numDoc);
    List<EmpleadoEntity> findByEstado(boolean estado);
    boolean existsByNumDoc(String numDoc);

}
