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

    private SucursalRepository repo;

    public List<Sucursal> listarSucursales() {
        return repo.findAll();
    }

    public Sucursal buscarPorId(Long id) {
        return repo.findById(id).orElseThrow(
                () -> new SucursalException("Sucursal con el id: " + id + " no encontrada"));
    }

    public List<Sucursal> buscarPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new SucursalException("El nombre no puede estar vacio");
        }

        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public Sucursal crearSucursal(Sucursal sucursal) {
        return repo.save(sucursal);
    }

    public Sucursal actualizarSucursal(Long id, Sucursal sucursalActualizada) {
        Sucursal existente = repo.findById(id).orElseThrow(
                () -> new SucursalException("Sucursal con el id: " + id + " no encontrada"));

        existente.setNombre(sucursalActualizada.getNombre());
        existente.setDireccion(sucursalActualizada.getDireccion());
        existente.setTelefono(sucursalActualizada.getTelefono());
        existente.setLatitud(sucursalActualizada.getLatitud());
        existente.setLongitud(sucursalActualizada.getLongitud());

        return repo.save(existente);
    }

    public void eliminarSucursal(Long id) {
        if (!repo.existsById(id)) {
            throw new SucursalException("Sucursal con el id: " + id + " no encontrada");
        }
        repo.deleteById(id);
    }

}
