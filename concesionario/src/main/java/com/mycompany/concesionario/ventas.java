/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.concesionario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author isra
 */
public class ventas extends javax.swing.JFrame {
private List<Integer> cocheIds = new ArrayList<>(); 

    /**
     * Creates new form ventas
     */
    public ventas() {
        initComponents();
        cargarClientes();
        cargarCoches();
        cargarExtrasDelCoche();
        cargarDatosDelCoche();

    }
    private void cargarClientes() {
        DefaultComboBoxModel<String> modeloListaClientes = new DefaultComboBoxModel<>();
    String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
    String username = "root";  
    String password = "1985"; 

    try {
        Connection conexion = DriverManager.getConnection(jdbcUrl, username, password);
        
        // Concatenar nombre y apellido en la consulta SQL
        String consultaSQL = "SELECT CONCAT(nombre, ' ', apellidos) AS nombreCompleto FROM Clientes";
        PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
        ResultSet resultado = consulta.executeQuery();

        // Añadir cada cliente a la lista
        while (resultado.next()) {
            String nombreCompleto = resultado.getString("nombreCompleto");
            modeloListaClientes.addElement(nombreCompleto);
        }

        listaCliente.setModel(modeloListaClientes);
        resultado.close();
        consulta.close();
        conexion.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los clientes: " + e.getMessage());
        }
        
    }
    
