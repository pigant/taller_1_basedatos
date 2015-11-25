/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.GUI.Ventanas;

import com.ignacio.tienda.BLL.Cliente;
import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import com.ignacio.tienda.GUI.ManejoErrorConexion;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ignacio
 */
public class JPVerClientes extends javax.swing.JPanel {

	/**
	 * Creates new form JPVerClientes
	 */
	public JPVerClientes() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jB_verCliente = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jT_clientes = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(400, 400));
        setLayout(new java.awt.GridBagLayout());

        jB_verCliente.setText("Ver clientes");
        jB_verCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_verClienteActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jB_verCliente, gridBagConstraints);

        jT_clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Número"
            }
        ));
        jT_clientes.setToolTipText("");
        jScrollPane1.setViewportView(jT_clientes);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 579;
        gridBagConstraints.ipady = 403;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 15, 15);
        add(jScrollPane1, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jB_verClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_verClienteActionPerformed
		try {
			ArrayList<Cliente> a = Cliente.getAll();
			if (a != null) {
				DefaultTableModel dtm = new DefaultTableModel();
				dtm.addColumn("Rut");
				dtm.addColumn("Nombre");
				for (Cliente c : a) {
					Object[] o = {
						c.getRut(),
						c.getNombre()
					};
					dtm.addRow(o);
				}
				jT_clientes.setModel(dtm);
				JOptionPane.showMessageDialog(this, "Se cargaron los clientes");
			} else {
				JOptionPane.showMessageDialog(this,
						"No se cargaron los datos de los clientes",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SinBaseDatosException ex) {
				ManejoErrorConexion.mostrar(this, ex);
		}
    }//GEN-LAST:event_jB_verClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_verCliente;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jT_clientes;
    // End of variables declaration//GEN-END:variables
}
