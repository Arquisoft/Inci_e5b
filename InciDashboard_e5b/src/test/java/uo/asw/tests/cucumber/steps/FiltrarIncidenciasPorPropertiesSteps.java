package uo.asw.tests.cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashSet;
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
public class FiltrarIncidenciasPorPropertiesSteps {
	
	@Autowired
	private DBManagementFacade dbManagement;
	
	private Incidence[] incidences = new Incidence[3];
	
	private String propertyName;
	private String propertyValue;

	@Given("^una lista de incidencias con properties:$")
	public void una_lista_de_incidencias_con_properties(List<IncidenceWith1Property> incidencesData) throws Throwable {
		int i = 0;
		for (IncidenceWith1Property incidenceData : incidencesData) {
			// Guardamos la propiedad en un set
			Set<Property> properties = new HashSet<>();
			properties.add(new Property(incidenceData.propertyName, incidenceData.propertyValue));

			// Creamos una incidencia con esa informacion y la guardamos en la lista
			incidences[i] = new Incidence(properties, incidenceData.name, incidenceData.description);
			i++;
		}
	}

	@Given("^la property con nombre \"([^\"]*)\" cuyo valor debe ser mayor de (\\d+)$")
	public void la_property_con_nombre_cuyo_valor_debe_ser_mayor_de(String propertyName, int propertyValue) throws Throwable {
	    this.propertyName = propertyName;
	    this.propertyValue = propertyValue + "";
	}

	@When("^el filtro se configura para solo dejar pasar las incidencias cuya temperatura sea superior a (\\d+)$")
	public void el_filtro_se_configura_para_solo_dejar_pasar_las_incidencias_cuya_temperatura_sea_superior_a(int arg1) throws Throwable {
		// Cargamos el filtro de la BD
		Filter filter = dbManagement.getFilter();
	    
		// Lo configuramos para marcar como peligrosas las incidencias que contengan la propiedad temperatura con valor mayor de 10
	    filter.setFilterResponse("accept").
			setApplyOn("property").
			setFilterOperation("greater").
			setPropertyType("double").
			setPropertyName(propertyName).
	    		setPropertyValue(propertyValue);
	    
	    // Salvamos el filtro en BD
	    dbManagement.updateFilter(filter);
	}

	@When("^se aplica el filtro sobre la lista de incidencias con properties$")
	public void se_aplica_el_filtro_sobre_la_lista_de_incidencias_con_properties() throws Throwable {
		// Recuperamos el filtro de la BD
		Filter filter = dbManagement.getFilter();
		
		// Aplicamos el filtro a cada una de las incidencias
		for (int i = 0; i < incidences.length; i++) {
			incidences[i] = filter.applyFilter(incidences[i]);
		}
	}

	@Then("^solamente la incidencia con nombre inc(\\d+) pasa el filtro$")
	public void solamente_la_incidencia_con_nombre_inc_pasa_el_filtro(int arg1) throws Throwable {		
		// Comprobamos que la unica incidencia que pasa el filtro es la que se llama inc1
		assertNotNull(incidences[0]); // inc1
		assertEquals("inc1", incidences[0].getName());
		assertNull(incidences[1]); // inc2
		assertNull(incidences[2]); // inc3
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
