/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.ComicDAL;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author ignacio
 */
public class Comic {

	private static HashMap<Integer, Comic> comics;
	private Integer codigo;
	private String nombre;
	private int numero;
	private boolean actualizar = false;

	public Comic() {
	}

	public Comic(String nombre, int numero) {
		this.nombre = nombre;
		this.numero = numero;
	}

	public Comic(Integer codigo, String nombre, int numero,
		boolean actualizar) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.numero = numero;
		this.actualizar = actualizar;
	}

	public Comic(Integer codigo, String nombre, int numero) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.numero = numero;
		actualizar = false;
	}

	public static Comic get(int codigo) {
		return get(codigo, false);
	}

	public static Comic get(int codigo, boolean cache) {
		Comic c;
		if (cache) {
			if (comics.containsKey(codigo)) {
				c = comics.get(codigo);
			} else {
				c = ComicDAL.get(codigo);
				comics.put(codigo, c);
			}
		} else {
			c = ComicDAL.get(codigo);
		}
		return c;
	}

	public static ArrayList<Comic> getAll() {
		return ComicDAL.getAll();
	}

	public boolean guardar() throws CodigoRepetidoException{
		boolean s = false;
		if (!actualizar) {
			Integer c;
			if (codigo != null) {
				c = new ComicDAL().guardar(codigo, nombre, numero);
			} else {
				c = new ComicDAL().guardar(nombre, numero);
			}
			if (c != null) {
				s = c > -1;
				if (s) {
					this.codigo = c;
				}
			}
			actualizar = true;
		} else {
			s = new ComicDAL().actualizar(nombre, numero, codigo);
		}
		return s;
	}

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

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.codigo);
		hash = 37 * hash + Objects.hashCode(this.nombre);
		hash = 37 * hash + this.numero;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Comic other = (Comic) obj;
		if (this.numero != other.numero) {
			return false;
		}
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		if (!Objects.equals(this.codigo, other.codigo)) {
			return false;
		}
		return true;
	}

}
