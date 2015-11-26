package com.ignacio.tienda.GUI.Ventanas;

import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import com.ignacio.tienda.GUI.ManejoErrorConexion;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ignacio
 */
public class jPVerRevistas extends javax.swing.JPanel {

	/**
	 * Creates new form jPVerRevistas
	 */
	public jPVerRevistas() {
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

        jB_verRevistas = new javax.swing.JButton();
        jL_cantidad = new javax.swing.JLabel();
        jL_dinero = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jT_comics = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(400, 400));
        setLayout(new java.awt.GridBagLayout());

        jB_verRevistas.setText("Ver revistas");
        jB_verRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jB_verRevistasActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        add(jB_verRevistas, gridBagConstraints);

        jL_cantidad.setText("0");
        jL_cantidad.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        add(jL_cantidad, gridBagConstraints);

        jL_dinero.setText("0");
        jL_dinero.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        add(jL_dinero, gridBagConstraints);

        jScrollPane1.setPreferredSize(new java.awt.Dimension(400, 400));

        jT_comics.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Número"
            }
        ));
        jT_comics.setToolTipText("");
        jScrollPane1.setViewportView(jT_comics);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 357;
        gridBagConstraints.ipady = 319;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 15, 0, 15);
        add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("Cantidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.25;
        add(jLabel1, gridBagConstraints);

        jLabel2.setText("Dinero: $");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.weightx = 0.25;
        add(jLabel2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void jB_verRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jB_verRevistasActionPerformed
		try {
			ArrayList<Comic> a = Comic.getAll();
			if (a != null) {
				DefaultTableModel dtm = new DefaultTableModel();
				dtm.addColumn("Código");
				dtm.addColumn("Nombre");
				dtm.addColumn("Número");
				for (Comic c : a) {
					Object[] o = {
						c.getCodigo(),
						c.getNombre(),
						c.getNumero()
					};
					dtm.addRow(o);
				}
				JOptionPane.showMessageDialog(this, "Se cargaron los comics");
				jT_comics.setModel(dtm);
				int cantidad = dtm.getRowCount();
				jL_dinero.setText(String.valueOf(cantidad * 1000));
				jL_cantidad.setText(String.valueOf(cantidad));
			} else {
				JOptionPane.showMessageDialog(this,
						"No se cargaron los datos de los comics",
						"Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (SinBaseDatosException ex) {
			ManejoErrorConexion.mostrar(this, ex);
		}
    }//GEN-LAST:event_jB_verRevistasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jB_verRevistas;
    private javax.swing.JLabel jL_cantidad;
    private javax.swing.JLabel jL_dinero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jT_comics;
    // End of variables declaration//GEN-END:variables
}
