package com.mascotas.service;

import com.mascotas.model.dto.MascotaDto;
import com.mascotas.model.entity.Mascota;

import java.util.List;

public interface IMascotaService {

    List<Mascota> listAll();
    Mascota save(MascotaDto MascotaDto);
    Mascota findById(Integer id);
    void delete(Mascota mascota);
    boolean existsById(Integer id);

}
