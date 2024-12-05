package com.dulcehogar.logica;

import javax.swing.JOptionPane;


public class Validaciones {
    
    public static boolean validarCampoVacio(String texto, String campo) {
        if (texto == null || texto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El campo " + campo + " no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }   
    
    public static boolean validarRUT(String rut) {
        if (rut == null || rut.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un RUT.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        if (rut.length() < 11 || rut.length() > 12) {
            JOptionPane.showMessageDialog(null, "El RUT debe tener entre 11 y 12 caracteres, incluyendo puntos y guión.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    
        if (!rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[\\dkK]")) {
            JOptionPane.showMessageDialog(null, "Formato de RUT inválido. Ej: 12.345.678-9", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public static boolean validarSoloNumeros(String texto, String campo) {
        if (!texto.matches("\\d{8}")) {
            JOptionPane.showMessageDialog(null, "El campo " + campo + " debe contener 8 números.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    public static boolean validarCorreo(String correo) {
        final String EMAIL_REGEX = "^(?!.*\\.\\.)[\\w.-]{1,64}@[a-zA-Z\\d-]+(\\.[a-zA-Z\\d-]+)*\\.[a-zA-Z]{2,}$";
        if (!correo.matches(EMAIL_REGEX)) {
            JOptionPane.showMessageDialog(null, "Formato de correo inválido. Ej: hola@mail.com", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }  
}
