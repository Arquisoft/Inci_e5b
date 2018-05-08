Feature: agente con datos invalidos envia una incidencia y se reporta
 
	  Scenario: agente no existente en la aplicacion añade una incidencia básica y se reporta
			    Given el agente invalido con el login "AAAAAA" , el password "1234" y el kind "Person"
			    	And los siguientes datos de incidencia: name "Nombre" , description "Descripcion"
			    		
			    When el agente envía su incidencia
			    
			    Then la incidencia no se envia y se reporta