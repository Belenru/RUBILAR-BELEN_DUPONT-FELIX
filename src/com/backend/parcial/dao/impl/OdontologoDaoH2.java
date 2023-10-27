package com.backend.parcial.dao.impl;

import com.backend.parcial.dao.H2Connection;
import com.backend.parcial.dao.IDao;
import com.backend.parcial.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoH2 implements IDao <Odontologo> {

    private final Logger LOGGER = Logger.getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        Odontologo odontologoRegistrado = null;

        try {
            connection =H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGO (NOMBRE, APELLIDO, NUMERO_MATRICULA) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, odontologo.getNombre());
            preparedStatement.setString(2, odontologo.getApellido());
            preparedStatement.setString(3, odontologo.getNumeroMatricula());
            preparedStatement.execute();

            ResultSet resultSet =preparedStatement.getGeneratedKeys();
            odontologoRegistrado = new Odontologo(odontologo.getNombre(), odontologo.getApellido(), odontologo.getNumeroMatricula());

            while (resultSet.next()){
                odontologoRegistrado.setId(resultSet.getInt("id"));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el odontologo: " + odontologoRegistrado);

            }catch (Exception e) {
        LOGGER.error(e.getMessage());
        e.printStackTrace();
        if (connection != null)
            try {
                connection.rollback();
                LOGGER.info("Tuvimos un problema");
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            } catch (SQLException exception) {
                LOGGER.error(exception.getMessage());
                exception.printStackTrace();
            }
        }
     finally {
        try {
            connection.close();
        } catch (Exception ex) {
            LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
        }
    }


        return odontologoRegistrado;
    }

    ////////////////////////////

    @Override
    public List<Odontologo> listarTodos() {
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try{
            connection = H2Connection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGO");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){


            }

            LOGGER.info("Listado de domicilios obtenido: " + odontologos);

        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }



        return odontologos;
    }

    @Override
    public Odontologo buscarPorId(int id) {
        Odontologo odontologo = null;
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM ODONTOLOGO WHERE ID = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                odontologo = new Odontologo(rs.getString(1), rs.getString(2), rs.getString(3));
            }

            if(odontologo == null) LOGGER.error("No se ha encontrado el domicilio con id: " + id);
            else LOGGER.info("Se ha encontrado el profesional: " + odontologo);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return odontologo;
    }


}

