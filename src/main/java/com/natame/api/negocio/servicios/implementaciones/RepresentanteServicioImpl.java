package com.natame.api.negocio.servicios.implementaciones;

import com.natame.api.dao.interfaces.RepresentanteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;
import com.natame.api.negocio.servicios.interfaces.RepresentanteServicio;
import org.springframework.stereotype.Service;

@Service
public class RepresentanteServicioImpl implements RepresentanteServicio {
    private final RepresentanteDAO representanteDAO;
    RepresentanteServicioImpl(RepresentanteDAO representanteDAO){
        this.representanteDAO = representanteDAO;
    }
    @Override
    public Representante consultarRepresentante(DAODataModel<Long> idRepresentante) throws Exception {
        return representanteDAO.obtenerRepresentante(idRepresentante);
    }

    @Override
    public Representante crearRepresentante(DAODataModel<Representante> representante) throws Exception {
        return representanteDAO.agregarRepresentante(representante);
    }
}
