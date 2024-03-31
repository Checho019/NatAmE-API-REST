package com.natame.api.dao.implementaciones;

import com.natame.api.dao.interfaces.ClienteDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    private final List<Cliente> clientes;

    public ClienteDAOImpl(){

        this.clientes = new ArrayList<>(
                Arrays.asList(
                        new Cliente(
                                1L,
                                "correo",
                                "sergio",
                                "santiago",
                                "duarte",
                                "rojas",
                                "bogota",
                                "calle1",
                                123L,
                                1L,
                                1L)
                )
        );
    }

    @Override
    public Cliente obtenerCliente(DAODataModel<String> correo) throws Exception{
        for (Cliente cliente: clientes){
            if (cliente.getCorreo().equals(correo.data())){
                return cliente;
            }
        }
        return null;
    }

    @Override
    public Cliente agregarCliente(DAODataModel<Cliente> cliente) throws Exception{
        this.clientes.add(cliente.data());
        return cliente.data();
    }
}
