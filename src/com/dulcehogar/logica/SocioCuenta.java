package com.dulcehogar.logica;

import java.util.ArrayList;

public class SocioCuenta extends Socio {
    private int valorDeCuota;       // Valor estándar de la cuota
    private int cantidadDeAporte;  // Total acumulado de cuotas pagadas
    private int ultimoPago;        // Último monto pagado
    

    public SocioCuenta(){
    }
    
    // Constructor que hereda de Socio
    public SocioCuenta(String rut, String nombre, String apellidoPaterno, String apellidoMaterno,
                       String correo, String domicilio, String region, String ciudad,
                       String comuna, int telefono, int numeroDeSocio) {
        super(rut, nombre, apellidoPaterno, apellidoMaterno, correo, domicilio, region, ciudad, comuna, telefono, numeroDeSocio);
        this.valorDeCuota = 0;       // Valor inicial de la cuota
        this.cantidadDeAporte = 0;  // Inicializar total de aportes
        this.ultimoPago = 0;        // Inicializar último pago
      
    }

    // Getters y Setters
    public int getValorDeCuota() {
        return valorDeCuota;
    }

    public void setValorDeCuota(int valorDeCuota) {
        this.valorDeCuota = valorDeCuota;
    }

    public int getCantidadDeAporte() {
        return cantidadDeAporte;
    }

    public int getUltimoPago() {
        return ultimoPago;
    }

    // Método para registrar un pago de cuota
    public void pagoDeCuota(int monto) {
        this.ultimoPago = monto;
        this.cantidadDeAporte += monto;
    }


    // Método para mostrar datos financieros
    public String mostrarDatosFinancieros() {
        return "Valor de la cuota: $" + valorDeCuota +
               "\nÚltimo pago: $" + ultimoPago +
               "\nTotal aportado: $" + cantidadDeAporte;
    }
}

