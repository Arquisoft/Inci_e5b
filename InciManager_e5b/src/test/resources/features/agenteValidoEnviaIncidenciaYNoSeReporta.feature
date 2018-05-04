Feature: agente con datos validos envia una incidencia y no se reporta
 
	  Scenario: agente existente en la aplicacion añade una incidencia básica y no se reporta
			    Given el agente existente con el login "31668313G" , el password "1234" y el kind "Person"
			    	And los siguientes datos basicos de la incidencia: name "Nombre" , description "Descripcion"
			    		
			    When el agente envía la incidencia al sistema
			    
			    Then la incidencia es enviada correctamente y no se reporta