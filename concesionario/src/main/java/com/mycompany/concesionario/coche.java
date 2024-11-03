/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.concesionario;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author isra
 */
public class coche extends javax.swing.JFrame {
   private List<Integer> cocheIds = new ArrayList<>(); 

    public coche() {
        initComponents();
        cargarCochesEnTabla();
        configurarTabla();
    }

private void configurarTabla() {
    tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {  // Evita ejecuciones múltiples
                    cargarDatosSeleccionados();
                }
            }
        });
}
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        transmision = new javax.swing.JRadioButton();
        volante = new javax.swing.JRadioButton();
        techo = new javax.swing.JRadioButton();
        marca = new javax.swing.JTextField();
        modelo = new javax.swing.JTextField();
        precio = new javax.swing.JTextField();
        anadirC = new javax.swing.JButton();
        borrarC = new javax.swing.JButton();
        editarC = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Marca", "Modelo", "Precio", "Transmision auto", "volante calefactable", "Techo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jLabel1.setText("MARCA");

        jLabel2.setText("MODELO");

        jLabel3.setText("PRECIO");

        transmision.setText("TRANSMISION AUTOMÁTICA");
        transmision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transmisionActionPerformed(evt);
            }
        });

        volante.setText("VOLANTE CALEFACTABLE");
        volante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volanteActionPerformed(evt);
            }
        });

        techo.setText("TECHO PANORAMICO");

        marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marcaActionPerformed(evt);
            }
        });

        anadirC.setText("AÑADIR");
        anadirC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anadirCActionPerformed(evt);
            }
        });

        borrarC.setText("BORRAR");
        borrarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarCActionPerformed(evt);
            }
        });

        editarC.setText("EDITAR");
        editarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(anadirC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(borrarC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarC)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(precio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(marca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(volante)
                                .addComponent(transmision)
                                .addComponent(techo)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(precio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(transmision)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(volante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(techo)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(anadirC)
                    .addComponent(borrarC)
                    .addComponent(editarC))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void transmisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transmisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_transmisionActionPerformed

    private void volanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volanteActionPerformed

    private void marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_marcaActionPerformed

    private void anadirCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anadirCActionPerformed
        
        String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
        String username = "root";  
        String password = "1985"; 

        // Obtener valores de los campos
        String marcaText = marca.getText();
        String modeloText = modelo.getText();
        String precioText = precio.getText();  // Mantenerlo como String temporalmente
        boolean transmisionAuto = transmision.isSelected();
        boolean volanteCalefactable = volante.isSelected();
        boolean techoCristal = techo.isSelected();

        double precioValue;
        try {
            precioValue = Double.parseDouble(precioText);  // Convertir el precio
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el precio.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Consulta SQL para insertar los datos en la base de datos
        String insertSql = "INSERT INTO Coches (Marca, Modelo, precio, transmision, volanteCalefaccion, techo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement statement = connection.prepareStatement(insertSql)) {

            statement.setString(1, marcaText);
            statement.setString(2, modeloText);
            statement.setDouble(3, precioValue);
            statement.setBoolean(4, transmisionAuto);
            statement.setBoolean(5, volanteCalefactable);
            statement.setBoolean(6, techoCristal);

            // Ejecutar la inserción y actualizar la tabla
            statement.executeUpdate();
            cargarCochesEnTabla();
            JOptionPane.showMessageDialog(this, "Coche añadido correctamente.");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al añadir el coche: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }                                 
    
    }//GEN-LAST:event_anadirCActionPerformed

    private void borrarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarCActionPerformed
        int selectedRow = tabla.getSelectedRow();
        if (selectedRow != -1) {
            int cocheId = cocheIds.get(selectedRow); // Obtener el ID del coche correspondiente
            String deleteSql = "DELETE FROM Coches WHERE id = ?";
            String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
            String username = "root";  
            String password = "1985"; 

            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
                 PreparedStatement statement = connection.prepareStatement(deleteSql)) {

                statement.setInt(1, cocheId);
                statement.executeUpdate();
                cargarCochesEnTabla(); // Refresca la tabla
                JOptionPane.showMessageDialog(this, "Coche borrado correctamente.");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al borrar el coche: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un coche para borrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_borrarCActionPerformed

    private void editarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCActionPerformed
        // TODO add your handling code here:
        int selectedRow = tabla.getSelectedRow(); // Obtiene la fila seleccionada
   String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
   String username = "root";  
   String password = "1985"; 

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un coche para editar.");
        return;
    }

    // Obtener el ID del coche seleccionado
    int cocheId = cocheIds.get(selectedRow);

    // Obtén los valores de los campos de texto
    String marcaText = marca.getText();
    String modeloText = modelo.getText();
    String precioText = precio.getText();
    boolean transmisionAuto = transmision.isSelected();
    boolean volanteCalefactable = volante.isSelected();
    boolean techoCristal = techo.isSelected();

    double precioValue;
    try {
        precioValue = Double.parseDouble(precioText);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Ingrese un valor numérico válido para el precio.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Consulta SQL para actualizar el registro en la base de datos
    String updateSql = "UPDATE Coches SET Marca = ?, Modelo = ?, precio = ?, transmision = ?, volanteCalefaccion = ?, techo = ? WHERE id = ?";

    try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
         PreparedStatement statement = connection.prepareStatement(updateSql)) {

        // Asignación de parámetros
        statement.setString(1, marcaText);
        statement.setString(2, modeloText);
        statement.setDouble(3, precioValue);
        statement.setBoolean(4, transmisionAuto);
        statement.setBoolean(5, volanteCalefactable);
        statement.setBoolean(6, techoCristal);
        statement.setInt(7, cocheId); // ID del coche que se va a editar

        // Ejecutar la actualización y refrescar la tabla
        statement.executeUpdate();
        cargarCochesEnTabla(); // Actualiza la tabla después de la edición
        JOptionPane.showMessageDialog(this, "Coche editado correctamente.");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al editar el coche: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }//GEN-LAST:event_editarCActionPerformed

private void cargarCochesEnTabla() {
    String jdbcUrl = "jdbc:mariadb://localhost:3306/concesionario";
        String username = "root";
        String password = "1985";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            String selectSql = "SELECT * FROM Coches";
            ResultSet resultSet = statement.executeQuery(selectSql);

            DefaultTableModel model = (DefaultTableModel) tabla.getModel();
            model.setRowCount(0);  // Limpia la tabla
            cocheIds.clear(); // Limpia la lista de IDs

            while (resultSet.next()) {
                Object[] fila = {
                    resultSet.getString("Marca"),
                    resultSet.getString("Modelo"),
                    resultSet.getDouble("precio"),
                    resultSet.getBoolean("transmision"),
                    resultSet.getBoolean("volanteCalefaccion"),
                    resultSet.getBoolean("techo")
                };
                model.addRow(fila);
                cocheIds.add(resultSet.getInt("id")); // Agrega el ID a la lista
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los coches de la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarDatosSeleccionados() {
         int selectedRow = tabla.getSelectedRow();

        if (selectedRow != -1) {  // Si hay una fila seleccionada
            marca.setText(tabla.getValueAt(selectedRow, 0).toString());
            modelo.setText(tabla.getValueAt(selectedRow, 1).toString());
            precio.setText(tabla.getValueAt(selectedRow, 2).toString());
            transmision.setSelected((boolean) tabla.getValueAt(selectedRow, 3));
            volante.setSelected((boolean) tabla.getValueAt(selectedRow, 4));
            techo.setSelected((boolean) tabla.getValueAt(selectedRow, 5));
        }   
    }





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
            java.util.logging.Logger.getLogger(coche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(coche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(coche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(coche.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new coche().setVisible(true);
            }
        });
          
     
}   
    
    



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anadirC;
    private javax.swing.JButton borrarC;
    private javax.swing.JButton editarC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField marca;
    private javax.swing.JTextField modelo;
    private javax.swing.JTextField precio;
    private javax.swing.JTable tabla;
    private javax.swing.JRadioButton techo;
    private javax.swing.JRadioButton transmision;
    private javax.swing.JRadioButton volante;
    // End of variables declaration//GEN-END:variables
}
