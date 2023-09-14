package com.mycompany.proyecto1;

import java.util.LinkedList;

/**
 *
 * @author Usuario
 */
public class Estudiante extends Persona {
    public LinkedList<Estudiante> estudiantes = new LinkedList();
    
    public Estudiante(int codigo, String nombre, String apellido, String correo, String genero, String passwordPersona) {
        super(codigo, nombre, apellido, correo, genero, passwordPersona);
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
}
