package com.natame.api.dto;

import com.natame.api.utils.Credenciales;

public record DAODataModel<T> (
        T data,
        Credenciales credenciales
){ }
