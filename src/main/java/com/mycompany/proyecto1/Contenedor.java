package com.mycompany.proyecto1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Contenedor implements Serializable {
    public LinkedList<Usuario> usuarios = new LinkedList<>();
    public LinkedList<Profesor> profesores = new LinkedList<>();
    public LinkedList<Estudiante> estudiantes = new LinkedList();
    public List<Curso> cursos = new ArrayList<>();
    
    public Contenedor() {
    }
}
