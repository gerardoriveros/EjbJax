package py.pol.una.ii.pw.test;


//import static org.junit.Assert.*;
//import io.restassured.RestAssured.*;
//import io.restassured.matcher.RestAssuredMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static io.restassured.RestAssured.given;
/**
 * @author gerardo
 *
 */
public class ClientResourceRESTServiceTest {
	
	@Test
	public final void testListAllClients() {
		
		
        given()
        	.when()
        	.get("http://localhost:8082/EjbJaxRS-web/rest/clientes")
        	.then()
        	.statusCode(200)
        	.body("nombre", contains(equalTo("Jose")));
	}

	@Test
	public final void testLookupClientById() {
		given()
    	.when()
    	.get("http://localhost:8082/EjbJaxRS-web/rest/clientes/1")
    	.then()
    	.statusCode(200)
    	.body("id_cliente", equalTo(1));
	}


//	@Test
//	public final void testLookupClientById() {
//		fail("Not yet implemented"); // TODO
//	}

//	@Test
//	public final void testUpdateClient() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testDeleteClient() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testCreateClient() {
//		fail("Not yet implemented"); // TODO
//	}

}