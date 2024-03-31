package com.natame.api.negocio.entidades;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Representante {
    @NotNull
    @Positive
    private long identificacion;
    @Email
    @NotNull
    private String correo;
    @NotEmpty
    private String nombre1;
    private String nombre2;
    @NotEmpty
    private String apellido1;
    private String apellido2;
    @NotEmpty
    private char genero;
    @NotNull
    private Date fechaNacimiento;
    private Date fechaContratacion;
    @NotNull
    @Positive
    private long telefono;
    @NotEmpty
    private String diraccion;
    @NotNull
    @Positive
    private byte codigoRegion;
}
