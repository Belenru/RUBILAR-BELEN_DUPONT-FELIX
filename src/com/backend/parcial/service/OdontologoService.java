package com.backend.parcial.service;

import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private IDao<Odontologo> odontologoIDaoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDaoIDao = odontologoIDao;
    }

    public Odontologo registrarOdontologo(Odontologo odontologo){
        return odontologoIDaoIDao.registrar(odontologo);
    }

    public List<Odontologo> listarTodos(){
        return odontologoIDaoIDao.listarTodos();
    }

}
