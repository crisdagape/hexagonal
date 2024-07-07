package com.codigo.mshuamandagahexagonal.infraestructure.mapper;


import com.codigo.mshuamandagahexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.codigo.mshuamandagahexagonal.infraestructure.entity.EmpleadoEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public EmpleadoDTO mapToDto(EmpleadoEntity empleadoEntity){
        return modelMapper.map(empleadoEntity,EmpleadoDTO.class);
    }

    public EmpleadoEntity mapToEntity(EmpleadoDTO empleadoDTO){
        return modelMapper.map(empleadoDTO,EmpleadoEntity.class);
    }

}