    private void cargarCoches() {
        listaCoche.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
       
            }
        });
        
        DefaultComboBoxModel<String> modeloListaCoches = new DefaultComboBoxModel<>();
        String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
        String username = "root";
        String password = "1985";

        try (Connection conexion = DriverManager.getConnection(jdbcUrl, username, password)) {
            String consultaSQL = "SELECT CONCAT(marca, ' ', modelo) AS cocheCompleto, id FROM Coches";
            PreparedStatement consulta = conexion.prepareStatement(consultaSQL);
            ResultSet resultado = consulta.executeQuery();

            while (resultado.next()) {
                String cocheCompleto = resultado.getString("cocheCompleto");
                modeloListaCoches.addElement(cocheCompleto);
            }

            listaCoche.setModel(modeloListaCoches);
            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los coches: " + e.getMessage());
        }

        // Añadimos un listener al JComboBox listaCoche para cargar los extras cuando se selecciona un coche
        listaCoche.addActionListener(evt -> cargarExtrasDelCoche());
        listaCoche.addActionListener(e -> cargarDatosDelCoche());

    }

    private void cargarExtrasDelCoche() {
        String cocheSeleccionado = (String) listaCoche.getSelectedItem();
        if (cocheSeleccionado == null) return;

        String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
        String username = "root";
        String password = "1985";
        String consultaSQL = "SELECT transmision, volanteCalefaccion, techo FROM Coches WHERE CONCAT(marca, ' ', modelo) = ?";

        try (Connection conexion = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {

            consulta.setString(1, cocheSeleccionado);
            ResultSet resultado = consulta.executeQuery();

            if (resultado.next()) {
                StringBuilder extras = new StringBuilder();
                if (resultado.getBoolean("transmision")) {
                    extras.append("Transmisión automática\n");
                }
                if (resultado.getBoolean("volanteCalefaccion")) {
                    extras.append("Volante calefactable\n");
                }
                if (resultado.getBoolean("techo")) {
                    extras.append("Techo panorámico\n");
                }
                areaExtra.setText(extras.toString());
            } else {
                areaExtra.setText("No hay extras disponibles para el coche seleccionado.");
            }

            resultado.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los extras del coche: " + e.getMessage());
        }
    }
   
    private void cargarDatosDelCoche() {
    String cocheSeleccionado = (String) listaCoche.getSelectedItem();
    if (cocheSeleccionado == null) return;

    String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
    String username = "root";
    String password = "1985";
    String consultaSQL = "SELECT Marca, Modelo, precio, volanteCalefaccion, techo FROM Coches WHERE CONCAT(Marca, ' ', Modelo) = ?";

    try (Connection conexion = DriverManager.getConnection(jdbcUrl, username, password);
         PreparedStatement consulta = conexion.prepareStatement(consultaSQL)) {

        consulta.setString(1, cocheSeleccionado);
        ResultSet resultado = consulta.executeQuery();

        if (resultado.next()) {
            String marcaCoche = resultado.getString("Marca");
            String modeloCoche = resultado.getString("Modelo");
            double precioCoche = resultado.getDouble("precio");
            double totalPrecio = precioCoche; // Inicializar el precio total con el precio base

            // Mostrar información del coche en el JTextArea de precio
            StringBuilder precioTexto = new StringBuilder("Coche: " + marcaCoche + " " + modeloCoche + "\n");

            // Verificar y mostrar precios de los extras
            if (resultado.getBoolean("volanteCalefaccion")) {
                precioTexto.append("Volante calefactable: 500€\n");
                totalPrecio += 500; // Añadir precio del volante calefactable al total
            } else {
                precioTexto.append("Volante calefactable: No disponible\n");
            }
            if (resultado.getBoolean("techo")) {
                precioTexto.append("Techo panorámico: 1500€\n");
                totalPrecio += 1500; // Añadir precio del techo panorámico al total
            } else {
                precioTexto.append("Techo panorámico: No disponible\n");
            }

            // Mostrar los precios en el JTextArea de precio
            precio.setText(precioTexto.toString());
            // Mostrar el total en el JTextField total
            total.setText("Precio total: " + totalPrecio + "€"); // Asegúrate de que 'total' es tu JTextField
        } else {
            // Si no se encontró el coche, se limpian los campos
            precio.setText("No hay datos disponibles para el coche seleccionado.");
            total.setText("");
        }

        resultado.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los datos del coche: " + e.getMessage());
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        listaCliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        listaCoche = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        areaExtra = new javax.swing.JTextArea();
        guardarVenta = new javax.swing.JButton();
        cancelarVenta = new javax.swing.JButton();
        precioCoche = new javax.swing.JScrollPane();
        precio = new javax.swing.JTextArea();
        total = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        iva = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Clientes");

        listaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Coche");

        jLabel3.setText("Extras");

        areaExtra.setColumns(20);
        areaExtra.setRows(5);
        jScrollPane1.setViewportView(areaExtra);

        guardarVenta.setText("Guardar Venta");
        guardarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarVentaActionPerformed(evt);
            }
        });

        cancelarVenta.setText("Cancelar Venta");
        cancelarVenta.setToolTipText("");
        cancelarVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarVentaActionPerformed(evt);
            }
        });

        precio.setColumns(20);
        precio.setRows(5);
        precioCoche.setViewportView(precio);

        jLabel4.setText("TOTAL: ");
        jLabel4.setToolTipText("");

        jLabel5.setText("IVA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                                .addComponent(precioCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(195, 195, 195)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addGap(20, 20, 20)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(iva, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                                    .addComponent(total))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(guardarVenta)
                                .addGap(57, 57, 57)
                                .addComponent(cancelarVenta))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel2)
                                .addComponent(listaCliente, 0, 325, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addComponent(listaCoche, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(listaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addComponent(listaCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(guardarVenta)
                            .addComponent(cancelarVenta))
                        .addGap(27, 27, 27))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(precioCoche, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addContainerGap(59, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_guardarVentaActionPerformed

    private void cancelarVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarVentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarVentaActionPerformed

    private void listaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listaClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaExtra;
    private javax.swing.JButton cancelarVenta;
    private javax.swing.JButton guardarVenta;
    private javax.swing.JTextField iva;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> listaCliente;
    private javax.swing.JComboBox<String> listaCoche;
    private javax.swing.JTextArea precio;
    private javax.swing.JScrollPane precioCoche;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
