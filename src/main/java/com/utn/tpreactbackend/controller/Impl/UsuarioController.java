package com.utn.tpreactbackend.controller.Impl;

import com.utn.tpreactbackend.entities.Usuario;
import com.utn.tpreactbackend.service.Impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioService> {

    @Autowired
    private UsuarioService usuarioService;

    public UsuarioController(UsuarioService servicio) {
        super(servicio);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginUsuario) {
        Usuario usuario = usuarioService.findByNombreUsuario(loginUsuario.getNombreUsuario());
        if (usuario != null && new BCryptPasswordEncoder().matches(loginUsuario.getClave(), usuario.getClave())) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario y/o Clave incorrectos, vuelva a intentar");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario newUsuario) {
        // Verifica si ya existe un usuario con el mismo nombre de usuario
        Usuario existingUsuario = usuarioService.findByNombreUsuario(newUsuario.getNombreUsuario());
        if (existingUsuario != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una cuenta con este nombre de usuario");
        }

        // Encripta la contraseña
        String encriptedClave = new BCryptPasswordEncoder().encode(newUsuario.getClave());
        newUsuario.setClave(encriptedClave);

        // Valida el rol (solo acepta "Admin", "Operador" o "Visor")
        if (!isValidRol(newUsuario.getRol())) {
            return ResponseEntity.badRequest().body("Rol inválido. Los roles válidos son: Admin, Operador, Visor");
        }

        // Guarda el usuario en la base de datos
        Usuario savedUsuario = usuarioService.save(newUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    private boolean isValidRol(String rol) {
        return rol.equals("Admin") || rol.equals("Operador") || rol.equals("Visor");
    }
}




