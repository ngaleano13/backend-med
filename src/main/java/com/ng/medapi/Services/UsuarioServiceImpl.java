package com.ng.medapi.Services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ng.medapi.Exceptions.UsuarioException;
import com.ng.medapi.Models.Rol;
import com.ng.medapi.Models.Usuario;
import com.ng.medapi.Repositories.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository usuarioRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
    }

    @Override
    public Usuario buscarPorDocumento(String documento) {
        return usuarioRepo.findByDocumento(documento);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setRol(Rol.USER);
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario existente = usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));

        existente.setEmail(usuarioActualizado.getEmail());
        existente.setDocumento(usuarioActualizado.getDocumento());
        existente.setTelefono(usuarioActualizado.getTelefono());
        existente.setRol(usuarioActualizado.getRol());
        existente.setPassword(passwordEncoder.encode(usuarioActualizado.getPassword()));
        existente.setTurnos(usuarioActualizado.getTurnos());

        return usuarioRepo.save(existente);
    }

    @Override
    public Usuario actualizarRol(Long id) {
        Usuario existente = usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));
        existente.setRol(Rol.ADMIN);
        return usuarioRepo.save(existente);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepo.existsById(id)) {
            throw new UsuarioException("Usuario con el id: " + id + " no encontrada");
        }
        usuarioRepo.deleteById(id);
    }

}
