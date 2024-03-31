package com.natame.api.negocio.servicios.implementaciones;

import com.natame.api.dao.interfaces.ClienteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.servicios.interfaces.ClienteServicio;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicioImpl implements ClienteServicio {
    private final ClienteDAO clienteDAO;

    public ClienteServicioImpl(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    @Override
    public Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception {
        return clienteDAO.agregarCliente(cliente);
    }

    @Override
    public Cliente consultarCliente(DAODataModel<String> correoCliente) throws Exception {
        return clienteDAO.obtenerCliente(correoCliente);
    }
}
