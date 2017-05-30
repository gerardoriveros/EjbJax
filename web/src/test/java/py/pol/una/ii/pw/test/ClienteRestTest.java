package py.pol.una.ii.pw.test;
/*

import static org.junit.Assert.*;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import py.pol.una.ii.pw.rest.ClienteResourceRESTService;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;

public class ClienteRestTest {
		

	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;
	
	
	@InjectMocks
	private ClienteResourceRESTService transactions;


	@Before
	public void setUp() throws Exception {
	    MockitoAnnotations.initMocks(this);
	    mockMvc = MockMvcBuilders.standaloneSetup(transactions).build();
	}

	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

	}
	

	@Test
	public void verifyAllToDoList() throws Exception {
		//mockMvc.perform(MockMvcRequestBuilders.get("/EjbJaxRS-web/rest/clientes/").accept(MediaType.APPLICATION_JSON))
			//.andExpect(jsonPath("$", hasSize(1))).andDo(print());
	}
	
	

}*/


import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.jboss.arquillian.container.test.api.Deployment;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import py.pol.una.ii.pw.model.Cliente;
import py.pol.una.ii.pw.rest.JaxRsActivator;

//import py.pol.una.ii.pw.model.Cliente;

/**
 * Arquillian Extension REST API Test Case
 * 
 * Annotate the TestClass's TestMethods with JAX-RS Client annotations.
 * 
 * Executes the REST request in the background for so to inject back the Response into the TestMethods arguments.  
 *
 * @author <a href="mailto:aslak@redhat.com">Aslak Knutsen</a>
 * @version $Revision: $
 */
@RunWith(Arquillian.class)
public class ClienteRestTest
{
   /*@Deployment(testable = false)
   public static WebArchive create() {
      return ShrinkWrap.create(WebArchive.class)
            .addPackage(Cliente.class.getPackage())
            .addClasses(EntityManagerProducer.class, CustomerResource.class, JaxRsActivator.class)
            .addAsResource("import.sql")
            .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }*/

 /*  @Test @GET @Path("/EjbJaxRS-web/rest/clientes/") @Consumes(MediaType.APPLICATION_JSON)
   public void testListAllCustomers(ClientResponse<List<Cliente>> response) 
   {
	   System.out.println(Status.OK.getStatusCode()+" Status code ..");
	   System.out.println(response.getStatus()+" Status code ..");
      Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
      
      List<Cliente> customers = response.getEntity(new GenericType<List<Cliente>>(){});
      Assert.assertEquals(1, customers.size());
   
   }*/
   
   @Test
   public void testGetCustomerByIdUsingClientRequest() throws Exception {
       //deploymentUrl = new URL("http://localhost:8081/EjbJaxRS-web/rest/clientes/");
       //GET http://localhost:8080/test/rest/customer/1
       ClientRequest request = new ClientRequest("http://localhost:8081/EjbJaxRS-web/rest/clientes/1");
       request.header("Accept", MediaType.APPLICATION_JSON);
       
       // we're expecting a String back
       ClientResponse<String> responseObj = request.get(String.class);

       //Assert.assertEquals(200, responseObj.getStatus());
       System.out.println("GET /customer/1 HTTP/1.1\n\n" + responseObj.getEntity());
       
      

       /*String response = responseObj.getEntity().replaceAll("<\\?xml.*\\?>", "").trim();
       Assert.assertEquals("<customer><id>1</id><name>Acme Corporation</name></customer>", response);*/
       
   }

   
   /*

   @Test @GET @Path("rest/customer/1") @Consumes(MediaType.APPLICATION_XML)
   public void shouldBeAbleToListACustomer(ClientResponse<Customer> response) 
   {
      Assert.assertEquals(Status.OK.getStatusCode(), response.getStatus());
      
      Customer customers = response.getEntity();
      Assert.assertEquals(1, customers.getId().intValue());
   }*/
}