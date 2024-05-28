package com.natame.api.controladores;

import com.natame.api.dao.implementaciones.CarritoDAO;
import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Carrito;
import com.natame.api.negocio.entidades.Cliente;
import com.natame.api.negocio.servicios.interfaces.ClienteServicio;
import com.natame.api.utils.Credenciales;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/carrito")
@CrossOrigin("*")
public class CarritoController {
    private final CarritoDAO carritoDao;

    public CarritoController(CarritoDAO carritoDao) {
        this.carritoDao = carritoDao;
    }

    @GetMapping("obtener/{pedido}")
    public ResponseEntity<Carrito> obtenerCarrito(@PathVariable long pedido,
                                                  @RequestHeader("user") String user,
                                                  @RequestHeader("password") String password) throws Exception{
        DAODataModel<Long> pedidoDDM = new DAODataModel<>(pedido, new Credenciales(user, password));
        Carrito carrito = carritoDao.obtenerCarrito(pedidoDDM);
        if (carrito == null){
            throw new Exception("El pedido que busca no existe");
        }
        return ResponseEntity.ok(carrito);
    }

    @PostMapping("agregar")
    public ResponseEntity<Carrito> agregarCarrito(@Valid @RequestBody Carrito carrito,
                                                  @RequestHeader("user") String user,
                                                  @RequestHeader("password") String password) throws Exception{
        DAODataModel<Carrito> carritoDDM = new DAODataModel<>(carrito, new Credenciales(user, password));
        carritoDao.agregarCarrito(carritoDDM);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("obtener/{pedido}")
                .buildAndExpand(carrito.getCodPedido())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}

