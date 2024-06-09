package com.utn.tpreactbackend.controller.Impl;

import com.utn.tpreactbackend.entities.Usuario;
import com.utn.tpreactbackend.service.Impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class UsuarioController extends BaseControllerImpl<Usuario, UsuarioService> {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public UsuarioController(UsuarioService servicio) {
        super(servicio);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario loginUsuario) {
        try {
            // Autenticar al usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getClave())
            );

            // Establecer la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Obtener los detalles del usuario autenticado
            Usuario usuario = usuarioService.findByNombreUsuario(loginUsuario.getNombreUsuario());

            // Retornar los detalles del usuario
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
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

        // Encripta la contraseña antes de guardarla
        newUsuario.setClave(passwordEncoder.encode(newUsuario.getClave()));

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




