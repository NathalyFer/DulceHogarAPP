package com.dulcehogar.ui;

import com.dulcehogar.logica.Principal;
import com.dulcehogar.logica.Socio;
import com.dulcehogar.logica.SocioCuenta;
import com.dulcehogar.ui.Cuentas;
import com.dulcehogar.logica.Validaciones;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Socios extends javax.swing.JFrame {
    
    private ArrayList<Socio> listaSocio;          // Maneja datos personales
    private ArrayList<SocioCuenta> listaCuenta;  // Maneja datos financieros
    private javax.swing.JTextArea datos; 

    // Constructor con parámetros
    
    public Socios(ArrayList<Socio> listaSocio, ArrayList<SocioCuenta> listaCuenta) {
        this.listaSocio = new ArrayList<>(); // Inicializamos listas
        this.listaCuenta = new ArrayList<>(); 
        configurarTxtArea(); // Configurar el JTextArea
        initComponents();
    }
    
    private void configurarTxtArea() {
        datos = new javax.swing.JTextArea();
        datos.setEditable(false);
    }

    private void registrarSocio() {
     
        // Verificar si los campos son válidos
        if (!validarCamposRegistro()) {
            return; // Si la validación falla, salir del método
        }

        // Verificar duplicidad del RUT
        String rut = txtRut.getText().trim();
        if (listaSocio.stream().anyMatch(s -> s.getRut().equalsIgnoreCase(rut))) {
            JOptionPane.showMessageDialog(this, "Ya existe un socio con este RUT.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Procesar los datos del socio
            String nombre = txtNombre.getText().trim();
            String apellidoPaterno = txtApellidoPaterno.getText().trim();
            String apellidoMaterno = txtApellidoMaterno.getText().trim();
            String correo = txtCorreo.getText().trim();
            String domicilio = txtDomicilio.getText().trim();
            String region = txtRegion.getText().trim();
            String ciudad = txtCiudad.getText().trim();
            String comuna = txtComuna.getText().trim();
            int telefono = Integer.parseInt(txtTelefono.getText().trim());

            // Generar número de socio, primero debemos instanciar la clase que tiene el metodo
            Principal principal = new Principal();
            int numeroDeSocio = principal.generarNumeroDeSocioAleatorio(rut);

            // Crear y añadir el nuevo socio
            Socio nuevoSocio = new Socio(rut, nombre, apellidoPaterno, apellidoMaterno, correo, domicilio, region, ciudad, comuna, telefono, numeroDeSocio);
            listaSocio.add(nuevoSocio);

            // Crear y añadir la cuenta del socio
            SocioCuenta nuevaCuenta = new SocioCuenta(rut, nombre, apellidoPaterno, apellidoMaterno, correo, domicilio, region, ciudad, comuna, telefono, numeroDeSocio);
            nuevaCuenta.setValorDeCuota(50000); // Valor predeterminado
            listaCuenta.add(nuevaCuenta);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "¡Socio registrado exitosamente!\nNúmero de socio: " + numeroDeSocio, "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar los campos después de registrar
            txtRut.setText("");
            txtNombre.setText("");
            txtApellidoPaterno.setText("");
            txtApellidoMaterno.setText("");
            txtCorreo.setText("");
            txtDomicilio.setText("");
            txtRegion.setText("");
            txtCiudad.setText("");
            txtComuna.setText("");
            txtTelefono.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarCamposRegistro() {
        
        // Verificar cada campo y detener si alguna validación falla
        if (!Validaciones.validarCampoVacio(txtRut.getText(), "RUT")) {
            return false;
        }
        if (!Validaciones.validarRUT(txtRut.getText())) {
            return false;
        }
        if (!Validaciones.validarCampoVacio(txtNombre.getText(), "Nombre")) {
            return false;
        }
        if (!Validaciones.validarCampoVacio(txtApellidoPaterno.getText(), "Apellido Paterno")) {
            return false;
        }
        if (!Validaciones.validarCampoVacio(txtApellidoMaterno.getText(), "Apellido Materno")) {
            return false; // Corregir el campo
        }
        if (!Validaciones.validarCampoVacio(txtCorreo.getText(), "Correo")) {
            return false;
        }
        if (!Validaciones.validarCorreo(txtCorreo.getText())) {
            return false;
        }
        if (!Validaciones.validarSoloNumeros(txtTelefono.getText(), "Teléfono")) {
            return false;
        }

        // Si todas las validaciones pasaron
        return true;
    }


    //*************************************************************************//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlRegistro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();
        txtRut = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtApellidoPaterno = new javax.swing.JTextField();
        txtApellidoMaterno = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtDomicilio = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtComuna = new javax.swing.JTextField();
        txtRegion = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        pnlBusqueda = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtRutBuscar = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txaBuscar = new javax.swing.JTextArea();
        jScrollBar1 = new javax.swing.JScrollBar();
        btnSalir = new javax.swing.JButton();
        btnIrCuenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setText("Ingresa tus datos para registrarte:");

        jLabel3.setText("RUT");

        jLabel4.setText("Nombre");

        jLabel5.setText("Apellido P");

        jLabel6.setText("Apellido M");

        jLabel7.setText("Región");

        jLabel8.setText("Ciudad");

        jLabel9.setText("Comuna");

        jLabel10.setText("Domicilio");

        jLabel11.setText("Teléfono");

        jLabel12.setText("Correo");

        btnRegistrarse.setBackground(new java.awt.Color(153, 204, 255));
        btnRegistrarse.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnRegistrarse.setText("REGISTRARSE");
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        txtRut.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtApellidoPaterno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtApellidoMaterno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtDomicilio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtComuna.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtRegion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 2, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 153));
        jLabel14.setText("El número de socio se generará automaticamente una vez registrado");

        txtCiudad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtCorreo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout pnlRegistroLayout = new javax.swing.GroupLayout(pnlRegistro);
        pnlRegistro.setLayout(pnlRegistroLayout);
        pnlRegistroLayout.setHorizontalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlRegistroLayout.createSequentialGroup()
                                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlRegistroLayout.createSequentialGroup()
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13))
                                    .addComponent(txtDomicilio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlRegistroLayout.createSequentialGroup()
                                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(pnlRegistroLayout.createSequentialGroup()
                                                .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel8))
                                            .addGroup(pnlRegistroLayout.createSequentialGroup()
                                                .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel6)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                                            .addComponent(txtCiudad)))
                                    .addComponent(txtComuna, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlRegistroLayout.createSequentialGroup()
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegistrarse)
                                .addGap(77, 77, 77)))))
                .addContainerGap())
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(112, 112, 112))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(34, 34, 34))
        );
        pnlRegistroLayout.setVerticalGroup(
            pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistroLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtApellidoPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtApellidoMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(28, 28, 28)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(pnlRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(btnRegistrarse))
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Registro de Socios", pnlRegistro);

        pnlBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Ingresa el RUT para su búsqueda:");

        txtRutBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtRutBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRutBuscarActionPerformed(evt);
            }
        });

        jLabel16.setForeground(new java.awt.Color(0, 51, 153));
        jLabel16.setText("Si hay un socio registrado con el RUT ingresado sus datos se mostrarán en el recuadro:");

        btnBuscar.setBackground(new java.awt.Color(204, 204, 255));
        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txaBuscar.setColumns(20);
        txaBuscar.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        txaBuscar.setRows(5);
        jScrollPane2.setViewportView(txaBuscar);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                    .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBusquedaLayout = new javax.swing.GroupLayout(pnlBusqueda);
        pnlBusqueda.setLayout(pnlBusquedaLayout);
        pnlBusquedaLayout.setHorizontalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16)
                    .addComponent(jSeparator2)
                    .addGroup(pnlBusquedaLayout.createSequentialGroup()
                        .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtRutBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscar)
                        .addGap(64, 64, 64)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBusquedaLayout.setVerticalGroup(
            pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBusquedaLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBusquedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtRutBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Buscar a socio", pnlBusqueda);

        btnSalir.setBackground(new java.awt.Color(255, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnIrCuenta.setBackground(new java.awt.Color(204, 255, 204));
        btnIrCuenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnIrCuenta.setText("IR ACUENTA SOCIO");
        btnIrCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIrCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnSalir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIrCuenta)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnIrCuenta)
                    .addComponent(btnSalir))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        
        validarCamposRegistro();
        registrarSocio();
        
    }//GEN-LAST:event_btnRegistrarseActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        
        JOptionPane.showMessageDialog(this, "Cerrando la aplicación.", "Salir", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);     
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
    
        // Limpiar el área de texto antes de mostrar los resultados
        txaBuscar.setText("");

        // Obtener el RUT ingresado
        String rutBuscado = txtRutBuscar.getText().trim();

        // Verificar que el RUT no esté vacío
        if (rutBuscado.isEmpty()) {
            txaBuscar.append("Por favor, ingrese un RUT para buscar.\n");
            return;
        }

        // Buscar el socio por RUT en la lista
        boolean encontrado = false; 

        for (Socio socio : listaSocio) {
            // Comparar el RUT ingresado con el de cada socio
            if (socio.getRut().equalsIgnoreCase(rutBuscado)) {
                // Mostrar los datos del socio encontrado
                txaBuscar.append("------- Datos Encontrados -------\n");
                txaBuscar.append("Rut: " + socio.getRut() + "\n");
                txaBuscar.append("Nombre: " + socio.getNombre() + "\n");
                txaBuscar.append("Apellido Paterno: " + socio.getApellidoPaterno() + "\n");
                txaBuscar.append("Apellido Materno: " + socio.getApellidoMaterno() + "\n");
                txaBuscar.append("Domicilio: " + socio.getDomicilio() + "\n");
                txaBuscar.append("Comuna: " + socio.getComuna() + "\n");
                txaBuscar.append("Región: " + socio.getRegion() + "\n");
                txaBuscar.append("Ciudad: " + socio.getCiudad() + "\n");
                txaBuscar.append("Teléfono: " + socio.getTelefono() + "\n");
                txaBuscar.append("Correo: " + socio.getCorreo() + "\n");
                txaBuscar.append("-----------------------------------");

                encontrado = true; 
                break; 
            }
        }

        // Si no se encuentra el socio
        if (!encontrado) {
            txaBuscar.append("No se encontró ningún socio con ese RUT.\n");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtRutBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRutBuscarActionPerformed

    }//GEN-LAST:event_txtRutBuscarActionPerformed

    private void btnIrCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIrCuentaActionPerformed
               
        new Cuentas(listaSocio, listaCuenta).setVisible(true);
        this.dispose();      
    }//GEN-LAST:event_btnIrCuentaActionPerformed

    public static void main(String args[]) {
         ArrayList<Socio> listaSocio = new ArrayList<>();
         ArrayList<SocioCuenta> listaCuenta = new ArrayList<>();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            new Socios(listaSocio, listaCuenta).setVisible(true);
            }
        });
    }

    //////***********************************************************//

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnIrCuenta;
    private javax.swing.JButton btnRegistrarse;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlBusqueda;
    private javax.swing.JPanel pnlRegistro;
    private javax.swing.JTextArea txaBuscar;
    private javax.swing.JTextField txtApellidoMaterno;
    private javax.swing.JTextField txtApellidoPaterno;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtComuna;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRegion;
    private javax.swing.JTextField txtRut;
    private javax.swing.JTextField txtRutBuscar;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
   
}
