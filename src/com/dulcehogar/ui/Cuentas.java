package com.dulcehogar.ui;

import com.dulcehogar.logica.Socio; // Importamos las clases y UI que ocupamos en el Frame
import com.dulcehogar.logica.SocioCuenta;
import com.dulcehogar.logica.Validaciones;
import com.dulcehogar.ui.Socios;
import java.util.ArrayList;
import javax.swing.*;

public class Cuentas extends javax.swing.JFrame {

    private ArrayList<Socio> listaSocio;          // Lista de socios
    private ArrayList<SocioCuenta> listaCuenta;  // Lista de cuentas

    public Cuentas(ArrayList<Socio> listaSocio, ArrayList<SocioCuenta> listaCuenta) {
        this.listaSocio = listaSocio;
        this.listaCuenta = listaCuenta;
        initComponents();
    }

    private SocioCuenta cuenta; // Variable de instancia para almacenar la cuenta actual

    private void buscarSocioParaPago() { // btnPago
        String rut = txtRutPago.getText().trim();

        if (!Validaciones.validarRUT(rut)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un RUT válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Socio socio = listaSocio.stream()
             .filter(s -> s.getRut().equalsIgnoreCase(rut))
             .findFirst()
             .orElse(null);

        if (socio == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un socio con este RUT.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposPago();
            return;
        }

        cuenta = listaCuenta.stream()
             .filter(c -> c.getNumeroDeSocio() == socio.getNumeroDeSocio())
             .findFirst()
             .orElse(null);

        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una cuenta asociada al socio.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposPago();
            return;
        }

        // Mostrar detalles de la cuota y el número total de cuotas
        txtMontoCuota.setText(String.valueOf(cuenta.getValorDeCuota()));
        int cuotasPagadas = cuenta.getCantidadDeAporte() / cuenta.getValorDeCuota();
        txtNumCuotas.setText(String.valueOf(12 - cuotasPagadas));
    }

