package com.natame.api.negocio.entidades;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @NotNull
    @Positive
    private long identificacion;
    @Email
    private String correo;
    @NotNull
    @NotEmpty
    private String nombre1;
    private String nombre2;
    @NotEmpty
    private String apellido1;
    private String apellido2;
    @NotEmpty
    private String ciudad;
    @NotEmpty
    private String direccion;
    @NotNull
    @Positive
    private long telefono;
    @NotNull
    @Positive
    private long idRepCapto;
    @NotNull
    @Positive
    private long idRepActual;
}
