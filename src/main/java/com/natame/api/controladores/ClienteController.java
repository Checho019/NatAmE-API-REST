package com.natame.api.controladores;

import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.servicios.implementaciones.ClienteServicioImpl;
import com.natame.api.negocio.servicios.interfaces.ClienteServicio;
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
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String correo){
        Cliente cliente = clienteServicio.consultarCliente(correo);
        if (cliente == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("agregar")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente){
        clienteServicio.agregarCliente(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("obtener/{correo}")
                .buildAndExpand(cliente.getCorreo())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
