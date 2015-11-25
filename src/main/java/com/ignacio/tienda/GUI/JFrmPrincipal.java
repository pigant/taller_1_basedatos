/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.GUI;

import com.ignacio.tienda.BLL.Cliente;
import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.GUI.Ventanas.JPBuscarComic;
import com.ignacio.tienda.GUI.Ventanas.JPInforme;
import com.ignacio.tienda.GUI.Ventanas.JPIngresoVenta;
import com.ignacio.tienda.GUI.Ventanas.JPVerClientes;
import com.ignacio.tienda.GUI.Ventanas.JPVerVenta;
import com.ignacio.tienda.GUI.Ventanas.JpIngresoCliente;
import com.ignacio.tienda.GUI.Ventanas.jPIngresoComic;
import com.ignacio.tienda.GUI.Ventanas.jPVerRevistas;
import javax.swing.JOptionPane;

/**
 *
 * @author ignacio
 */
public class JFrmPrincipal extends javax.swing.JFrame {

	/**
	 * Creates new form JFrmPrincipal
	 */
	public JFrmPrincipal() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize
	 * the form. WARNING: Do NOT modify this code. The content of this
	 * method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jP_principal = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMI_insertarCliente = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMI_clientes = new javax.swing.JMenuItem();
        jMI_comics = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        jP_principal.setMinimumSize(new java.awt.Dimension(500, 500));
        jP_principal.setPreferredSize(new java.awt.Dimension(500, 500));
        jP_principal.setLayout(new javax.swing.BoxLayout(jP_principal, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(jP_principal);

        jMenu1.setText("Agregar");

        jMI_insertarCliente.setText("Cliente");
        jMI_insertarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_insertarClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jMI_insertarCliente);

        jMenuItem2.setText("Comic");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Venta");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Ver");

        jMI_clientes.setText("Clientes");
        jMI_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_clientesActionPerformed(evt);
            }
        });
        jMenu3.add(jMI_clientes);

        jMI_comics.setText("Comics");
        jMI_comics.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMI_comicsActionPerformed(evt);
            }
        });
        jMenu3.add(jMI_comics);

        jMenuItem4.setText("Venta");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Buscar");

        jMenuItem1.setText("Buscar comic");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar1.add(jMenu4);

        jMenu2.setText("Informes");

        jMenuItem5.setText("Ver informe");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMI_insertarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_insertarClienteActionPerformed
		JpIngresoCliente i = new JpIngresoCliente();
		cambiarVentana(i);
    }//GEN-LAST:event_jMI_insertarClienteActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
		jPIngresoComic i = new jPIngresoComic();
		cambiarVentana(i);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
		JPIngresoVenta i = new JPIngresoVenta();
		cambiarVentana(i);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMI_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_clientesActionPerformed
		JPVerClientes i = new JPVerClientes();
		cambiarVentana(i);
    }//GEN-LAST:event_jMI_clientesActionPerformed

    private void jMI_comicsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMI_comicsActionPerformed
		jPVerRevistas i = new jPVerRevistas();
		cambiarVentana(i);
    }//GEN-LAST:event_jMI_comicsActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
		JPBuscarComic i = new JPBuscarComic();
		cambiarVentana(i);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
		JPVerVenta v = new JPVerVenta();
		cambiarVentana(v);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

	private void cambiarVentana(javax.swing.JPanel i) {
		jP_principal.removeAll();
		i.setSize(jP_principal.getSize());
		jP_principal.add(i);
		jP_principal.repaint();
		pack();
	}

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
		JPInforme i = new JPInforme();
		cambiarVentana(i);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

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
			java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(JFrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrmPrincipal j = new JFrmPrincipal();
				j.setLocationRelativeTo(null);
				j.setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem jMI_clientes;
    private javax.swing.JMenuItem jMI_comics;
    private javax.swing.JMenuItem jMI_insertarCliente;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jP_principal;
    // End of variables declaration//GEN-END:variables
}
