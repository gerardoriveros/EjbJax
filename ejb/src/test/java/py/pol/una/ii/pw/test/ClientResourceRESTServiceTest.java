package py.pol.una.ii.pw.test;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import py.pol.una.ii.pw.service.ClienteService;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import static io.restassured.RestAssured.given;
public class ClientResourceRESTServiceTest {
	
	

	@Test
	public final void testListAllClients() {
        given()
        	.when()
        	.get("http://localhost:8081/EjbJaxRS-web/rest/clientes/")
        	.then()
        	.body("id_cliente",hasItems(1))
        	.statusCode(200);
        System.out.println("1 ...");
	}

	@Test
	public final void testLookupClientById() {
		given()
    	.when()
    	.get("http://localhost:8081/EjbJaxRS-web/rest/clientes/1")
    	.then()
    	.statusCode(200)
    	.body("id_cliente", equalTo(1));
		System.out.println("2 ...");
	}

	@Test
	public final void testCreateClient() {
		Map<String,String> client = new HashMap<String, String>();
		 client.put("id_cliente", "666");
        client.put("nombre", "Gerardo Riveros");
        client.put("telefono", "+5959123456");
        client.put("ruc", "123456-7");
        client.put("saldo", "1000");

        given()
        .contentType("application/json")
        .body(client)
        .when()
        .post("http://localhost:8081/EjbJaxRS-web/rest/clientes/nuevo")
        .then()
        .statusCode(200);  
        
        System.out.println("3 ...");
        
	}
	
	@Test
	public final void testDeleteClient() {
		Map<String,String> client = new HashMap<String, String>();
		 client.put("id_cliente", "666");
      
        given()
        .contentType("application/json")
        .body(client)
        .when()
        .delete("http://localhost:8081/EjbJaxRS-web/rest/clientes/666")
        .then()
        .statusCode(200);  
        
        System.out.println("4 ...");
        
	}
	
	
	
}