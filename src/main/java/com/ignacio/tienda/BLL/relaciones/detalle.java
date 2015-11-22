/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL.relaciones;

/**
 *
 * @author ignacio
 */
public class detalle {
	private int idDetalle;
	private int codigoComic;

	public detalle() {
	}

	public detalle(int idDetalle, int codigoComic) {
		this.idDetalle = idDetalle;
		this.codigoComic = codigoComic;
	}

	public int getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}

	public int getCodigoComic() {
		return codigoComic;
	}

	public void setCodigoComic(int codigoComic) {
		this.codigoComic = codigoComic;
	}
	
}
