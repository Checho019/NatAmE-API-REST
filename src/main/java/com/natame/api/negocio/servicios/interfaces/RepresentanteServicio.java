package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;

public interface RepresentanteServicio {
    public Representante crearRepresentante(DAODataModel<Long> idRepresentante) throws Exception;
    public Representante consultarRepresentante(DAODataModel<Representante> representante) throws Exception;
}
