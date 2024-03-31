package com.natame.api.controladores;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.servicios.interfaces.ClienteServicio;
import com.natame.api.utils.Credenciales;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteServicio clienteServicio;

    public ClienteController(ClienteServicio clienteServicio) {
        this.clienteServicio = clienteServicio;
    }

    @GetMapping("obtener/{correo}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String correo,
                                                  @RequestHeader("user") String user,
                                                  @RequestHeader("password") String password) throws Exception{
        DAODataModel<String> correoDDO = new DAODataModel<>(correo, new Credenciales(user, password));
        Cliente cliente = clienteServicio.consultarCliente(correoDDO);
        if (cliente == null){
            throw new Exception("El cliente que busca no existe");
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("agregar")
    public ResponseEntity<Cliente> agregarCliente(@Valid @RequestBody Cliente cliente,
                                                  @RequestHeader("user") String user,
                                                  @RequestHeader("password") String password) throws Exception{
        DAODataModel<Cliente> clienteDDM = new DAODataModel<>(cliente, new Credenciales(user, password));
        clienteServicio.agregarCliente(clienteDDM);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("obtener/{correo}")
                    .buildAndExpand(cliente.getCorreo())
                    .toUri();
        return ResponseEntity.created(location).build();
    }
}
