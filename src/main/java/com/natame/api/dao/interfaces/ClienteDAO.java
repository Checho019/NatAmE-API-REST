package com.natame.api.dao.interfaces;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;

public interface ClienteDAO {
    Cliente obtenerCliente(DAODataModel<String> correo) throws Exception;
    Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception;
}
