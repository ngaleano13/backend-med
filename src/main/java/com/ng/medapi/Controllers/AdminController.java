package com.ng.medapi.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ng.medapi.Dtos.UsuarioDTO;
import com.ng.medapi.Dtos.UsuarioUpdateDTO;
import com.ng.medapi.Models.Usuario;
import com.ng.medapi.Services.UsuarioService;
import com.ng.medapi.Utils.UsuarioMapper;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/admin/usuarios")
@AllArgsConstructor
public class AdminController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.buscarPorId(id));
    }

    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioDTO> actualizarUsuarioComoAdmin(
            @PathVariable Long id,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {

        Usuario actualizado = usuarioService.actualizarUsuarioPorId(id, usuarioUpdateDTO);
        UsuarioDTO dto = usuarioMapper.toDTO(actualizado);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/modificarRol/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Usuario> acutalizarAdmin(@PathVariable Long id) {
        Usuario actualizado = usuarioService.actualizarRol(id);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

}