package com.codigo.mshuamandagahexagonal.infraestructure.adapters;

import com.codigo.mshuamandagahexagonal.domain.aggregates.constants.Constants;
import com.codigo.mshuamandagahexagonal.domain.aggregates.dto.EmpleadoDTO;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.Empleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.request.RequestEmpleado;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseBase;
import com.codigo.mshuamandagahexagonal.domain.aggregates.response.ResponseReniec;
import com.codigo.mshuamandagahexagonal.domain.ports.out.EmpleadoServiceout;
import com.codigo.mshuamandagahexagonal.infraestructure.dao.EmpleadoRepository;
import com.codigo.mshuamandagahexagonal.infraestructure.entity.EmpleadoEntity;
import com.codigo.mshuamandagahexagonal.infraestructure.mapper.EmpleadoMapper;
import com.codigo.mshuamandagahexagonal.infraestructure.redis.RedisService;
import com.codigo.mshuamandagahexagonal.infraestructure.rest.ClientReniec;
import com.codigo.mshuamandagahexagonal.infraestructure.util.Util;
import feign.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpleadoAdapter implements EmpleadoServiceout {
    private final EmpleadoRepository empleadoRepository;
    private final ClientReniec reniec;
    private final EmpleadoMapper empleadoMapper;
    private final RestTemplate restTemplate;
    private final RedisService redisService;

    @Value("${token}")
    private String token;

    @Override
    public ResponseBase crearPersonaOut(Empleado empleado) {
        if(!empleadoRepository.existsByNumDoc(empleado.getNumDoc())){
            EmpleadoEntity empleadoEntity = getEntityRestTemplate(empleado);
                if(empleadoEntity!=null)
                {
                    empleadoRepository.save(empleadoEntity);
                    EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleadoRepository.save(empleadoEntity));
                    return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITO, Optional.of(empleadoDTO) );
                }
                else {
                return new ResponseBase(Constants.CODIGO_ERROR,Constants.MENSAJE_ERROR,Optional.empty())  ;
                 }
                }
        else{
                return new ResponseBase(Constants.CODIGO_ERROR,Constants.MENSAJE_EXISTE_EMPLEADO_BD,Optional.empty());
            }
    }
    @Override
    public ResponseBase GetEmpeladoOut(String numDoc) {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_KEY_GUARDAR+numDoc);
        if(redisInfo!=null){
            EmpleadoEntity empleado = Util.convertirDesdeString(redisInfo,EmpleadoEntity.class);
            EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleado);
            return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITOSO_DESDE_REDIS,Optional.of(empleadoDTO));
        }else{
            EmpleadoEntity empleado = empleadoRepository.findByNumDoc(numDoc);
            String dataToRedis=Util.convertirAString(empleado);
            redisService.saveInRedis(Constants.REDIS_KEY_GUARDAR+numDoc,dataToRedis,10);
            EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleado);
            return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITOSO_DESDE_BD,Optional.of(empleadoDTO));
        }
    }

    @Override
    public ResponseBase GetAllOut() {
        String redisInfo = redisService.getFromRedis(Constants.REDIS_KEY_LISTA);
        if(redisInfo!=null) {
            EmpleadoEntity empleado = Util.convertirDesdeString(redisInfo,EmpleadoEntity.class);
            EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleado);
            return new ResponseBase(Constants.CODIGO_EXITO,Constants.MENSAJE_EXITOSO_DESDE_REDIS,Optional.of(empleadoDTO));
        }
        else {
            List<EmpleadoEntity> empleadoEntityList = empleadoRepository.findByEstado(true);
            if (empleadoEntityList != null) {
                String dataToRedis=Util.convertirAStringArray(empleadoEntityList);
                redisService.saveInRedis(Constants.MENSAJE_EXITOSO_DESDE_REDIS,dataToRedis,10);
                return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITOSO_DESDE_REDIS, Optional.of(empleadoEntityList));

            } else {
                return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
            }
        }

    }

    @Override
    public ResponseBase UpdateEmpeladoOut(RequestEmpleado empleado) {
        if (empleadoRepository.findByNumDoc(empleado.getNumDoc()) != null) {
            EmpleadoEntity empleadoEntity = getEntityRestTemplateUpdate(empleado);
            EmpleadoEntity empleado1 = empleadoRepository.findByNumDoc(empleado.getNumDoc()); // .findByNumDoc(empleado.getNumDoc());
            if (empleadoEntity != null) {
                empleadoEntity.setId(empleado1.getId());
                empleadoEntity.setUsuaCrea(empleado1.getUsuaCrea());
                empleadoEntity.setDateCrea(empleado1.getDateCrea());
                empleadoEntity.setUsuaDelete(empleado1.getUsuaDelete());
                empleadoEntity.setDateDelete(empleado1.getDateDelete());
                empleadoRepository.save(empleadoEntity);
                EmpleadoDTO empleadoDTO = empleadoMapper.mapToDto(empleadoEntity);
                return new ResponseBase(Constants.CODIGO_EXITO, Constants.MENSAJE_EXITO_ACTUALIZADO, Optional.of(empleadoDTO));
            } else {
                return new ResponseBase(Constants.CODIGO_ERROR, Constants.MENSAJE_ERROR, Optional.empty());
            }
           }
        else {
                return new ResponseBase(Constants.CODIGO_ERROR,Constants.MENSAJE_NOEXISTE_DOC_BD,Optional.empty())  ;
            }
    }

    @Override
    public boolean deleteEmpleadoOut(String numDoc) {
        if(redisService.exists(Constants.REDIS_KEY_GUARDAR + numDoc))
          {
            redisService.deleteKey(Constants.REDIS_KEY_GUARDAR + numDoc);
            EmpleadoEntity empleadoEntity = empleadoRepository.findByNumDoc(numDoc);
            empleadoEntity.setDateDelete(new Timestamp(System.currentTimeMillis()));
            empleadoEntity.setUsuaDelete(Constants.USU_ADMIN);
            empleadoEntity.setEstado(Constants.ESTADO_INACTIVO);
            empleadoRepository.save(empleadoEntity);

            return true;
        }
        else  {
            return false;
        }

    }


    private EmpleadoEntity getEmpleadoEntity(RequestEmpleado requestEmpleado){
        ResponseReniec responseReniec = getExec(requestEmpleado.getNumDoc());
        EmpleadoEntity empleadoEntity = new EmpleadoEntity();
        empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
        empleadoEntity.setNombre(responseReniec.getNombres());
        empleadoEntity.setApellido(responseReniec.getApellidoPaterno() +" "+responseReniec.getApellidoMaterno()  );
        empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
        empleadoEntity.setEstado(Constants.ESTADO_ACTIVO);
        empleadoEntity.setUsuaCrea(Constants.USU_ADMIN);
        empleadoEntity.setDateCrea(new Timestamp(System.currentTimeMillis()));

        return empleadoEntity;
    }
    private ResponseReniec getExec(String numero){
        String head="Bearer "+token;
        ResponseReniec responseReniec = reniec.getInfoReniec(numero,head);
        return  responseReniec;
    }

    private  EmpleadoEntity getEntityRestTemplate(Empleado requestEmpleado){
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero="+requestEmpleado.getNumDoc();
        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)),
                    ResponseReniec.class
            );
            ResponseReniec responseReniec = response.getBody();
            EmpleadoEntity empleadoEntity = new EmpleadoEntity();
            empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
            empleadoEntity.setNombre(responseReniec.getNombres());
            empleadoEntity.setApellido(responseReniec.getApellidoPaterno() +" "+responseReniec.getApellidoMaterno()  );
            empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
            empleadoEntity.setEstado(Constants.ESTADO_ACTIVO);
            empleadoEntity.setUsuaCrea(Constants.USU_ADMIN);
            empleadoEntity.setDateCrea(new Timestamp(System.currentTimeMillis()));

            return empleadoEntity;
        }catch (HttpClientErrorException e ){
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA " +e.getStatusCode());
        }
        return null;
    }
    private  EmpleadoEntity getEntityRestTemplateUpdate(RequestEmpleado requestEmpleado){
        String url = "https://api.apis.net.pe/v2/reniec/dni?numero="+requestEmpleado.getNumDoc();
        try {
            ResponseEntity<ResponseReniec> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    new HttpEntity<>(createHeaders(token)),
                    ResponseReniec.class
            );
            ResponseReniec responseReniec = response.getBody();
            EmpleadoEntity empleadoEntity = new EmpleadoEntity();
            empleadoEntity.setNumDoc(responseReniec.getNumeroDocumento());
            empleadoEntity.setNombre(responseReniec.getNombres());
            empleadoEntity.setApellido(responseReniec.getApellidoPaterno() +" "+responseReniec.getApellidoMaterno()  );
            empleadoEntity.setTipoDoc(responseReniec.getTipoDocumento());
            empleadoEntity.setEdad(requestEmpleado.getEdad());
            empleadoEntity.setCargo(requestEmpleado.getCargo());
            empleadoEntity.setCorreo(requestEmpleado.getCorreo());
            empleadoEntity.setSalario(requestEmpleado.getSalario());
            empleadoEntity.setTelefono(requestEmpleado.getTelefono());
            empleadoEntity.setDepartamento(requestEmpleado.getDepartamento());
            empleadoEntity.setEstado(Constants.ESTADO_ACTIVO);
            empleadoEntity.setUsuaUpdate(Constants.USU_ADMIN);
            empleadoEntity.setDateUdpate(new Timestamp(System.currentTimeMillis()));

            return empleadoEntity;
        }catch (HttpClientErrorException e ){
            System.err.println("ERROR AL CONSUMIR EL API EXTERNA " +e.getStatusCode());
        }
        return null;
    }
    private HttpHeaders createHeaders(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer " + token);
        return headers;
    }

}
