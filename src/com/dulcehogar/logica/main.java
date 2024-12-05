package com.dulcehogar.logica;

import com.dulcehogar.ui.Inicio;


public class main {
    
       public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }  
    
}
