package com.mascotas.model.dao;

import com.mascotas.model.entity.Mascota;
import org.springframework.data.repository.CrudRepository;

public interface MascotaDao extends CrudRepository<Mascota, Integer> {
}
