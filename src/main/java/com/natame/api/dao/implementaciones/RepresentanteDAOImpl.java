package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.RepresentanteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RepresentanteDAOImpl implements RepresentanteDAO {
    private List<Representante> representantes = new ArrayList<>();

    @Override
    public Representante obtenerRepresentante(DAODataModel<Long> id) throws Exception {
        for (Representante representante: representantes){
            if (representante.getIdentificacion() == id.data()){
                return representante;
            }
        }
        return null;
    }

    @Override
    public Representante agregarRepresentante(DAODataModel<Representante> representante) throws Exception {
        representantes.add(representante.data());
        return representante.data();
    }
}
