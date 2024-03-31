package com.natame.api.dao.interfaces;

import com.natame.api.negocio.entidades.Cliente;

public interface ClienteDAO {
    public Cliente obtenerCliente(String correo) throws Exception;
    public Cliente agregarCliente(Cliente cliente) throws Exception;
}
