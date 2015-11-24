/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.DAL;

import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ignacio
 */
public class ComicDAL {

	public ComicDAL() {
	}

	public static Comic get(int codigo) {
		Comic c = null;
		BD bd = null;
		try {
			bd = new BD();
			ArrayList<Object[]> a = bd.select("comic",
				"codigo=" + codigo, "codigo", "nombre", "numero");
			if (a != null && a.size() > 0) {
				Object[] o = a.get(0);
				c = new Comic(
					(int) o[0],
					(String) o[1],
					(int) o[2]);
			}
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return c;
	}

	public static ArrayList<Comic> getAll() {
		ArrayList<Comic> ac = new ArrayList<>();
		BD bd = null;
		try {
			bd = new BD();
			ResultSet r = bd.createStatement().executeQuery(
				"select codigo, nombre, numero from comic");
			while (r.next()) {
				ac.add(new Comic(r.getInt("codigo"),
					r.getString("nombre"),
					r.getInt("codigo")));
			}
			r.close();
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return ac;
	}

	public Integer guardar(Integer codigo, String nombre, int numero) throws CodigoRepetidoException {
		Integer salida = null;
		BD bd = null;
		try {
			bd = new BD();
			boolean estado = bd.update(
				"insert into comic (codigo, nombre, numero) values (?,?,?)",
				codigo, nombre, numero);
			if (estado) {
				salida = bd.lastId();
			}
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		}
		return salida;
	}

	public Integer guardar(String nombre, int numero) {
		Integer salida = null;
		BD bd = null;
		try {
			bd = new BD();
			boolean estado = bd.update(
				"insert into comic (nombre, numero) values (?,?)",
				nombre, numero);
			if (estado) {
				salida = bd.lastId();
			}
		} catch (SinBaseDatosException ex) {
			System.out.println("algo de mierda");
			Logger.getLogger(ComicDAL.class.getName()).
				log(Level.SEVERE, ex.getMessage(), ex.getCause());
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return salida;
	}

	public boolean actualizar(String nombre, int numero, int codigo) {
		boolean salida = false;
		BD bd = null;
		try {
			bd = new BD();
			salida = bd.update(
				"update comic set nombre=?, numero=? where codigo=?",
				nombre, numero, codigo);
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return salida;
	}

	public boolean borrar(int codigo) {
		boolean salida = false;
		BD bd = null;
		try {
			bd = new BD();
			salida = bd.update("delete from comic where codigo=?", codigo);
		} catch (SinBaseDatosException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} catch (CodigoRepetidoException ex) {
			Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (bd != null) {
				bd.close();
			}
		}
		return salida;
	}

}
