package com.mycompany.proyecto1;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Usuario
 */
public class Profesor extends Persona implements Serializable {
   
    private List<Curso> cursosAsignados;
    public String contrasenaAsignada;
    
    public Profesor(int codigo, String nombre, String apellido, String correo, String genero, String passwordPersona, String contrasenaAsignada) {
        super(codigo, nombre, apellido, correo, genero, "1234");
        this.contrasenaAsignada = contrasenaAsignada;
        if (contrasenaAsignada != null && !contrasenaAsignada.isEmpty()) {
            this.contrasenaPersonalizada = contrasenaAsignada;
        } else {
            this.contrasenaPersonalizada = "1234"; // Contraseña predeterminada
        }
        this.cursosAsignados = new ArrayList<>();

    }
    
    
    
public String getGenero() {
        return genero;
}
public int getCodigo(){
    return codigo;
}
public String getNombre(){
    return nombre;
}
public String getApellido(){
    return apellido;
}
public String getCorreo(){
    return correo;

}
public String getPasswordPersona(){
    return contrasenaPersonalizada;
}

public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setApellido(String nuevoApellido) {
        this.apellido = nuevoApellido;
    }

    public void setCorreo(String nuevoCorreo) {
        this.correo = nuevoCorreo;
    }

  
    public void setGenero(String nuevoGenero) {
        this.genero = nuevoGenero;
    }
    
    @Override
public String toString() {
    return nombre + " " + apellido;
}
public List<Curso> getCursosAsignados() {
        return cursosAsignados;
    }

    
    public void asignarCurso(Curso curso) {
        cursosAsignados.add(curso);
    }
    
}
