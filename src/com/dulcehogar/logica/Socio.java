package com.dulcehogar.logica;

import java.util.ArrayList;

public class Socio {
    private String rut;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String domicilio;
    private String region;
    private String ciudad;
    private String comuna;
    private int telefono;
    private int numeroDeSocio;

    
    public Socio() {
    }
    
    public Socio(String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
                 String correo, String domicilio, String region, String ciudad,
                 String comuna, int telefono, int numeroDeSocio) {
        this.rut = rut;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.domicilio = domicilio;
        this.region = region;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.telefono = telefono;
        this.numeroDeSocio = numeroDeSocio;
    }

    // Getters y Setters

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getNumeroDeSocio() {
        return numeroDeSocio;
    }

    public void setNumeroDeSocio(int numeroDeSocio) {
        this.numeroDeSocio = numeroDeSocio;
    }
    

    public String mostrarDatos() {
        return "RUT: " + rut +
               "\nNombre: " + nombre + " " + apellidoPaterno + " " + apellidoMaterno +
               "\nCorreo: " + correo +
               "\nTeléfono: " + telefono +
               "\nNúmero de socio: " + numeroDeSocio;
    }
}

