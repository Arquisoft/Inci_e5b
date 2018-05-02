package uo.asw.tests.cucumber.steps;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.InciDashboardE5bApplication;
import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;

@ContextConfiguration(classes=InciDashboardE5bApplication.class, loader=SpringApplicationContextLoader.class)
@SpringBootTest
public class MarcarIncidenciasComoPeligrosasSteps {
	
	@Autowired
	private DBManagementFacade dbManagement;
	
	private List<Incidence> incidences = new LinkedList<>();
	private String tag;
	
	@Given("^una lista de incidencias:$")
	public void una_lista_de_incidencias(List<IncidenceWith2TagsData> incidencesData) throws Throwable {
	    for (IncidenceWith2TagsData incidenceData : incidencesData) {
	    		// Guardamos los tags en un set
	    		Set<String> tags = new HashSet<>();
	    		tags.add(incidenceData.tag1);
	    		tags.add(incidenceData.tag2);
	    		
	    		// Creamos una incidencia con esa informacion y la guardamos en la lista
			incidences.add( new Incidence(incidenceData.name, incidenceData.description, tags) );
		}
	}

	@Given("^el tag \"([^\"]*)\"$")
	public void el_tag(String tag) throws Throwable {
	    this.tag = tag;
	}

	@When("^el filtro se configura para marcar como peligrosas las incidencias que contengan el tag$")
	public void el_filtro_se_configura_para_marcar_como_peligrosas_las_incidencias_que_contengan_el_tag() throws Throwable {
	    // Cargamos el filtro de la BD
		Filter filter = dbManagement.getFilter();
	    
		// Lo configuramos para marcar como peligrosas las incidencias que contenga el tag indicado
	    filter.setFilterResponse("markAsDangerous").
			setApplyOn("tag").
			setFilterOperation("contains").
			setTag(tag);
	    
	    // Salvamos el filtro en BD
	    dbManagement.updateFilter(filter);
	}
	
	@When("^se aplica el filtro sobre la lista de incidencias$")
	public void se_aplica_el_filtro_sobre_la_lista_de_incidencias() throws Throwable {
		// Recuperamos el filtro de la BD
		Filter filter = dbManagement.getFilter();
		
		// Aplicamos el filtro a cada una de las incidencias
		for (Incidence incidence : incidences) {
			filter.applyFilter(incidence);
		}
	}

	@Then("^solamente la incidencia inc(\\d+) es marcada como peligrosa$")
	public void solamente_la_incidencia_inc_es_marcada_como_peligrosa(int arg1) throws Throwable {
		Incidence inc1 = null;
		Incidence inc2 = null;
		Incidence inc3 = null;
		
		for (Incidence incidence : incidences) {
			if(incidence.getName().equals("inc1")) inc1 = incidence;
			if(incidence.getName().equals("inc2")) inc2 = incidence;
			if(incidence.getName().equals("inc3")) inc3 = incidence;
		}
		
		// Comprobamos que la unica incidencia marcada como peligrosa sea la que se llama inc1
		assertEquals(true, inc1.isDangerous());
		assertEquals(false, inc2.isDangerous());
		assertEquals(false, inc3.isDangerous());
	}
	
	/**
	 * Clase auxiliar para recoger los datos de las incidencias del feature
	 * @author carlos
	 *
	 */
	public static class IncidenceWith2TagsData {
	    public String name;
	    public String description;
	    public String tag1;
	    public String tag2;
	  }

}
