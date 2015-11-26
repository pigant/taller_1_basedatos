package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.VentaDAL;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ignacio
 */
public class Venta {

	private static HashMap<Integer, Venta> ventas = new HashMap();
	private Integer idVenta;
	private Cliente c;
	private ArrayList<Detalle> d = new ArrayList();
	private boolean actualizar;

	public Venta() {
	}

	public Venta(final int idVenta, final Cliente c) {
		this.idVenta = idVenta;
		this.c = c;
	}

	public void addDetalle(final Detalle detalle) {
		d.add(detalle);
		detalle.setVenta(this);
	}

	public Integer getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(final Integer idVenta) {
		actualizar = true;
		this.idVenta = idVenta;
	}

	public Cliente getCliente() {
		return c;
	}

	public void setCliente(final Cliente c) {
		this.c = c;
	}

	public boolean guardar() throws CodigoRepetidoException, SinBaseDatosException {
		boolean s = false;
		if (actualizar) {
			// [ ] Actualiza la venta
			s = new VentaDAL().actualizar(c.getRut(), idVenta);
		} else {
			// [x] crea la venta
			// [x] obtiene el id de la venta
			s = new VentaDAL().guardar(idVenta, c.getRut());
			for (Detalle detalle : d) {
				s &= detalle.guardar();
			}
		}
		return s;
	}

	public boolean borrar() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	public static Venta get(final int id) {
		return get(id, false);
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Venta{" + "idVenta=").append(idVenta).append(", c=").append(c).append(", d= [");
		for (Detalle detalle : d) {
			sb.append(detalle).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("] }");
		return sb.toString();
	}

	public ArrayList<Detalle> getDetalles() {
		return d;
	}

	void addDetalle(int id, Detalle detalle) {
		detalle.setIdDetalle(id);
		addDetalle(detalle);
	}

}
