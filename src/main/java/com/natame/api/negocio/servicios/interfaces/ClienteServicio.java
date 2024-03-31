package com.natame.api.negocio.servicios.interfaces;

import com.natame.api.negocio.entidades.Cliente;

public interface ClienteServicio {
    public Cliente agregarCliente(Cliente cliente) throws Exception;
    public Cliente consultarCliente(String correoCliente) throws Exception;
}
