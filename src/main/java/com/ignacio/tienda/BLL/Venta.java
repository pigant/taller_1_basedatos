/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.VentaDAL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ignacio
 */
public class Venta implements CrudOperationBLL {

	private static HashMap<Integer, Venta> ventas = new HashMap();
	private Integer idVenta;
	private Cliente c;
	private ArrayList<Detalle> d;
	private boolean actualizar;

	public Venta() {
		d = new ArrayList<>();
	}

	public Venta(final int idVenta, final Cliente c) {
		this.idVenta = idVenta;
		this.c = c;
	}

	public void addDetalle(final Detalle detalle) {
		actualizar = true;
		d.add(detalle);
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(final Integer idVenta) {
		this.idVenta = idVenta;
	}

	public Cliente getCliente() {
		return c;
	}

	public void setCliente(final Cliente c) {
		actualizar = true;
		this.c = c;
	}

	@Override
	public boolean guardar() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		/*boolean s = false;
		if (actualizar) {
			// [ ] Actualiza la venta
			// [ ] Actualiza los detalles
		} else {
			// [ ] crea la venta
			// [ ] obtiene el id de la venta
			// [ ] agrega los detalles
		}
		return s;*/
	}

	@Override
	public boolean borrar() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static Venta get(final int id) {
		return get(id, true);
	}

	public static Venta get(final int id, final boolean cache) {
		Venta v;
		if (cache) {
			if (ventas.containsKey(id)) {
				v = ventas.get(id);
			} else {
				v = VentaDAL.get(id);
				ventas.put(id, v);
			}
		} else {
			v = VentaDAL.get(id);
		}
		return v;
	}

}
