package com.utn.tpreactbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Usuario extends Base {

    private String nombreUsuario;
    private String clave;
    private String rol;

    public void setClave(String clave) {
        this.clave = new BCryptPasswordEncoder().encode(clave);
    }
}
