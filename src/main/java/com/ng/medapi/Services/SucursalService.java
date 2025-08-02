package com.ng.medapi.Services;

import java.util.List;

import com.ng.medapi.Models.Sucursal;

public interface SucursalService {

    List<Sucursal> listarSucursales();
    Sucursal buscarPorId(Long id);
    List<Sucursal> buscarPorNombre(String nombre);
    Sucursal crearSucursal (Sucursal sucursal);
    Sucursal actualizarSucursal(Long id, Sucursal sucursal);
    void eliminarSucursal(Long id);

}