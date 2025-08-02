package com.ng.medapi.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ng.medapi.Models.Sucursal;
import com.ng.medapi.Services.SucursalService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/sucursales")
@AllArgsConstructor
public class SucursalController {
    
    private final SucursalService service;

      @GetMapping
    public ResponseEntity<List<Sucursal>> listarTodas() {
        return ResponseEntity.ok(service.listarSucursales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Sucursal>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(service.buscarPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<Sucursal> crear(@RequestBody @Valid Sucursal sucursal) {
        Sucursal creada = service.crearSucursal(sucursal);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Long id, @RequestBody @Valid Sucursal sucursal) {
        Sucursal actualizada = service.actualizarSucursal(id, sucursal);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarSucursal(id);
        return ResponseEntity.noContent().build();
    }

}
