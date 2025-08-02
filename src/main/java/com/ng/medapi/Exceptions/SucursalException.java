package com.ng.medapi.Exceptions;

public class SucursalException extends RuntimeException {

    public SucursalException(String mensaje) {
        super(mensaje);
    }

    public SucursalException(String mensaje, Throwable problema) {
        super(mensaje, problema);
    }
}
