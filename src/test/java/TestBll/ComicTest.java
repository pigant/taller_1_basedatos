/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBll;

import com.ignacio.tienda.BLL.Cliente;
import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.BLL.Detalle;
import com.ignacio.tienda.BLL.Venta;
import com.ignacio.tienda.BLL.VentaBuilder;
import com.ignacio.tienda.DAL.exception.ClienteNoExisteException;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
import com.ignacio.tienda.DAL.exception.CompraNoExisteException;
import com.ignacio.tienda.DAL.exception.SinBaseDatosException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ignacio
 */
public class ComicTest {

	public ComicTest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	// TODO add test methods here.
	// The methods must be annotated with annotation @Test. For example:
	//
	// @Test
	// public void hello() {}
	@Test
	public void CRUD_comic() throws CodigoRepetidoException, SinBaseDatosException {
		//Creacion
		Comic c = new Comic("BatmanTest", 999);
		assertTrue("No se guardo el comic", c.guardar());
		//lectura
		Comic ct = Comic.get(c.getCodigo());
		System.out.println(ct);
		//Actualizacion
		c.setNumero(998);
		assertTrue("No se actualizo", c.guardar());
		//Lectura
		ct = Comic.get(c.getCodigo());
		System.out.println(ct);
		//borrado
		assertTrue("No se pude borrar", c.borrar());
	}

	@Test
	public void CRUD_cliente() throws CodigoRepetidoException, ClienteNoExisteException, SinBaseDatosException {
		//Creacion
		Cliente c = new Cliente(17142732, "IgnacioTest");
		assertTrue("No se guardo el cliente", c.guardar());
		//lectura
		Cliente ct = Cliente.get(c.getRut());
		System.out.println(ct);
		//Actualizacion
		c.setRut(998);
		assertTrue("No se actualizo", c.guardar());
		//Lectura
		ct = Cliente.get(c.getRut());
		System.out.println(ct);
		//borrado
		assertTrue("No se pude borrar", c.borrar());
	}

	@Test
	public void venta() throws SinBaseDatosException, CompraNoExisteException, CodigoRepetidoException, ClienteNoExisteException {
		//creacion cliente
		Cliente c = new Cliente(666, "IgnacioTest");
		try {
			assertTrue("No se guardo el cliente", c.guardar());
		} catch (CodigoRepetidoException ex) {
			System.out.println("Ya existe el cliente con id 666, se eliminara");
		}
		Comic comic = new Comic("La caquita", 30);
		comic.setCodigo(999);
		try {
			assertTrue("No se creo el comic", comic.guardar());
		} catch (CodigoRepetidoException ex) {
			System.out.println("Ya existe el comic con id 999, se eliminara");
		}
		ArrayList<Comic> a = new ArrayList<>(1);
		a.add(comic);
		int idVenta = 999;
		boolean isCreado = VentaBuilder.crear(idVenta, c.getRut(), a);
		assertTrue("No se creo la venta",isCreado);
		//Obtiene la venta para visualizar
		Venta vv = VentaBuilder.getVenta(idVenta);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(vv);
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		//borrado
		c.borrar();
		comic.borrar();
		vv.borrar();
	}
}
