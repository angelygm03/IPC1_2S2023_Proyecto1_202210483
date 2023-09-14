package com.mycompany.proyecto1;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Usuario implements Serializable {
    public String nombreUsuario;
    public String password;
    public boolean usuarioProfesor;
    
    public Usuario(String nombreUsuario, String password, boolean usuarioProfesor) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.usuarioProfesor = usuarioProfesor;
    }

    public boolean verificarContraseña(String contraseña) {
        return this.password.equals(contraseña);
    }

    
}
