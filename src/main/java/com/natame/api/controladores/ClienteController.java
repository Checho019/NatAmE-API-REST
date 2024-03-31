package com.natame.api.controladores;

import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.servicios.interfaces.ClienteServicio;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable String correo) throws Exception{
        Cliente cliente = clienteServicio.consultarCliente(correo);
        if (cliente == null){
            throw new Exception("El cliente que busca no existe");
        }
        return ResponseEntity.ok(cliente);
    }

    @PostMapping("agregar")
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) throws Exception{
        clienteServicio.agregarCliente(cliente);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("obtener/{correo}")
                    .buildAndExpand(cliente.getCorreo())
                    .toUri();
        return ResponseEntity.created(location).build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Exception> manejoException(Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

}
