package uo.asw.inciDashboard.currentIncidences;

import java.util.ArrayList;
import java.util.List;
//
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;

@Service
public class ReceiveFilteredIncidenceImpl implements ReceiveFilteredIncidence {
	
	List<Incidence> listaincidencias = new ArrayList<Incidence>();
	
	/* TODO
	 * El método ReceiveFilteredIncidence se encarga de recibir un objeto Incidence del componente Filter.
	 * La información de dicho objeto es la que será mostrada por la interfaz web de forma dinámica. 
	 * Si la incidencia está marcada como peligrosa, se deberá dar un aviso por la interfaz para que los operarios se percaten de ello.
	 *  
	 */
	@Override
	public void receiveFilteredIncidence(Incidence incidence) {
		listaincidencias.add(incidence);
	}
	
	
	public List<Incidence> getListaincidencias() {
		// para añadir incidencias
		añadirIncidenciasLista();
		
		return listaincidencias;
	}
	
	/** Metodo creado para añadir incidencias a la lista con el fin de hacer pruebas
	 * 
	 * @param incidence
	 */
	public void añadirIncidenciasLista() {
		Agent agent1 = new Agent("316683136", "1234", "Person");
		agent1.setName("Juan");
		agent1.setEmail("email@email.com");
		Agent agent2 = new Agent("1245478", "1234", "Person");
		agent1.setName("Pepe");
		agent1.setEmail("email@email.com");
		Agent agent3 = new Agent("71452145", "1234", "Person");
		agent1.setName("Pedro");
		agent1.setEmail("email@email.com");
		Agent agent4 = new Agent("7452145241", "1234", "Person");
		agent1.setName("Jaime");
		agent1.setEmail("email@email.com");
		Operator opreator2 = new Operator("AAAAAAA2", "Juan");
		opreator2.setPassword("123456");
		Operator opreator3 = new Operator("AAAAAAA3", "Francisco");
		opreator3.setPassword("123456");
		Operator opreator4 = new Operator("AAAAAAA4", "Rodrigo");
		opreator4.setPassword("123456");
		Operator opreator5 = new Operator("AAAAAAA5", "Pepe");
		opreator5.setPassword("123456");
		
		Incidence i = new Incidence(1, "XXX", agent1, opreator5, "Fuego", "Coche ardiendo", "43.35,-5.85", null, null, "Abierta", "Mucho humo", "12/02/2018", true);
		Incidence i2 = new Incidence(2, "ZZZ", agent2, opreator2, "Inundacion", "Calle inundada lluvia", "43.56,-5.90", null, null, "Abierta", "Mucha agua", "12/02/2018", true);
		Incidence i3 = new Incidence(3, "YYY", agent3, opreator3, "Accidente", "Colision entre dos coches", "43.29,-5.69", null, null, "Abierta", "Ya esta solucionado", "12/02/2018", true);
		Incidence i4 = new Incidence(4, "WWW", agent4, opreator4, "Accidente", "Colision entre coche y camión", "43.10,-5.69", null, null, "Abierta", "Ya esta solucionado", "12/02/2018", false);
		
		
		listaincidencias.add(i);
		listaincidencias.add(i2);
		listaincidencias.add(i3);
		listaincidencias.add(i4);
	}
	

}
