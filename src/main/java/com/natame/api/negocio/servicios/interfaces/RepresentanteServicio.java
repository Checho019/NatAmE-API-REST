package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;

public interface RepresentanteServicio {
    Representante consultarRepresentante(DAODataModel<Long> idRepresentante) throws Exception;
    Representante crearRepresentante(DAODataModel<Representante> representante) throws Exception;
}
