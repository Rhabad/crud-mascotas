package com.mascotas.service.impl;

import com.mascotas.model.dao.MascotaDao;
import com.mascotas.model.entity.Mascota;
import com.mascotas.service.IMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MascotaImpl implements IMascota {

    @Autowired
    private MascotaDao mascotaDao;

    @Override
    @Transactional
    public Mascota save(Mascota mascota) {
        return mascotaDao.save(mascota);
    }

    @Override
    @Transactional(readOnly = true)
    public Mascota findById(Integer id) {
        return mascotaDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Mascota mascota) {
        mascotaDao.delete(mascota);
    }
}
