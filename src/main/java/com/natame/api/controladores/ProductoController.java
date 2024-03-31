package com.natame.api.controladores;

import com.natame.api.negocio.entidades.Producto;
import com.natame.api.negocio.servicios.interfaces.ProductoServicio;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Producto> obtenerProducto(@PathVariable int codigo) throws Exception{
        Producto producto = productoServicio.consultarProducto(codigo);
        if (producto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/")
    public ResponseEntity<List<Producto>> obtenerProductos() throws Exception {
        List<Producto> productos = productoServicio.obtenerProductos();
        return ResponseEntity.ok(productos);
    }
}
