package com.ng.medapi.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ng.medapi.Exceptions.SucursalException;
import com.ng.medapi.Models.Sucursal;
import com.ng.medapi.Repositories.SucursalRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SucursalServiceImpl implements SucursalService {

    private SucursalRepository sucursalRepo;

    @Override
    public List<Sucursal> listarSucursales() {
        return sucursalRepo.findAll();
    }

    @Override
    public Sucursal buscarPorId(Long id) {
        return sucursalRepo.findById(id).orElseThrow(
                () -> new SucursalException("Sucursal con el id: " + id + " no encontrada"));
    }

    @Override
    public List<Sucursal> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new SucursalException("El nombre no puede estar vacío");
        }

        List<Sucursal> sucursales = sucursalRepo.findByNombreContainingIgnoreCase(nombre);

        if (sucursales.isEmpty()) {
            throw new SucursalException("No se encontraron sucursales con ese nombre");
        }

        return sucursales;
    }

    @Override
    public Sucursal crearSucursal(Sucursal sucursal) {
        return sucursalRepo.save(sucursal);
    }

    @Override
    public Sucursal actualizarSucursal(Long id, Sucursal sucursalActualizada) {
        Sucursal existente = sucursalRepo.findById(id).orElseThrow(
                () -> new SucursalException("Sucursal con el id: " + id + " no encontrada"));

        existente.setNombre(sucursalActualizada.getNombre());
        existente.setDireccion(sucursalActualizada.getDireccion());
        existente.setTelefono(sucursalActualizada.getTelefono());
        existente.setLatitud(sucursalActualizada.getLatitud());
        existente.setLongitud(sucursalActualizada.getLongitud());

        return sucursalRepo.save(existente);
    }

    @Override
    public void eliminarSucursal(Long id) {
        if (!sucursalRepo.existsById(id)) {
            throw new SucursalException("Sucursal con el id: " + id + " no encontrada");
        }
        sucursalRepo.deleteById(id);
    }

}
