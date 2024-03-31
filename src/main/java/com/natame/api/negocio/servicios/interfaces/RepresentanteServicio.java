package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.negocio.entidades.Representante;

public interface RepresentanteServicio {
    public Representante crearRepresentante(long idRepresentante) throws Exception;
    public Representante consultarRepresentante(Representante representante) throws Exception;
}
