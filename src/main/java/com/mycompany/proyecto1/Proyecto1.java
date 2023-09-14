package com.mycompany.proyecto1;


import gui.LoginJFrame;

import java.util.LinkedList;
/**
 *
 * @author Usuario
 */
public class Proyecto1 {
public static LinkedList<Usuario> usuarios = new LinkedList<>();

    public static void main(String[] args) {
        AppState.deserializar();

         if (AppState.usuarios.isEmpty()) {
        Administrador administradorPrueba = new Administrador("admin", "admin");
        AppState.usuarios.push(administradorPrueba);
        
    }
        LoginJFrame loginJFrame = new LoginJFrame();
    }
   

}





