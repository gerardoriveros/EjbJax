package py.pol.una.ii.pw.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;
import py.pol.una.ii.pw.model.Cliente;
import py.pol.una.ii.pw.rest.ClienteResourceRESTService;

public class TestCliente extends TestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testListAllClientes() {
		fail("Not yet implemented");
	}

	@Test
	public void testLookupClienteById() {
		Integer id = new Integer(1);
		ClienteResourceRESTService clienteResource = new ClienteResourceRESTService(); 
		Cliente cliente = clienteResource.lookupClienteById(id);
		assertNotNull(cliente);
		assertEquals(cliente.getId_cliente(), id);
	}

	@Test
	public void testCreateCliente() {		
		fail("Not yet implemented");
	}

	@Test
	public void testAlterCliente() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveCliente() {
		fail("Not yet implemented");
	}

}
