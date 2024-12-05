package com.dulcehogar.logica;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class Principal {

    private ArrayList<Socio> listaSocio;          // Maneja datos personales
    private ArrayList<SocioCuenta> listaCuenta;  // Maneja datos financieros

    public Principal() {
        listaSocio = new ArrayList<>();
        listaCuenta = new ArrayList<>();
    }

    private void registrarSocio() {
        String rut = JOptionPane.showInputDialog("Ingrese el RUT (Ej: 12.345.678-9):");
        if (!Validaciones.validarRUT(rut)) return;

        if (buscarSocioPorRut(rut) != null) {
            mostrarError("Ya existe un socio registrado con este RUT.");
            return;
        }

        String nombre = solicitarCampo("Ingrese su nombre:", "Nombre");
        if (nombre == null) return;
        String apellidoPaterno = solicitarCampo("Ingrese su apellido paterno:", "Apellido Paterno");
        if (apellidoPaterno == null) return;
        String apellidoMaterno = solicitarCampo("Ingrese su apellido materno:", "Apellido Materno");
        if (apellidoMaterno == null) return;
        String correo = solicitarCampo("Ingrese su correo:", "Correo");
        if (!Validaciones.validarCorreo(correo)) return;
        String domicilio = solicitarCampo("Ingrese su domicilio:", "Domicilio");
        if (domicilio == null) return;
        String region = solicitarCampo("Ingrese su región:", "Región");
        if (region == null) return;
        String ciudad = solicitarCampo("Ingrese su ciudad:", "Ciudad");
        if (ciudad == null) return;
        String comuna = solicitarCampo("Ingrese su comuna:", "Comuna");
        if (comuna == null) return;
        String telefonoStr = solicitarCampo("Ingrese su teléfono (8 dígitos):", "Teléfono");
        if (!Validaciones.validarSoloNumeros(telefonoStr, "Teléfono")) return;
        int telefono = Integer.parseInt(telefonoStr);

        int numeroDeSocio = generarNumeroDeSocioAleatorio(rut);

        // Crear y registrar en listas correspondientes
        Socio nuevoSocio = new Socio(rut, nombre, apellidoPaterno, apellidoMaterno, correo,
                                     domicilio, region, ciudad, comuna, telefono, numeroDeSocio);
        listaSocio.add(nuevoSocio);

        SocioCuenta nuevaCuenta = new SocioCuenta(rut, nombre, apellidoPaterno, apellidoMaterno, correo,
                                                  domicilio, region, ciudad, comuna, telefono, numeroDeSocio);
        nuevaCuenta.setValorDeCuota(5000); // Establecer valor predeterminado de cuota
        listaCuenta.add(nuevaCuenta);

        JOptionPane.showMessageDialog(null, "¡Socio registrado exitosamente!\n" + nuevoSocio.mostrarDatos(),
                "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    private String solicitarCampo(String mensaje, String campo) {
        String input = JOptionPane.showInputDialog(mensaje);
        if (!Validaciones.validarCampoVacio(input, campo)) return null;
        return input;
    }

    public int generarNumeroDeSocioAleatorio(String rut) {
        int numeroDeSocio;
        do {
            numeroDeSocio = (int) (Math.random() * 900000000) + 100000000;
        } while (buscarCuentaPorNumeroDeSocio(numeroDeSocio) != null);
        return numeroDeSocio;
    }

    private Socio buscarSocioPorRut(String rut) {
        return listaSocio.stream().filter(s -> s.getRut().equalsIgnoreCase(rut)).findFirst().orElse(null);
    }

    private SocioCuenta buscarCuentaPorNumeroDeSocio(int numeroDeSocio) {
        return listaCuenta.stream().filter(c -> c.getNumeroDeSocio() == numeroDeSocio).findFirst().orElse(null);
    }

    public void pagarCuota() {
        String rut = JOptionPane.showInputDialog("Ingrese su RUT para pagar la cuota:");
        Socio socio = buscarSocioPorRut(rut);
        if (socio == null) {
            mostrarError("RUT no encontrado.");
            return;
        }

        SocioCuenta cuenta = buscarCuentaPorNumeroDeSocio(socio.getNumeroDeSocio());
        if (cuenta == null) {
            mostrarError("No se encontró una cuenta asociada.");
            return;
        }

        String montoStr = JOptionPane.showInputDialog("Ingrese el monto de la cuota a pagar:");
        if (!Validaciones.validarSoloNumeros(montoStr, "Monto")) return;
        int monto = Integer.parseInt(montoStr);

        cuenta.pagoDeCuota(monto);
        JOptionPane.showMessageDialog(null, "Pago registrado exitosamente. Último pago: $" + monto,
                "Pago exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void consultarCuotaPagada() {
        String rut = JOptionPane.showInputDialog("Ingrese su RUT para consultar la última cuota pagada:");
        Socio socio = buscarSocioPorRut(rut);
        if (socio == null) {
            mostrarError("RUT no encontrado.");
            return;
        }

        SocioCuenta cuenta = buscarCuentaPorNumeroDeSocio(socio.getNumeroDeSocio());
        if (cuenta == null) {
            mostrarError("No se encontró una cuenta asociada.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Última cuota pagada: $" + cuenta.getUltimoPago(),
                "Consulta de cuota pagada", JOptionPane.INFORMATION_MESSAGE);
    }

    public void consultarTotalPagos() {
        String rut = JOptionPane.showInputDialog("Ingrese su RUT para consultar el total de pagos:");
        Socio socio = buscarSocioPorRut(rut);
        if (socio == null) {
            mostrarError("RUT no encontrado.");
            return;
        }

        SocioCuenta cuenta = buscarCuentaPorNumeroDeSocio(socio.getNumeroDeSocio());
        if (cuenta == null) {
            mostrarError("No se encontró una cuenta asociada.");
            return;
        }

        JOptionPane.showMessageDialog(null, "Total pagado: $" + cuenta.getCantidadDeAporte(),
                "Consulta de total de pagos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
