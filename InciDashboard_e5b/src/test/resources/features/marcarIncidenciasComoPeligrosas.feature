Feature: marcar incidencias como peligrosas
 
	  Scenario: las incidencias que contengan el tag fuego son marcadas como peligrosas
			    Given una lista de incidencias:
			      | name    | description 	| tag1	| tag2 	| 
			      | inc1    | descr1   	 	| fuego	| calor	|
			      | inc2    | descr2    		| frio	| agua	|
			      | inc3    | descr3    		| calor	| miedo	|
			    	And el tag "fuego"
			    		
			    When el filtro se configura para marcar como peligrosas las incidencias que contengan el tag
			    And se aplica el filtro sobre la lista de incidencias
			    
			    Then solamente la incidencia inc1 es marcada como peligrosa
	    
	  Scenario: las incidencias que tengan una propiedad temperatura con valor superior a 10 son marcadas como peligrosas
			    Given una lista de incidencias con propiedades:
			      | name    | description 	| propertyName	| propertyValue 	| 
			      | inc1    | descr1   	 	| temperatura	| 20				|
			      | inc2    | descr2    		| temperatura	| 5				|
			      | inc3    | descr3    		| aire			| 100			|
			    	And la propiedad con nombre "temperatura" cuyo valor debe ser mayor de 10
			    		
			    When el filtro se configura para marcar como peligrosas las incidencias cuya temperatura sea superior a 10
			    And se aplica el filtro sobre la lista de incidencias con propiedades
			    
			    Then solamente la incidencia con nombre inc1 es marcada como peligrosa