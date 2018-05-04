package uo.asw.tests.cucumber.steps;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.InciManagerE5bApplication;
import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciManager.IdentifierGenerator;
import uo.asw.inciManager.IncidenceService;

@ContextConfiguration(classes=InciManagerE5bApplication.class, loader=SpringApplicationContextLoader.class)
@SpringBootTest
public class AgenteInvalidoEnviaIncidenciaYSeReportaSteps {
	
	@Autowired
	private IncidenceService incidenceService;

	private Agent agent;
	private Incidence incidence;
	
	@Given("^el agente invalido con el login \"([^\"]*)\" , el password \"([^\"]*)\" y el kind \"([^\"]*)\"$")
	public void el_agente_invalido_con_el_login_el_password_y_el_kind(String login, String password, String kind) throws Throwable {
		agent = new Agent(login, password, kind);
	}

	@Given("^los siguientes datos de incidencia: name \"([^\"]*)\" , description \"([^\"]*)\"$")
	public void los_siguientes_datos_de_incidencia_name_description(String name, String description) throws Throwable {
		// Creamos una incidencia básica con los datos pasados y un identifier generado automaticamente
	    incidence = new Incidence(IdentifierGenerator.getIdentifier());
	    incidence.setName(name);
	    incidence.setDescription(description);
	}

	@When("^el agente envía su incidencia$")
	public void el_agente_envía_su_incidencia() throws Throwable {
		incidenceService.manageIncidence(agent.getIdentifier(), agent.getPassword(), agent.getKind(), incidence);
	}

	@Then("^la incidencia no se envia y se reporta$")
	public void la_incidencia_no_se_envia_y_se_reporta() throws Throwable {
	    assertIncidenceIsReported();
	}

	private void assertIncidenceIsReported() {
		String reportedIncidenceExcepectedMessage = 
				"[ERROR] Error sending incidence with identifier: " + incidence.getIdentifier();
		
		boolean incidenceReported = false;
		ReversedLinesFileReader file = null;
		
		try {
		    file = new ReversedLinesFileReader(new File("reports.log"));

		    // Sacamos la ultima linea de fichero y comprobamos si contiene el texto esperado
		    String lastLine = file.readLine();
		    if(lastLine.contains(reportedIncidenceExcepectedMessage))
		    		incidenceReported = true;
		    
		} catch(IOException e) { 
			e.printStackTrace();
		}finally {
			try {
				if(file != null)
					file.close();
			} catch (IOException e) { }
		}
		
		assertTrue(incidenceReported);
	}

}
