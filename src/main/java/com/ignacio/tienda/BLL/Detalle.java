/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.DetalleDAL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class Detalle {

	private static final Logger LOG = Logger.getLogger("main");

	static ArrayList<Detalle> getAll(int codigo) {
		return DetalleDAL.getAll(codigo);
	}

	private Integer idDetalle;
	private Comic comic;
	private Venta venta;
	private boolean actualizar;

	public Detalle() {
	}

	public Detalle(Comic comic, Venta venta) {
		this.comic = comic;
		this.venta = venta;
	}

	public Detalle(Venta v){
		this.venta = venta;
	}

	public Detalle(final int idDetalle, final Comic comic, final Venta venta) {
		this.idDetalle = idDetalle;
		this.comic = comic;
		this.venta = venta;
		this.actualizar = true;
	}

	public Detalle(Comic comic) {
		this.comic = comic;
	}

	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(final int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Comic getComic() {
		return comic;
	}

	public void setCodigoComic(final Comic comic) {
		this.comic = comic;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(final Venta venta) {
		this.venta = venta;
	}

	public static Detalle get(int id) {
		return DetalleDAL.get(id);
	}

	public boolean guardar() {
		boolean s = false;
		if (actualizar) {
			LOG.log(Level.FINE, "Actualizando el detalle {0}", this);
			if (idDetalle != null) {
				s = new DetalleDAL().actualizar(
						venta.getIdVenta(), comic.getCodigo(), idDetalle);
				if (s) {
					LOG.log(Level.FINE, "Detalle actualizado");
				} else {
					LOG.log(Level.FINE, "No se actualizo el detalle");
				}
			} else {
				LOG.log(Level.WARNING, "Se intento actualizar un detalle, "
						+ "el cual no tiene un id asignado");
			}
		} else {
			// [x] Guardar el detalle, obteniendo el id del comic
			LOG.log(Level.FINE, "Guardando un detalle {0}", this);
			idDetalle = new DetalleDAL().guardar(venta.getIdVenta(), comic.getCodigo());
			if (idDetalle != null) {
				s = true;
				LOG.log(Level.FINE, "Detalle guardado");
			} else {
				LOG.log(Level.FINE, "No se guardo el detalle");
			}
		}
		return s;
	}

	public boolean borrar() {
		boolean s = false;
		LOG.log(Level.FINE, "Eliminando el detalle {0}", this);
		s = new DetalleDAL().borrar(idDetalle);
		if (s) {
			LOG.log(Level.INFO, "Detalle eliminado {0}", this);
		} else {
			LOG.log(Level.FINE, "El detalle no fue eliminado");
		}
		return s;
	}

	@Override
	public String toString() {
		return "Detalle{" + "idDetalle=" + idDetalle + ", comic=" + comic + ", idVenta=" + venta.getIdVenta() + '}';
	}

}
