/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.ClienteDAL;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;

/**
 *
 * @author ignacio
 */
public class Cliente {

	private int rut;
	private int rutPrevio;
	private String nombre;
	private boolean cambio = false;

	public Cliente() {
	}

	public Cliente(int rut, String nombre) {
		this.rut = rut;
		this.rutPrevio = rut;
		this.nombre = nombre;
	}

	public static Cliente get(int rut) {
		return ClienteDAL.get(rut);
	}

	public boolean guardar() throws CodigoRepetidoException {
		boolean s = false;
		if (cambio) {
			s = new ClienteDAL().actualizar(rut, nombre, rutPrevio);
		} else {
			s = new ClienteDAL().guardar(rut, nombre);
		}
		return s;
	}

	public boolean borrar() {
		boolean salida = false;
		salida = new ClienteDAL().borrar(rut);
		return salida;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.cambio = true;
		this.rutPrevio = this.rut;
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.cambio = true;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cliente{" + "rut=" + rut + ", nombre=" + nombre + '}';
	}

	

}
