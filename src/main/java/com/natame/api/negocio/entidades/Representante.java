package com.natame.api.negocio.entidades;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Representante {

    private long identificacion;
    private String correo;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private char genero;
    private Date fechaNacimiento;
    private Date fechaContratacion;
    private long telefono;
    private String diraccion;
    private byte codigoRegion;

}
