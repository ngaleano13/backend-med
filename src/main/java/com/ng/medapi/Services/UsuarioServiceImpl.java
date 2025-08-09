package com.ng.medapi.Services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ng.medapi.Dtos.UsuarioUpdateDTO;
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
    public Usuario actualizarUsuario(String email, UsuarioUpdateDTO datosNuevos) {
        Usuario existente = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));

        validarCambios(existente, datosNuevos);
        return usuarioRepo.save(existente);
    }

    @Override
    public Usuario actualizarUsuarioPorId(Long id, UsuarioUpdateDTO datosNuevos) {
        Usuario existente = usuarioRepo.findById(id)
                .orElseThrow(() -> new UsuarioException("Usuario no encontrado"));

        validarCambios(existente, datosNuevos);
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

    private void validarCambios(Usuario existente, UsuarioUpdateDTO datosNuevos) {
        if (datosNuevos.getEmail() != null && !datosNuevos.getEmail().isBlank()) {
            existente.setEmail(datosNuevos.getEmail());
        }
        if (datosNuevos.getTelefono() != null && !datosNuevos.getTelefono().isBlank()) {
            existente.setTelefono(datosNuevos.getTelefono());
        }
        if (datosNuevos.getDocumento() != null && !datosNuevos.getDocumento().isBlank()) {
            existente.setDocumento(datosNuevos.getDocumento());
        }
        if (datosNuevos.getPassword() != null && !datosNuevos.getPassword().isBlank()) {
            validarPassword(datosNuevos.getPassword());
            existente.setPassword(passwordEncoder.encode(datosNuevos.getPassword()));
        }
    }

    private void validarPassword(String password) {
        if (password.length() < 8) {
            throw new UsuarioException("La contraseña debe tener al menos 8 caracteres");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new UsuarioException("La contraseña debe contener al menos una mayuscula");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new UsuarioException("La contraseña debe contener al menos un numero");
        }
    }

}
