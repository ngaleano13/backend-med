package com.ng.medapi.Utils;

import org.springframework.stereotype.Component;

import com.ng.medapi.Dtos.UsuarioDTO;
import com.ng.medapi.Models.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioDTO dto = new UsuarioDTO();
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());
        dto.setDocumento(usuario.getDocumento());
        return dto;
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setDocumento(dto.getDocumento());
        return usuario;
    }
}