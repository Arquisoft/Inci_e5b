package uo.asw.participants.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import uo.asw.AgentsE5bApplication;
import uo.asw.agents.service.AgentsService;
import uo.asw.dbManagement.model.Agent;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgentsE5bApplication.class)
@WebAppConfiguration
public class WebControllerTest {

    @Autowired
    private WebApplicationContext wac;
 
    private MockMvc mockMvc;
    
    @Autowired
    private AgentsService agentDAO;
     
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @Test
	/**
	 * Test que comprueba el funcionamiento de la pagina web
	 * cuando el usuario y la contraseña son correctos
	 * @throws Exception
	 */
    public void showInfoTest1() throws Exception {
    	
      mockMvc.perform(post("/info")
		.param("login", "31668313G")
		.param("password", "1234")
		.param("kind","Person"))
      	.andExpect(status().isOk())
      	.andExpect(model().attributeExists("resultado"))
      	.andExpect(view().name("view"));
    	
    }

    
    @Test
    /**
	 * Test que comprueba el funcionamiento de la pagina web
	 * cuando el usuario es incorrecto
	 * @throws Exception
	 */
    public void showInfoTest2() throws Exception {
    	
        mockMvc.perform(post("/info")
    	.param("login", "usuario")
		.param("password", "1234")
		.param("kind", "Person"))
     	.andExpect(view().name("error"));

    }
    
    @Test
    /**
	 * Test que comprueba el funcionamiento de la pagina web
	 * cuando la contraseña es incorrecta
	 * @throws Exception
	 */
    public void showInfoTest3() throws Exception {

       	mockMvc.perform(post("/info")
       	.param("login", "31668313G")
       	.param("password", "1243")
       	.param("kind","Person"))
     	.andExpect(view().name("error"));
   
    }
    
    @Test
    /**
	 * Test que comprueba el funcionamiento de la pagina web
	 * cuando login, password y kind son vacios
	 * @throws Exception
	 */
    public void showInfoTest4() throws Exception {

       	mockMvc.perform(post("/info")
       	.param("login", "")
       	.param("password", "")
       	.param("kind",""))
        .andExpect(view().name("error"));
   
    }
    
   @Test
   /**
    * Test que comprueba que al hacer una peticion a / nos devuelve
    * la pagina de login
    * @throws Exception
    */
   public void showViewTest() throws Exception {
       mockMvc.perform(get("/"))
           .andExpect(status().isOk())
           .andExpect(view().name("log"));
   }
    
   
   @Test
   /**
    * Test que comprueba el cambio de contraseña
    * @throws Exception
    */
   public void changePasswordTest1() throws Exception {
	   Agent c = agentDAO.getAgent("31668313G", "1234", "Person");

	   //Cambio de contraseña
       mockMvc.perform(post("/changePassword")
    	.param("password", "1234")
		.param("newPassword", "new")
		.sessionAttr("agent", c))
        .andExpect(status().isOk())
    	.andExpect(view().name("view"));

	   //Cambio de contraseña de nuevo por la original
       mockMvc.perform(post("/changePassword")
    	.param("password", "new")
		.param("newPassword", "1234")
		.sessionAttr("agent", c))
        .andExpect(status().isOk())
     	.andExpect(view().name("view"));
   }
   
   @Test
   /**
    * Test que comprueba el cambio de contraseña, cuando 
    * la contraseña es incorrecta
    * @throws Exception
    */
   public void changePasswordTest2() throws Exception {
	   Agent c = agentDAO.getAgent("31668313G", "1234", "Person");

       mockMvc.perform(post("/changePassword")
    	.param("password", "password")
		.param("newPassword", "new")
		.sessionAttr("agent", c))
    	.andExpect(view().name("errorContrasena"));
       
   }
   
   @Test
   /**
    * Test que comprueba el cambio de email
    * @throws Exception
    */
   public void changeEmailTest1() throws Exception {
	   Agent c = agentDAO.getAgent("31668313G", "1234", "Person");

	   //Cambio de email
       mockMvc.perform(post("/changeEmail")
    	.param("email", "juanNuevo@gmail.com")
		.sessionAttr("agent", c))
        .andExpect(status().isOk())
    	.andExpect(view().name("view"));

       assertEquals("juanNuevo@gmail.com", c.getEmail());
       
	   //Cambio de email de nuevo por el original
       mockMvc.perform(post("/changeEmail")
    	.param("email", "juan@gmail.com")
		.sessionAttr("agent", c))
        .andExpect(status().isOk())
     	.andExpect(view().name("view"));
       
       assertEquals("juan@gmail.com", c.getEmail());

   }
}
