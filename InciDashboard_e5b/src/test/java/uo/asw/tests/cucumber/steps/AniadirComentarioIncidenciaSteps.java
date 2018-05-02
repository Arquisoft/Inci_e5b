package uo.asw.tests.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.InciDashboardE5bApplication;
import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Incidence;

@ContextConfiguration(classes=InciDashboardE5bApplication.class, loader=SpringApplicationContextLoader.class)
@SpringBootTest
public class AniadirComentarioIncidenciaSteps {
	
	@Autowired
	private DBManagementFacade dbManagement;
	
	private String operatorIdentifier;
	private Incidence incidence;
	

	@Given("^el operario con el identificador \"([^\"]*)\"$")
	public void el_operario_con_el_identificador(String operatorIdentifier) throws Throwable {
		this.operatorIdentifier = operatorIdentifier;
	}

	@Given("^la primera de sus incidencias asignadas$")
	public void la_primera_de_sus_incidencias_asignadas() throws Throwable {
		incidence = dbManagement.getOperatorIncidences(operatorIdentifier).get(0);
	}

	@When("^el operario añade a la incidencia el comentario \"([^\"]*)\"$")
	public void el_operario_añade_a_la_incidencia_el_comentario(String comentario) throws Throwable {
		incidence.setOperatorComments(comentario);
	}

	@When("^la incidencia se actualiza en base de datos$")
	public void la_incidencia_se_actualiza_en_base_de_datos() throws Throwable {
		dbManagement.updateIncidence(incidence);
	}

	@When("^la incidencia se recupera de la base de datos$")
	public void la_incidencia_se_recupera_de_la_base_de_datos() throws Throwable {
		incidence = dbManagement.getOperatorIncidences(operatorIdentifier).get(0);
	}

	@Then("^la incidencia tiene el comentario \"([^\"]*)\"$")
	public void la_incidencia_tiene_el_comentario(String comentario) throws Throwable {
		assertEquals(comentario, incidence.getOperatorComments());
	}

}
