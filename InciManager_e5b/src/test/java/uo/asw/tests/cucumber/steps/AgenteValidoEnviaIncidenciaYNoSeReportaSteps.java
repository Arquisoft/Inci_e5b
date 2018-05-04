package uo.asw.tests.cucumber.steps;

import static org.junit.Assert.assertFalse;

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
public class AgenteValidoEnviaIncidenciaYNoSeReportaSteps {
	
	@Autowired
	private IncidenceService incidenceService;

	private Agent agent;
	private Incidence incidence;
	
	@Given("^el agente existente con el login \"([^\"]*)\" , el password \"([^\"]*)\" y el kind \"([^\"]*)\"$")
	public void el_agente_existente_con_el_login_el_password_y_el_kind(String login, String password, String kind) throws Throwable {
		agent = new Agent(login, password, kind);
	}

	@Given("^los siguientes datos basicos de la incidencia: name \"([^\"]*)\" , description \"([^\"]*)\"$")
	public void los_siguientes_datos_basicos_de_la_incidencia_name_description(String name, String description) throws Throwable {
		// Creamos una incidencia básica con los datos pasados y un identifier generado automaticamente
	    incidence = new Incidence(IdentifierGenerator.getIdentifier());
	    incidence.setName(name);
	    incidence.setDescription(description);
	}

	@When("^el agente envía la incidencia al sistema$")
	public void el_agente_envía_la_incidencia_al_sistema() throws Throwable {
		incidenceService.manageIncidence(agent.getIdentifier(), agent.getPassword(), agent.getKind(), incidence);
	}

	@Then("^la incidencia es enviada correctamente y no se reporta$")
	public void la_incidencia_es_enviada_correctamente_y_no_se_reporta() throws Throwable {
		assertIncidenceIsNotReported();
	}

	private void assertIncidenceIsNotReported() {
		String reportedIncidenceExcepectedMessage = 
				"[ERROR] Error sending incidence with identifier: " + incidence.getIdentifier();
		
		boolean incidenceReported = true;
		ReversedLinesFileReader file = null;
		
		try {
		    file = new ReversedLinesFileReader(new File("reports.log"));

		    // Sacamos la ultima linea de fichero y comprobamos si contiene el texto esperado
		    String lastLine = file.readLine();
		    
		    if(lastLine == null)
	    			incidenceReported = false;
		    else if(!lastLine.contains(reportedIncidenceExcepectedMessage))
		    		incidenceReported = false;
		    
		} catch(IOException e) { 
			e.printStackTrace();
		}finally {
			try {
				if(file != null)
					file.close();
			} catch (IOException e) { }
		}
		
		assertFalse(incidenceReported);
	}

}
