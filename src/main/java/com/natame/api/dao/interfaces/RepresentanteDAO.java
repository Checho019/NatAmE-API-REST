package com.natame.api.dao.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;

public interface RepresentanteDAO {
    Representante obtenerRepresentante(DAODataModel<Long> id) throws Exception;
    Representante agregarRepresentante(DAODataModel<Representante> representante) throws Exception;
}
