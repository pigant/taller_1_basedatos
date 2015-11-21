/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestBll;

import com.ignacio.tienda.BLL.Cliente;
import com.ignacio.tienda.BLL.Comic;
import com.ignacio.tienda.DAL.exception.CodigoRepetidoException;
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
	public void CRUD() {
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
	public void CRUD_cliente() throws CodigoRepetidoException {
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
}
