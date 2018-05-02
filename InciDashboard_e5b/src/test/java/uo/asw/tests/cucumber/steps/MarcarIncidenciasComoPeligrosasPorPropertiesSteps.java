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

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import uo.asw.InciDashboardE5bApplication;
import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Property;

@ContextConfiguration(classes=InciDashboardE5bApplication.class, loader=SpringApplicationContextLoader.class)
@SpringBootTest
public class MarcarIncidenciasComoPeligrosasPorPropertiesSteps {
	
	@Autowired
	private DBManagementFacade dbManagement;
	
	private List<Incidence> incidences = new LinkedList<>();
	
	// Escenario 2: Marcar incidencias con propiedades como peligrosas
	
	private String propertyName;
	private String propertyValue;

	@Given("^una lista de incidencias con propiedades:$")
	public void una_lista_de_incidencias_con_propiedades(List<IncidenceWith1Property> incidencesData) throws Throwable {
		for (IncidenceWith1Property incidenceData : incidencesData) {
			// Guardamos la propiedad en un set
			Set<Property> properties = new HashSet<>();
			properties.add(new Property(incidenceData.propertyName, incidenceData.propertyValue));

			// Creamos una incidencia con esa informacion y la guardamos en la lista
			incidences.add(new Incidence(properties, incidenceData.name, incidenceData.description));
		}
	}

	@Given("^la propiedad con nombre \"([^\"]*)\" cuyo valor debe ser mayor de (\\d+)$")
	public void la_propiedad_con_nombre_cuyo_valor_debe_ser_mayor_de(String propertyName, int propertyValue) throws Throwable {
	    this.propertyName = propertyName;
	    this.propertyValue = propertyValue + "";
	}

	@When("^el filtro se configura para marcar como peligrosas las incidencias cuya temperatura sea superior a (\\d+)$")
	public void el_filtro_se_configura_para_marcar_como_peligrosas_las_incidencias_cuya_temperatura_sea_superior_a(int arg1) throws Throwable {
		// Cargamos el filtro de la BD
		Filter filter = dbManagement.getFilter();
	    
		// Lo configuramos para marcar como peligrosas las incidencias que contengan la propiedad temperatura con valor mayor de 10
	    filter.setFilterResponse("markAsDangerous").
			setApplyOn("property").
			setFilterOperation("greater").
			setPropertyType("double").
			setPropertyName(propertyName).
	    		setPropertyValue(propertyValue);
	    
	    // Salvamos el filtro en BD
	    dbManagement.updateFilter(filter);
	}

	@When("^se aplica el filtro sobre la lista de incidencias con propiedades$")
	public void se_aplica_el_filtro_sobre_la_lista_de_incidencias_con_propiedades() throws Throwable {
		// Recuperamos el filtro de la BD
		Filter filter = dbManagement.getFilter();
		
		// Aplicamos el filtro a cada una de las incidencias
		for (Incidence incidence : incidences) {
			filter.applyFilter(incidence);
		}
	}

	@Then("^solamente la incidencia con nombre inc(\\d+) es marcada como peligrosa$")
	public void solamente_la_incidencia_con_nombre_inc_es_marcada_como_peligrosa(int arg1) throws Throwable {
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
	 * Clase auxiliar para recoger los datos de las incidencias con propiedades del feature
	 * @author carlos
	 */
	public static class IncidenceWith1Property {
		public String name;
		public String description;
		public String propertyName;
		public String propertyValue;
	}
	
}
