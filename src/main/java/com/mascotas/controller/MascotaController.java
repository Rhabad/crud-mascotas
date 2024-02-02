package com.mascotas.controller;

import com.mascotas.model.dto.MascotaDto;
import com.mascotas.model.entity.Mascota;
import com.mascotas.model.payload.MensajeResponse;
import com.mascotas.service.IMascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class MascotaController {

    @Autowired
    private IMascotaService mascotaService;


    @RequestMapping(value = "/mascotas", method = RequestMethod.GET)
    public ResponseEntity<?> showAll(){
        List<Mascota> getList = mascotaService.listAll();

        if (getList == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("No Hay Registro Alguno En La Base De Datos")
                    .object(null)
                    .build()
                , HttpStatus.OK);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .object(getList)
                .build()
            , HttpStatus.OK);
    }

    @RequestMapping(value = "/mascota", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody MascotaDto mascotaDto){
        Mascota mascotaSave = null;
        try {
            mascotaSave = mascotaService.save(mascotaDto);

            mascotaDto = MascotaDto.builder()
                            .id(mascotaSave.getId())
                            .nombre(mascotaSave.getNombre())
                            .tipo_animal(mascotaSave.getTipo_animal())
                            .raza(mascotaSave.getRaza())
                            .fechaNacimiento(mascotaSave.getFechaNacimiento())
                        .build();

            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("Guardado Correctamente")
                    .object(mascotaDto)
                    .build()
                ,HttpStatus.CREATED);
        } catch (DataAccessException exData) {
            //en caso que no...
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exData.getMessage())
                    .object(null)
                    .build(), HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody MascotaDto mascotaDto, @PathVariable Integer id){
        Mascota mascotaUpdate = null;

        try {
            //Mascota findMascota = mascotaService.findById(id); // o mascotaDto.getId
            if (mascotaService.existsById(id)){ //la forma correcta
                mascotaDto.setId(id);

                mascotaUpdate = mascotaService.save(mascotaDto);

                mascotaDto = MascotaDto.builder()
                        .id(mascotaUpdate.getId())
                        .nombre(mascotaUpdate.getNombre())
                        .tipo_animal(mascotaUpdate.getTipo_animal())
                        .raza(mascotaUpdate.getRaza())
                        .fechaNacimiento(mascotaUpdate.getFechaNacimiento())
                        .build();

                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("Guardado Correctamente")
                        .object(mascotaDto)
                        .build()
                        ,HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(MensajeResponse.builder()
                        .mensaje("El Registro No Se Encuentra En La Base De Datos")
                        .object(null)
                        .build(), HttpStatus.NOT_FOUND);
            }



        } catch (DataAccessException exData) {
            //en caso que no...
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exData.getMessage())
                    .object(null)
                    .build(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){

        try {
            Mascota mascotaDelete = mascotaService.findById(id);
            mascotaService.delete(mascotaDelete);
            //que me devuelva esto
            return new ResponseEntity<>(mascotaDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exData) {
            //en caso que no...
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje(exData.getMessage())
                    .object(null)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Mascota mascota = mascotaService.findById(id);

        if (mascota == null){
            return new ResponseEntity<>(MensajeResponse.builder()
                    .mensaje("El Registro que intenta buscar no existe")
                    .object(null)
                    .build()
                , HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MensajeResponse.builder()
                .mensaje("")
                .object(MascotaDto.builder()
                        .id(mascota.getId())
                        .nombre(mascota.getNombre())
                        .tipo_animal(mascota.getTipo_animal())
                        .raza(mascota.getRaza())
                        .fechaNacimiento(mascota.getFechaNacimiento())
                        .build())
                .build()
            , HttpStatus.OK);
    }

}
