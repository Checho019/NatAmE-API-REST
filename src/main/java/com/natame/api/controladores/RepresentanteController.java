package com.natame.api.controladores;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Representante;
import com.natame.api.negocio.servicios.interfaces.RepresentanteServicio;
import com.natame.api.utils.Credenciales;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/representante")
@CrossOrigin("*")
public class RepresentanteController {

    private final RepresentanteServicio representanteServicio;

    RepresentanteController(RepresentanteServicio representanteServicio){
        this.representanteServicio = representanteServicio;
    }

    @GetMapping("obtener/{id}")
    public ResponseEntity<Representante> obtenerRepresentante(@PathVariable long id,
                                                              @RequestHeader("user") String user,
                                                              @RequestHeader("password") String password) throws Exception{
        DAODataModel<Long> idDDM = new DAODataModel<>(id, new Credenciales(user, password));
        Representante representante = representanteServicio.consultarRepresentante(idDDM);
        if (representante == null){
            throw new Exception("El representante que busca no existe");
        }
        return ResponseEntity.ok(representante);
    }

    @PostMapping("agregar")
    public ResponseEntity<Representante> agregarRepresentante(@Valid @RequestBody Representante representante,
                                                              @RequestHeader("user") String user,
                                                              @RequestHeader("password") String password) throws Exception{
        DAODataModel<Representante> representanteDDM = new DAODataModel<>(representante, new Credenciales(user, password));
        representanteServicio.crearRepresentante(representanteDDM);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("obtener/{id}")
                .buildAndExpand(representante.getIdentificacion())
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
