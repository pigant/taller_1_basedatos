package com.ignacio.tienda.DAL;

import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sistemas
 */
public class BD {

	private Connection c;

	public BD() throws SinBaseDatosException {
		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://localhost/tienda",
					"root", "root");
		} catch (SQLException ex) {
			throw new SinBaseDatosException(ex);
		}
	}

	public boolean update(String consulta, Object... parametros)
			throws CodigoRepetidoException {
		try {
			PreparedStatement ps = c.prepareStatement(consulta);
			for (int i = 0; i < parametros.length; i++) {
				Object o = parametros[i];
				ps.setObject(i + 1, o);
			}
			int sa = ps.executeUpdate();
			ps.close();
			return sa == 1;
		} catch (SQLException ex) {
			if (ex.getErrorCode() == 1062) {
				throw new CodigoRepetidoException(ex);
			}
			Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
			return false;
		}
	}

	public Statement createStatement() throws SQLException {
		return c.createStatement();
	}

	public ArrayList<Object[]> select(
			String tabla,
			String condicion,
			String... columnas) {
		//
		try {
			StringBuilder sb = new StringBuilder("select ");
			for (String c : columnas) {
				sb.append(c).append(',');
			}
			sb.deleteCharAt(sb.length() - 1).
					append(" from ").
					append(tabla).append(" where ").
					append(condicion);
			//System.out.println(sb.toString());
			ResultSet r = c.createStatement().executeQuery(sb.toString());
			ArrayList<Object[]> array = new ArrayList<>();
			while (r.next()) {
				Object[] o = new Object[columnas.length];
				for (int i = 0; i < columnas.length; i++) {
					o[i] = r.getObject(columnas[i]);
				}
				array.add(o);
			}
			r.close();
			return array;
		} catch (SQLException ex) {
			Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}
	}

	public void close() {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException ex) {
				Logger.getLogger(BD.class.getName()).log(
						Level.SEVERE, null, ex);
			}
		}
	}

	int lastId() throws SQLException {
		int id;
		ResultSet r;
		String consulta = "select last_insert_id() as i";
		r = createStatement().executeQuery(consulta);
		r.next();
		id = r.getInt("i");
		r.close();
		return id;
	}
}
