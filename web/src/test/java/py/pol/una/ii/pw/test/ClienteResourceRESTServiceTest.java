package py.pol.una.ii.pw.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class ClienteResourceRESTServiceTest {

	@Test
	public final void testListAllClients() {
        given()
        	.when()
        	.get("http://localhost:8081/EjbJaxRS-web/rest/clientes")
        	.then()
        	.body("id",hasItems(1))
        	.statusCode(200);
	}

	@Test
	public final void testLookupClientById() {
		given()
    	.when()
    	.get("http://localhost:8081/EjbJaxRS-web/rest/clientes/1")
    	.then()
    	.statusCode(200)
    	.body("id", equalTo(1));
	}

	@Test
	public final void testCreateClient() {
		Map<String,String> client = new HashMap<String, String>();
        client.put("nombre", "Pedro J");
        client.put("telefono", "+5959123456");
        client.put("ruc", "123456-7");
        client.put("saldo", "0");

        given()
        .contentType("application/json")
        .body(client)
        .when()
        .post("http://localhost:8081/EjbJaxRS-web/rest/clientes")
        .then()
        .statusCode(200);  
	}
}