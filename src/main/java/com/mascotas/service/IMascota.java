package com.mascotas.service;

import com.mascotas.model.dto.MascotaDto;
import com.mascotas.model.entity.Mascota;
import org.springframework.stereotype.Service;

public interface IMascota {

    Mascota save(MascotaDto MascotaDto);
    Mascota findById(Integer id);
    void delete(Mascota mascota);

}
