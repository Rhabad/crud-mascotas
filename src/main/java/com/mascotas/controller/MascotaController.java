package com.mascotas.controller;

import com.mascotas.model.dto.MascotaDto;
import com.mascotas.model.entity.Mascota;
import com.mascotas.service.IMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
public class MascotaController {

    @Autowired
    private IMascota mascotaService;

    @RequestMapping(value = "/mascota", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public MascotaDto create(@RequestBody MascotaDto mascotaDto){
        Mascota mascotaSave = mascotaService.save(mascotaDto);

        return MascotaDto.builder()
                .id(mascotaSave.getId())
                .nombre(mascotaSave.getNombre())
                .tipo_animal(mascotaSave.getTipo_animal())
                .raza(mascotaSave.getRaza())
                .fechaNacimiento(mascotaSave.getFechaNacimiento())
                .build();
    }

    @RequestMapping(value = "/mascota", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public MascotaDto update(@RequestBody MascotaDto mascotaDto){
        Mascota mascotaUpdate = mascotaService.save(mascotaDto);

        return MascotaDto.builder()
                .id(mascotaUpdate.getId())
                .nombre(mascotaUpdate.getNombre())
                .tipo_animal(mascotaUpdate.getTipo_animal())
                .raza(mascotaUpdate.getRaza())
                .fechaNacimiento(mascotaUpdate.getFechaNacimiento())
                .build();
    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();

        try {
            Mascota mascotaDelete = mascotaService.findById(id);
            mascotaService.delete(mascotaDelete);
            //que me devuelva esto
            return new ResponseEntity<>(mascotaDelete, HttpStatus.NO_CONTENT);

        } catch (DataAccessException exData) {
            //en caso que no...
            response.put("mensaje", exData.getMessage());
            response.put("mascota", null);

            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public MascotaDto showById(@PathVariable Integer id){
        Mascota mascota = mascotaService.findById(id);

        return MascotaDto.builder()
                .id(mascota.getId())
                .nombre(mascota.getNombre())
                .tipo_animal(mascota.getTipo_animal())
                .raza(mascota.getRaza())
                .fechaNacimiento(mascota.getFechaNacimiento())
                .build();
    }

}
