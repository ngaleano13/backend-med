package com.ng.medapi.Services;

import java.util.List;

import com.ng.medapi.Dtos.UsuarioUpdateDTO;
import com.ng.medapi.Models.Usuario;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario buscarPorId(Long id);
    Usuario buscarPorDocumento(String documento);
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(String email, UsuarioUpdateDTO datosNuevos);
    Usuario actualizarUsuarioPorId(Long id, UsuarioUpdateDTO datosNuevos);
    Usuario actualizarRol(Long id);
    void eliminarUsuario(Long id);
    Usuario buscarPorEmail(String email);
}
