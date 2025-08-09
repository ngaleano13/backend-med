package com.ng.medapi.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping("/registro")
    public ResponseEntity<Usuario> registrarUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/perfil")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UsuarioDTO> verPerfil(@AuthenticationPrincipal UserDetails userDetails) {
        Usuario usuario = usuarioService.buscarPorEmail(userDetails.getUsername());
        UsuarioDTO dto = usuarioMapper.toDTO(usuario);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/actualizar")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UsuarioDTO> actualizarPerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UsuarioUpdateDTO usuarioUpdateDTO) {

        Usuario actualizado = usuarioService.actualizarUsuario(userDetails.getUsername(), usuarioUpdateDTO);
        UsuarioDTO dto = usuarioMapper.toDTO(actualizado);
        return ResponseEntity.ok(dto);
    }

}