package com.mycompany.proyecto1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Administrador extends Usuario implements Serializable {
    public LinkedList<Curso> cursos = new LinkedList();
    public ArrayList<Profesor> profesores = new ArrayList();
    public ArrayList<Estudiante> estudiantes = new ArrayList();
    
    public ArrayList<Profesor> getProfesores() {
        return profesores;
    }
    
    public void crearProfesor(int codigo, String nombre, String apellido, String correo, String genero, String passwordPersona, String contrasenaAsignada) {
        Profesor profesor = new Profesor(codigo, nombre, apellido, correo, genero, passwordPersona, contrasenaAsignada);
        profesores.add(profesor);
    }
    public void crearEstudiante(int codigo, String nombre, String apellido, String correo, String genero, String passwordPersona) {
        Estudiante estudiante = new Estudiante(codigo, nombre, apellido, correo, genero, passwordPersona);
        estudiantes.add(estudiante);
    }
    public void crearCurso(int codigoCurso, String nombreCurso, int creditos, Profesor profesor) {
        Curso curso = new Curso(codigoCurso, nombreCurso, creditos, profesor);
        cursos.add(curso);
    }
    
    public Administrador(String nombreUsuario, String password) {
        super(nombreUsuario, password,true);
    }   

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }
    
}
