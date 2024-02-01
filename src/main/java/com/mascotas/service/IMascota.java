package com.mascotas.service;

import com.mascotas.model.entity.Mascota;
import org.springframework.stereotype.Service;

public interface IMascota {

    Mascota save(Mascota mascota);
    Mascota findById(Integer id);
    void delete(Mascota mascota);

}
