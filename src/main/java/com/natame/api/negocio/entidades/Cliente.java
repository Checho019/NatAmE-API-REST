package com.natame.api.negocio.entidades;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    private long identificacion;
    private String correo;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String ciudad;
    private String direccion;
    private long telefono;
    private byte codigoRegion;
    private long idRepCapto;
    private long idRepActual;
}
