package com.natame.api.dto;

public record ProductoPrecioVista(
        String nombre,
        long precio,
        long disponible,
        int codRegion
) {
}
