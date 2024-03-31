package com.natame.api.dao.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;

public interface ClienteDAO {
    public Cliente obtenerCliente(DAODataModel<String> correo) throws Exception;
    public Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception;
}
