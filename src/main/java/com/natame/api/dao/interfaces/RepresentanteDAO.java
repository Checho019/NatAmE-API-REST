package com.natame.api.dao.interfaces;

import com.natame.api.negocio.entidades.Representante;

public interface RepresentanteDAO {
    public Representante obtenerRepresentante(long id) throws Exception;
    public Representante agregarRepresentante(Representante representante) throws Exception;
}
