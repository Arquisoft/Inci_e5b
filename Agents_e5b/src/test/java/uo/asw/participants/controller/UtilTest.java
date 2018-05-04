package uo.asw.participants.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.AgentsE5bApplication;
import uo.asw.agents.util.AgentMin;
import uo.asw.agents.util.Check;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AgentsE5bApplication.class)
@WebAppConfiguration
public class UtilTest {
	
	private static String[] validEmails, invalidEmails;
	private AgentMin agentMin;
	
	 /**
     * Carga de datos
     */
    @BeforeClass
    public static void emailProviderText() {
        validEmails = new String[] { "test@example.com",
                "test-101@example.com", "test.101@yahoo.com",
                "test101@example.com", "test_101@example.com",
                "test-101@test.net", "test.100@example.com.au", "test@e.com",
                "test@1.com", "test@example.com.com", "test+101@example.com",
                "101@example.com", "test-101@example-test.com" };
 
        invalidEmails = new String[] { "example", "example@.com.com",
                "exampel101@test.a", "exampel101@.com", ".example@test.com",
                "example**()@test.com", "example@%*.com",
                "example..101@test.com", "example.@test.com",
                "test@example_101.com", "example@test@test.com",
                "example@test.com.a5" };
    }

    @Before
    public void setUp() throws Exception {
    	agentMin = new AgentMin("111111H", "nombre", null, "usuario@gmail.com", "Agent", 1);
    }

	@Test
	public void newAgentMinTest() {
		String id = "222222J";
		String nombre = "fernando";
		String apellidos = "sanchez";
		String kind = "Person";
		int kindCode = 1;
		String email = "fernando@gmail.com";
		String location = "1,1";

		agentMin.setId(id);
		agentMin.setName(nombre + apellidos);
		agentMin.setKind(kind);
		agentMin.setKindCode(kindCode);
		agentMin.setEmail(email);
		agentMin.setLocation(location);

	
		assertEquals(id, agentMin.getId());
		assertEquals(nombre+apellidos, agentMin.getName());
		assertEquals(kind, agentMin.getKind());
		assertEquals(kindCode, agentMin.getKindCode());
		assertEquals(email, agentMin.getEmail());
		assertEquals(location, agentMin.getLocation());
	}
	
	@Test
	public void checkTest(){
		assertTrue(Check.validateEmail("juan@gmail.com"));
		assertTrue(Check.validateEmail("juan@uniovi.es"));
		
		assertFalse(Check.validateEmail("usuario"));
		assertFalse(Check.validateEmail("usuario@gmail"));
		assertFalse(Check.validateEmail("usuario.com"));
	}

    /**
     * Test para email valido
     */
    @Test
    public void validEmailTest() {
        for (String temp : validEmails) {
            assertTrue(Check.validateEmail(temp));
        }
    }
 
    /**
     * Test para email no valido
     */
    @Test
    public void invalidEmailTest() {
        for (String temp : invalidEmails) {
          assertFalse(Check.validateEmail(temp));
        }
    }
    
}
