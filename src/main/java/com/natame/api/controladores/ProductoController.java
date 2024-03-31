package com.natame.api.controladores;

import com.natame.api.dto.DAODataModel;
import com.natame.api.negocio.entidades.Producto;
import com.natame.api.negocio.servicios.interfaces.ProductoServicio;
import com.natame.api.utils.Credenciales;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/producto")
@CrossOrigin("*")
public class ProductoController {

    private final ProductoServicio productoServicio;

    public ProductoController(ProductoServicio productoServicio){
        this.productoServicio = productoServicio;
    }

    @GetMapping("obtener/{codigo}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int codigo,
                                                    @RequestHeader("user") String user,
                                                    @RequestHeader("password") String password) throws Exception{
        DAODataModel codigoDDM = new DAODataModel(codigo, new Credenciales(user, password));
        Producto producto = productoServicio.consultarProducto(codigoDDM);
        if (producto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerProductos(@RequestHeader("user") String user,
                                                           @RequestHeader("password") String password) throws Exception {
        DAODataModel<?> credenciales = new DAODataModel<>(null,new Credenciales(user, password));
        List<Producto> productos = productoServicio.obtenerProductos(credenciales);
        return ResponseEntity.ok(productos);
    }
}
