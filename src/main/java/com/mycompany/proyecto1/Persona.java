
package com.mycompany.proyecto1;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Persona implements Serializable {
   public int codigo;
   public String nombre;
   public String apellido;
   public String correo;
   public String genero;
   public String contrasenaPersonalizada;

    public Persona(int codigo, String nombre, String apellido, String correo, String genero, String contrasenaPersonalizada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
        
        if (contrasenaPersonalizada != null && !contrasenaPersonalizada.isEmpty()) {
            this.contrasenaPersonalizada = contrasenaPersonalizada;
        } else {
            this.contrasenaPersonalizada = "1234"; // Contraseña predeterminada
        }
    }
    public boolean verificarContrasenaPersonalizada(String contrasena) {
        return this.contrasenaPersonalizada.equals(contrasena);
    } 
    
}
