package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;

public interface ClienteServicio {
    Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception;
    Cliente consultarCliente(DAODataModel<Long> id) throws Exception;
}
