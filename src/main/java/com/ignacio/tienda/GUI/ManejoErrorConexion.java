/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.GUI;

import com.ignacio.tienda.DAL.BD;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author ignacio
 */
public class ManejoErrorConexion {

	public static void mostrar(JComponent j, Throwable t) {
		boolean repetir = true;
		while (repetir) {
			JOptionPane.showMessageDialog(j, "No hay conexion a la base de datos!", "ERROR", JOptionPane.ERROR_MESSAGE);
			int seleccion = JOptionPane.showConfirmDialog(j,
					"¿Desea intentar conectarse nuevamente?",
					"Consulta",
					JOptionPane.YES_NO_OPTION);
			if (seleccion == JOptionPane.YES_OPTION) {
				try {
					BD bd = new BD();
					repetir = false;
				} catch (SinBaseDatosException ex) {
					continue;
				}
			} else if (seleccion == JOptionPane.NO_OPTION) {
				JOptionPane.showMessageDialog(j, "Se cerrará el programa, por no poder conectarse");
				System.exit(-1);
			}
		}
	}
}
