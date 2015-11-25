/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.BD;
import com.ignacio.tienda.DAL.exception.ClienteNoExisteException;
import com.ignacio.tienda.DAL.exception.CompraNoExisteException;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class VentaBuilder {

	public static boolean crear(final int idVenta,
			final int rut,
			final ArrayList<Comic> comics) throws ClienteNoExisteException, CodigoRepetidoException, SinBaseDatosException {
		//
		boolean salida = false;
		Venta v = new Venta(idVenta, Cliente.get(rut));
		for (Comic c : comics) {
			v.addDetalle(new Detalle(c));
		}
		salida = v.guardar();
		return salida;
	}

	private Cliente c;
	private ArrayList<Detalle> ld = new ArrayList();

	public VentaBuilder addDetalle(Detalle d) {
		return this;
	}

	public VentaBuilder setCliente(Cliente c) {
		this.c = c;
		return this;
	}

	public boolean guardar() throws CodigoRepetidoException, SinBaseDatosException {
		Venta v = new Venta();
		v.setCliente(c);
		boolean x = v.guardar();
		if (x) {
			for (Detalle d : ld) {
				v.addDetalle(d);
				d.guardar();
			}
		}
		return false;
	}

	public static Venta getVenta(int codigo) throws CompraNoExisteException {
		BD bd;
		ResultSet r;
		Cliente c;
		Venta v = null;
		try {
			bd = new BD();
			//Cliente
			c = Cliente.findPorCompra(codigo);
			//detalles
			v = new Venta();
			ArrayList<Detalle> d = Detalle.getAll(codigo);
			for (Detalle detalle : d) {
				v.addDetalle(detalle);
			}
			v.setCliente(c);

		} catch (SinBaseDatosException ex) {
			Logger.getLogger(VentaBuilder.class.getName()).log(Level.SEVERE, null, ex);
		}
		return v;
	}

}
