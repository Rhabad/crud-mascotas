package com.mascotas.controller;

import com.mascotas.model.entity.Mascota;
import com.mascotas.service.IMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1")
public class MascotaController {

    @Autowired
    private IMascota mascotaService;

    @RequestMapping(value = "/mascota", method = RequestMethod.POST)
    public Mascota create(@RequestBody Mascota mascota){
        return mascotaService.save(mascota);
    }

    @RequestMapping(value = "/mascota", method = RequestMethod.PUT)
    public Mascota update(@RequestBody Mascota mascota){
        return mascotaService.save(mascota);
    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Integer id){
        Mascota mascotaDelete = mascotaService.findById(id);
        mascotaService.delete(mascotaDelete);
    }

    @RequestMapping(value = "/mascota/{id}", method = RequestMethod.GET)
    public Mascota showById(@PathVariable Integer id){
        return mascotaService.findById(id);
    }

}
