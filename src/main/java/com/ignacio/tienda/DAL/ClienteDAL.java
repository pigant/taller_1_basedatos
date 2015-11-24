/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ignacio.tienda.DAL;

import com.ignacio.tienda.BLL.Cliente;
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
public class ClienteDAL {

    public static Cliente get(int rut) throws ClienteNoExisteException {
        Cliente c = null;
        BD bd = null;
        try {
            bd = new BD();
            ArrayList<Object[]> a = bd.select("cliente",
                    "rut=" + rut, "rut", "nombre");
            if (a != null && a.size() > 0) {
                Object[] o = a.get(0);
                c = new Cliente(
                        (int) o[0],
                        (String) o[1]);
            }
        } catch (SinBaseDatosException ex) {
            Logger.getLogger(ComicDAL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (bd != null) {
                bd.close();
            }
        }
		if (c == null){
			throw new ClienteNoExisteException();
		}
        return c;
    }

    public static ArrayList<Cliente> getAll() {
        ArrayList<Cliente> a = null;
        BD bd;
        try {
            bd = new BD();
            a = new ArrayList<>();
            ResultSet r = bd.createStatement().executeQuery(
                    "select rut, nombre from cliente");
            while (r.next()) {
                a.add(new Cliente(
                        r.getInt("rut"),
                        r.getString("nombre")));
            }
        } catch (SinBaseDatosException ex) {
            Logger.getLogger(ClienteDAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }

    public boolean guardar(int rut, String nombre) throws CodigoRepetidoException {
        boolean salida = false;
        BD bd = null;
        try {
            bd = new BD();
            salida = bd.update(
                    "insert into cliente (rut, nombre) values (?,?)",
                    rut, nombre);
        } catch (SinBaseDatosException ex) {
            Logger.getLogger(ComicDAL.class.getName()).
                    log(Level.SEVERE, ex.getMessage(), ex.getCause());
        } finally {
            if (bd != null) {
                bd.close();
            }
        }
        return salida;
    }

    public boolean actualizar(int rut, String nombre, int rutPrevio) {
        boolean salida = false;
        BD bd = null;
        try {
            bd = new BD();
            salida = bd.update(
                    "update cliente set rut=?, nombre=? where rut=?",
                    rut, nombre, rutPrevio);
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

    public boolean borrar(int rut) {
        boolean salida = false;
        BD bd = null;
        try {
            bd = new BD();
            salida = bd.update("delete from cliente where rut=?", rut);
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