    private void pagarCuota() { // btnPagar
        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "Primero debe buscar un socio antes de registrar un pago.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int monto = Integer.parseInt(txtPagarCuota.getText().trim());

            // Validar monto
            int totalAdeudado = cuenta.getValorDeCuota() * (12 - cuenta.getCantidadDeAporte() / cuenta.getValorDeCuota());
            if (monto <= 0 || monto > totalAdeudado) {
                JOptionPane.showMessageDialog(this, "El monto ingresado no es válido. Total adeudado: " + totalAdeudado, "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Registrar pago
            cuenta.pagoDeCuota(monto);

            JOptionPane.showMessageDialog(this, "Pago registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar campos
            int cuotasPagadas = cuenta.getCantidadDeAporte() / cuenta.getValorDeCuota();
            txtNumCuotas.setText(String.valueOf(12 - cuotasPagadas));
            txtPagarCuota.setText(""); // Limpiar el campo de monto a pagar

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

//Metodo limpiarCamposPago
    private void limpiarCamposPago() {
        txtRutPago.setText("");
        txtMontoCuota.setText("");
        txtNumCuotas.setText("");
        txtPagarCuota.setText("");
    }

///*******************************************************
    private void buscarMontoPagado() { // btnConsultaPago
        String rut = txtRutConsulta.getText().trim();

        if (!Validaciones.validarRUT(rut)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un RUT válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar al socio por su RUT
        Socio socio = listaSocio.stream()
             .filter(s -> s.getRut().equalsIgnoreCase(rut))
             .findFirst()
             .orElse(null);

        if (socio == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un socio con este RUT.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposMontoPagado();
            return;
        }

        // Buscar la cuenta asociada al socio
        SocioCuenta cuenta = listaCuenta.stream()
             .filter(c -> c.getNumeroDeSocio() == socio.getNumeroDeSocio())
             .findFirst()
             .orElse(null);

        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una cuenta asociada al socio.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposMontoPagado();
            return;
        }

        // Obtener datos del último pago y el monto total aportado
        int ultimoPago = cuenta.getUltimoPago();
        int montoTotalAportado = cuenta.getCantidadDeAporte();

        // Mostrar los datos en los campos correspondientes
        txtUltimoPagoRealizado.setText(String.valueOf(ultimoPago));

    }

    private void limpiarCamposMontoPagado() {
        txtRutConsulta.setText("");
        txtUltimoPagoRealizado.setText("");

    }

    private void buscarPorPagar() { // btnPorPagar
        String rut = txtRutPorPagar.getText().trim();

        if (!Validaciones.validarRUT(rut)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un RUT válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar al socio por su RUT
        Socio socio = listaSocio.stream()
             .filter(s -> s.getRut().equalsIgnoreCase(rut))
             .findFirst()
             .orElse(null);

        if (socio == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un socio con este RUT.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposPorPagar();
            return;
        }

        // Buscar la cuenta asociada al socio
        SocioCuenta cuenta = listaCuenta.stream()
             .filter(c -> c.getNumeroDeSocio() == socio.getNumeroDeSocio())
             .findFirst()
             .orElse(null);

        if (cuenta == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una cuenta asociada al socio.", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarCamposPorPagar();
            return;
        }

        // Calcular el monto total adeudado
        int totalCuotas = 12;
        int cuotasPagadas = cuenta.getCantidadDeAporte() / cuenta.getValorDeCuota();
        int totalAdeudado = cuenta.getValorDeCuota() * (totalCuotas - cuotasPagadas);

        // Mostrar el total adeudado
        txtCuotasPorPagar.setText(String.valueOf(cuotasPagadas));
        txtMontoPorPagar.setText(String.valueOf(totalAdeudado));
    }

    private void limpiarCamposPorPagar() {
        txtRutPorPagar.setText("");
        txtCuotasPorPagar.setText("");
        txtMontoPorPagar.setText("");
    }

//************************************************************************************************///
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlPagarCuota = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRutPago = new javax.swing.JTextField();
        btnPago = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMontoCuota = new javax.swing.JTextField();
        txtNumCuotas = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtPagarCuota = new javax.swing.JTextField();
        btnPagar = new javax.swing.JButton();
        pnlConsultarTotal = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnConsultaPago = new javax.swing.JButton();
        txtRutConsulta = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtUltimoPagoRealizado = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        pnlCuotasRestantes = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtRutPorPagar = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnPorPagar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtCuotasPorPagar = new javax.swing.JTextField();
        txtMontoPorPagar = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("CUENTA DE SOCIOS");

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel2.setText("RUT Socio:");

        txtRutPago.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRutPago.setForeground(new java.awt.Color(0, 51, 153));
        txtRutPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutPagoActionPerformed(evt);
            }
        });

        btnPago.setBackground(new java.awt.Color(204, 204, 255));
        btnPago.setText("BUSCAR");
        btnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoActionPerformed(evt);
            }
        });

        jLabel3.setText("Monto Cuota");

        jLabel4.setText("N° de Cuotas");

        txtMontoCuota.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNumCuotas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel9.setText("Monto a Pagar");

        btnPagar.setBackground(new java.awt.Color(255, 204, 153));
        btnPagar.setText("PAGAR");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPagarCuotaLayout = new javax.swing.GroupLayout(pnlPagarCuota);
        pnlPagarCuota.setLayout(pnlPagarCuotaLayout);
        pnlPagarCuotaLayout.setHorizontalGroup(
            pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPagarCuotaLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPagarCuotaLayout.createSequentialGroup()
                        .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPagarCuotaLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtRutPago, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPago))
                            .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtMontoCuota, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                .addComponent(txtNumCuotas))))
                    .addGroup(pnlPagarCuotaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPagarCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlPagarCuotaLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(15, 15, 15)))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPagarCuotaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addGap(70, 70, 70))
        );
        pnlPagarCuotaLayout.setVerticalGroup(
            pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPagarCuotaLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRutPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnPago))
                .addGap(32, 32, 32)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMontoCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlPagarCuotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPagarCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPagar)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pagar Cuota Mensual", pnlPagarCuota);

        jLabel5.setText("RUT Socio");

        btnConsultaPago.setBackground(new java.awt.Color(204, 204, 255));
        btnConsultaPago.setText("BUSCAR");
        btnConsultaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaPagoActionPerformed(evt);
            }
        });

        txtRutConsulta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRutConsulta.setForeground(new java.awt.Color(0, 51, 153));

        jLabel6.setText("Último pago realizado");

        txtUltimoPagoRealizado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlConsultarTotalLayout = new javax.swing.GroupLayout(pnlConsultarTotal);
        pnlConsultarTotal.setLayout(pnlConsultarTotalLayout);
        pnlConsultarTotalLayout.setHorizontalGroup(
            pnlConsultarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarTotalLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pnlConsultarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsultarTotalLayout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addGap(47, 47, 47))
                    .addGroup(pnlConsultarTotalLayout.createSequentialGroup()
                        .addGroup(pnlConsultarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUltimoPagoRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlConsultarTotalLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtRutConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnConsultaPago))
                            .addGroup(pnlConsultarTotalLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel6)))
                        .addContainerGap(52, Short.MAX_VALUE))))
        );
        pnlConsultarTotalLayout.setVerticalGroup(
            pnlConsultarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsultarTotalLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(pnlConsultarTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRutConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(btnConsultaPago))
                .addGap(27, 27, 27)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtUltimoPagoRealizado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Consultar Monto Total Pagado", pnlConsultarTotal);

        jLabel7.setText("RUT socio");

        txtRutPorPagar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRutPorPagar.setForeground(new java.awt.Color(0, 51, 102));

        btnPorPagar.setBackground(new java.awt.Color(204, 204, 255));
        btnPorPagar.setText("BUSCAR");
        btnPorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorPagarActionPerformed(evt);
            }
        });

        jLabel8.setText("Número de cuotas por pagar:");

        txtCuotasPorPagar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtMontoPorPagar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtMontoPorPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMontoPorPagarActionPerformed(evt);
            }
        });

        jLabel10.setText("Monto Restante por Pagar:");

        javax.swing.GroupLayout pnlCuotasRestantesLayout = new javax.swing.GroupLayout(pnlCuotasRestantes);
        pnlCuotasRestantes.setLayout(pnlCuotasRestantesLayout);
        pnlCuotasRestantesLayout.setHorizontalGroup(
            pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuotasRestantesLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCuotasRestantesLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(36, 36, 36)
                        .addComponent(txtRutPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnPorPagar))
                    .addGroup(pnlCuotasRestantesLayout.createSequentialGroup()
                        .addGroup(pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtCuotasPorPagar, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(100, 100, 100)
                        .addGroup(pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMontoPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        pnlCuotasRestantesLayout.setVerticalGroup(
            pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCuotasRestantesLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRutPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPorPagar))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlCuotasRestantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCuotasPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMontoPorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cuotas por Pagar", pnlCuotasRestantes);

        jButton1.setBackground(new java.awt.Color(255, 153, 153));
        jButton1.setText("CERRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(47, 47, 47))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaPagoActionPerformed
        buscarMontoPagado();
    }//GEN-LAST:event_btnConsultaPagoActionPerformed

    private void btnPorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorPagarActionPerformed
        buscarPorPagar();
    }//GEN-LAST:event_btnPorPagarActionPerformed

    private void btnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoActionPerformed
        buscarSocioParaPago();
    }//GEN-LAST:event_btnPagoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, "Cerrando la aplicación.", "Salir", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);//funciona OK
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed

        pagarCuota();
    }//GEN-LAST:event_btnPagarActionPerformed

    private void txtRutPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutPagoActionPerformed

    }//GEN-LAST:event_txtRutPagoActionPerformed

    private void txtMontoPorPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMontoPorPagarActionPerformed

    }//GEN-LAST:event_txtMontoPorPagarActionPerformed

    public static void main(String args[]) { //pasamos las listas con las que trabajamos

        ArrayList<Socio> listaSocio = new ArrayList<>();
        ArrayList<SocioCuenta> listaCuenta = new ArrayList<>();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(ArrayList<SocioCuenta> listaCuenta) {
                new Cuentas(listaSocio, listaCuenta).setVisible(true);
            }

            @Override
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultaPago;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnPago;
    private javax.swing.JButton btnPorPagar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlConsultarTotal;
    private javax.swing.JPanel pnlCuotasRestantes;
    private javax.swing.JPanel pnlPagarCuota;
    private javax.swing.JTextField txtCuotasPorPagar;
    private javax.swing.JTextField txtMontoCuota;
    private javax.swing.JTextField txtMontoPorPagar;
    private javax.swing.JTextField txtNumCuotas;
    private javax.swing.JTextField txtPagarCuota;
    private javax.swing.JTextField txtRutConsulta;
    private javax.swing.JTextField txtRutPago;
    private javax.swing.JTextField txtRutPorPagar;
    private javax.swing.JTextField txtUltimoPagoRealizado;
    // End of variables declaration//GEN-END:variables

    private static class cuenta {

        public cuenta() {
        }
    }
}
