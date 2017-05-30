package py.pol.una.ii.pw.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import py.pol.una.ii.pw.model.Cliente;
import py.pol.una.ii.pw.rest.ClienteResourceRESTService;
import py.pol.una.ii.pw.service.ClienteService;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/test/BeanConfig.xml" })
//@ContextConfiguration("/mybatis-config.xml")
@ContextConfiguration("/mybatis-config.xml")
@WebAppConfiguration
public class ClienteRESTTest {

	 private MockMvc mockMvc;

	    @Mock
	    private ClienteService clienteService;

	    @InjectMocks
	    private ClienteResourceRESTService clienteResourseRESTService;
	    

        @Autowired
	    private WebApplicationContext webApplicationContext;
	    
	    @Before
	    public void init(){
	        MockitoAnnotations.initMocks(this);
	        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).dispatchOptions(true).build();
	                
	    }
	    
	    
	    
	    @Test
	    public void getCliente() throws Exception {
	    	
/*
	        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("http://localhost:8081/");
	        ResultActions result = mockMvc.perform(request);
	        result.andExpect(status().isOk());*/
	        
	    	
	    	//this.mockMvc.perform(get("https://www.google.com.py/?gws_rd=ssl")).andExpect(status().isOk());
//	    	System.out.println(status().isOk());
	    
	        this.mockMvc.perform(get("http://localhost:8081/EjbJaxRS-web/rest/clientes")
	        		.contentType(MediaType.APPLICATION_JSON_VALUE));
	          //.andExpect(jsonPath("$.nombre").value("asdf"));
	        System.out.println(jsonPath("$.nombre").value("asdf")+"probando");
	        
	    }
	    
	    @Test
	    public void testGetAllClientes() throws Exception {
	    	System.out.println("probanto test..");
	        List<Cliente> clientes = Arrays.asList(
	                new Cliente(1, "Daenerys Targaryen"),
	                new Cliente(2, "John Snow"));
	        
	        for (Cliente cliente : clientes) {
	        	System.out.println(cliente.getNombre().toString());
			}
	        
	        mockMvc.perform(MockMvcRequestBuilders.get("/EjbJaxRS-web/rest/clientes/1").accept(MediaType.APPLICATION_JSON));
			
	        
	        /* when(clienteService.getAllCliente()).thenReturn(clientes);
	        mockMvc.perform(get("/users"))
	                .andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(jsonPath("$", hasSize(2)))
	                .andExpect(jsonPath("$[0].id", is(1)))
	                .andExpect(jsonPath("$[0].username", is("Daenerys Targaryen")))
	                .andExpect(jsonPath("$[1].id", is(2)))
	                .andExpect(jsonPath("$[1].username", is("John Snow")));
	        verify(userService, times(1)).getAll();
	        verifyNoMoreInteractions(userService);*/
	    }

}
