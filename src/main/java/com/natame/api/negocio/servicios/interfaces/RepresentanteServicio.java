package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.negocio.entidades.Representante;

public interface RepresentanteServicio {
    public Representante crearRepresentante(long idRepresentante);
    public Representante consultarRepresentante(Representante representante);
}
