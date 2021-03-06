package com.ignacio.tienda.BLL;

import com.ignacio.tienda.DAL.ClienteDAL;
import com.ignacio.tienda.DAL.exception.ClienteNoExisteException;
import com.ignacio.tienda.DAL.exception.CompraNoExisteException;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author ignacio
 */
public class Cliente {

	private static HashMap<Integer, Cliente> clientes = new HashMap();

	public static ArrayList<Cliente> getAll() throws SinBaseDatosException {
		return ClienteDAL.getAll();
	}

	public static String findMejorCliente() throws SinBaseDatosException {
		return ClienteDAL.findMejorCliente();
	}

	static Cliente findPorCompra(int codigo) throws CompraNoExisteException, SinBaseDatosException {
		return ClienteDAL.findPorCompra(codigo);
	}

	private Integer rut;
	private int rutPrevio;
	private String nombre;
	private boolean actualizar = false;

	public Cliente() {
	}

	public Cliente(int rut, String nombre) {
		this.rut = rut;
		this.rutPrevio = rut;
		this.nombre = nombre;
	}

	public static Cliente get(int rut) throws ClienteNoExisteException, SinBaseDatosException {
		return get(rut, false);
	}

	public static Cliente get(int rut, boolean cache) throws ClienteNoExisteException, SinBaseDatosException {
		Cliente c;
		if (cache) {
			if (clientes.containsKey(rut)) {
				c = clientes.get(rut);
			} else {
				c = ClienteDAL.get(rut);
				clientes.put(rut, c);
			}

		} else {
			c = ClienteDAL.get(rut);
		}
		return c;
	}

	public boolean guardar() throws CodigoRepetidoException, SinBaseDatosException {
		boolean s = false;
		if (actualizar) {
			s = new ClienteDAL().actualizar(rut, nombre, rutPrevio);
		} else {
			s = new ClienteDAL().guardar(rut, nombre);
		}
		return s;
	}

	public boolean borrar() throws SinBaseDatosException {
		boolean salida = false;
		salida = new ClienteDAL().borrar(rut);
		return salida;
	}

	public int getRut() {
		return rut;
	}

	public void setRut(int rut) {
		this.actualizar = this.rut != null && this.nombre != null;
		this.rutPrevio = this.rut;
		this.rut = rut;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.actualizar = this.rut != null && this.nombre != null;
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Cliente{" + "rut=" + rut + ", nombre=" + nombre + '}';
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 97 * hash + this.rut;
		hash = 97 * hash + Objects.hashCode(this.nombre);
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
		final Cliente other = (Cliente) obj;
		if (this.rut != other.rut) {
			return false;
		}
		if (!Objects.equals(this.nombre, other.nombre)) {
			return false;
		}
		return true;
	}

}
