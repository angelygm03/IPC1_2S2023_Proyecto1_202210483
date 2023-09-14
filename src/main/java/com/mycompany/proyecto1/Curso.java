package com.mycompany.proyecto1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Curso implements Serializable {
    public int codigoCurso;
    public String nombreCurso;
    public int creditos;
    public Profesor profesor;
    public LinkedList<Estudiante> estudiantes = new LinkedList();
    public List<Profesor> listaDeProfesores = new ArrayList<>();

    public Curso(int codigoCurso, String nombreCurso, int creditos, Profesor profesor) {
        this.codigoCurso = codigoCurso;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
        this.profesor = profesor;
    }
    
public Profesor getProfesor() {
    return this.profesor;
}
public int getCodigoCurso() {
    return codigoCurso;
}
public String getNombreCurso(){
    return nombreCurso;
}
public int getCreditos(){
    return creditos;
}

public void setCodigoCurso(int nuevoCodigoCurso) {
        this.codigoCurso = nuevoCodigoCurso;
    }

    public void setNombreCurso(String nuevoNombreCurso) {
        this.nombreCurso = nuevoNombreCurso;
    }

    public void setCreditos(int nuevoCreditos) {
        this.creditos = nuevoCreditos;
    }

  
    public void setProfesor(Profesor nuevoProfesor) {
    this.profesor = nuevoProfesor;
}

}
