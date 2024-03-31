package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;

public interface ClienteServicio {
    public Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception;
    public Cliente consultarCliente(DAODataModel<String> correoCliente) throws Exception;
}
