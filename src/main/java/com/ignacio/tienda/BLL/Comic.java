/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.ComicDAL;

/**
 *
 * @author ignacio
 */
public class Comic implements CrudOperationBLL{

	Integer codigo;
	String nombre;
	int numero;

	public Comic() {
	}

	public Comic(String nombre, int numero) {
		this.nombre = nombre;
		this.numero = numero;
	}

	public Comic(Integer codigo, String nombre, int numero) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.numero = numero;
	}

	public static Comic get(int codigo) {
		return ComicDAL.get(codigo);
	}

	@Override
	public boolean guardar() {
		boolean s = false;
		if (codigo == null) {
			Integer c = new ComicDAL().guardar(nombre, numero);
			if (c != null) {
				s = c > -1;
				if (s) {
					this.codigo = c;
				}
			}
		} else {
			s = new ComicDAL().actualizar(nombre, numero, codigo);
		}
		return s;
	}

	@Override
	public boolean borrar() {
		boolean salida = false;
		if (codigo != null) {
			salida = new ComicDAL().borrar(codigo);
		}
		return salida;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Comic{" + "codigo=" + codigo + ", nombre=" + nombre + ", numero=" + numero + '}';
	}

}
