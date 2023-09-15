package com.mycompany.proyecto1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class AppState {
    public static Usuario loggedUser; 
    public static LinkedList<Usuario> usuarios = new LinkedList();
    public static LinkedList<Profesor> profesores = new LinkedList<>();
    public static LinkedList<Estudiante> estudiantes = new LinkedList<>();
    public static List<Curso> cursos = new ArrayList<>();
    public static String rutaUsuariosSerializados = "./DatosSerializados/appState.bin";
    
    public static void serializar(){
        File file = new File(rutaUsuariosSerializados);
        
               
        if(!file.exists()) {
            file.getParentFile().mkdir();
        }
        //Se crea el archivo donde se va a serializar
        
        try {
            file.createNewFile();
        } catch(IOException ex) {
            System.out.println("No se pudo crear el archivo de serialización");
        }
        //Se escriben los datos serializados sobre el archivo
        try{
            FileOutputStream fos = new FileOutputStream (rutaUsuariosSerializados);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usuarios);
            oos.writeObject(profesores);
            oos.writeObject(estudiantes);
            oos.writeObject(cursos);
        } catch (FileNotFoundException ex) {
            System.out.println("No se encontró el archivo para serializar");
        } catch (IOException ex){
            System.out.println("Ocurrió un error al intentar escribir sobre el archivo");
        }
    }
    
    public static void deserializar() {
        try {
            File file = new File(rutaUsuariosSerializados);
            if (!file.exists()) {
                return;
            }
            FileInputStream fis = new FileInputStream(rutaUsuariosSerializados);
            ObjectInputStream ois = new ObjectInputStream(fis);
            
            usuarios = (LinkedList<Usuario>) ois.readObject();
            profesores = (LinkedList<Profesor>) ois.readObject();
            estudiantes = (LinkedList<Estudiante>) ois.readObject();
            cursos = (List<Curso>) ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AppState.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex){
            Logger.getLogger(AppState.class.getName()).log(Level.SEVERE,null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppState.class.getName()).log(Level.SEVERE,null, ex);
        }
        
    }
}
